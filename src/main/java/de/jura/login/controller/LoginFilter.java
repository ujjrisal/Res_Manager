package de.jura.login.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {

	
	public void destroy() {

	}

	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		
		// http request, response are casted to HttpServletRequest & HttpServletResponse respectively. 
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		

		LoginController  session = (LoginController) req.getSession().getAttribute("loginController");

		String url = req.getRequestURI();

		if (session == null ) {

			if (url.indexOf("newindex.xhtml") >= 0
					|| url.indexOf("insert.xhtml") >= 0
					|| url.indexOf("search.xhtml") >= 0
					|| url.indexOf("logout.xhtml") >= 0) {
				res.sendRedirect(((FilterConfig) req).getServletContext()
						.getContextPath() + "/login.xhtml");
			} else {
				chain.doFilter(request, response);
			}

		} else {
			if (url.indexOf("signup.xhtml") >= 0
					|| url.indexOf("login.xhtml") >= 0) {
				res.sendRedirect(((FilterConfig) req).getServletContext()
						.getContextPath() + "/newindex.xhtml");
			} else if (url.indexOf("logout.xhtml") >= 0) {
				req.getSession().removeAttribute("loginController");
				res.sendRedirect(((FilterConfig) req).getServletContext()
						.getContextPath() + "/login.xhtml");

			} else {
				chain.doFilter(request, response);
			}
		}

	}

	
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
