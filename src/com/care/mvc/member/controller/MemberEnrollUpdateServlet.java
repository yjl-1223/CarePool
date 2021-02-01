package com.care.mvc.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.care.mvc.member.model.service.MemberService;
import com.care.mvc.member.model.vo.Member;


@WebServlet("/member/enrollupdate")
public class MemberEnrollUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public MemberEnrollUpdateServlet() {
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg = "";
		String location = "";
		Member member = new Member();
		int result = 0;
		
		member.setMemId(request.getParameter("userId"));
		member.setMemRole(request.getParameter("role"));
		member.setMemEmail(request.getParameter("email"));
		member.setMemPhone(request.getParameter("phone"));
		member.setMemAddr(request.getParameter("addr1") + " , " + request.getParameter("addr2") + " , " + request.getParameter("addr3")); // 지역주소 + 상세주소
		member.setMemBirth(request.getParameter("birth"));
		
		System.out.println(member);

		result = new MemberService().enrollupdateMember(member);
		
		if(result > 0) {
			msg = "성공적으로 수정되었습니다.";
		} else {
			msg = "회원정보 수정에 실패했습니다.";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", location);
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}
}
