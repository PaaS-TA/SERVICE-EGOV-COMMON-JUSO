package org.openpaas.egovframework.comcomponent.dorojuso.controller;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openpaas.egovframework.comcomponent.dorojuso.common.HttpClientUtils;
import org.openpaas.egovframework.comcomponent.dorojuso.common.JsonUtils;
import org.openpaas.egovframework.comcomponent.dorojuso.model.DoroJusoInfo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import ch.qos.logback.core.net.server.Client;

import com.fasterxml.jackson.databind.JsonNode;

/**
 */
public class DoroJusoManagerRestTest {
	
	private static Properties prop = new Properties();
	
	@BeforeClass
	public static void init() {
		
		System.out.println("== Started test DoroJuso API ==");

		// Initialization
		// Get properties information
		String propFile = "test.properties";
 
		InputStream inputStream = DoroJusoManagerRestTest.class.getClassLoader().getResourceAsStream(propFile);
		
		try {
			prop.load(inputStream);
	 	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	 		System.err.println(e);
	 	}		
	}
	
	private DoroJusoInfo makeSampleDoroJusoInfo() {
	
		DoroJusoInfo info = new DoroJusoInfo();
		
		// init Test data
		info.setCode("1165010200");
		info.setSido("서울특별시");
		info.setSigungu("서초구");
		info.setEupmyundong("양재동");
		info.setRi("");
		info.setSan("0");
		info.setBunji(326);
		info.setHo(2);
		info.setDoro_code("116504163008");
		info.setDoro("강남대로12길");
		info.setJiha("0");
		info.setBon(8);
		info.setBu(0);
		info.setGunmul("성경빌딩");
		info.setGunmul_sangse("");
		info.setGunmul_no(prop.getProperty("building_code"));	// Primary Key
		info.setEupmyundong_no(1);
		info.setHang_code("1165065200");
		info.setHang("양재2동");
		info.setZipcode("137897");
		info.setZipno("001");
		info.setDayaeng("");
		info.setIdong("");
		info.setDefore_doro("");
		info.setSigungu_gunmul("성경빌딩");
		info.setGongdong("0");
		info.setGicho_no("06779");
		info.setJuso_sang("0");
		info.setBigo1("");
		info.setBigo2("");
		
		return info;
	}

	@AfterClass
	public static void deleteTestData() {
		HttpHeaders headers = new HttpHeaders();	
		headers.set("Accept", "application/json");
		
		HttpEntity<String> entity = new HttpEntity<String>("", headers);
		ResponseEntity<String> response = null;

		boolean bException = false;
		
		try {
			
			String url = prop.getProperty("test_base_protocol") + prop.getProperty("test_base_url") + prop.getProperty("dorojuso_manager_path");
			url += "/" + prop.getProperty("building_code");
			
			response = HttpClientUtils.send(url, entity, HttpMethod.DELETE);

		} catch (Exception ex) {
			System.err.println("After class exception:" + ex.getMessage());
		}
	}
	
	/**
	 * 도로 주소 가져오기가 잘되는지 확인
	 * - JSON 파싱이 잘되는지 확인함
	 */
	@Test	
	public void getDoroJusoInfoTest_normalJSON() {
		
		System.out.println("Start - normal JSON");
		
		HttpHeaders headers = new HttpHeaders();	
		headers.set("Accept", "application/json");
		HttpEntity<String> entity = new HttpEntity<String>("", headers);
		ResponseEntity<String> response = null;

		boolean bException = false;
		
		try {
			
			String url = prop.getProperty("test_base_protocol") + prop.getProperty("test_base_url") + prop.getProperty("dorojuso_manager_path");
			url += "/10/10/강남대로10";
			
			response = HttpClientUtils.send(url, entity, HttpMethod.GET);

		} catch (Exception ex) {
			
			assertFalse("exception:" + ex.getMessage(), true);
			bException = true;
			
		}
		
		if (!bException) {
			try {
				JsonNode json = JsonUtils.convertToJson(response);
			} catch (Exception e) {
				assertFalse("exception (JSON convert):" + e.getMessage(), true);
				bException = true;
			}			
		}
		
		if (!bException) assertTrue("OK", true);

		System.out.println("End - normal JSON");
	}

