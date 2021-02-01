package com.care.mvc.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.care.mvc.member.model.service.MemberService;
import com.care.mvc.member.model.vo.Member;


@WebServlet(name="login", urlPatterns="/member/login")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberLoginServlet() {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.getRequestDispatcher("/views/member/login.jsp").forward(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memId = request.getParameter("userId");
		String memPwd = request.getParameter("userPwd");
		String saveId = request.getParameter("saveId"); 			// 아이디 저장 체크박스 부분
		
		Member member = null;
		
		System.out.println("memId : " + memId + ", memPwd : " + memPwd);     // 나중에 savId 추가해야함
		
		member = new MemberService().login(memId, memPwd);
		
		System.out.println(member);
		
		if(saveId != null) {
			Cookie cookie = new Cookie("saveId", memId);
			cookie.setMaxAge(60 * 60 * 24);  // 쿠키 1일 유지
			response.addCookie(cookie);
		} else {
			Cookie cookie = new Cookie("saveId", "");
			
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		
		if(member != null) {
			HttpSession session = request.getSession();
			
			session.setAttribute("loginMember", member);
			
			System.out.println("Session ID : " + session.getId());
			
			response.sendRedirect(request.getContextPath() + "/");
		} else {
			request.setAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
			request.setAttribute("loc", "/member/login");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/common/msg.jsp");
			
			dispatcher.forward(request, response);
			
		}
	}

}