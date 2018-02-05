package org.openpaas.egovframework.comcomponent.dorojuso.service;

import java.util.ArrayList;
import java.util.List;

import org.openpaas.egovframework.comcomponent.dorojuso.common.StringUtils;
import org.openpaas.egovframework.comcomponent.dorojuso.dao.DoroJusoDAO;
import org.openpaas.egovframework.comcomponent.dorojuso.dao.impl.DoroJusoDAOImpl;
import org.openpaas.egovframework.comcomponent.dorojuso.exception.DoroJusoException;
import org.openpaas.egovframework.comcomponent.dorojuso.model.Common;
import org.openpaas.egovframework.comcomponent.dorojuso.model.DoroJuso;
import org.openpaas.egovframework.comcomponent.dorojuso.model.DoroJusoInfo;
import org.openpaas.egovframework.comcomponent.dorojuso.model.Juso;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 도로주소 조회 Service
 * 
 * @author 안찬영
 *
 * History
 * 2015.7.14 도로명주소 검색 개발
 */
@Service
public class DoroJusoService  {

	private static final Logger logger = LoggerFactory.getLogger(DoroJusoService.class);

	@Autowired
	DoroJusoDAO doroJusoDao;
	
	private static int MAX_ADDRESS = 1000;
	
	/**
	 * 도로 주소 정보 조회
	 * 
	 * @param currentPage	현재 페이지
	 * @param countPerPage	페이지당 정보 갯수
	 * @param keyword	검색 키워드
	 * @return	도로주소 정보
	 * @throws DoroJusoException	서버 내부 오류
	 */
	public DoroJuso getDoroJuso(int currentPage, int countPerPage, String keyword) throws DoroJusoException {
		
		logger.debug("getDoroJuso service");
		
		// 건수, 메세지 등 정보
		DoroJuso doroJuso = new DoroJuso();
		List<Juso> jusos = new ArrayList<Juso>();
		
		Common common = new Common();		
		common.setCurrentPage(currentPage);
		common.setCountPerPage(countPerPage);
		

		if (countPerPage > MAX_ADDRESS) {
			
			common.setErrorCode(Common.ERR_MAX);
			common.setErrorMessage(Common.MSG_ERR_MAX);
			
		} else {
			try {

				List<DoroJusoInfo> doroJusos = doroJusoDao.getDoroJuso(currentPage, countPerPage, keyword);
				
				
				if (doroJusos.size() > MAX_ADDRESS) {
					
					common.setErrorCode(Common.ERR_TOOMANY);
					common.setErrorMessage(Common.MSG_ERR_TOOMANY);
					
				} else {
					
					for (DoroJusoInfo item: doroJusos) {
						
						Juso juso = new Juso();
						
						// 전체 도로명 주소
						juso.setRoadAddr(item.getSido() + " " + item.getSigungu() + " " + item.getDoro() + " " + item.getBon() + " (" + item.getEupmyundong() + ")");
						// 도로명 주소
						juso.setRoadAddrPart1(item.getSido() + " " + item.getSigungu() + " " + item.getDoro() + " " + item.getBon());
						// 도로명주소 상세
						juso.setRoadAddrPart2("(" + item.getEupmyundong() + ")");
						// 지번
						juso.setJibunAddr(item.getSido() + " " + item.getSigungu() + " " + item.getEupmyundong() + " " + item.getBunji() + "-" + item.getHo());
						// 도로명주소 (영문)
						juso.setEngAddr("");
						// 우편번호
						juso.setZipNo(StringUtils.changeZipCode(item.getZipcode()));
						// 행정구역코드
						juso.setAdmCd(item.getCode());
						// 도로명코드
						juso.setRnMgtSn(item.getDoro_code());
						// 건물관리번호
						juso.setBdMgtSn(item.getGunmul_no());
						
						jusos.add(juso);
						
					}

					common.setTotalCount(doroJusos.size());
					
					common.setErrorCode(Common.OK);
					common.setErrorMessage(Common.MSG_OK);
				}
				

			} catch (Exception ex) {
				
				common.setErrorCode(Common.ERR_SYSTEM);
				common.setErrorMessage(Common.MSG_ERR_SYSTEM);
		
			}		
		
		}
		
		// Return 객체 만들기
		// 공토 부분
		doroJuso.setCommon(common);
		// 주소 정보
		doroJuso.setJuso(jusos);
		
		return doroJuso;
	}

	
}