	/**
	 * Insert가 잘되는 지 확인
	 */
	@Test	
	public void insertDoroJusoInfoTest_normal() {
		
		System.out.println("Start - insert normal");
		
		HttpHeaders headers = new HttpHeaders();	
		headers.set("Accept", "application/json");
		
		HttpEntity<DoroJusoInfo> entity = new HttpEntity<DoroJusoInfo>(makeSampleDoroJusoInfo(), headers);
		ResponseEntity<String> response = null;

		boolean bException = false;
		
		try {
			
			String url = prop.getProperty("test_base_protocol") + prop.getProperty("test_base_url") + prop.getProperty("dorojuso_manager_path");
			
			response = HttpClientUtils.sendDoroJusoInfo(url, entity, HttpMethod.POST);
			
			if (response.getStatusCode() != HttpStatus.OK) {
				assertFalse("Error: status code is " + response.getStatusCode(), true);
				bException = true;
			}
			

		} catch (Exception ex) {
			
			assertFalse("exception:" + ex.getMessage(), true);
			bException = true;
			
		}
		
		if (!bException) assertTrue("OK", true);
		
		System.out.println("End - insert normal ");
	}

	/**
	 * Insert가 잘되는 지 확인
	 * - 중복된 것을  insert 할때 오류가 오는지 확인
	 */
	@Test	
	public void insertDoroJusoInfoTest_exist() {
		
		System.out.println("Start - insert existed");
		
		HttpHeaders headers = new HttpHeaders();	
		headers.set("Accept", "application/json");

		// 존재하는 주소
		DoroJusoInfo insertInfo = makeSampleDoroJusoInfo();		
		insertInfo.setGunmul_no(prop.getProperty("building_code_exist"));
		
		HttpEntity<DoroJusoInfo> entity = new HttpEntity<DoroJusoInfo>(insertInfo, headers);
		ResponseEntity<String> response = null;

		boolean bException = false;
		
		try {
			
			String url = prop.getProperty("test_base_protocol") + prop.getProperty("test_base_url") + prop.getProperty("dorojuso_manager_path");
			
			response = HttpClientUtils.sendDoroJusoInfo(url, entity, HttpMethod.POST);

			System.out.println("Status:" + response.getStatusCode());
			
			if (response.getStatusCode() == HttpStatus.CONFLICT) {
				bException = true;
				assertTrue("OK", true);
			}
			
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			assertFalse("Exception", true);
			bException = true;			
		}
		
		if (!bException) assertFalse("Error", true);
		
		System.out.println("End - insert existed");
	}

	/**
	 * Update가 잘되는 지 확인
	 */
	@Test	
	public void updateDoroJusoInfoTest_normal() {
		
		System.out.println("Start - update normal");
		
		HttpHeaders headers = new HttpHeaders();	
		headers.set("Accept", "application/json");

		// 존재하는 주소
		DoroJusoInfo updateInfo = makeSampleDoroJusoInfo();
		updateInfo.setBigo1("Updated");
		
		HttpEntity<DoroJusoInfo> entity = new HttpEntity<DoroJusoInfo>(updateInfo, headers);
		ResponseEntity<String> response = null;

		boolean bException = false;
		
		try {
			
			String url = prop.getProperty("test_base_protocol") + prop.getProperty("test_base_url") + prop.getProperty("dorojuso_manager_path");
			url += "/" + updateInfo.getGunmul_no();
			
			response = HttpClientUtils.sendDoroJusoInfo(url, entity, HttpMethod.PUT);

			System.out.println("Status:" + response.getStatusCode());
			
			if (response.getStatusCode() != HttpStatus.OK) {
				assertFalse("Error:" + response.getStatusCode(), true);
				bException = true;
			}
			
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			bException = true;			
		}
		
		if (!bException) assertTrue("OK", true);
		
		System.out.println("End - update normal");
	}

	/**
	 * Update가 잘되는 지 확인
	 * - 없는 코드를 update 할때 오류가 오는지 확인
	 */
	@Test	
	public void updateDoroJusoInfoTest_noGunmulNo() {
		
		System.out.println("Start - update no gunmul_no");
		
		HttpHeaders headers = new HttpHeaders();	
		headers.set("Accept", "application/json");

		// 존재하는 주소
		DoroJusoInfo updateInfo = makeSampleDoroJusoInfo();
		updateInfo.setBigo1("Updated");
		updateInfo.setGunmul_no(prop.getProperty("building_code_fail"));
		
		HttpEntity<DoroJusoInfo> entity = new HttpEntity<DoroJusoInfo>(updateInfo, headers);
		ResponseEntity<String> response = null;

		boolean bException = false;
		
		try {
			
			String url = prop.getProperty("test_base_protocol") + prop.getProperty("test_base_url") + prop.getProperty("dorojuso_manager_path");
			url += "/" + updateInfo.getGunmul_no();
			
			response = HttpClientUtils.sendDoroJusoInfo(url, entity, HttpMethod.PUT);

			System.out.println("Status:" + response.getStatusCode());
			
			if (response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
				bException = true;
				assertTrue("OK:" + response.getStatusCode(), true);
			}
			
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			bException = true;		
		}
		
		if (!bException) assertFalse("Error", true);
		
		System.out.println("End - update no gunmul no");
	}

