package com.nt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.dto.EmployeeDTO;
import com.nt.service.IEmployeeService;
import com.nt.service.impl.EmployeeServiceImpl;

//@WebServlet("/register")
//@WebServlet(value = "/register")
@WebServlet(urlPatterns = "/register", name = "MyOwnServlet")
public class EmployeeController extends HttpServlet{

	private IEmployeeService service;
		
	@Override
	public void init() throws ServletException {
		service = new EmployeeServiceImpl();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter pw = null;
		EmployeeDTO dto = null;
		int count = 0;
				
		pw = resp.getWriter();
		resp.setContentType("text/html");
		
		String name = req.getParameter("ename");
		String addr  = req.getParameter("addr");
		
		String doj = req.getParameter("doj");
		Date date = Date.valueOf(doj);
		
		double basSal = Double.parseDouble(req.getParameter("sal"));
		
		dto = new EmployeeDTO();
		dto.setEmpName(name);
		dto.setEmpAddr(addr);
		dto.setDoj(date);
		dto.setBasSal(basSal);
			
		try {
			count = service.insertEmp(dto);
			if(count!=0) {
				pw.println("<h3 style='color:green'>Registration successful</h3>");
			}
			else {
				pw.println("<h3 style='color:red'>Registration failed</h3>");
			}
		} catch (Exception e) {
			e.printStackTrace();
			pw.println("<h3 style='color:green'>Failed to Register......INTERNAL ERROR</h3>");
		}
		finally {
			pw.println("<a href='index.jsp'>HOME</a>");
			if(pw!=null)
				pw.close();
		}
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);		
	}
	
	@Override
	public void destroy() {
		service = null; 		// empty the object
	}
}
