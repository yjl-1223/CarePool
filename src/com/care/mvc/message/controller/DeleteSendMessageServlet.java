package com.care.mvc.message.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.care.mvc.message.model.service.MessageService;

@WebServlet("/delete/send")
public class DeleteSendMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteSendMessageServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg ="";
		String loc ="";
		int sendNum = Integer.parseInt(request.getParameter("SendNum"));
		System.out.println(request.getParameter("SendNum"));
		int resultS = new MessageService().deleteSMsg(sendNum);
		
		if(resultS > 0) {
			msg="삭제가 성공하였습니다.";
			loc="/msg/send";
		} else {
			msg="삭제 실패!!";
			loc="/msg/send";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}
}
