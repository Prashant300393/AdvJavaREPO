package com.amdocs.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/errorurl", name = "err")
public class ErrorServlet extends HttpServlet{

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter pw = null;
		RequestDispatcher rd , rd1 = null;
		
		pw = resp.getWriter();
		resp.setContentType("text/html");
		
//		rd = req.getRequestDispatcher("/headerurl");
		rd = req.getRequestDispatcher("headerurl");	 // HeaderServlet called
		rd.include(req, resp);
		
		pw.println("<h1 style='color:red;text-align:center'>Internal Db Problem - Try Again</h1>");
		
		rd1 = req.getRequestDispatcher("footer.html");	 // FooterHtml called
		rd1.include(req, resp);
		
		pw.close();
		
	}
		
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
