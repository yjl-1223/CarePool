package com.care.mvc.message.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.care.mvc.message.model.service.MessageService;
import com.care.mvc.message.model.vo.ReceiveMessage;
import com.care.mvc.message.model.vo.ReceiveMessageImg;

@WebServlet("/recMsg/details")
public class ReceiveMessageDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReceiveMessageDetailsServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int recNo = Integer.parseInt(request.getParameter("rec_no"));
		
		ReceiveMessage recmessage = new MessageService().RecDetails(recNo);
		ReceiveMessageImg imgR = new MessageService().RecDetailsImgName(recNo);
		
		request.setAttribute("recmessage", recmessage);
		request.setAttribute("imgR", imgR);
		
		request.getRequestDispatcher("/views/message/rec_msg_detail.jsp").forward(request, response);
	}
}




