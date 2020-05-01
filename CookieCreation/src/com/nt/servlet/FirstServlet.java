package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/createurl")
public class FirstServlet extends HttpServlet{
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter pw = null;
		Cookie ck1,ck2,ck3,ck4=null;
		
		resp.setContentType("text/html");
		pw = resp.getWriter();
		
		// create Cookies (InMemory Cookies)
		ck1 = new Cookie("username", "Prashant");
		ck2 = new Cookie("password", "prashu123");
		// add the Cookies to the Response object
		resp.addCookie(ck1);
		resp.addCookie(ck2);
		
		// create Cookies (Persistent Cookies)
		ck3 = new Cookie("Location", "Pune");
		ck4 = new Cookie("Lang", "Marathi");
		// set MaxAge for the Cookies (Persistent Cookies)
		ck3.setMaxAge(60);
		ck4.setMaxAge(50);
		// add the Cookies to the Response object
		resp.addCookie(ck3);
		resp.addCookie(ck4);
		
		pw.println("Cookies created and Added to Response");
	}
}
