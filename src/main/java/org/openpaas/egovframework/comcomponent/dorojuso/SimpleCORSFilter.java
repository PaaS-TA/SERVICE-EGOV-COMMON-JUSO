package org.openpaas.egovframework.comcomponent.dorojuso;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

/**
 * Cross-site HTTP request 처리를 위한 필터
 * 
 * @Author 안찬영
 * 
 * History
 * 2015.7.1 최초 Framework 구성시 작업
 */
@Component
public class SimpleCORSFilter  implements Filter {

	/**
	 * Filter 추가
	 * 
	 * @param req	ServletRequest
	 * @param res	ServletReponse
	 * @param chain	FilterChain
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		chain.doFilter(req, res);
	}

	/**
	 * 초기화
	 */
	public void init(FilterConfig filterConfig) {}

	/**
	 * 종료
	 */
	public void destroy() {}

}
