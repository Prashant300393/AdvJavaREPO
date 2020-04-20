package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 		This is the Source Servlet we call RequestDispatcher method rd.include() from this servlet having Dest Servlet req,resp
 */

@WebServlet("/stategsturl")
public class StateGstServlet extends HttpServlet{

	@Override
	public  void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter pw = null;
		String name = null, cname = null, ptype = null;
		Double cost = null, sgst = null;
		ServletContext context1 = null, context2 = null;
		RequestDispatcher rd = null;
		
		pw = resp.getWriter();
		resp.setContentType("text/html");
		// Read the Form Data
		name = req.getParameter("pname");
		cname = req.getParameter("cname");
		ptype = req.getParameter("type");
		cost = Double.parseDouble(req.getParameter("cost"));
		
		
		// Calculate the SGST
		if(ptype.equalsIgnoreCase("Product"))
			sgst = cost * 0.12;
		else if(ptype.equalsIgnoreCase("Service"))
			sgst = cost * 0.1;
		else if(ptype.equalsIgnoreCase("Start-Up"))
			sgst = cost * 0.03;

		// Display The Form Data
		pw.println("<b style='color:blue;text-align:center'>Gst Info</b><br>");
		pw.println("<b>Project Name : "+name+"</b><br>");
		pw.println("<b>Comp Name : "+cname+"</b><br>");
		pw.println("<b>Project Type : "+ptype+"</b><br>");
		pw.println("<b>Project Cost : "+cost+"</b><br>");
		pw.println("<b><i>State GST: "+sgst+ "</i></b><br>");
		
		// Communication with Destination Servlet "ITCentralGstApp" using Cross Context Communication
		//  getServletContext() obj of Current App
		context1 = getServletContext();
		
		// get Foreign Context obje of "ITCentralGstApp"
		context2 = context1.getContext("/ITCentralGstApp");
		
		// get RequestDispatcher obj Pointing to "CentalGstServlet" of "ITCentralGstApp" 
		rd = context2.getRequestDispatcher("/centralgsturl");
		rd.include(req, resp);
		
		// Add Hyperlink to Home
		pw.println("<a href='index.html'>Home</a>");
		
		pw.close();
	
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	
}
