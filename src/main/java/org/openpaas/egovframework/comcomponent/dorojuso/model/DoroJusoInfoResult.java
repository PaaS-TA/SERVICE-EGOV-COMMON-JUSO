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
 * 주소 리스트 (관리용)
 * 
 * @author 안찬영
 *
 * History
 * 2015.7.14 도로명주소 검색 개발
 */
@XmlRootElement(name="results")
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DoroJusoInfoResult {

	/**
	 * 주소 리스트
	 */
	@NotEmpty
	@JsonSerialize
	@JsonProperty("juso")
	private List<DoroJusoInfo> juso = new ArrayList<DoroJusoInfo>();

	public List<DoroJusoInfo> getJuso() {
		return juso;
	}

	public void setJuso(List<DoroJusoInfo> juso) {
		this.juso = juso;
	}


}