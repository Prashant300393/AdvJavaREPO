package com.amdocs.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet{

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter pw = null;
		ServletContext context = null;
		pw = resp.getWriter();
		resp.setContentType("text/html");
		
		
		// get Access to ServletContext object
		context = getServletContext();
		pw.println("<br><b>Db Context param value : </b>"+context.getInitParameter("user"));

		pw.println("<br><br><h1>Details of Servlet using Context Object</h1>");
		pw.println("<br>Server info:	"+ context.getServerInfo());
		pw.println("<br>Servlet Context Name:	"+ context.getServletContextName());
		pw.println("<br>Mime Type:	"+ context.getMimeType("input.html"));
		pw.println("<br>Path of input.html:	"+ context.getRealPath("/input.html"));
		pw.println("<br>Path of web root folder:	"+ context.getRealPath("/"));
		pw.println("<br>Context Path of web App"+ context.getContextPath());
		pw.println("<br>Servlet API version"+ context.getMajorVersion()+"--"+context.getMinorVersion());
	
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
