package org.openpaas.egovframework.comcomponent.dorojuso.exception;

/**
 * 도로주소가 존재하지 않을 경우 Exception (Update, Delete시)
 * 
 * @author 안찬영
 *
 * History
 * 2015.7.1 최초 Framework 구성시 작업
 */
public class DoroJusoDoesNotExistException extends Exception {
	
	private static final long serialVersionUID = -62090827040416788L;

	/**
	 * 생성자 (query를 메세지로)
	 * @param query
	 */
	public DoroJusoDoesNotExistException(String query) {
		super("DoroJuso does not exist: query = " + query);
	}
	
}