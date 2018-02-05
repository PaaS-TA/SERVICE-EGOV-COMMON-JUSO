package org.openpaas.egovframework.comcomponent.dorojuso.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.openpaas.egovframework.comcomponent.dorojuso.dao.DoroJusoDAO;
import org.openpaas.egovframework.comcomponent.dorojuso.exception.DoroJusoException;
import org.openpaas.egovframework.comcomponent.dorojuso.exception.DoroJusoExistsException;
import org.openpaas.egovframework.comcomponent.dorojuso.model.DoroJusoInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * 도로정보 DAO 구현
 * 
 * @author 안찬영
 *
 * History
 * 2015.7.14 도로명주소 검색 개발
 */
@Repository
public class DoroJusoDAOImpl extends JdbcDaoSupport implements DoroJusoDAO {

	private static final Logger logger = LoggerFactory.getLogger(DoroJusoDAOImpl.class);

	@Autowired
	private DataSource dataSource;
 
	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	/** 
	 * 도로주소 리스트 조회
	 */
	public List<DoroJusoInfo> getDoroJuso(int currentPage, int pageTotal, String keyword) {
		
		logger.info("getDoroJuso");
		
		String sql = "SELECT * FROM doro_juso " +
				"WHERE sigungu LIKE '" + keyword + "%' OR eupmyundong LIKE '" + keyword + "%' OR doro LIKE '" + keyword + "%' " + 
				"ORDER BY sido, sigungu, eupmyundong, doro ASC " +				
				"LIMIT " + pageTotal + " offset " + currentPage;
		
		List<DoroJusoInfo> doroJusos = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(DoroJusoInfo.class));
		
		logger.info("getDoroJuso finished : " + doroJusos.size());
		
