package com.care.mvc.message.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.care.mvc.message.model.service.MessageService;
import com.care.mvc.message.model.vo.SendMessage;
import com.care.mvc.message.model.vo.SendMessageImg;

@WebServlet("/sendMsg/details")
public class SendMessageDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SendMessageDetailsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int sendNo = Integer.parseInt(request.getParameter("send_no"));
		
		SendMessage sendmessage = new MessageService().SendDetails(sendNo);
		SendMessageImg imgS = new MessageService().SendDetailsImgName(sendNo);
		
		request.setAttribute("sendmessage", sendmessage);
		request.setAttribute("imgS", imgS);
		
		request.getRequestDispatcher("/views/message/send_msg_detail.jsp").forward(request, response);
	}
}
