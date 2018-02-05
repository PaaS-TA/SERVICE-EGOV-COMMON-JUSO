package org.openpaas.egovframework.comcomponent.dorojuso.exception;

/**
 * 잘못된 Request가 있을 경우의 Exception
 * 
 * @author 안찬영
 *
 * History
 * 2015.7.1 최초 Framework 구성시 작업
 */
public class DoroJusoBadRequestException extends Exception {

	private static final long serialVersionUID = 4719676639792071582L;

	/**
	 * 생성자 (Message 설정)
	 * @param message
	 */
	public DoroJusoBadRequestException(String message) {
		super(message);
	}

	/**
	 * 생성자 (메세지와 throwable 설정)
	 * @param message
	 * @param cause
	 */
	public DoroJusoBadRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 생성자 (throwable 설정)
	 * @param cause
	 */
	public DoroJusoBadRequestException(Throwable cause) {
		super(cause);
	}

}
