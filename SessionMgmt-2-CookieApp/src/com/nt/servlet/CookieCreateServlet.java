package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/firsturl")
public class CookieCreateServlet extends HttpServlet{
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter pw = null;
		String pname,fname, gender = null;
		Cookie ck1, ck2, ck3 = null;
		
		resp.setContentType("text/html");
		pw = resp.getWriter();
		
		// read form Data
		pname = req.getParameter("pname");
		fname = req.getParameter("fname");
		gender = req.getParameter("gender");
		
		// create Cookie
		ck1 = new Cookie("pname", pname);
		ck2 = new Cookie("fname", fname);
		ck3 = new Cookie("gender", gender);
		
		resp.addCookie(ck1);
		resp.addCookie(ck2);
		resp.addCookie(ck3);
		
		pw.println("<h2 style='color:red;text-align=center'>Income Tax Details </h2><br>");
		pw.println("<form action='secondurl' method='post'>");
		pw.println("<table bgcolor='orange' align='center'>");
		pw.println("<tr><td>Income of the Year</td><td><input type='text' name='income'</td></tr>");
		pw.println("<tr><td>Tax </td><td><input type='text' name='tax'</td></tr>");
		pw.println("<tr><td></td><td><input type='submit' value='Submit'</td></tr>");
		pw.println("</table></form>");
		
		pw.close();
	}
}
