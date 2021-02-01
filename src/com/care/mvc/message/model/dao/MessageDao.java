package com.care.mvc.message.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.care.mvc.common.jdbc.JDBCTemplate;
import com.care.mvc.common.util.PageInfo;
import com.care.mvc.member.model.vo.Member;
import com.care.mvc.message.model.vo.ReceiveMessage;
import com.care.mvc.message.model.vo.ReceiveMessageImg;
import com.care.mvc.message.model.vo.SendMessage;
import com.care.mvc.message.model.vo.SendMessageImg;

public class MessageDao {
	
	private int findSendNo(Connection conn, SendMessage sendMessage) {
		ResultSet rs = null;
		Statement stmt = null;
		String query = "";
		int sendNo = 0;
		
		query = "SELECT SEQ_SEND_NO.NEXTVAL FROM DUAL";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				sendNo = Integer.parseInt(rs.getString(1)); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return sendNo;		
	}
	
	private int findRecNo(Connection conn, ReceiveMessage receiveMessage) {
		ResultSet rs = null;
		Statement stmt = null;
		String query = "";
		int recNo = 0;
		
		query = "SELECT SEQ_REC_NO.NEXTVAL FROM DUAL";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				recNo = Integer.parseInt(rs.getString(1)); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return recNo;		
	}
	

	public ArrayList<ReceiveMessage> listRevMsg(Connection conn, PageInfo info, Member loginMember) {
		ArrayList<ReceiveMessage> list = new ArrayList<ReceiveMessage>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT * "
				+ "FROM ("
				+ "    SELECT ROWNUM AS RNUM, REC_NO, SEND_ID , REC_BODY, REC_DATE, MEM_ID, STATUS"
				+ "    FROM ("
				+ "        SELECT R.REC_NO, R.SEND_ID, R.REC_BODY, R.REC_DATE, M.MEM_ID, R.STATUS"
				+ "        FROM REC_MSG R JOIN MEMBER M ON(R.MEM_ID = M.MEM_ID) WHERE R.STATUS = 'Y' AND R.MEM_ID =? "
				+ "        ORDER BY R.REC_NO DESC"
				+ "    )"
				+ ") WHERE RNUM BETWEEN ? AND ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, loginMember.getMemId());
			pstmt.setInt(2, info.getStartList());
			pstmt.setInt(3, info.getEndList());
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				ReceiveMessage recMsg = new ReceiveMessage();
				
				recMsg.setRec_no(rset.getInt("REC_NO"));
				recMsg.setSend_id(rset.getString("SEND_ID"));
				recMsg.setRowNum(rset.getInt("RNUM"));
				recMsg.setRec_body(rset.getString("REC_BODY"));
				recMsg.setRec_date(rset.getDate("REC_DATE"));
				recMsg.setMem_id(rset.getString("MEM_ID"));
				
				list.add(recMsg);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}
	
	// no: REC_NO (받은쪽지번호)
	public ArrayList<ReceiveMessageImg> listRevMsgImg(Connection conn, int no) {
		ArrayList<ReceiveMessageImg> list = new ArrayList<ReceiveMessageImg>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT REC_IMG_NO, REC_IMG_PATH, REC_IMG_NAME_ORG, REC_IMG_NAME_SAV, REC_NO "
				+ " FROM REC_IMAGE "
				+ " WHERE REC_NO = ? "
				+ " ORDER BY REC_IMG_NO DESC"; // 순서는 REC_NO 해도 상관없을거같다
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				ReceiveMessageImg recMsgImg = new ReceiveMessageImg();
				
				recMsgImg.setRec_img_no(rset.getInt("REC_IMG_NO"));
				recMsgImg.setRec_img_path(rset.getString("REC_IMG_PATH"));
				recMsgImg.setRec_img_name_org(rset.getString("REC_IMG_NAME_ORG"));
				recMsgImg.setRec_img_name_sav(rset.getString("REC_IMG_NAME_SAV"));
				recMsgImg.setRec_no(rset.getInt("REC_NO"));
				
				list.add(recMsgImg); // list에 값이 한 개
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}

