package com.amdocs.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		pw = resp.getWriter();
		resp.setContentType("text/html");
		
		// reading the request Attribute
		String attr = (String)req.getAttribute("attribute");
		
		// Displaying the value
		pw.println("<h2><b>Second Servlet attribute = ' "+attr+" '  </b></h2>");
		
		// forwarding the request to ThirdServlet
		rd = req.getRequestDispatcher("thirdurl");
		rd.forward(req, resp);
		
		// close the Stream
		pw.close();

	}
	
	
}
