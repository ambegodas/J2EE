/**
 * @autor pathmasri
 * May 26, 2015 10:12:34 PM
 */
package com.pathmasri.wrappers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.eclipse.jdt.internal.compiler.ast.SuperReference;

public class MyRequestWrapper extends HttpServletRequestWrapper {
	/**
	 * @param request
	 */
	public MyRequestWrapper(HttpServletRequest request) {
		super(request);
	}
   @Override
    public Object getAttribute(String s){
    	System.out.println("test");
	   return super.getAttribute(s);
    }

}
