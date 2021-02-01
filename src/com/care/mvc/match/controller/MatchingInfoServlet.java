package com.care.mvc.match.controller;

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

@WebServlet("/match/info")
public class MatchingInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MatchingInfoServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String memId = request.getParameter("memId");
		
		Guard guard = new GuardAndPatientService().checkGuard(memId);
		Patient patient = new GuardAndPatientService().checkPatient(memId);
		
		if(guard.getMemId() != null && patient.getGuard_no() != 0) {
			request.setAttribute("guard", guard);
			request.setAttribute("patient", patient);
			request.getRequestDispatcher("/views/match/guardianMatch.jsp").forward(request, response);
			return;
		}else {
			Care care = new CareService().checkCare(memId);
			PatientWanted patWanted = new CareService().checkPatWanted(memId); 
			
			request.setAttribute("care", care);
			request.setAttribute("patWanted", patWanted);
			request.getRequestDispatcher("/views/match/caregiverMatch.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
	}

}
