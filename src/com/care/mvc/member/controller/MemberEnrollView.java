package com.care.mvc.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.care.mvc.member.model.service.MemberService;
import com.care.mvc.member.model.vo.Member;


@WebServlet("/member/enrollview")
public class MemberEnrollView extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public MemberEnrollView() {
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
		System.out.println(userId);
		Member member = new MemberService().findMemberById(userId);
		System.out.println(member);
		request.setAttribute("member", member);
		request.getRequestDispatcher("/views/member/enrollview.jsp").forward(request, response);
	}
}
