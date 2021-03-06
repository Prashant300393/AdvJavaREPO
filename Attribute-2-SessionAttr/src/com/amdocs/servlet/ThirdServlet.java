package com.amdocs.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**	if We give request to "/firsturl" => ThirdServlet will be executed with attribute VALUE	
 * 
 * 	Here, Attribute(directly from SecondServlet and indirectly coming from FirstServlet) is reading in ThirdServlet 
 * if we give direct request to "/thirdurl"  it will be executed with NULL value
 */

@WebServlet("/thirdurl")
public class ThirdServlet extends HttpServlet{
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter pw = null;
		HttpSession ses = null;
		String pwd = null;
		
		resp.setContentType("text/html");
		pw = resp.getWriter();
		
		// read current session created in FirstServlet
		ses = req.getSession(false);			// reading existing session
		if(ses!=null) { // if Session Exist
			// reading the session Attribute
			pwd = (String)ses.getAttribute("pwd");
			// modifying the Attribute
			ses.setAttribute(pwd, "pc123456");
		}
		
		// Displaying the value
		pw.println("<h2><b>Third Servlet :: '"+ses.getCreationTime()+"' </b></h2>");
		pw.println("<h2><b>Third Servlet :: '"+ses.getAttribute(pwd)+"' </b></h2>");
		pw.println("<h2><b>Third Servlet :: '"+ses.getId()+"' </b></h2>");
		// Session Max Active Time Period :: defualt is 30 Minutes
		pw.println("<h2><b>Third Servlet :: '"+TimeUnit.SECONDS.toMinutes((ses.getMaxInactiveInterval()))+"' </b></h2>");

		
		// close the Stream
		pw.close();
		
	}
	
	
}
