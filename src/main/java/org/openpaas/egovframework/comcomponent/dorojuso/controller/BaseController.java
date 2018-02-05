package org.openpaas.egovframework.comcomponent.dorojuso.controller;

import javax.servlet.http.HttpServletResponse;

import org.openpaas.egovframework.comcomponent.dorojuso.exception.DoroJusoDoesNotExistException;
import org.openpaas.egovframework.comcomponent.dorojuso.exception.DoroJusoExistsException;
import org.openpaas.egovframework.comcomponent.dorojuso.exception.DoroJusoBadRequestException;
import org.openpaas.egovframework.comcomponent.dorojuso.model.ResultMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller 들의 상위 Controller로 공통으로 사용하는 Exception, error 핸들링을 정의함. 
 * 
 * History
 * 2015.7.1 최초 Framework 구성시 작업
 */
public class BaseController {

	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

	/**
	 * Insert시 도로 주소가 존재할때의 Exception
	 * 
	 * @param ex 
	 * @param response
	 * @return
	 */
	@ExceptionHandler(DoroJusoExistsException.class)
	@ResponseBody
	public ResponseEntity<ResultMessage> handleException(
			DoroJusoExistsException ex, 
			HttpServletResponse response) {
	    return getErrorResponse(ex.getMessage(), HttpStatus.CONFLICT);
	}
	
	/**
	 * Delete, Update시 도로주소가 존재하지 않을 경우의 Exception
	 * 
	 * @param ex
	 * @param response
	 * @return
	 */
	@ExceptionHandler(DoroJusoDoesNotExistException.class)
	@ResponseBody
	public ResponseEntity<ResultMessage> handleException(
			DoroJusoDoesNotExistException ex, 
			HttpServletResponse response) {
	    return getErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	/**
	 * 도로주소 Insert, Update시 정보가 잘못 요청되면 발생하는 Exception
	 * 
	 * @param ex
	 * @param response
	 * @return
	 */
	@ExceptionHandler(DoroJusoBadRequestException.class)
	@ResponseBody
	public ResponseEntity<ResultMessage> handleException(
			DoroJusoBadRequestException ex, 
			HttpServletResponse response) {
	    return getErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * 일반적인 Exception으로 서버에서 발생되는 내부 오류등을 정의함.
	 * 
	 * @param ex
	 * @param response
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseEntity<ResultMessage> handleException(
			Exception ex, 
			HttpServletResponse response) {
		logger.warn("Exception", ex);
	    return getErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * 발생된 Exception을 Status Code를 설정하여 Response를 보냄
	 * 
	 * @param message
	 * @param status
	 * @return
	 */
	public ResponseEntity<ResultMessage> getErrorResponse(String message, HttpStatus status) {
		return new ResponseEntity<ResultMessage>(new ResultMessage(message), 
				status);
	}
	
}
