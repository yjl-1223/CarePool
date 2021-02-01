package com.care.mvc.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.care.mvc.member.model.service.MemberService;
import com.care.mvc.member.model.vo.Member;

@WebServlet(name = "enroll", urlPatterns = "/member/enroll")
public class MemberEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberEnrollServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		request.getRequestDispatcher("/views/member/enroll.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String msg = "";
		String location = "";
		String confirm = "";
		Member member = new Member();
		String role = request.getParameter("role");
		String userId = request.getParameter("userId");
		System.out.println(role);
		
		// 회원가입 맨 밑에 선택하는 부분 (선택 안하고 눌러도 작동하는 것을 이걸 통해서 방지, 메세지 띄우고, 다시 창으로 돌아가게 만듬)
		if (role == null) {
			msg = "보호자/보호사를 선택해주세요";
			location = "/member/enroll";
			request.setAttribute("msg", msg);
			request.setAttribute("loc", location);
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			return;
		};
		
		member.setMemId(request.getParameter("userId"));
		member.setMemRole(request.getParameter("role"));
		member.setMemName(request.getParameter("userName"));
		member.setMemPwd(request.getParameter("userPwd"));
		member.setMemEmail(request.getParameter("email"));
		member.setMemPhone(request.getParameter("phone"));
		member.setMemAddr(request.getParameter("addr1") + " , " + request.getParameter("addr2") + " , "+ request.getParameter("addr3")); // 지역주소 + 상세주소
		member.setMemBirth(request.getParameter("birth"));
		
		int result = new MemberService().enrollMember(member);
		

		String addr1 = member.getMemAddr();
		
		String[] arr = addr1.split(",");
		
		System.out.println(arr[0]);

		if (result > 0) {
			confirm = "회원가입 성공!! 프로필 등록을 하시겠습니까?";
			request.setAttribute("userId", userId);
			request.setAttribute("role", role); // guardian 혹은 caregiver
			request.setAttribute("confirm", confirm);
			request.getRequestDispatcher("/views/common/confirm.jsp").forward(request, response);
			return;
		} else {
			msg = "회원가입 실패";
			location = "/member/enroll";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", location);

		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

}