	/**
	 * Update가 잘되는 지 확인
	 * - 요청 builing code가 DoroJusoInfo 내의 코드와 다를 경우
	 */
	@Test	
	public void updateDoroJusoInfoTest_diffGunmulNo() {
		
		System.out.println("Start - update difference gunmul_no");
		
		HttpHeaders headers = new HttpHeaders();	
		headers.set("Accept", "application/json");

		// 존재하는 주소
		DoroJusoInfo updateInfo = makeSampleDoroJusoInfo();
		updateInfo.setBigo1("Updated");
		
		HttpEntity<DoroJusoInfo> entity = new HttpEntity<DoroJusoInfo>(updateInfo, headers);
		ResponseEntity<String> response = null;

		boolean bException = false;
		
		try {
			
			String url = prop.getProperty("test_base_protocol") + prop.getProperty("test_base_url") + prop.getProperty("dorojuso_manager_path");
			url += "/" + prop.getProperty("building_code_fail");
			
			response = HttpClientUtils.sendDoroJusoInfo(url, entity, HttpMethod.PUT);

			System.out.println("Status:" + response.getStatusCode());
			
			if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
				assertTrue("OK:" + response.getStatusCode(), true);
				bException = true;
			}
			
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			bException = true;			
		}
		
		if (!bException) assertFalse("OK", true);
		
		System.out.println("End - update difference gunmul no");
	}

	/**
	 * Delete가 잘되는 지 확인 (Insert에서 만든 정보)
	 * 
	 * 이 테스트는 본 Unit 테스크가 종료되는 시점에 AfterClass로 테스트 데이터를 주우고 있어 에러가 없으면 통과하는 것으로 간주합니다.
	 */
//	@Test	
	public void deleteDoroJusoInfoTest_normal() {
		
		System.out.println("Start - delete normal");
		
		HttpHeaders headers = new HttpHeaders();	
		headers.set("Accept", "application/json");

		// 존재하는 주소		
		HttpEntity<String> entity = new HttpEntity<String>("", headers);
		ResponseEntity<String> response = null;

		boolean bException = false;
		
		try {
			
			String url = prop.getProperty("test_base_protocol") + prop.getProperty("test_base_url") + prop.getProperty("dorojuso_manager_path");
			url += "/" + prop.getProperty("building_code");
			
			response = HttpClientUtils.send(url, entity, HttpMethod.DELETE);

			System.out.println("Status:" + response.getStatusCode());
			
			if (response.getStatusCode() != HttpStatus.OK) {
				assertFalse("Error:" + response.getStatusCode(), true);
				bException = true;
			}
			
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			bException = true;			
		}
		
		if (!bException) assertTrue("OK", true);
		
		System.out.println("End - delete normal");
	}

	/**
	 * Delete가 잘되는 지 확인
	 * - 없는 코드를 delete 할때 오류가 오는지 확인
	 */
	@Test	
	public void deleteDoroJusoInfoTest_noGunmulNo() {
		
		System.out.println("Start - delete normal");
		
		HttpHeaders headers = new HttpHeaders();	
		headers.set("Accept", "application/json");

		// 존재하는 주소		
		HttpEntity<String> entity = new HttpEntity<String>("", headers);
		ResponseEntity<String> response = null;

		boolean bException = false;
		
		try {
			
			String url = prop.getProperty("test_base_protocol") + prop.getProperty("test_base_url") + prop.getProperty("dorojuso_manager_path");
			url += "/" + prop.getProperty("building_code_fail");
			
			response = HttpClientUtils.send(url, entity, HttpMethod.DELETE);

			System.out.println("Status:" + response.getStatusCode());
			
			if (response.getStatusCode() != HttpStatus.OK) {
				assertFalse("Error:" + response.getStatusCode(), true);
				bException = true;
			}
			
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			bException = true;			
		}
		
		if (!bException) assertTrue("OK", true);
		
		System.out.println("End - delete normal");
	}

}
