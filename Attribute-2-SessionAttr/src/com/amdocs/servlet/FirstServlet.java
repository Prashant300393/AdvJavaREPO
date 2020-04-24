package com.amdocs.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**	Session Attribute is created one for browser at Server..it is specific to browser irrespective of req,resp  		
 * 		Visible in all web comps of web Application only when these comp get Req from same browser for which
 * 		Session obj and Attribute is created
 * 
 * 		if We give request to "/firsturl" => ThirdServlet will be executed with attribute VALUE
 * 		Here, ses.getSession is created in FirstServlet and Forwarding the Data to SecondServlet
 */

@WebServlet("/firsturl")
public class FirstServlet extends HttpServlet{

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter pw = null;
		RequestDispatcher rd = null;
		HttpSession ses = null;
		
		pw = resp.getWriter();
		resp.setContentType("text/html");
		
		// creating the Session obj
		ses = req.getSession();
//		ses = req.getSession(true);
	
		// Setting the Session Attribute
		ses.setAttribute("username", "Prashant"	);
		ses.setAttribute("pwd", "prashant123");
		
		pw.println("<h2><b>First Servlet :: '"+ses.getCreationTime()+"' </b></h2>");
		pw.println("<h2><b>First Servlet :: '"+ses.getAttribute("username")+"' </b></h2>");
		pw.println("<h2><b>First Servlet :: '"+ses.getId()+"' </b></h2>");
		pw.println("<h2><b>First Servlet :: '"+ses.getLastAccessedTime()+"' </b></h2>");
		
		// forwarding the request to SecondServlet
		rd = req.getRequestDispatcher("secondurl");
		rd.forward(req, resp);
		
		// close the Stream
		pw.close();

		
	}
	
}
