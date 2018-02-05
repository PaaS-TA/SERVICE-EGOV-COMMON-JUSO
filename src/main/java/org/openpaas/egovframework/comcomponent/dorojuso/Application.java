package org.openpaas.egovframework.comcomponent.dorojuso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Spring Boot에서 시작 Class
 * 
 * @author 안찬영
 * 
 * History
 * 2015.7.1 최초 Framework 구성시 작업
 */
@EnableAutoConfiguration
@ComponentScan
public class Application {

	/**
	 * main 함수
	 * 
	 * @param args
	 */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    	
}