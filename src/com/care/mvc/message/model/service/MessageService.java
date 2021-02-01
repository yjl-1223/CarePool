package com.care.mvc.message.model.service;

import static com.care.mvc.common.jdbc.JDBCTemplate.commit;
import static com.care.mvc.common.jdbc.JDBCTemplate.getConnection;
import static com.care.mvc.common.jdbc.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.care.mvc.common.jdbc.JDBCTemplate;
import com.care.mvc.common.util.PageInfo;
import com.care.mvc.member.model.vo.Member;
import com.care.mvc.message.model.dao.MessageDao;
import com.care.mvc.message.model.vo.ReceiveMessage;
import com.care.mvc.message.model.vo.ReceiveMessageImg;
import com.care.mvc.message.model.vo.SendMessage;
import com.care.mvc.message.model.vo.SendMessageImg;

public class MessageService {

	public ArrayList<ReceiveMessage> RevListmsg(PageInfo info, Member loginMember) {
		
		Connection conn = getConnection();
		
		ArrayList<ReceiveMessage> list = new MessageDao().listRevMsg(conn, info, loginMember);
		
		for(ReceiveMessage msg : list) {
			int no = msg.getRec_no(); 
				msg.setImgs(new MessageDao().listRevMsgImg(conn, no));
		}
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	public ArrayList<ReceiveMessageImg> RevListmsgImg(int no) {
		Connection conn = getConnection();
		
		ArrayList<ReceiveMessageImg> list = new MessageDao().listRevMsgImg(conn, no);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	public ArrayList<SendMessage> SendListmsg(PageInfo info, Member loginMember) {
		Connection conn = getConnection();
		
		ArrayList<SendMessage> list = new MessageDao().listSendMsg(conn, info, loginMember);
		
		for(SendMessage msg : list) {
			int no = msg.getSend_no(); 
				msg.setImgs(new MessageDao().listSendMsgImg(conn, no));
//				System.out.println(msg.getImgs());
		}
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	

	public int sendMsg(SendMessage sendM) {
		
		Connection conn = getConnection();
		
		int resultS = new MessageDao().SendMsg(conn, sendM);
		
		if(resultS > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		return resultS;
	}

	public int sendImage(SendMessageImg smi, SendMessage sm) {
		
		Connection conn = getConnection();
		
		int resultSI = 0;
		
		resultSI = new MessageDao().sendMsgImage(conn, smi, sm);
		
		if(resultSI > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return resultSI;
	}
	
	public int recMsg(ReceiveMessage recM) {
		Connection conn = getConnection();
		
		int resultR = new MessageDao().RecMsg(conn, recM);
		
		if(resultR > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		return resultR;
	}

	public int receiveImage(ReceiveMessageImg rmi, ReceiveMessage rm) {
		Connection conn = getConnection();
		
		int resultRI = new MessageDao().recMsgImage(conn, rmi, rm);
		
		if(resultRI > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		return resultRI;
		}

	public int getMsgList(Member loginMember, String send_id) {
		
		Connection conn = getConnection();
		
		int result = new MessageDao().getMsgList(conn, loginMember ,send_id);
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int sendMsgList(Member loginMember, String rec_id) {

		Connection conn = getConnection();
		
		int result = new MessageDao().sendMsgList(conn, loginMember, rec_id);
		
		JDBCTemplate.close(conn);
		
		return result;
	}


	public int deleteRMsg(int recNum) {
		
		Connection conn = getConnection();
		
		int resultR = new MessageDao().deleteRMsg(conn, recNum);
		
		if(resultR > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		return resultR;
	}

	public int deleteSMsg(int sendNum) {
		Connection conn = getConnection();
		
		int resultS = new MessageDao().deleteSMsg(conn, sendNum);
		
		if(resultS > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		return resultS;
	}

	public SendMessage SendDetails(int sendNo) {
		
		Connection conn = getConnection();
		
		SendMessage sendmessage = new MessageDao().SendDetails(conn, sendNo);
		
		JDBCTemplate.close(conn);
		
		return sendmessage;
	}

	public SendMessageImg SendDetailsImgName(int sendNo) {
		
		Connection conn = getConnection();
		
		SendMessageImg imgS = new MessageDao().SendDetailsImgName(conn, sendNo);
		
		JDBCTemplate.close(conn);
		
		return imgS;
	}

	public ReceiveMessage RecDetails(int recNo) {
		
		Connection conn = getConnection();
		
		ReceiveMessage recmessage = new MessageDao().RecDetails(conn, recNo);
		
		JDBCTemplate.close(conn);
		
		return recmessage;
	}

	public ReceiveMessageImg RecDetailsImgName(int recNo) {
		
		Connection conn = getConnection();
		
		ReceiveMessageImg imgR = new MessageDao().RecDetailsImgName(conn, recNo);
		
		JDBCTemplate.close(conn);
		
		return imgR;
	}

	public ArrayList<SendMessage> searchSendId(String id, PageInfo info) {
		Connection conn = getConnection();
		
		ArrayList<SendMessage> list = new MessageDao().searchSendId(conn, info, id);
		
		for(SendMessage msg : list) {
			int no = msg.getSend_no(); 
				msg.setImgs(new MessageDao().listSendMsgImg(conn, no));
		}
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	public ArrayList<ReceiveMessage> RecSearchMsg(PageInfo info, String id) {
		Connection conn = getConnection();
		
		ArrayList<ReceiveMessage> list = new MessageDao().RecSearchMsg(conn, info, id);
		
		for(ReceiveMessage msg : list) {
			int no = msg.getRec_no(); 
				msg.setImgs(new MessageDao().listRevMsgImg(conn, no));
		}
		
		JDBCTemplate.close(conn);
		
		return list;
	}
}
