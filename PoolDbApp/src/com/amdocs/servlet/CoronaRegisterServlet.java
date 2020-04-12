package com.amdocs.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class CoronaRegisterServlet extends HttpServlet{

	private static final String sql = "INSERT INTO CORONA_REGISTRATION VALUES(CORONA_PATID_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter pw = null;
		String patName=null, patAddrs=null, gender=null, stage=null;
		int age = 0;
		Connection con = null;
		PreparedStatement pst = null;
		int count = 0;
		
		pw = resp.getWriter();
		resp.setContentType("text/html");

		// Read Form Data
		patName = req.getParameter("patName");
		patAddrs = req.getParameter("patAddrs");
		age = Integer.parseInt(req.getParameter("age"));
		gender = req.getParameter("gender");
		stage = req.getParameter("stage");
		
		try {
			// get Pooled Jdbc Connection object
			con = getPooledConnection();
			// create Jdbc PreparedStatment object
			pst = con.prepareStatement(sql);
			// SET Parateters
			pst.setString(1, patName);
			pst.setString(2, patAddrs);
			pst.setInt(3, age);
			pst.setString(4, gender);
			pst.setString(5, stage);
			
			// Execute Query
			count = pst.executeUpdate();
			
			if(count!=0) {
				pw.println("<h1 style='color:green'>Register success...!!!!!!!!!</h1>");
			}
			else {
				pw.println("<h1 style='color:red'>Register failed...!!!!!!!!!</h1>");
			}
		}
		 catch (SQLException se) {
				pw.println("<h1 style='color:red'>Register failed...!!!!!!!!!</h1>");
				se.printStackTrace();
			}
		catch (Exception e) {
			pw.println("<h1 style='color:red'>Internal Error, Unable to Register...!!!!!!!!!</h1>");
			e.printStackTrace();
		}
		finally {
			
			try {
				if(pst!=null)
					pst.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			
			try {
				if(con!=null)
					con.close();		//  Releases the Jdbc Conn Obj Back to Jdbc Conn Pool
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			
			pw.println("<h1><a href='register.html'>Home Page</a></h1>");
			
			try {
				if(pw!=null)
					pw.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}// finally
	
	}// doGet
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	// Helper Method to get Pooled Jdbc Connection object from Server
	private Connection getPooledConnection() throws Exception {
		DataSource ds = null;					// it represents Jdbc Conn pool having CONN objects
		Connection con = null;				// it represents Connectivity b/w Java App & Db s/w
		InitialContext context = null; 	// it represents Connectivity b/w Java App & Jndi Register
		
		// create InitialContext object
		context = new InitialContext();
		
		// get Datasource object through lookup() operation Returns Object downcast to Datasource
		ds = (DataSource) context.lookup("java:/DsJndi");	// WILDFLY 

//		ds = (DataSource) context.lookup("DsJndi");		// GLASSFISH
		
		// get Pooled Jdbc Connection object using Datasource
		con = ds.getConnection();
		
		return con;
	}

}//class