	public ArrayList<SendMessage> listSendMsg(Connection conn, PageInfo info, Member loginMember) {
		ArrayList<SendMessage> list = new ArrayList<SendMessage>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT * "
				+ "FROM ("
				+ "    SELECT ROWNUM AS RNUM, SEND_NO, REC_ID , SEND_BODY, SEND_DATE, MEM_ID, STATUS"
				+ "    FROM ("
				+ "        SELECT S.SEND_NO, S.REC_ID, S.SEND_BODY, S.SEND_DATE, M.MEM_ID, S.STATUS"
				+ "        FROM SEND_MSG S JOIN MEMBER M ON(S.MEM_ID = M.MEM_ID) WHERE S.STATUS = 'Y' AND S.MEM_ID =?"
				+ "        ORDER BY S.SEND_NO DESC"
				+ "    )"
				+ ") WHERE RNUM BETWEEN ? AND ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, loginMember.getMemId());
			pstmt.setInt(2, info.getStartList());
			pstmt.setInt(3, info.getEndList());
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				SendMessage sendMsg = new SendMessage();
				
				sendMsg.setSend_no(rset.getInt("SEND_NO"));
				sendMsg.setRec_id(rset.getString("REC_ID"));
				sendMsg.setRowNum(rset.getInt("RNUM"));
				sendMsg.setSend_body(rset.getString("SEND_BODY"));
				sendMsg.setSend_date(rset.getDate("SEND_DATE"));
				sendMsg.setMem_id(rset.getString("MEM_ID"));
				
				list.add(sendMsg);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}

	public int SendMsg(Connection conn, SendMessage sendM) {
		int resultS = 0;
		PreparedStatement pstmt = null;
		
		String query = "INSERT INTO SEND_MSG VALUES(SEQ_SEND_NO.NEXTVAL,?,?,SYSDATE,?,DEFAULT)";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, sendM.getRec_id());
			pstmt.setString(2, sendM.getSend_body());
			pstmt.setString(3, sendM.getMem_id());
			
