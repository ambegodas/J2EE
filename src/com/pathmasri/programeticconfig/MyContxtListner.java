/**
 * @autor pathmasri
 * Jul 13, 2015 9:07:45 PM
 */
package com.pathmasri.programeticconfig;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class MyContxtListner implements ServletContextListener {

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		ServletContext sctx = arg0.getServletContext();
		FilterRegistration.Dynamic r = sctx.addFilter("AuthFilter", "com.pathmasri.programeticconfig.MyAuthFilter");
		EnumSet<DispatcherType> types = EnumSet.of(DispatcherType.REQUEST,DispatcherType.FORWARD);
		r.addMappingForUrlPatterns(types, false, "*");
		
	}

}
