package org.openpaas.egovframework.comcomponent.dorojuso.service;

import java.util.List;

import org.openpaas.egovframework.comcomponent.dorojuso.dao.DoroJusoDAO;
import org.openpaas.egovframework.comcomponent.dorojuso.exception.DoroJusoBadRequestException;
import org.openpaas.egovframework.comcomponent.dorojuso.exception.DoroJusoDoesNotExistException;
import org.openpaas.egovframework.comcomponent.dorojuso.exception.DoroJusoException;
import org.openpaas.egovframework.comcomponent.dorojuso.exception.DoroJusoExistsException;
import org.openpaas.egovframework.comcomponent.dorojuso.model.DoroJusoInfo;
import org.openpaas.egovframework.comcomponent.dorojuso.model.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 도로주소 관리 Service
 * 
 * @author 안찬영
 *
 * History
 * 2015.7.14 도로명주소 검색 개발
 */
@Service
public class DoroJusoManagerService {

	@Autowired
	DoroJusoDAO dao;
	
	/**
	 * 도로주소 정보 가져오기
	 * 
	 * @param currentPage	현재 페이지
	 * @param countPerPage	페이지당 갯수
	 * @param keyword	검색 키워드
	 * @return	도로주소 리스트
	 * @throws DoroJusoException	내부 서버 오류
	 */
	public List<DoroJusoInfo> getDoroJusoInfo(int currentPage, int countPerPage, String keyword) throws DoroJusoException {
		
		return dao.getDoroJuso(currentPage, countPerPage, keyword);
	}

	/**
	 * 한개의 도로주소 조회
	 * 
	 * @param gunmul_no	건물관리 번호
	 * @return	도로주소 정보
	 * @throws DoroJusoException	내부 서버 오류
	 */
	public DoroJusoInfo getDoroJusoInfoOne(String gunmul_no) throws DoroJusoException {
		
		return dao.getDoroJusoOne(gunmul_no);
	}

	/**
	 * 도로주소 등록
	 * 
	 * @param doroJusoInfo	도로주소 정보
	 * @return	결과 메세지
	 * @throws DoroJusoException	서버 내부 오류
	 * @throws DoroJusoExistsException	도로주소가 존재할 경우 오류
	 */
	public ResultMessage insertDoroJusoInfo(DoroJusoInfo doroJusoInfo) throws DoroJusoException, DoroJusoExistsException {
		
		int count = dao.insertDoroJuso(doroJusoInfo);
		
		if (count < 1) {
			throw new DoroJusoException("Failed to insert a DoroJusoInfo. gumnul_no:"+ doroJusoInfo.getGunmul_no());
		}
		
		return new ResultMessage("DoroJuso saved. gumnul_no:"+ doroJusoInfo.getGunmul_no());
	}

	/**
	 * 도로주소 정보 수정
	 * 
	 * @param building_code	건물관리 번호
	 * @param doroJusoInfo	도록 주소 정보
	 * @return	결과 메세지
	 * @throws DoroJusoBadRequestException	정보가 부정확할 경우 오류
	 * @throws DoroJusoException	서버 내부 오류
	 * @throws DoroJusoDoesNotExistException	존재하지 않는 도로정보일 경우 오류
	 */
	public ResultMessage updateDoroJusoInfo(String building_code, DoroJusoInfo doroJusoInfo) throws DoroJusoBadRequestException, DoroJusoException, DoroJusoDoesNotExistException {
		
		if (!building_code.equals(doroJusoInfo.getGunmul_no())) {
			throw new DoroJusoBadRequestException("Gunmul_no is not same PATH value. (PATH value:" + building_code + ", gumnul_no:" + doroJusoInfo.getGunmul_no() + ")");
		}
		
		int count = dao.updateDoroJuso(doroJusoInfo);
		
		if (count < 1) {
			throw new DoroJusoException("Failed to update a DoroJusoInfo. gumnul_no:"+ doroJusoInfo.getGunmul_no());
		}
		
		return new ResultMessage("DoroJuso updated. gunmul_no:" + doroJusoInfo.getGunmul_no());
	}

	/**
	 * 도로주소 삭제
	 * 
	 * @param building_code	건물관리 번호
	 * @return	결과 메세지
	 * @throws DoroJusoException	서버 내부 오류
	 * @throws DoroJusoDoesNotExistException	도로주소가 존재하지 않을 경우 오류
	 */
	public ResultMessage deleteDoroJusoInfo(String building_code)  throws DoroJusoException, DoroJusoDoesNotExistException{
		
		int count = dao.deleteDoroJuso(building_code);
		
		if (count < 1) {
			throw new DoroJusoException("Failed to delete a DoroJusoInfo. gumnul_no:"+ building_code);
		}
		
		return new ResultMessage("DoroJuso deleted. gunmul_no:" + building_code);
	}

}