			resultS = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return resultS;
	}
	
	public int sendMsgImage(Connection conn, SendMessageImg smi, SendMessage sm) {
	      int resultI = 0;
	      PreparedStatement Ipstmt = null;
	      int sendNo = findSendNo(conn, sm);
	      
	      try {
	         String sendMsgImg = "INSERT INTO SEND_IMAGE VALUES (SEQ_SEND_IMAGE_NO.NEXTVAL,?,?,?,?)";
	         
	         Ipstmt = conn.prepareStatement(sendMsgImg);
	         
	         Ipstmt.setString(1, smi.getSend_img_path());
	         Ipstmt.setString(2, smi.getSend_img_name_org());
	         Ipstmt.setString(3, smi.getSend_img_name_sav());
	         Ipstmt.setInt(4, sendNo+1); // ?
	         
	         resultI = Ipstmt.executeUpdate();
	         
	         System.out.println(resultI);
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         JDBCTemplate.close(Ipstmt);
	      }
	      
	      return resultI;
	   }

	public int RecMsg(Connection conn, ReceiveMessage recM) {
		int resultR = 0;
		PreparedStatement pstmt = null;
		
		String query = "INSERT INTO REC_MSG VALUES(SEQ_REC_NO.NEXTVAL,?,?,SYSDATE,?,DEFAULT)";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, recM.getMem_id());
			pstmt.setString(2, recM.getRec_body());
			pstmt.setString(3, recM.getSend_id());
			
			resultR = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return resultR;
	}
	
	public int recMsgImage(Connection conn, ReceiveMessageImg rmi, ReceiveMessage rm) {
		int resultRI = 0;
		PreparedStatement Ipstmt = null;
		int recNo = findRecNo(conn, rm);
		
		String query = "INSERT INTO REC_IMAGE VALUES (SEQ_REC_IMAGE_NO.NEXTVAL,?,?,?,?)";
		
		try {
			Ipstmt = conn.prepareStatement(query);
			
			 Ipstmt.setString(1, rmi.getRec_img_path());
	         Ipstmt.setString(2, rmi.getRec_img_name_org());
	         Ipstmt.setString(3, rmi.getRec_img_name_sav());
	         Ipstmt.setInt(4, recNo+1);
			
	         resultRI = Ipstmt.executeUpdate();
	         
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resultRI;
	}

	public int getMsgList(Connection conn, Member loginMember, String send_id) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "SELECT COUNT(*) FROM REC_MSG "
				+ "WHERE STATUS = 'Y' AND MEM_ID =? AND SEND_ID = NVL(?,SEND_ID)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, loginMember.getMemId());
			pstmt.setString(2, send_id);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1); // 받은쪽지번호 컬럼값
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int sendMsgList(Connection conn, Member loginMember, String rec_id) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "SELECT COUNT(*) FROM SEND_MSG "
				+ "WHERE STATUS = 'Y' AND MEM_ID =? AND REC_ID = NVL(?,REC_ID)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, loginMember.getMemId());
			pstmt.setString(2, rec_id);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1); // 보낸쪽지번호 컬럼값
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int deleteRMsg(Connection conn, int recNum) {
		int resultR = 0;
		PreparedStatement pstmt = null;
		
		String query ="UPDATE REC_MSG SET STATUS ='N' WHERE REC_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, recNum);
			
			resultR = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return resultR;
	}

	public int deleteSMsg(Connection conn, int sendNum) {
		int resultS = 0;
		PreparedStatement pstmt = null;
		
		String query ="UPDATE SEND_MSG SET STATUS ='N' WHERE SEND_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, sendNum);
			
			resultS = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return resultS;
	}


	public SendMessage SendDetails(Connection conn, int sendNo) {
		SendMessage sendmessage = new SendMessage();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT SEND_NO, REC_ID, SEND_BODY, SEND_DATE, MEM_ID "
				+ " FROM SEND_MSG "
				+ " WHERE SEND_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, sendNo);
			
			rset = pstmt.executeQuery();
				if(rset.next()) {
				
				sendmessage.setSend_no(rset.getInt("SEND_NO"));
				sendmessage.setRec_id(rset.getString("REC_ID"));
				sendmessage.setSend_body(rset.getString("SEND_BODY"));
				sendmessage.setSend_date(rset.getDate("SEND_DATE"));
				sendmessage.setMem_id(rset.getString("MEM_ID"));
				
			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return sendmessage;
	}

	public SendMessageImg SendDetailsImgName(Connection conn, int sendNo) {
		SendMessageImg imgS = new SendMessageImg();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT SEND_IMG_NO, SEND_IMG_PATH, SEND_IMG_NAME_ORG, SEND_IMG_NAME_SAV, SEND_NO "
				+ " FROM SEND_IMAGE"
				+ " WHERE SEND_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, sendNo);
			
			rset = pstmt.executeQuery();
				if(rset.next()) {
				
					imgS.setSend_img_no(rset.getInt("SEND_IMG_NO"));
					imgS.setSend_img_path(rset.getString("SEND_IMG_PATH"));
					imgS.setSend_img_name_org(rset.getString("SEND_IMG_NAME_ORG"));
					imgS.setSend_img_name_sav(rset.getString("SEND_IMG_NAME_SAV"));
					imgS.setSend_no(rset.getInt("SEND_NO"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return imgS;
	}


	public ReceiveMessage RecDetails(Connection conn, int recNo) {
		ReceiveMessage recmessage = new ReceiveMessage();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT REC_NO, SEND_ID, REC_BODY, REC_DATE, MEM_ID "
				+ " FROM REC_MSG "
				+ " WHERE REC_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, recNo);
			
			rset = pstmt.executeQuery();
				if(rset.next()) {
					recmessage.setRec_no(rset.getInt("REC_NO"));
					recmessage.setSend_id(rset.getString("SEND_ID"));
					recmessage.setRec_body(rset.getString("REC_BODY"));
					recmessage.setRec_date(rset.getDate("REC_DATE"));
					recmessage.setMem_id(rset.getString("MEM_ID"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return recmessage;
	}


	public ReceiveMessageImg RecDetailsImgName(Connection conn, int recNo) {
		ReceiveMessageImg imgR = new ReceiveMessageImg();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT REC_IMG_NO, REC_IMG_PATH, REC_IMG_NAME_ORG, REC_IMG_NAME_SAV, REC_NO "
				+ " FROM REC_IMAGE"
				+ " WHERE REC_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, recNo);
			
			rset = pstmt.executeQuery();
				if(rset.next()) {
				
					imgR.setRec_img_no(rset.getInt("REC_IMG_NO"));
					imgR.setRec_img_path(rset.getString("REC_IMG_PATH"));
					imgR.setRec_img_name_org(rset.getString("REC_IMG_NAME_ORG"));
					imgR.setRec_img_name_sav(rset.getString("REC_IMG_NAME_SAV"));
					imgR.setRec_no(rset.getInt("REC_NO"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return imgR;
	}

	public ArrayList<SendMessage> searchSendId(Connection conn, PageInfo info, String id) {
		ArrayList<SendMessage> list = new ArrayList<SendMessage>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT * "
				+ "FROM ("
				+ "    SELECT ROWNUM AS RNUM, SEND_NO, REC_ID , SEND_BODY, SEND_DATE, MEM_ID, STATUS"
				+ "    FROM ("
				+ "        SELECT S.SEND_NO, S.REC_ID, S.SEND_BODY, S.SEND_DATE, M.MEM_ID, S.STATUS"
				+ "        FROM SEND_MSG S JOIN MEMBER M ON(S.MEM_ID = M.MEM_ID) WHERE S.STATUS = 'Y'"
				+ "          AND REC_ID =?"
				+ "        ORDER BY S.SEND_NO DESC"
				+ "    )"
				+ ") WHERE RNUM BETWEEN ? AND ? ";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, id);
			pstmt.setInt(2, info.getStartList());
			pstmt.setInt(3, info.getEndList());
			//?
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				SendMessage sendMsg = new SendMessage();
				
				sendMsg.setSend_no(rset.getInt("SEND_NO"));
				sendMsg.setRec_id(rset.getString("REC_ID"));
				sendMsg.setRowNum(rset.getInt("RNUM"));
				sendMsg.setSend_body(rset.getString("SEND_BODY"));
				sendMsg.setSend_date(rset.getDate("SEND_DATE"));
				sendMsg.setMem_id(rset.getString("MEM_ID"));
				
				list.add(sendMsg);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}

	public ArrayList<ReceiveMessage> RecSearchMsg(Connection conn, PageInfo info, String id) {
		ArrayList<ReceiveMessage> list = new ArrayList<ReceiveMessage>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT * "
				+ "FROM ("
				+ "    SELECT ROWNUM AS RNUM, REC_NO, SEND_ID , REC_BODY, REC_DATE, MEM_ID, STATUS"
				+ "    FROM ("
				+ "        SELECT R.REC_NO, R.SEND_ID, R.REC_BODY, R.REC_DATE, M.MEM_ID, R.STATUS"
				+ "        FROM REC_MSG R JOIN MEMBER M ON(R.MEM_ID = M.MEM_ID) WHERE R.STATUS = 'Y'"
				+ "			AND SEND_ID = ? "		
				+ "        ORDER BY R.REC_NO DESC"
				+ "    )"
				+ ") WHERE RNUM BETWEEN ? AND ? ";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, id);
			pstmt.setInt(2, info.getStartList());
			pstmt.setInt(3, info.getEndList());
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				ReceiveMessage recMsg = new ReceiveMessage();
				
				recMsg.setRec_no(rset.getInt("REC_NO"));
				recMsg.setSend_id(rset.getString("SEND_ID"));
				recMsg.setRowNum(rset.getInt("RNUM"));
				recMsg.setRec_body(rset.getString("REC_BODY"));
				recMsg.setRec_date(rset.getDate("REC_DATE"));
				recMsg.setMem_id(rset.getString("MEM_ID"));
				
				list.add(recMsg);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}

	public ArrayList<SendMessageImg> listSendMsgImg(Connection conn, int no) {
		ArrayList<SendMessageImg> list = new ArrayList<SendMessageImg>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = " SELECT SEND_IMG_NO, SEND_IMG_PATH, SEND_IMG_NAME_ORG, SEND_IMG_NAME_SAV, SEND_NO "
				+ " FROM SEND_IMAGE "
				+ " WHERE SEND_NO = ? "
				+ " ORDER BY SEND_IMG_NO DESC"; 
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				SendMessageImg sendMsgImg = new SendMessageImg();
				
				sendMsgImg.setSend_img_no(rset.getInt("SEND_IMG_NO"));
				sendMsgImg.setSend_img_path(rset.getNString("SEND_IMG_PATH"));
				sendMsgImg.setSend_img_name_org(rset.getString("SEND_IMG_NAME_ORG"));
				sendMsgImg.setSend_img_name_sav(rset.getString("SEND_IMG_NAME_SAV"));
				sendMsgImg.setSend_no(rset.getInt("SEND_NO"));
				
				list.add(sendMsgImg);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}
	
}
