package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/showurl")
public class SecondServlet extends HttpServlet{

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PrintWriter pw = null;
		Cookie cookie[ ] = null;

		resp.setContentType("text/html");
		pw = resp.getWriter();

		cookie= req.getCookies();

		if(cookie!=null) {
			pw.println("<h2 style='color:red;text-align=center'>Displaying Cookies</h2>");
			pw.println("<table bgcolor='cyan' border='1'>");
			pw.println("<tr><th>Cookie Name</th><th>Cookie value</th></tr>");
			for(Cookie ck : cookie) {
				pw.println("<tr><th>"+ck.getName()+"</th><th>"+ck.getValue()+"</th></tr>");
			}
			pw.println("</table>");
		}// if

	}// get
}//class
