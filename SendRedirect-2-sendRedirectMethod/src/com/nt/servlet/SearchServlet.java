package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/searchurl")
public class SearchServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter pw = null;
		String url = null;
		// get PrintWriter obj
		pw = resp.getWriter();
		//set content type
		resp.setContentType("text/html");
		
		// read form Data
		String val = req.getParameter("val");
		String engine = req.getParameter("engine");
		
		if(engine.equalsIgnoreCase("google"))
			url = "https://www.google.com/search?q="+val;
		else if(engine.equalsIgnoreCase("bing"))
			url = "https://www.bing.com/search?q="+val;
		else if(engine.equalsIgnoreCase("yahoo"))
			url = "https://search.yahoo.com/search?p"+val;
			
		// perform SendRedirection
		System.out.println("before sendRedirect() method"); // console output wont be Discarded
		pw.println("<b> Before sendRedirect() method </b>"); // HTML output will be Discarded
		
		resp.sendRedirect(url);
		
		System.out.println("after sendRedirect() method");
		pw.println("<b> After sendRedirect() method </b>"); // HTML output will be Discarded

		// close the stream 
		pw.close();
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}

