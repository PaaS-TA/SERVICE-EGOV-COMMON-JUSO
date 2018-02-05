package org.openpaas.egovframework.comcomponent.dorojuso.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * API 결과에서 공통으로 사용되는 부분 (성공여부, 오류코드 등)
 *  
 * @author 안찬영
 *
 * History
 * 2015.7.14 도로명주소 검색 개발
 */
@XmlRootElement
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Common {

	/**
	 * 정상
	 */
	@JsonIgnore
	public static String OK = "0";	// 정상
	/**
	 * 정상 메세지
	 */
	@JsonIgnore
	public static String MSG_OK = "정상";	 // 정상
	/**
	 * 시스템 오류
	 */
	@JsonIgnore
	public static String ERR_SYSTEM = "-999";	// 시스템 에러
	/**
	 * 시스템 오류 메세지
	 */
	@JsonIgnore
	public static String MSG_ERR_SYSTEM = "시스템에서 오류가 발생하였습니다.";	// 시스템 에러
	
	/**
	 * DoroJuso 기존 API에서 정의되어 있었으나 이번에서는 구현하지 않음. 
	 * API플랫폼에서 치리되는 부분임
	 */
	// API 플랫폼에서는 자체 오류메세제 전달
//	public static String ERR_AUTH = "E001";	// 승인되지 않은 Key 입니다.
	// API 플랫폼에서는 자체 오류메세제 전달
//	public static String ERR_SITE = "E002";	// 승인되지 않은 사이트 입니다.
	// API 플랫폼에서는 자체 오류메세제 전달
//	public static String ERR_PATH = "E003";	// 정상적인 경로로 접속하시기 바랍니다.
	/**
	 * 최대값오류
	 */
	@JsonIgnore
	public static String ERR_MAX = "E004";
	/**
	 * 최대값 오류 메세지
	 */
	@JsonIgnore
	public static String MSG_ERR_MAX = "검색 결과는 최대 1000건 입니다. currentPage, countPerpage를 확인 하시기 바랍니다.";
	/**
	 * 검색 결과가 많을 경우 오류
	 */
	@JsonIgnore
	public static String ERR_TOOMANY = "P001";	// 검색 결과가 너무 많습니다. 도로명 주소 또는 지번을 입력하세요.
	/**
	 * 검색 결과가 많을 경우 오류 메세지
	 */
	@JsonIgnore
	public static String MSG_ERR_TOOMANY = "검색 결과가 너무 많습니다. 도로명 주소 또는 지번을 입력하세요.";
	
	/**
	 * 전체 갯수
	 */
	@NotEmpty
	@JsonSerialize
	@JsonProperty("totalCount")
	private int totalCount;
	
	/**
	 * 현재 페이지
	 */
	@NotEmpty
	@JsonSerialize
	@JsonProperty("currentPage")
	private int currentPage;

	/**
	 * 페이지당 갯수
	 */
	@NotEmpty
	@JsonSerialize
	@JsonProperty("countPerPage")
	private int countPerPage;

	/**
	 * 오류 코드
	 */
	@NotEmpty
	@JsonSerialize
	@JsonProperty("errorCode")
	private String errorCode;

	/**
	 * 오류 메세지
	 */
	@NotEmpty
	@JsonSerialize
	@JsonProperty("errorMessage")
	private String errorMessage;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getCountPerPage() {
		return countPerPage;
	}

	public void setCountPerPage(int countPerPage) {
		this.countPerPage = countPerPage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	
}

