package org.openpaas.egovframework.comcomponent.dorojuso.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 도로 주소 객체
 * 
 * @author 안찬영
 *
 * History
 * 2015.7.14 도로명주소 검색 개발 
 */
@XmlRootElement(name="results")
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DoroJuso {

	/**
	 * 공통 부분
	 */
	@NotEmpty
	@JsonSerialize
	@JsonProperty("common")
	private Common common;
	
	/**
	 * 주소 리스트
	 */
	@NotEmpty
	@JsonSerialize
	@JsonProperty("juso")
	private List<Juso> juso = new ArrayList<Juso>();


	public Common getCommon() {
		return common;
	}


	public void setCommon(Common common) {
		this.common = common;
	}


	public List<Juso> getJuso() {
		return juso;
	}


	public void setJuso(List<Juso> juso) {
		this.juso = juso;
	}

	
}
