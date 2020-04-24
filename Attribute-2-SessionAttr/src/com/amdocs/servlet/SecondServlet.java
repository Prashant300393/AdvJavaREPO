package com.amdocs.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**	if We give request to "/secondurl" => ThirdServlet will be executed with attribute NULL
 * 
 * 	Here, Attribute(coming from FirstServlet) is reading in SecondServlet and Forwarding it to ThirdServlet
 * if we give direct REQUEST to "/secondurl" ThirdServlet will be Executed and the value of the Attribute is NULL
 */

@WebServlet("/secondurl")
public class SecondServlet extends HttpServlet{

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PrintWriter pw = null;
		RequestDispatcher rd = null;
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
			ses.setAttribute(pwd, "pc123");
		}

		// Displaying the value
		pw.println("<h2><b>Second Servlet :: '"+ses.getCreationTime()+"' </b></h2>");
		pw.println("<h2><b>Second Servlet :: '"+ses.getAttribute(pwd)+"' </b></h2>");
		pw.println("<h2><b>Second Servlet :: '"+ses.getId()+"' </b></h2>");
		// Session Max Active Time Period :: defualt is 30 Minutes
		pw.println("<h2><b>Third Servlet :: '"+TimeUnit.SECONDS.toMinutes((ses.getMaxInactiveInterval()))+"' </b></h2>");


		// forwarding the request to ThirdServlet
		rd = req.getRequestDispatcher("thirdurl");
		rd.forward(req, resp);

		// close the Stream
		pw.close();

	}


}
