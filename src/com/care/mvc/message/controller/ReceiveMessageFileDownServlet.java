package com.care.mvc.message.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/receiveFile/filedown")
public class ReceiveMessageFileDownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReceiveMessageFileDownServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파일 다운로드
			String nameori = request.getParameter("nameori");
			String namesav = request.getParameter("namesav");
			
			System.out.println("nameori : " + nameori + ", namesav : " + namesav);
			
//			 1. 전송할 파일에 대한 경로와 파일명을 가져온다.
			String path = getServletContext().getRealPath("/upload/msgimg");
			String file = path + "/" + namesav;
					
			File downFile = new File(file);
			
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(downFile));
			
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
			
			String downName = null;
			String header = request.getHeader("user-agent");
			
			boolean isMISE = header.indexOf("MSIE") != -1 || header.indexOf("Trident") != -1;
			
			System.out.println(header);
			
			if(isMISE) {
				downName = URLEncoder.encode(nameori, "UTF-8").replaceAll("\\+", "%20");
				
			} else {
				downName = new String(namesav.getBytes("UTF-8"), "ISO-8859-1");
			}
			
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + downName);
			
			int read = -1;
			
			while ((read = bis.read()) != -1) {
				bos.write(read);
		}
			bos.close();
			bis.close();
		}

}
