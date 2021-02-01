package com.care.mvc.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.care.mvc.GuardAndPatient.model.service.GuardAndPatientService;
import com.care.mvc.GuardAndPatient.model.vo.Guard;
import com.care.mvc.GuardAndPatient.model.vo.Patient;
import com.care.mvc.care.model.service.CareService;
import com.care.mvc.care.model.vo.Care;
import com.care.mvc.care.model.vo.PatientWanted;
import com.care.mvc.member.model.service.MemberService;
import com.care.mvc.member.model.vo.Member;

@WebServlet("/check/profile")
public class CheckProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckProfileServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memId = request.getParameter("memId");
		Member member = new MemberService().findMemberById(memId);
		Guard guard = new GuardAndPatientService().checkGuard(memId);
		Patient patient = new GuardAndPatientService().checkPatient(memId);
		Care care = new CareService().checkCare(memId);
		PatientWanted patWanted = new CareService().checkPatWanted(memId); 
		
		if(guard.getMemId() != null && patient.getGuard_no() != 0) {
			request.setAttribute("guard", guard);
			request.setAttribute("patient", patient);
			request.getRequestDispatcher("/views/patient/patprofilecheck.jsp").forward(request, response);
			return;
		}else if(care.getCareNo() != 0 && care.getCareGen() != null) {
			request.setAttribute("care", care);
			request.setAttribute("patWanted", patWanted);
			request.getRequestDispatcher("/views/care/careprofilecheck.jsp").forward(request, response);
			return;
		}else {
			request.setAttribute("member", member);
			request.getRequestDispatcher("/views/member/membercheck.jsp").forward(request, response);
			return;
		}
	}
}
