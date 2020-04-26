package com.amdocs.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/secondurl")
public class SecondServlet extends HttpServlet {


	private static final String INSERT_QUERY = "INSERT INTO PERSONTAB VALUES(PER_ID_SEQ.NEXTVAL,?,?,?,?,?)";
	private DataSource ds;

	public SecondServlet() {
		try {
			InitialContext ic = new InitialContext();
			ds = (DataSource)ic.lookup("java:/comp/env/DsJndi");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PrintWriter pw = null;
		Connection con = null;
		PreparedStatement ps = null;

		resp.setContentType("text/html");
		pw = resp.getWriter();

		// read form data
		String name = req.getParameter("name");
		String fname = req.getParameter("fname");
		String ms = req.getParameter("ms");

		// read Second Form data 
		String f2val1 = req.getParameter("f2t1");
		String f2val2 = req.getParameter("f2t2");

		// Write both form1/req1 data and form2/req2 data to DB Table as Record
		try {

			// get Jdbc Pooler connection
			con = ds.getConnection();
			// create PreparedStatement obje
			ps = con.prepareStatement(INSERT_QUERY);
			// set Query param values
			ps.setString(1, name);
			ps.setString(2, fname);
			ps.setString(3, ms);
			ps.setString(4, f2val1);
			ps.setString(5, f2val2);

			//execute query
			int count = ps.executeUpdate();

			if(count!=0) {
				pw.println("<h2 style='color':blue;text-align:center>Person Registration Successful</h2><br><br>");
				pw.println("<b>Form 1/ Req 1 Data : ' "+name+" '  , ' "+fname+" '  , ' "+ms+" ' </b><br><br>");
				pw.println("<b>Form 2/ Req 2 Data : ' "+f2val1+" '  ,  ' "+f2val2+" ' </b><br><br>");
			}
			else {
				pw.println("<h2 style='color':red;text-align:center>Person Registration Failed</h2><br><br>");
			}

			pw.println("<a href='index.html'>HOME</a>");

		} 
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(ps!=null)
					ps.close();
			}
			catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			}
			catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if(pw!=null)
					pw.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
