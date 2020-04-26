package com.amdocs.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/firsturl")
public class FirstServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter pw = null;
		
		// set Content-Type
		resp.setContentType("text/html");
		// get PrintWriter obj
		pw = resp.getWriter();
		
		// read form data
		String name = req.getParameter("name");
		String fname = req.getParameter("fname");
		String ms = req.getParameter("ms");
		
		// If Single show another Form Page, if Married Show Another 
		if(ms.equalsIgnoreCase("single")) {
			
			pw.println("<h2 style='color:blue;text-align:center'>Provide Bachelor Life Details</h2><br><br>");
			pw.println("<form action='secondurl' method='post'>");
			pw.println("<table bgcolor='pink' align='center'>");
			pw.println("<tr><td>When you will Marry</td><td><input type='text' name='f2t1'></td></tr>");
			pw.println("<tr><td>Why you want to Marry</td><td><input type='text' name='f2t2'></td></tr>");
			pw.println("<input type='hidden' name='name' value='"+name+"'>");
			pw.println("<input type='hidden' name='fname' value='"+fname+"'>");
			pw.println("<input type='hidden' name='ms' value='"+ms+"'>");
			pw.println("<tr><td colspan='2'><input type='submit' value='Submit'></td></tr>");
			pw.println("</table>");
			pw.println("</form>");
		}
		else if (ms.equalsIgnoreCase("married")) {
			
			pw.println("<h2 style='color:blue;text-align:center'>Provide Married Life Details</h2><br><br>");
			pw.println("<form action='secondurl' method='post'>");
			pw.println("<table bgcolor='orange' align='center'>");
			pw.println("<tr><td>Spouse Name</td><td><input type='text' name='f2t1'></td></tr>");
			pw.println("<tr><td>No of Kids</td><td><input type='text' name='f2t2'></td></tr>");
			pw.println("<input type='hidden' name='name' value='"+name+"'>");
			pw.println("<input type='hidden' name='fname' value='"+fname+"'>");
			pw.println("<input type='hidden' name='ms' value='"+ms+"'>");
			pw.println("<tr><td colspan='2'><input type='submit' value='Submit'></td></tr>");
			pw.println("</table>");
			pw.println("</form>");
		}
					
	}
	
}
