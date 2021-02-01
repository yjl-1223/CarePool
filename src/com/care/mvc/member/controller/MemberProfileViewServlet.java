package com.care.mvc.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.care.mvc.GuardAndPatient.model.service.GuardAndPatientService;
import com.care.mvc.GuardAndPatient.model.vo.Guard;
import com.care.mvc.care.model.service.CareService;
import com.care.mvc.care.model.vo.Care;
import com.care.mvc.member.model.service.MemberService;
import com.care.mvc.member.model.vo.Member;

@WebServlet("/profile/view")
public class MemberProfileViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberProfileViewServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg = "";
		String loc = "";
		String confirm = "";
		String memId = request.getParameter("memId");
		Guard guard = new GuardAndPatientService().checkGuard(memId);
		Member member = new MemberService().findMemberById(memId);
		
		String role = member.getMemRole();
		
		Care care = new CareService().checkCare(memId);
		System.out.println("care : " + care);
		
		Cookie cookie = new Cookie("userId", request.getParameter("userId")); 
		cookie.setMaxAge(60 * 60 * 24);
		response.addCookie(cookie);
		
		if(memId.equals(guard.getMemId())) {
			request.setAttribute("memId", memId);
			request.setAttribute("guard", guard);
			request.getRequestDispatcher("/views/patient/modifyPatProfile.jsp").forward(request, response);
			return;
		}else if(memId.equals(care.getMemId())) {
			request.setAttribute("memId", memId);
			request.setAttribute("care", care);
			request.getRequestDispatcher("/views/care/modifyCareProfile.jsp").forward(request, response);
			return;
		}else{
			confirm = "등록이 되어있지 않습니다. 프로필 등록을 하시겠습니까?";
			request.setAttribute("userId", memId);
			request.setAttribute("role", role); // guardian 혹은 caregiver
			request.setAttribute("confirm", confirm);
			request.getRequestDispatcher("/views/common/confirm.jsp").forward(request, response);
			return;
		}
	}
}
