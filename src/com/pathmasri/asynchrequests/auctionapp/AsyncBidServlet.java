package com.pathmasri.asynchrequests.auctionapp;

import java.io.IOException;
import java.util.Date;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AsyncBidServlet
 */
@WebServlet(urlPatterns="/AsyncBidServlet",asyncSupported=true)
public class AsyncBidServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	   System.out.println("Asynchronous bid servlet called");
       String ref = request.getParameter("input");
       AsyncContext ctx = request.startAsync();
       Date d = new Date();
       System.out.println(d);
       ctx.setTimeout(20000);
       
       AuctionSystem system = (AuctionSystem)getServletContext().getAttribute("items");
       AuctionItem item = system.findItem(ref);
       item.addPendingRequest(ctx);
       System.out.println("Asynchronous bid servlet exit");
       Date d1 = new Date();
       System.out.println(d1);
       
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
