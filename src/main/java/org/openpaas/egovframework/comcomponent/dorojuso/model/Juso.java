package org.openpaas.egovframework.comcomponent.dorojuso.model;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 주소 객체
 * 
 * @author 안찬영
 *
 * History
 * 2015.7.14 도로명주소 검색 개발
 */
@XmlRootElement
//@XmlAccessorType(XmlAccessType.FIELD)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Juso {

	/**
	 * 도로 주소 (전체)
	 */
	@NotEmpty
	@JsonSerialize
	@JsonProperty("roadAddr")
	private String roadAddr;
	
	/**
	 * 도로 주소 부분1
	 */
	@NotEmpty
	@JsonSerialize
	@JsonProperty("roadAddrPart1")
	private String roadAddrPart1;
	
	/**
	 * 도로주소 부분2
	 */
	@NotEmpty
	@JsonSerialize
	@JsonProperty("roadAddrPart2")
	private String roadAddrPart2;

	/**
	 * 지번 주소
	 */
	@JsonSerialize
	@JsonProperty("jibunAddr")
	private String jibunAddr;
	
	/**
	 * 영문 도로 주소
	 */
	@NotEmpty
	@JsonSerialize
	@JsonProperty("engAddr")
	private String engAddr;

	/** 
	 * 우편번호
	 */
	@NotEmpty
	@JsonSerialize
	@JsonProperty("zipNo")
	private String zipNo;
	
	/**
	 * 관리 코드
	 */
	@NotEmpty
	@JsonSerialize
	@JsonProperty("admCd")
	private String admCd;
	
	/**
	 * 도로관리 번호
	 */
	@NotEmpty
	@JsonSerialize
	@JsonProperty("rnMgtSn")
	private String rnMgtSn;

	/**
	 * 건물관리 번호(Primary Key)
	 */
	@NotEmpty
	@JsonSerialize
	@JsonProperty("bdMgtSn")
	private String bdMgtSn;

	@NotEmpty
	public String getRoadAddr() {
		return roadAddr;
	}

	public void setRoadAddr(String roadAddr) {
		this.roadAddr = roadAddr;
	}

	public String getRoadAddrPart1() {
		return roadAddrPart1;
	}

	public void setRoadAddrPart1(String roadAddrPart1) {
		this.roadAddrPart1 = roadAddrPart1;
	}

	public String getRoadAddrPart2() {
		return roadAddrPart2;
	}

	public void setRoadAddrPart2(String roadAddrPart2) {
		this.roadAddrPart2 = roadAddrPart2;
	}

	public String getJibunAddr() {
		return jibunAddr;
	}

	public void setJibunAddr(String jibunAddr) {
		this.jibunAddr = jibunAddr;
	}

	public String getEngAddr() {
		return engAddr;
	}

	public void setEngAddr(String engAddr) {
		this.engAddr = engAddr;
	}

	public String getZipNo() {
		return zipNo;
	}

	public void setZipNo(String zipNo) {
		this.zipNo = zipNo;
	}

	public String getAdmCd() {
		return admCd;
	}

	public void setAdmCd(String admCd) {
		this.admCd = admCd;
	}

	public String getRnMgtSn() {
		return rnMgtSn;
	}

	public void setRnMgtSn(String rnMgtSn) {
		this.rnMgtSn = rnMgtSn;
	}

	public String getBdMgtSn() {
		return bdMgtSn;
	}

	public void setBdMgtSn(String bdMgtSn) {
		this.bdMgtSn = bdMgtSn;
	}
}
