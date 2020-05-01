package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/secondurl")
public class CookieReadServlet extends HttpServlet{
	
	private static final String  SQL = "insert into cookie_person_info values(cookie_pid.nextval, ?,?,?,?,?)";
	
	@Resource(name = "DsJndi")
	private DataSource ds;
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				
		PrintWriter pw = null;
		String pname=null, fname=null, gender = null;
		Double income , tax = null;
		Cookie[ ] cks= null;
		Connection con =null;
		PreparedStatement ps = null;
		int count = 0;
		
		resp.setContentType("text/html");
		pw = resp.getWriter();
		
		// read form Data
		income = Double.valueOf(req.getParameter("income"));
		tax = Double.valueOf(req.getParameter("tax"));
		
		// Read Cookies
		cks = req.getCookies();
		if(cks!=null && cks.length!=0) {
			pname = cks[0].getValue();
			fname = cks[1].getValue();
			gender = cks[2].getValue();
		}
		
		try {
			// get JDBC pooled Connection
			con = ds.getConnection();
			ps = con.prepareStatement(SQL);
			
			ps.setString(1, pname);
			ps.setString(2, fname);
			ps.setString(3, gender);
			ps.setDouble(4, income);
			ps.setDouble(5, tax);
			
			count = ps.executeUpdate();
			if(count!=0) {
				pw.println("Successfully Registered");
			}
			else {
				pw.println("Registered Failed");
			}
			
		} 
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(ps!=null) {
					ps.close();
				}
			} 
			catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(con!=null) {
					con.close();
				}
			} 
			catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		// Display the both form Data
		pw.println("<br><b>Form #1/req #1 : : "+pname+"---"+fname+"--"+gender+"---</b>");
		pw.println("<br><b>Form #2/req #2 : : "+tax+"---"+income+"</b><br>");
		
		pw.println("<a href='index.html'>Home</a>");
		
		pw.close();
	}
}
