package org.openpaas.egovframework.comcomponent.dorojuso.common;

/**
 * String 처리를 위한 Utility
 * 
 * @author 안찬영
  *
 * History
 * 2015.7.1 최초 Framework 구성시 작업
 */
public class StringUtils {

	/**
	 * DB의 우편번호 (6자리)를 xxx-xxx형태로 변환
	 * 
	 * @param zipcode 우편번호 (6자리 xxxxxx)
	 * @return xxx-xxx형태의 우편번호
	 */
	public static String changeZipCode(String zipcode) {
		String result = "";
		
		if (null == zipcode) return "";
		if (zipcode.length() != 6) return "";
		
		result = zipcode.substring(0, 3) + "-" + zipcode.substring(3,  6);
		
		return result;
	}
}
