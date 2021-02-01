package com.care.mvc.care.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/careIntro")
public class CareIntroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CareIntroServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.getRequestDispatcher("/views/care/careintro.jsp").forward(request, response);
	
	}
}
