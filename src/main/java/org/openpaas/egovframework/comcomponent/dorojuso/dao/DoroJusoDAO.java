package org.openpaas.egovframework.comcomponent.dorojuso.dao;

import java.util.List;

import org.openpaas.egovframework.comcomponent.dorojuso.exception.DoroJusoException;
import org.openpaas.egovframework.comcomponent.dorojuso.exception.DoroJusoExistsException;
import org.openpaas.egovframework.comcomponent.dorojuso.model.DoroJusoInfo;

/**
 * 도로명 주소 DAO Interface
 * 
 * @author 안찬영
 *
 * History
 * 2015.7.14 도로명주소 검색 개발
 */
public interface DoroJusoDAO {

	/**
	 * 도로 정보 리스트 가져오기
	 * 
	 * @param currentPage	현재 페이지
	 * @param pageTotal	페이지내 정보 갯수
	 * @param keyword	검색 키워드
	 * @return	도로정보 리스트
	 */
	abstract public List<DoroJusoInfo> getDoroJuso(int currentPage, int pageTotal, String keyword);
	
	/**
	 * 도로주소 하나 가져오기
	 * 
	 * @param gunmul_no	건물번호
	 * @return
	 */
	abstract public DoroJusoInfo getDoroJusoOne(String gunmul_no);

	/**
	 * 도로주소 등록
	 * 
	 * @param doroJusoInfo	도로주소 정보
	 * @return	등록된 갯수
	 * @throws DoroJusoException	서버 내부 오류
	 * @throws DoroJusoExistsException	존재하는 도로주소정보일 경우 오류
	 */
	abstract public int insertDoroJuso(DoroJusoInfo doroJusoInfo) throws DoroJusoException, DoroJusoExistsException;

	/**
	 * 도로주소 수정
	 * 
	 * @param doroJusoInfo	도로주소 정보 
	 * @return	수정된 갯수
	 * @throws DoroJusoException	서버 내부 오류
	 */
	abstract public int updateDoroJuso(DoroJusoInfo doroJusoInfo) throws DoroJusoException;

	/**
	 * 도로주소 삭제
	 * 
	 * @param building_code	건물관리 번호
	 * @return	삭제된 갯수
	 * @throws DoroJusoException	서버 내부 오류
	 */
	abstract public int deleteDoroJuso(String building_code) throws DoroJusoException;
	
}
