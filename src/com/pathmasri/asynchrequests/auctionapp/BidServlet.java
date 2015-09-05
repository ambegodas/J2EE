package com.pathmasri.asynchrequests.auctionapp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BidServlet
 */

public class BidServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getSession().setAttribute("user", new User("Pathmasri"));
		ServletContext ctx = getServletContext();
		RequestDispatcher dispatcher = ctx.getRequestDispatcher("/WEB-INF/jsp/auctionHome.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("Bid request recieved");
		User user = (User)request.getSession().getAttribute("user");
		AuctionSystem system = (AuctionSystem)getServletContext().getAttribute("items");
		String ref = request.getParameter("ref");
		int amount = Integer.parseInt(request.getParameter("amount"));
		
		AuctionItem item = system.findItem(ref);
		item.updateBid(user, amount);
		System.out.println("Bid request completed");
		
		
		
	}

}
