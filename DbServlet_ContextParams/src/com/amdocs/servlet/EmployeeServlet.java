package com.amdocs.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmployeeServlet extends HttpServlet {

	private static final String GET_EMP_DETAILS = "SELECT EID, ENAME, ESAL, DEPT FROM EMPTAB WHERE EID = ? ";

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		PrintWriter pw = null;
		int empNo = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ServletContext context= null;
		String driver, url, user, pwd = null;

		try {

			// get PrintWriter
			pw = res.getWriter();

			// set Content Response Type
			res.setContentType("text/html");

			// READ FORM data
			empNo = Integer.parseInt(req.getParameter("empno"));

			// get Access to context Object
			context = getServletContext();

			// READ Servlet INIT PARAM
			driver = context.getInitParameter("driver");
			url = context.getInitParameter("url");
			user = context.getInitParameter("user");
			pwd = context.getInitParameter("pwd");

			// Register JDBC Driver
			Class.forName(driver);
			// CREATE Conn Object
			con = DriverManager.getConnection(url, user, pwd);
			// CREATE PreparedStatement having pre-compiler query
			ps = con.prepareStatement(GET_EMP_DETAILS);
			// SET QUERY PARAM VALUES
			ps.setInt(1, empNo);
			// Execute The Query
			rs = ps.executeQuery();
			// Process the ResultSet object
			// using if because we are fetching only ONE DETAIL, Result will be 1 Row or 0
			if(rs.next()) {
				pw.println("<h1 style='color:blue'>Employee Details are </h1>");
				pw.println("<h1 style='color:blue'>Employee No</h1> : "+rs.getInt(1));
				pw.println("<h1 style='color:blue'>Employee Name</h1> : "+rs.getString(2));
				pw.println("<h1 style='color:blue'>Employee Sal</h1> : "+rs.getDouble(3));
				pw.println("<h1 style='color:blue'>Employee Dept</h1> : "+rs.getString(4));

			}
			else {
				pw.println("<h1 style='color:red'>Employee not found </h1>");
			}
		}// try 
		catch (SQLException se) {
			pw.print("<h1 style='color:red'>Internal Db Problem </h1>");
			se.printStackTrace();
		}
		catch (ClassNotFoundException cnf) {
			pw.print("<h1 style='color:red'>Db Problem </h1>");
			cnf.printStackTrace();
		}
		catch (Exception e) {
			pw.print("<h1 style='color:red'>Db Problem </h1>");
			e.printStackTrace();
		}
		finally {
			try {
				// close JDBC objects	
				if(rs!=null)
					rs.close();
			}
			catch (SQLException se) {
				se.printStackTrace();
			}
			
			try {
				// close JDBC objects	
				if(ps!=null)
					ps.close();
			}
			catch (SQLException se) {
				se.printStackTrace();
			}
			
			try {
				// close JDBC objects	
				if(con!=null)
					con.close();
			}
			catch (SQLException se) {
				se.printStackTrace();
			}
			// HOME LINK
			pw.print("<br><br><a href = 'input.html'>HOME</a>");
			
			try {
				// close JDBC objects	
				if(pw!=null)
					pw.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}//finally

	}// doGet
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}// doPost

}// CLASS
