package com.amdocs.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/** if We give request to "/firsturl" => ThirdServlet will be executed with attribute VALUE
 * 
 * 	Here, req.setAttribute is creating in FirstServlet and Forwarding this Data to SecondServlet
 */

@WebServlet("/firsturl")
public class FirstServlet extends HttpServlet{

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter pw = null;
		RequestDispatcher rd = null;
		
		pw = resp.getWriter();
		resp.setContentType("text/html");
		
		// creating the attribute
		req.setAttribute("attribute", "Value");
		pw.println("<h2><b>First Servlet ::  </b></h2>");
		
		// forwarding the request to SecondServlet
		rd = req.getRequestDispatcher("secondurl");
		rd.forward(req, resp);
		
		// close the Stream
		pw.close();

		
	}
	
}
