package com.care.mvc.match.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.care.mvc.care.model.vo.Care;
import com.care.mvc.match.model.service.MatchService;
import com.care.mvc.member.model.vo.Member;

@WebServlet("/match/list")
public class MatchListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MatchListServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/views/match/list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] time = request.getParameterValues("time");
		String[] gender = request.getParameterValues("gender");
		String[] qual = request.getParameterValues("qual");
		String[] years = request.getParameterValues("years");
		String[] addr = request.getParameterValues("addr"); // "00시 00구"
		String[] pay = request.getParameterValues("pay");
	
		Map<String, String[]> options = new HashMap<>();
		
		List<Care> profiles = new ArrayList<>();
		
		if (null != time && time.length > 0) {
			options.put("time", time);
		} 
		
		if (null != gender && gender.length > 0) {
			options.put("gender", gender);
		} 
		
		if (null != qual && qual.length > 0) {
			options.put("qual", qual);
		} 

		if (null != years && years.length > 0) {
			options.put("years", years);
		} 
		
		if (null != addr && addr.length > 0) {
			options.put("addr", addr);
		}
		
		if (null != pay && pay.length > 0) {
			options.put("pay", pay);		
		} 
	
		profiles = new MatchService().searchProfiles(options);
				
		request.setAttribute("profiles", profiles);
		request.getRequestDispatcher("/views/match/list.jsp").forward(request, response);
	}

}
