/**
 * @autor pathmasri
 * May 30, 2015 4:35:25 PM
 */
package com.pathmasri.asynchrequests.auctionapp;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AuctionBootstrap implements ServletContextListener {


	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
	
		AuctionSystem system = new AuctionSystem();
		ServletContext ctx = arg0.getServletContext();
		ctx.setAttribute("items", system);
		
	}

}
