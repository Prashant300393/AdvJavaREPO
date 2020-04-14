package com.nt.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

@WebServlet("/downloadurl")
public class DownloadServlet extends HttpServlet {

	public  void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fileName = null;
		String filePath  = null;;
		File downloadFile = null;
		ServletContext context = null;
		String mimeType  = null;
		InputStream inputStream = null;
		ServletOutputStream outputStream = null;
		
		
		// reads the File Name to be downloaded from the HyperLink as Addidtional Param Value
		fileName = req.getParameter("filename");
		// get the location of File from Server machine file system you want to download
		filePath = "C:/Users/Prashant/Desktop/images/";

		// create File object Locating and Holding the File to be downloaded
		downloadFile = new File(filePath+fileName);

		// Obtains ServletContext object
		context = getServletContext();

		// gets the MIME type of the File
		mimeType = context.getMimeType(filePath+fileName);		
//		if (mimeType == null) {        
//			// set to binary type if MIME mapping not found
//			mimeType = "application/octet-stream";
//		}
		resp.setContentType(mimeType!=null?mimeType:"application/octet-stream"); // modifies response

		// forces download
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment;filename=%s", downloadFile.getName());
		resp.setHeader(headerKey, headerValue);
		
		// create InputStram pointing to File to be Downloaded
		inputStream = new FileInputStream(downloadFile);

		/**
		 *  	Create ServletOutputStream pointing to Response object 
		 * 		ServletOutputStream can write both Text and Binary data to Response object
		 * 		Where PrintWriter can only write Text Data to response object
		 */
		outputStream = resp.getOutputStream();
		
		// copy the File Content to response object using IOUtils.copy(is, os) method of commons-io.jar
		IOUtils.copy(inputStream, outputStream);
		
		// close streams
		outputStream.close();
		inputStream.close();

	}


	public  void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
