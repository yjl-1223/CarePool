package com.care.mvc.message.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.care.mvc.message.model.service.MessageService;

@WebServlet("/delete/rec")
public class DeleteReceiveMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteReceiveMessageServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg ="";
		String loc ="";
		System.out.println(request.getParameter("recNum"));
		int recNum = Integer.parseInt(request.getParameter("recNum"));
		
		int resultR = new MessageService().deleteRMsg(recNum);
		
		if(resultR > 0) {
			msg="삭제가 성공하였습니다.";
			loc="/msg/get";
		} else {
			msg="삭제 실패!!";
			loc="/msg/get";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}
}
