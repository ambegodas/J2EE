/**
 * @autor pathmasri
 * May 30, 2015 11:56:16 AM
 */
package com.pathmasri.asynchrequests;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import com.sun.tools.ws.processor.model.Request;

@WebServlet(urlPatterns="/TestAsychrnousRequest", asyncSupported=true)
public class TestAsychrnousRequest extends GenericServlet {


	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		
		AsyncContext context = arg0.startAsync();
		MyAsyncTask asyncTask = new MyAsyncTask(context);
		context.start(asyncTask);

	}

}
