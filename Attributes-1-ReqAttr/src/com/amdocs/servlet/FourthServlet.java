package com.amdocs.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/** if We give request to "/fourthurl" => FourthServlet will be executed with attribute NULL
 * 
 * 	Because, it is not using same Req, Resp object, it is having different req, resp object
 */

@WebServlet("/fourthurl")
public class FourthServlet extends HttpServlet{
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter pw = null;
		
		pw = resp.getWriter();
		resp.setContentType("text/html");
		
		String attr = (String)req.getAttribute("attribute");
		
		pw.println("<h2><b>Fourth Servlet ::  ' "+attr+" '  </b></h2>");
		
		// close the Stream
		pw.close();
		
	}
	
}
