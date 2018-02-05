package org.openpaas.egovframework.comcomponent.dorojuso.exception;

/**
 * 도로주소가 있을 경우 Exception (Insert시)
 * 
 * @author 안찬영
 *
 * History
 * 2015.7.1 최초 Framework 구성시 작업
 */
public class DoroJusoExistsException extends Exception {

	private static final long serialVersionUID = -914571358227517785L;
	
	/**
	 * 생성자 (query를 설정)
	 * @param query
	 */
	public DoroJusoExistsException(String query) {
		super("DoroJuso is existed : query = " + query);
	}

}
