package org.openpaas.egovframework.comcomponent.dorojuso.controller;

import org.openpaas.egovframework.comcomponent.dorojuso.exception.DoroJusoException;
import org.openpaas.egovframework.comcomponent.dorojuso.model.DoroJuso;
import org.openpaas.egovframework.comcomponent.dorojuso.service.DoroJusoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 도로주소 검색 Contoller 
 * 일반 사용자가 도로주소를 검색할때 사용하는 부분임
 * 
 * @author 안찬영
 *
 * History
 * 2015.7.14 도로명주소 검색 개발
 */
@Controller
public class DoroJusoController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(DoroJusoController.class);

	@Autowired 
	private DoroJusoService service;
	
	@Autowired 
	public DoroJusoController(DoroJusoService service) {
		this.service = service;
	}

	/**
	 * OPTION으로 요청이 왔을 때의 처리
	 * API 플랫폼에서 해당 API의 유효성검증등으로 사용하고 있음
	 * 
	 * @param currentPage
	 * @param countPerPage
	 * @param keyword
	 * @return
	 * @throws DoroJusoException
	 */
	@RequestMapping(value = "/addrlink/addrLinkApi.do", method = RequestMethod.OPTIONS)
	public @ResponseBody DoroJuso getDoroJusoOption(@RequestParam(value="currentPage") int currentPage, 
												@RequestParam(value="countPerPage") int countPerPage, 
												@RequestParam(value="keyword") String keyword) throws DoroJusoException {
		
		logger.debug("OPTION: /addrlink/addrLinkApi.do ");
		logger.debug("currentPage:" + currentPage);
		logger.debug("countPerPage:" + countPerPage);
		logger.debug("keyword:" + keyword);
		
		return new DoroJuso();
	}
	
	/**
	 * 도로명 주소를 호출하는 API (json과 xml을 지원함)
	 * 
	 * @param currentPage 현재 패이지
	 * @param countPerPage	패이지내 갯수
	 * @param keyword	검색어
	 * @return	도로명 주소 리스트
	 * @throws DoroJusoException
	 */
	@RequestMapping(value = "/addrlink/addrLinkApi.do", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
	public @ResponseBody DoroJuso getDoroJuso(@RequestParam(value="currentPage") int currentPage, 
												@RequestParam(value="countPerPage") int countPerPage, 
												@RequestParam(value="keyword") String keyword) throws DoroJusoException {
		
		logger.debug("GET: /addrlink/addrLinkApi.do ");
		logger.debug("currentPage:" + currentPage);
		logger.debug("countPerPage:" + countPerPage);
		logger.debug("keyword:" + keyword);
		
		return service.getDoroJuso(currentPage, countPerPage, keyword);
	}
	
}
