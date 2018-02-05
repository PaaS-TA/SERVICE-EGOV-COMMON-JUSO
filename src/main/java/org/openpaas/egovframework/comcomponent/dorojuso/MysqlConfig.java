package org.openpaas.egovframework.comcomponent.dorojuso;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
//import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * MySQL에 대한 Datasource 정의
 * 
 * @author 안찬영
 *
 * History
 * 2015.7.1 최초 Framework 구성시 작업
 */
@Configuration
@PropertySource("classpath:datasource.properties")
public class MysqlConfig {

	@Autowired
	private Environment env;
	
	/**
	 * Datasource 정의
	 * Properties 파일에서 정보를 가져옴
	 * 
	 * @return DataSource
	 */
	@Bean
    public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		//dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setDriverClassName(env.getRequiredProperty("JDBC.Driver"));
		dataSource.setUrl(env.getProperty("JDBC.Url"));
		dataSource.setUsername(env.getProperty("JDBC.Username"));
		dataSource.setPassword(env.getProperty("JDBC.Password"));

		return dataSource;
    }
	
}
