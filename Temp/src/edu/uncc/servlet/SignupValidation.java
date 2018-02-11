package edu.uncc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class SignupValidation
 */
@WebServlet("/SignupValidation")
public class SignupValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupValidation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		  response.setContentType("text/html");

	      PrintWriter out = response.getWriter();
	      
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	      String DB_URL="jdbc:mysql://localhost:3306/ssdi";

	      //  Database credentials
	     String USER = "root";
	      String PASS = "root";
	      

	      
	      String user = request.getParameter("user");
	      String pass = request.getParameter("pass");
	      PreparedStatement  stmt = null;
	      Connection conn= null;
	      int flag = 0;
	      try {
	          // Register JDBC driver
	          Class.forName(JDBC_DRIVER);

	          // Open a connection
	           conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
	           String sql;
		          //sql = "insert into LOGIN Values";
		          sql = "INSERT INTO LOGIN"
				+ "(USERID, PASSWORD ) VALUES"
				+ "(?,?)";
	          // Execute SQL query
	           stmt = (PreparedStatement) conn.prepareStatement(sql);
	           stmt.setString(1, user);
	           stmt.setString(2, pass);
				
	           flag = stmt.executeUpdate();
	          
	          // Extract data from result set
	      
	          
	          if(flag > 0)
	          {
	        	  System.out.println("Record is inserted");
	        	  ServletContext sc = getServletContext();
	          sc.getRequestDispatcher("/index.html").forward(request, response);
	          }
	          else
	          {
	        	  ServletContext sc = getServletContext();
	        	  sc.getRequestDispatcher("/signup.html").forward(request, response);
	          }

	          // Clean-up environment
	          
	          stmt.close();
	          conn.close();
	       } catch(SQLException se) {
	          //Handle errors for JDBC
	          se.printStackTrace();
	       } catch(Exception e) {
	          //Handle errors for Class.forName
	          e.printStackTrace();
	       } finally {
	          //finally block used to close resources
	          try {
	             if(stmt!=null)
	                stmt.close();
	          } catch(SQLException se2) {
	          } // nothing we can do
	          try {
	             if(conn!=null)
	             conn.close();
	          } catch(SQLException se) {
	             se.printStackTrace();
	          } //end finally try
	       } //end try
	      
	      
	     
	      
	}

}
