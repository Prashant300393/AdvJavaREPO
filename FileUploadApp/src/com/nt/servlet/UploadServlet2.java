package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javazoom.upload.MultipartFormDataRequest;
import javazoom.upload.UploadBean;
import javazoom.upload.UploadFile;
import javazoom.upload.UploadParameters;

@WebServlet("/upload")
public class UploadServlet2 extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter pw = null;
		MultipartFormDataRequest mreq = null; // MultipartFormDataReq obj stores both Text & Binary DATA(Uploaded file)
		UploadBean bean = null;							 //  Main class to Upload FILE and specify FILE Details
		Hashtable ht = null;				// Here, to get the FileInfo, using getHistory() method which Returns "Vector"
		
		// get PrintWriter object
		pw = resp.getWriter();
		//set content type
		resp.setContentType("text/html");
		try 
		{
			// create MultiPartFormDataRequest obj which Holds both Text and Binary DATA
			mreq = new MultipartFormDataRequest(req);	 
			
			// create UploadBean Obj to Perform  "FileUploading", specify Destination folder, size, type and overriding restrictions
			bean = new UploadBean();
			
			bean.setOverwrite(false);		// allow the copy of file with same name
			bean.setFolderstore("C:/Users/Prashant/Desktop/images");	// Destination folder
			bean.setMaxfiles(100);																			// Max files Upload Limit
			bean.setFilesizelimit(500*1024);														// set FileSize 
			
			if(bean.isBlacklistEnabled()) {
				bean.setBlacklist("*.zip, *.png, *.exe");									// BlackListing the Files from Upload
			}
			System.out.println(bean.isBlacklistEnabled());
			
			// Upload the file to Store(SERVER MACHINE [ i.e Database, server could be anything ]
			bean.store(mreq);
			pw.println("<h2 style='color:red;text-align:center'>From Servlet 2</h2>");
			pw.println("<h2 style='color:green;text-align:center'>File Uploaded Successfully</h2>");
			
			// Display the uploaded File Names Using HASHTABLE and ENUMERATION
			ht = mreq.getFiles();
			Enumeration enm = ht.elements();
			
			while (enm.hasMoreElements()) {
				UploadFile file= (UploadFile)enm.nextElement();
				pw.println("<h3 style='color:blue;text-align:center'>File name: "+file.getFileName()+ " Content Type: "+file.getContentType()+" 	File size: "+file.getFileSize()+ "</h3><br>");
			}// while
			
		} //try 
		
		catch (Exception e) {
			pw.println("<h2 style='color:red;text-align:center'>Problem in Uploading File "+e.getMessage()+"</h2>");
			e.printStackTrace();
		}
		finally {
			pw.println("<a href='index.html'>Home Page</a>");
			if(pw!=null)
				pw.close();
		}
		
	}// doPost

}// servlet
