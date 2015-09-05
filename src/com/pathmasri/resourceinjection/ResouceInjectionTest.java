package com.pathmasri.resourceinjection;

import java.sql.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class ResouceInjectionTest
 */
@WebServlet("/ResouceInjectionTest")
public class ResouceInjectionTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private javax.sql.DataSource dataSource;
	
	public void init() throws ServletException {
		
		try {
			InitialContext ctx = new InitialContext();
			dataSource = (DataSource)ctx.lookup("java:comp/env/jdbc/ocwcd");
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResouceInjectionTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			java.sql.Connection con = dataSource.getConnection();
			Statement stmt = con.createStatement();
			String query = "select * from Student";
			ResultSet rs = stmt.executeQuery(query);
			PrintWriter out = response.getWriter();
	        response.setContentType("text/html");
	        rs.next();
	        out.write(rs.getString(2));
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
