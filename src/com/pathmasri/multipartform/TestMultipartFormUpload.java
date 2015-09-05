package com.pathmasri.multipartform;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class TestMultipartFormUpload
 */
@WebServlet("/TestMultipartFormUpload")
@MultipartConfig(fileSizeThreshold=1000000,maxRequestSize=10000000L)
public class TestMultipartFormUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestMultipartFormUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/jsp/multipartformupload.jsp");
		rd.forward(request, response);
     
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Part p = null;
		
		try{
			p = request.getPart("file1");
		} catch(IllegalStateException e){
			response.sendError(HttpServletResponse.SC_REQUEST_ENTITY_TOO_LARGE);
			return;
		}
		
		if(p==null){
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		String mimeType = p.getContentType();
		long filesize = p.getSize();
		String originalFileName = getFileName(p);
		
		if("yes".equals(request.getParameter("inspect"))){
			
			java.io.InputStream is = p.getInputStream();
			is.close();
			
		} else {
			p.delete();
		}
		
		response.getWriter().write("upload okay..");
		
	}
	
	public static String getFileName(Part p){
		 String h = p.getHeader("content-disposition");
		 String[] sections = h.split("\\s*;\\s*");
		 
		 for(String s: sections){
			 if(s.startsWith("filename=")){
				return s.substring(9).replace("\"", ""); 
			 }
		 }		 
		 return null;
	}

}
