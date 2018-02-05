package org.openpaas.egovframework.comcomponent.dorojuso.exception;

/**
 * 일반적인 오류 Exception
 * 
 * @author 안찬영
 *
 * History
 * 2015.7.1 최초 Framework 구성시 작업
 */
public class DoroJusoException extends Exception {

	private static final long serialVersionUID = -5544859893499349135L;

	/**
	 * 생성자 (Message 설정)
	 * @param message
	 */
	public DoroJusoException(String message) {
		super(message);
	}

	/**
	 * 생성자 (Message, throwable 설정)
	 * @param message
	 * @param cause
	 */
	public DoroJusoException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 생성자 (throwable 설정)
	 * @param cause
	 */
	public DoroJusoException(Throwable cause) {
		super(cause);
	}

}