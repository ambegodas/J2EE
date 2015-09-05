package com.pathmasri.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class NoCommitFilter
 */
@WebFilter("/NoCommitFilter")
public class NoCommitFilter implements Filter {
	
	private FilterConfig config;

    /**
     * Default constructor. 
     */
    public NoCommitFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		
		HttpServletResponse r = (HttpServletResponse)response;
		NoCommitResponseWrapper wrap = new NoCommitResponseWrapper(r);		
		chain.doFilter(request, wrap);
		
		if(wrap.isCommitted()){
			return;
		}
		if(wrap.geOut() != null){
			wrap.geOut().writeTo(response.getOutputStream());
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.config = fConfig;
				
	}

}
