package org.openpaas.egovframework.comcomponent.dorojuso.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 도로주소 DB 객체
 * @author 안찬영
 *
 * History
 * 2015.7.14 도로명주소 검색 개발
 */
//@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
//@JsonIgnoreProperties(ignoreUnknown = true)
public class DoroJusoInfo {

	/** 
	 * 법전동코드
	 */
	private String code;
	/**
	 * 시도명
	 */
	private String sido;
	/**
	 * 시군구명
	 */
	private String sigungu;
	/**
	 * 법정읍면동명
	 */
	private String eupmyundong;
	/**
	 * 법정리명
	 */
	private String ri;
	/**
	 * 산여부
	 */
	private String san;
	/**
	 * 지번본번(번지)
	 */
	private int bunji;
	/**
	 * 지번부번(호)
	 */
	private int ho;
	/**
	 * 도로명코드
	 */
	private String  doro_code;
	/**
	 * 도로명
	 */
	private String doro;
	/**
	 * 지하여부
	 */
	private String jiha;
	/**
	 * 건물본번
	 */
	private int bon;
	/**
	 * 건물부번
	 */
	private int bu;
	/**
	 * 건축물대장 건물명
	 */
	private String gunmul;
	/**
	 * 상세건물명
	 */
	private String gunmul_sangse;
	/**
	 * 건물관리번호
	 */
	private String gunmul_no;
	/**
	 * 읍면동일련번호
	 */
	private int eupmyundong_no;
	/**
	 * 행정동코드
	 */
	private String hang_code;
	/**
	 * 행정동명
	 */
	private String hang;
	/**
	 * 우편번호
	 */
	private String zipcode;
	/**
	 * 우편일련번호
	 */
	private String zipno;
	/**
	 * 다량배달처명
	 */
	private String dayaeng;
	/**
	 * 이동사유코드
	 */
	private String idong;
	/**
	 * 변경일자
	 */
	private String update_date;
	/**
	 * 변경전도로명주소
	 */
	private String defore_doro;
	/**
	 * 시군구용 건물명
	 */
	private String sigungu_gunmul;
	/**
	 * 공동주택여부
	 */
	private String gongdong;
	/**
	 * 기초구역번호
	 */
	private String gicho_no;
	/**
	 * 상세주소여부
	 */
	private String juso_sang;
	/**
	 * 비고1
	 */
	private String bigo1;
	/**
	 * 비고2
	 */
	private String bigo2;
	  
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSido() {
		return sido;
	}
	public void setSido(String sido) {
		this.sido = sido;
	}
	public String getSigungu() {
		return sigungu;
	}
	public void setSigungu(String sigungu) {
		this.sigungu = sigungu;
	}
	public String getEupmyundong() {
		return eupmyundong;
	}
	public void setEupmyundong(String eupmyundong) {
		this.eupmyundong = eupmyundong;
	}
	public String getRi() {
		return ri;
	}
	public void setRi(String ri) {
		this.ri = ri;
	}
	public String getSan() {
		return san;
	}
	public void setSan(String san) {
		this.san = san;
	}
	public int getBunji() {
		return bunji;
	}
	public void setBunji(int bunji) {
		this.bunji = bunji;
	}
	public int getHo() {
		return ho;
	}
	public void setHo(int ho) {
		this.ho = ho;
	}
	public String getDoro_code() {
		return doro_code;
	}
	public void setDoro_code(String doro_code) {
		this.doro_code = doro_code;
	}
	public String getDoro() {
		return doro;
	}
	public void setDoro(String doro) {
		this.doro = doro;
	}
	public String getJiha() {
		return jiha;
	}
	public void setJiha(String jiha) {
		this.jiha = jiha;
	}
	public int getBon() {
		return bon;
	}
	public void setBon(int bon) {
		this.bon = bon;
	}
	public int getBu() {
		return bu;
	}
	public void setBu(int bu) {
		this.bu = bu;
	}
	public String getGunmul() {
		return gunmul;
	}
	public void setGunmul(String gunmul) {
		this.gunmul = gunmul;
	}
	public String getGunmul_sangse() {
		return gunmul_sangse;
	}
	public void setGunmul_sangse(String gunmul_sangse) {
		this.gunmul_sangse = gunmul_sangse;
	}
	public String getGunmul_no() {
		return gunmul_no;
	}
	public void setGunmul_no(String gunmul_no) {
		this.gunmul_no = gunmul_no;
	}
	public int getEupmyundong_no() {
		return eupmyundong_no;
	}
	public void setEupmyundong_no(int eupmyundong_no) {
		this.eupmyundong_no = eupmyundong_no;
	}
	public String getHang_code() {
		return hang_code;
	}
	public void setHang_code(String hang_code) {
		this.hang_code = hang_code;
	}
	public String getHang() {
		return hang;
	}
	public void setHang(String hang) {
		this.hang = hang;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getZipno() {
		return zipno;
	}
	public void setZipno(String zipno) {
		this.zipno = zipno;
	}
	public String getDayaeng() {
		return dayaeng;
	}
	public void setDayaeng(String dayaeng) {
		this.dayaeng = dayaeng;
	}
	public String getIdong() {
		return idong;
	}
	public void setIdong(String idong) {
		this.idong = idong;
	}
	public String getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}
	public String getDefore_doro() {
		return defore_doro;
	}
	public void setDefore_doro(String defore_doro) {
		this.defore_doro = defore_doro;
	}
	public String getSigungu_gunmul() {
		return sigungu_gunmul;
	}
	public void setSigungu_gunmul(String sigungu_gunmul) {
		this.sigungu_gunmul = sigungu_gunmul;
	}
	public String getGongdong() {
		return gongdong;
	}
	public void setGongdong(String gongdong) {
		this.gongdong = gongdong;
	}
	public String getGicho_no() {
		return gicho_no;
	}
	public void setGicho_no(String gicho_no) {
		this.gicho_no = gicho_no;
	}
	public String getJuso_sang() {
		return juso_sang;
	}
	public void setJuso_sang(String juso_sang) {
		this.juso_sang = juso_sang;
	}
	public String getBigo1() {
		return bigo1;
	}
	public void setBigo1(String bigo1) {
		this.bigo1 = bigo1;
	}
	public String getBigo2() {
		return bigo2;
	}
	public void setBigo2(String bigo2) {
		this.bigo2 = bigo2;
	}
	  
	  
	@Override
	public String toString() {
		return "DoroJusoInfo [code=" + code + ", sido=" + sido + ", sigungu="
				+ sigungu + ", eupmyundong=" + eupmyundong + ", ri=" + ri
				+ ", san=" + san + ", bunji=" + bunji + ", ho=" + ho
				+ ", doro_code=" + doro_code + ", doro=" + doro + ", jiha="
				+ jiha + ", bon=" + bon + ", bu=" + bu + ", gunmul=" + gunmul
				+ ", gunmul_sangse=" + gunmul_sangse + ", gunmul_no="
				+ gunmul_no + ", eupmyundong_no=" + eupmyundong_no
				+ ", hang_code=" + hang_code + ", hang=" + hang + ", zipcode="
				+ zipcode + ", zipno=" + zipno + ", dayaeng=" + dayaeng
				+ ", idong=" + idong + ", update_date=" + update_date
				+ ", defore_doro=" + defore_doro + ", sigungu_gunmul="
				+ sigungu_gunmul + ", gongdong=" + gongdong + ", gicho_no="
				+ gicho_no + ", juso_sang=" + juso_sang + ", bigo1=" + bigo1
				+ ", bigo2=" + bigo2 + ", getCode()=" + getCode()
				+ ", getSido()=" + getSido() + ", getSigungu()=" + getSigungu()
				+ ", getEupmyundong()=" + getEupmyundong() + ", getRi()="
				+ getRi() + ", getSan()=" + getSan() + ", getBunji()="
				+ getBunji() + ", getHo()=" + getHo() + ", getDoro_code()="
				+ getDoro_code() + ", getDoro()=" + getDoro() + ", getJiha()="
				+ getJiha() + ", getBon()=" + getBon() + ", getBu()=" + getBu()
				+ ", getGunmul()=" + getGunmul() + ", getGunmul_sangse()="
				+ getGunmul_sangse() + ", getGunmul_no()=" + getGunmul_no()
				+ ", getEupmyundong_no()=" + getEupmyundong_no()
				+ ", getHang_code()=" + getHang_code() + ", getHang()="
				+ getHang() + ", getZipcode()=" + getZipcode()
				+ ", getZipno()=" + getZipno() + ", getDayaeng()="
				+ getDayaeng() + ", getIdong()=" + getIdong()
				+ ", getUpdate_date()=" + getUpdate_date()
				+ ", getDefore_doro()=" + getDefore_doro()
				+ ", getSigungu_gunmul()=" + getSigungu_gunmul()
				+ ", getGongdong()=" + getGongdong() + ", getGicho_no()="
				+ getGicho_no() + ", getJuso_sang()=" + getJuso_sang()
				+ ", getBigo1()=" + getBigo1() + ", getBigo2()=" + getBigo2()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
}
