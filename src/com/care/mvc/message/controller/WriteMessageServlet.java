package com.care.mvc.message.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.care.mvc.member.model.vo.Member;
import com.care.mvc.message.model.service.MessageService;
import com.care.mvc.message.model.vo.ReceiveMessage;
import com.care.mvc.message.model.vo.ReceiveMessageImg;
import com.care.mvc.message.model.vo.SendMessage;
import com.care.mvc.message.model.vo.SendMessageImg;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/msg/write")
public class WriteMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public WriteMessageServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String msg = "";
		String loc = "";
		HttpSession session = request.getSession(false);
		Member loginMember = (Member) session.getAttribute("loginMember");
		
		// 매칭검색 결과에서 쪽지보낼때 자동으로 아이디 띄워준다.
		String sendTo = request.getParameter("memId");
		
		System.out.println(sendTo);
		
		if (loginMember != null) {
			request.setAttribute("sendTo", sendTo);
			request.getRequestDispatcher("/views/message/write_message.jsp").forward(request, response);
		} else {
			msg = "로그인이 필요한 페이지입니다.";
			loc = "/member/login";
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);

			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파일 관련 ---------------------------------------------------
			String path = getServletContext().getRealPath("upload/msgimg");
			
			int maxSize = 1024 * 1024 * 20;  // 20mb
			
			String encoding = "UTF-8";
			MultipartRequest mr = new MultipartRequest(request, path, maxSize, encoding, new DefaultFileRenamePolicy());
			
			String fileName = mr.getFilesystemName("messageimg");       // 실제 이름(확인할려면 write_message.jsp 가야함)
			String upfileName = mr.getOriginalFileName("messageimg");
			
//			int sendNo = mr.getParameter("")
			String url = mr.getParameter(path);
			System.out.println(url);
			
			// 쪽지 사진 보내기
			SendMessageImg smi = new SendMessageImg();
			SendMessage sm = new SendMessage();
			
			smi.setSend_img_path(path);
			smi.setSend_img_name_org(fileName);
			smi.setSend_img_name_sav(upfileName);
			smi.setSend_no(sm.getSend_no());
			
			System.out.println(smi.getSend_img_no());
			System.out.println(sm.getSend_no());
			
			int resultSI = new MessageService().sendImage(smi, sm);
			
			// 쪽지 사진 받기
			ReceiveMessageImg rmi = new ReceiveMessageImg();
			ReceiveMessage rm = new ReceiveMessage();
			
			rmi.setRec_img_path(path);
			rmi.setRec_img_name_org(fileName);
			rmi.setRec_img_name_sav(upfileName);
			rmi.setRec_no(rm.getRec_no());
			System.out.println(rmi);
			int resultRI = new MessageService().receiveImage(rmi, rm);
		
			// 아래는 쪽지 관련
			String msg = "";
			String loc = "";
			SendMessage sendM = new SendMessage();
			HttpSession session = request.getSession(false);
			Member loginMember = (Member)session.getAttribute("loginMember");
		
			int resultS = 0;
			// 메세지 보내기
			sendM.setRec_id(mr.getParameter("rev_id"));
			sendM.setSend_body(mr.getParameter("msg_contents"));
			sendM.setMem_id(loginMember.getMemId());
			
			resultS = new MessageService().sendMsg(sendM);
			
			//-----------------------------------------------------------------
			// 메세지 받기
			int resultR = 0;
			ReceiveMessage recM = new ReceiveMessage();
			
			recM.setSend_id(mr.getParameter("rev_id"));
			recM.setRec_body(mr.getParameter("msg_contents"));
			recM.setMem_id(loginMember.getMemId());
			
			resultR = new MessageService().recMsg(recM);
			
			if(resultS > 0 && resultR > 0 && resultSI > 0 && resultRI > 0) {
				msg = "메세지를 성공적으로 보냈습니다.";
				loc = "/msg/write";
			}else {
				msg = "메세지 보내기 실패(잘못된 아이디 입니다)!";
				loc = "/msg/write";
			}
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		return;
	}
}
