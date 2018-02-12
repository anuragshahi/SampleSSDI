package edu.uncc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class LoginValidation
 */
@WebServlet("/LoginValidation")
public class LoginValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginValidation() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	
		

	      PrintWriter out = response.getWriter();
	      
	      String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	      String DB_URL="jdbc:mysql://localhost:3306/ssdi";

	      //  Database credentials
	     String USER = "root";
	      String PASS = "root";
	      
	
	      
	      String user = request.getParameter("user");
	      String pass = request.getParameter("pass");
	      Statement stmt = null;
	      Connection conn= null;
	  	PreparedStatement pstmt = null;
	      try {
	          // Register JDBC driver
	          Class.forName("com.mysql.jdbc.Driver");

	          // Open a connection
	           conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);

	          // Execute SQL query
	           stmt = (Statement) conn.createStatement();
	          String sql;
	          sql = "SELECT userid , password FROM LOGIN";
	          ResultSet rs = stmt.executeQuery(sql);
	          int flag = 0;
	          // Extract data from result set
	          while(rs.next()){
	             //Retrieve by column name
	              String userid = rs.getString("userid");
	             String password = rs.getString("password");
	            
	             //Display values
	          if(user.equals(userid)&& pass.equals(password)){
	        	  flag= 1;
	        	  break;
	          }
	          
	          }
	          
	          if(flag == 1)
	          {
	        	  List<Todo> demoNames = new ArrayList<Todo> ();
	        
	        	  sql = "SELECT title, completed , date_time FROM todo where id="+ "?";
	        	  pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        	  pstmt.setString(1, user);
	        	  rs = pstmt.executeQuery();
	        	  
	        	  System.out.println(rs);
	        	 
	        	  while(rs.next()){
	        		  Todo d1 = new Todo();
		              d1.setTodo(rs.getString("title"));
		              d1.setStatus(rs.getInt("completed")==1 ? "Completed": "Active");
		              d1.setDatetime(String.valueOf(rs.getTimestamp("date_time")));
		              
		              d1.setTimeremaining("30 seconds");
		              demoNames.add(d1);
		              System.out.println(d1.getTodo());
		              System.out.println(d1.getStatus());
		              System.out.println(d1.getDatetime());
	        	  }
	        	/*  Todo d1 = new Todo();
	              d1.setTodo("Shopping");
	              d1.setDatetime("2017-08-09;08:00am");
	              d1.setTimeremaining("30 seconds");
	              Todo d2 = new Todo();
	              d2.setTodo("HomeWork");
	              d2.setDatetime("2017-08-09;08:00am");
	              d2.setTimeremaining("30 seconds");
	              Todo d3  = new Todo();
	              d3.setTodo("Project ");
	              d3.setDatetime("2017-08-09;08:00am");
	              d3.setTimeremaining("30 seconds");
	              
	              demoNames.add(d1);
	              demoNames.add(d2);
	              demoNames.add(d3);*/
	        	  request.setAttribute("name", user);
	              request.setAttribute("demoNames", demoNames);
	              
	        	  
	        	  ServletContext sc = getServletContext();
	          sc.getRequestDispatcher("/view.jsp").forward(request, response);
	          }
	          else
	          {
	        	  ServletContext sc = getServletContext();
	        	  sc.getRequestDispatcher("/index.html").forward(request, response);
	          }

	          // Clean-up environment
	          rs.close();
	          pstmt.close();
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
