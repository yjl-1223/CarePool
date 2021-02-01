package com.care.mvc.message.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.care.mvc.common.util.PageInfo;
import com.care.mvc.member.model.vo.Member;
import com.care.mvc.message.model.service.MessageService;
import com.care.mvc.message.model.vo.SendMessage;

@WebServlet("/search/sendId")
public class SendSearchIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SendSearchIdServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg = "";
		String loc = "";
		int page = 0;
		int listCount = 0;
		PageInfo info = null;
		ArrayList<SendMessage> list = null;
		String Id = request.getParameter("Id");
		HttpSession session = request.getSession(false);
		Member loginMember = session != null ? (Member)session.getAttribute("loginMember") : null; 
		try {
			page = Integer.parseInt(request.getParameter("send_page"));
			System.out.println(page);
			
		}catch(NumberFormatException e) {
			page = 1;
		}
		
		// 나중에 파라미터(검색조건) 이 더 많아지면 맵에다가 담아서 보내자..
//		Map<String,Object> searchParam = new HashMap<String,Object>();
//		searchParam.put("rec_id", Id);
//		searchParam.put("mem_id", loginMember.getMemId());
//		searchParam.put("..1", "...");
//		searchParam.put("..2", "...");
//		searchParam.put("..3", "...");
//		listCount = new MessageService().sendMsgList(searchParam);
		listCount = new MessageService().sendMsgList(loginMember, Id);
		
		info = new PageInfo(page, 10, listCount, 10);
		list = new MessageService().searchSendId(Id, info);
		
		request.setAttribute("list", list);
		request.setAttribute("pageInfo", info);
		request.getRequestDispatcher("/views/message/send_searchId.jsp").forward(request, response);
	}
}
