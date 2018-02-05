package org.openpaas.egovframework.comcomponent.dorojuso.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 결과 정보 (관리용)
 * 
 * @author 안찬영
 *
 * History
 * 2015.7.14 도로명주소 검색 개발 
 */
public class ResultMessage {

	/**
	 * 메세지
	 */
	private String message;

	public ResultMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
