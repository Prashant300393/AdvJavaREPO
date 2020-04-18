package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/centralgsturl")
public class CentralGstServlet extends HttpServlet{

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		PrintWriter pw = null;
		String ptype = null;
		Double cost = null, cgst = null;
		
		
		pw = resp.getWriter();
		resp.setContentType("text/html");
		
		ptype = req.getParameter("type");
		cost = Double.parseDouble(req.getParameter("cost"));
		
		// Calculate the SGST
		if(ptype.equalsIgnoreCase("Product"))
			cgst = cost * 0.18;
		else if(ptype.equalsIgnoreCase("Service"))
			cgst = cost * 0.12;
		else if(ptype.equalsIgnoreCase("Start-Up"))
			cgst = cost * 0.8;

		pw.println("<b><i>Central GST : " +cgst+"</i></b><br>");
		
		// dont close the stream, it will commit the Response 
		
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
