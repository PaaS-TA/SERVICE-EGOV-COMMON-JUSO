package org.openpaas.egovframework.comcomponent.dorojuso.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openpaas.egovframework.comcomponent.dorojuso.controller.DoroJusoController;
import org.openpaas.egovframework.comcomponent.dorojuso.model.fixture.DoroJusoFixture;
import org.openpaas.egovframework.comcomponent.dorojuso.service.DoroJusoService;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class DoroJusoControllerTest {
	
	MockMvc mockMvc;

	@InjectMocks
	DoroJusoController controller;

	@Mock
	DoroJusoService doroJusoService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

	    this.mockMvc = MockMvcBuilders.standaloneSetup(controller).setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
	}
	
	/**
	 * 정상적으로 데이터 조회가 되는지 확인
	 * errorCode = 0, 데이터는 존재함.
	 * 
	 * @throws Exception
	 */
//	@Test
	public void catalogIsRetrievedCorrectly() throws Exception {when(doroJusoService.getDoroJuso(1, 100, "강남대로")).thenReturn(DoroJusoFixture.getDoroJuso());
	
	    this.mockMvc.perform(get("/addrlink/addrLinkApi.do")
	    	.param("currentPage", "1")
	    	.param("countPerPage", "100")
	    	.param("keyword", "강남대로")
	        .accept(MediaType.APPLICATION_JSON))	        
	        .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.juso", hasSize(1)));
	    
	    // TO DO - check rest of the json including plans
	}
	
	/**
	 * query해서 가져오는 갯수를 1000보다 크게 했을 경우 오류 확인
	 * 
	 */
	
	/**
	 * 특정 Query에 대해 갯수가 정확하게 넘어오는지 확인
	 * currentPage, countPerPage 등 response의 데이터를 비교 검증
	 */
	
	/**
	 * JSON, XML 로 변환이 잘되서 넘어오는지 확인
	 */
}
