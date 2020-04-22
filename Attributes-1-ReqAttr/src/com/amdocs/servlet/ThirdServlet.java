package com.amdocs.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		pw = resp.getWriter();
		resp.setContentType("text/html");
		
		// Read the request Attribute 
		String attr = (String)req.getAttribute("attribute");
		
		// Display the Value to the Page
		pw.println("<h2><b>Third Servlet attribute = ' "+attr+" '  </b></h2>");
		
		// close the Stream
		pw.close();
		
	}
	
	
}
