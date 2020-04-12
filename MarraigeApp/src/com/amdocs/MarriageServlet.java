package com.amdocs;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MarriageServlet extends HttpServlet {

	PrintWriter pw = null;
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		pw = response.getWriter();
		
		response.setContentType("text/html");
		
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String gender = request.getParameter("gender");
		
		if(age>=21 && gender.equalsIgnoreCase("M"))
			pw.print("<h3 style='color:red'>Mr. "+name+", You are Eligible for Marriage at Your Own Risk</h3>");
		else if (age>=18 && gender.equalsIgnoreCase("F"))
			pw.print("<h2 style='color:red'>Miss. "+name+", You are Eligible for Marriage At your own Risk</h2> ");
		else
			pw.print("<h3 style='color:red'>"+name+" YOU ARE NOT ELIGIBLE FOR MARRIAGE,</h3>");
	
		pw.close();
	}

}
