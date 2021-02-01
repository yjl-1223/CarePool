package com.care.mvc.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.care.mvc.member.model.service.MemberService;
import com.care.mvc.member.model.vo.Member;


@WebServlet("/member/mypage")
public class MemberMypageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public MemberMypageServlet() {
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		
		Member member = new MemberService().findMemberById(userId);
		
		request.setAttribute("member", member);
		request.getRequestDispatcher("/views/member/mypage.jsp").forward(request, response);
	  
	}
}
