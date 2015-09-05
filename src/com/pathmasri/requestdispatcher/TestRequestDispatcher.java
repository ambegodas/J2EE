package com.pathmasri.requestdispatcher;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestRequestDispatcher
 */
@WebServlet("/TestRequestDispatcher")
public class TestRequestDispatcher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestRequestDispatcher() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		response.setContentType("text/html");
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/WEB-INF/jsp/TestRequestDispatcher.jsp");
		
		if(request.getParameter("input").equals("forward")){
			response.getWriter().write("test forward");
			dispatcher.forward(request, response);
			response.getWriter().write("test");
			dispatcher.forward(request, response);
		} else if(request.getParameter("input").equals("include")){
			
			response.getWriter().write("test include");
			dispatcher.include(request, response);
			response.getWriter().write("test");
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