		return doroJusos;
	}

	/**
	 * 도로 정보 하나 조회
	 */
	public DoroJusoInfo getDoroJusoOne(String gunmul_no) {
		
		logger.info("getDoroJusoOne");
		
		String sql = "SELECT * FROM doro_juso " +
				"WHERE gunmul_no = '" + gunmul_no + "'";
		
		List<DoroJusoInfo> doroJusos = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(DoroJusoInfo.class));
		
		logger.info("getDoroJuso finished : " + doroJusos.size());
		
		
		DoroJusoInfo doroInfo = new DoroJusoInfo();
		
		if (doroJusos.size() > 0) {
			doroInfo = doroJusos.get(0);
		}
		
		return doroInfo;
	}

	/**
	 * 도로 정보 등록
	 */
	public int insertDoroJuso(DoroJusoInfo doroJusoInfo) throws DoroJusoException, DoroJusoExistsException {

		logger.info("insertDoroJuso");
		
		String sql = "INSERT INTO doro_juso (code, sido, sigungu, eupmyundong, ri, " 
					+ "san, bunji, ho, doro_code, doro, "
					+ "jiha, bon, bu, gunmul, gunmul_sangse, "
					+ "gunmul_no, eupmyundong_no, hang_code, hang, zipcode, "
					+ "zipno, dayaeng, idong, update_date, defore_doro, "
					+ "sigungu_gunmul, gongdong, gicho_no, juso_sang, bigo1, "
					+ "bigo2) "
					+ "VALUES "
					+ "(?,?,?,?,?,"
					+ "?,?,?,?,?,"
					+ "?,?,?,?,?,"
					+ "?,?,?,?,?,"
					+ "?,?,?,?,?,"
					+ "?,?,?,?,?,"
					+ "?)";
		
		int result = 0;
		
		try {
			result = getJdbcTemplate().update(sql, doroJusoInfo.getCode(), doroJusoInfo.getSido(), doroJusoInfo.getSigungu(), doroJusoInfo.getEupmyundong(), doroJusoInfo.getRi(),
					doroJusoInfo.getSan(), doroJusoInfo.getBunji(), doroJusoInfo.getHo(), doroJusoInfo.getDoro(), doroJusoInfo.getDoro(),
					doroJusoInfo.getJiha(), doroJusoInfo.getBon(), doroJusoInfo.getBu(), doroJusoInfo.getGunmul(), doroJusoInfo.getGunmul_sangse(),
					doroJusoInfo.getGunmul_no(), doroJusoInfo.getEupmyundong_no(), doroJusoInfo.getHang_code(), doroJusoInfo.getHang(), doroJusoInfo.getZipcode(),
					doroJusoInfo.getZipno(), doroJusoInfo.getDayaeng(), doroJusoInfo.getIdong(), doroJusoInfo.getUpdate_date(), doroJusoInfo.getDefore_doro(),
					doroJusoInfo.getSigungu_gunmul(), doroJusoInfo.getGongdong(), doroJusoInfo.getGicho_no(), doroJusoInfo.getJuso_sang(), doroJusoInfo.getBigo1(),
					doroJusoInfo.getBigo2()	);	
		} catch (DuplicateKeyException ex) {
			throw new DoroJusoExistsException(ex.getMessage());
		}
		
		return result;
	}

	/**
	 * 도로 정보 수정
	 */
	public int updateDoroJuso(DoroJusoInfo doroJusoInfo) throws DoroJusoException {
		
		String sql = "UPDATE doro_juso "
				+ "SET "
				+ "code = ?, sido = ?, sigungu = ?, eupmyundong = ?, ri = ?, " 
				+ "san = ?, bunji = ?, ho = ?, doro_code = ?, doro = ?, "
				+ "jiha = ?, bon = ?, bu = ?, gunmul = ?, gunmul_sangse = ?, "
//				+ "gunmul_no, "
				+ "eupmyundong_no = ?, hang_code = ?, hang = ?, zipcode = ?, "
				+ "zipno = ?, dayaeng = ?, idong = ?, update_date = ?, defore_doro = ?, "
				+ "sigungu_gunmul = ?, gongdong = ?, gicho_no = ?, juso_sang = ?, bigo1 = ?, "
				+ "bigo2 = ? "
				+ "WHERE gunmul_no = ?";
	
		int result = 0;
		
		try {
			result = getJdbcTemplate().update(sql, doroJusoInfo.getCode(), doroJusoInfo.getSido(), doroJusoInfo.getSigungu(), doroJusoInfo.getEupmyundong(), doroJusoInfo.getRi(),
					doroJusoInfo.getSan(), doroJusoInfo.getBunji(), doroJusoInfo.getHo(), doroJusoInfo.getDoro(), doroJusoInfo.getDoro(),
					doroJusoInfo.getJiha(), doroJusoInfo.getBon(), doroJusoInfo.getBu(), doroJusoInfo.getGunmul(), doroJusoInfo.getGunmul_sangse(),
//					doroJusoInfo.getGunmul_no(), 
					doroJusoInfo.getEupmyundong_no(), doroJusoInfo.getHang_code(), doroJusoInfo.getHang(), doroJusoInfo.getZipcode(),
					doroJusoInfo.getZipno(), doroJusoInfo.getDayaeng(), doroJusoInfo.getIdong(), doroJusoInfo.getUpdate_date(), doroJusoInfo.getDefore_doro(),
					doroJusoInfo.getSigungu_gunmul(), doroJusoInfo.getGongdong(), doroJusoInfo.getGicho_no(), doroJusoInfo.getJuso_sang(), doroJusoInfo.getBigo1(),
					doroJusoInfo.getBigo2(),
					doroJusoInfo.getGunmul_no());	
		} catch (Exception ex) {
			throw new DoroJusoException(ex.getMessage());
		}
		
		return result;
	}

	/**
	 * 도로정보 삭제
	 */
	public int deleteDoroJuso(String building_code) throws DoroJusoException{

		String sql = "DELETE FROM doro_juso "
				+ "WHERE gunmul_no = ?";
	
		int result = 0;
		
		try {
			result = getJdbcTemplate().update(sql, building_code);	
		} catch (Exception ex) {
			throw new DoroJusoException(ex.getMessage());
		}
		
		return result;
	}

}
