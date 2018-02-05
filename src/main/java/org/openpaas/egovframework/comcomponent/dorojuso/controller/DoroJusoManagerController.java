package org.openpaas.egovframework.comcomponent.dorojuso.controller;


import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.openpaas.egovframework.comcomponent.dorojuso.exception.DoroJusoBadRequestException;
import org.openpaas.egovframework.comcomponent.dorojuso.exception.DoroJusoDoesNotExistException;
import org.openpaas.egovframework.comcomponent.dorojuso.exception.DoroJusoException;
import org.openpaas.egovframework.comcomponent.dorojuso.exception.DoroJusoExistsException;
import org.openpaas.egovframework.comcomponent.dorojuso.model.DoroJuso;
import org.openpaas.egovframework.comcomponent.dorojuso.model.DoroJusoInfo;
import org.openpaas.egovframework.comcomponent.dorojuso.model.DoroJusoInfoResult;
import org.openpaas.egovframework.comcomponent.dorojuso.model.ResultMessage;
import org.openpaas.egovframework.comcomponent.dorojuso.service.DoroJusoManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 도로명 주소 관리를 위한 Controller
 * 
 * @author 안찬영
 *
 * History
 * 2015.7.14 도로명주소 검색 개발
 */
@Controller
public class DoroJusoManagerController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(DoroJusoManagerController.class);
	
	@Autowired
	private DoroJusoManagerService manager;
	
	/**
	 * OPTIONS 요청에 대한 처리
	 * 
	 */
	@RequestMapping(value = "/dorojuso/manager/**", method = RequestMethod.OPTIONS)
	@ResponseBody 
	public String getOptions() {
		logger.debug("Mnager OPTIONS : ");
		return "";
	}	

	/** 
	 * 도로명 주소 검색 (Path 방식)
	 * 
	 *  @param currentPage	현재 페이지
	 *  @param countPerPage 페이지내 주소 갯수
	 *  @param keyword	검색 키워드
	 */
	@RequestMapping(value = "/dorojuso/manager/{currentPage}/{countPerPage}/{keyword}", method = RequestMethod.GET)
	@ResponseBody 
	public DoroJusoInfoResult getDoroJusoInfo( @PathVariable("currentPage") int currentPage, 
										@PathVariable("countPerPage") int countPerPage,
										@PathVariable("keyword") String keyword ) throws DoroJusoException {
		
		logger.debug( "Manager GET: getDoroJuso");
		
		List<DoroJusoInfo> doros = manager.getDoroJusoInfo(currentPage, countPerPage, keyword);
		
		DoroJusoInfoResult jusos = new DoroJusoInfoResult();
		jusos.setJuso(doros);
		
		return jusos;
	}
	
	/** 
	 * 도로명 주소 검색 (Parameter 방식)
	 * 
	 *  @param currentPage	현재 페이지
	 *  @param countPerPage 페이지내 주소 갯수
	 *  @param keyword	검색 키워드
	 */
	@RequestMapping(value = "/dorojuso/manager", method = RequestMethod.GET)
	@ResponseBody 
	public DoroJusoInfoResult getDoroJusoInfoParam( @RequestParam(value="currentPage", required=false, defaultValue="10") int currentPage, 
											@RequestParam(value="countPerPage", required=false, defaultValue="10") int countPerPage, 
											@RequestParam(value="keyword", required=false) String keyword) throws DoroJusoException {
		
		logger.debug( "Manager GET: getDoroJuso");
		
		List<DoroJusoInfo> doros = manager.getDoroJusoInfo(currentPage, countPerPage, keyword);
		
		DoroJusoInfoResult jusos = new DoroJusoInfoResult();
		jusos.setJuso(doros);
		
		return jusos;
	}

	/**
	 * 한개의 도로명 주소 검색
	 * 
	 * @param gunmul_no 건물관리번호(Key)
	 * @return 도로주소 정보
	 * @throws DoroJusoException
	 */
	@RequestMapping(value = "/dorojuso/manager/{gunmul_no}", method = RequestMethod.GET)
	@ResponseBody 
	public DoroJusoInfo getDoroJusoInfoOne( @PathVariable("gunmul_no") String gunmul_no) throws DoroJusoException {
		
		logger.debug( "Manager GET: getDoroJuso");
		
		DoroJusoInfo doro = manager.getDoroJusoInfoOne(gunmul_no);
		
		return doro;
	}

	/**
	 * 도로주소 등록
	 * 
	 * @param doroJusoInfo	도로주소 정보
	 * @return
	 * @throws DoroJusoException	서버문제 발생시 오류(DB연결등)
	 * @throws DoroJusoExistsException	존재하는 주소일 경우 오류
	 */
	@RequestMapping(value = "/dorojuso/manager", method = RequestMethod.POST)
	@ResponseBody 
	public ResultMessage insertDoroJusoInfo(@RequestBody DoroJusoInfo doroJusoInfo) throws DoroJusoException, DoroJusoExistsException {
		
		logger.debug( "Manager POST: insertDoroJusoInfo");
		
		logger.info(doroJusoInfo.toString());
		
		return manager.insertDoroJusoInfo(doroJusoInfo);
	}

	/**
	 * 도로주소 정보 수정
	 * 
	 * @param building_code 건물관리 번호
	 * @param doroJusoInfo	도로 주소 정보
	 * @return
	 * @throws DoroJusoException	서버 내부 오류
	 * @throws DoroJusoDoesNotExistException	존재하지 않는 도로주소일 경우 오류
	 * @throws DoroJusoBadRequestException	요청한 정보의 속성에 문제가 있을 경우
	 */
	@RequestMapping(value = "/dorojuso/manager/{building_code}", method = RequestMethod.PUT)
	@ResponseBody 
	public ResultMessage updateDoroJusoInfo( @PathVariable("building_code") String building_code, @RequestBody DoroJusoInfo doroJusoInfo ) throws DoroJusoException, DoroJusoDoesNotExistException, DoroJusoBadRequestException {
		
		logger.debug( "Manager PUT: updateDoroJusoInfo");
		
		return manager.updateDoroJusoInfo(building_code, doroJusoInfo);
	}

	/**
	 * 도로 주소 삭제
	 * 
	 * @param building_code	건물관리 번호
	 * @return
	 * @throws DoroJusoException	서버 내부 오류
	 * @throws DoroJusoDoesNotExistException	존재하지 않는 도로정보일 경우 오류
	 */
	@RequestMapping(value = "/dorojuso/manager/{building_code}", method = RequestMethod.DELETE)
	@ResponseBody 
	public ResultMessage deleteDoroJusoInfo( @PathVariable("building_code") String building_code) throws DoroJusoException, DoroJusoDoesNotExistException {
		
		logger.debug( "Manager DELETE: deleteDoroJusoInfo");
		
		return manager.deleteDoroJusoInfo(building_code);
	}

}
