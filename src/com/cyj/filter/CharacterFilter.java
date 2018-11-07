package com.cyj.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class CharacterFilter
 */
@WebFilter("/CharacterFilter")
public class CharacterFilter implements Filter {

	private String encode;
    /**
     * Default constructor. 
     */
    public CharacterFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		// 이 클래스의 객체 소멸 시
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		// 요청 발생 시 실행
		System.out.println("Character Filter In");
		request.setCharacterEncoding(encode); // <init-param>의 encode(value:UTF-8) 가져옴
		response.setCharacterEncoding(encode);
		chain.doFilter(request, response);
		System.out.println("Character Filter Out");
		// 응답 발생 시 실행
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		// 이 클래스의 객체가 생성될 때
		// init() : 초기화 메서드
		encode = fConfig.getInitParameter("encode"); //멤버변수 encode에 넣어서 doFilter에서 쓸거임
	}

}