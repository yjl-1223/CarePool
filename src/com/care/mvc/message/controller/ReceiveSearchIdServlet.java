package com.care.mvc.message.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.care.mvc.common.util.PageInfo;
import com.care.mvc.member.model.vo.Member;
import com.care.mvc.message.model.service.MessageService;
import com.care.mvc.message.model.vo.ReceiveMessage;

@WebServlet("/search/recId")
public class ReceiveSearchIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReceiveSearchIdServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg = "";
		String loc = "";
		int page = 0;
		int listCount = 0;
		PageInfo info = null;
		ArrayList<ReceiveMessage> list = null;
		String Id = request.getParameter("Id");
		System.out.println(Id);
		HttpSession session = request.getSession(false);
		Member loginMember = session != null ? (Member)session.getAttribute("loginMember") : null; 
		
		try {
			page = Integer.parseInt(request.getParameter("rec_page"));
			System.out.println(page);
			
		}catch(NumberFormatException e) {
			page = 1;
		}
		
		listCount = new MessageService().getMsgList(loginMember, Id);
		info = new PageInfo(page, 10, listCount, 10);
		list = new MessageService().RecSearchMsg(info, Id);
		
		request.setAttribute("list", list);
		request.setAttribute("pageInfo", info);
		request.getRequestDispatcher("/views/message/receive_searchId.jsp").forward(request, response);
		
	}

}
