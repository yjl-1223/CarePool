package com.care.mvc.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.care.mvc.member.model.vo.Member;

import static com.care.mvc.common.jdbc.JDBCTemplate.close;

public class MemberDao {

	public MemberDao() {
	}
	
	// 회원가입 
	public int insertMember(Connection conn, Member member) {
		int result = 0;
		String query = null;
		PreparedStatement pstmt = null;

		try {
			// ROLE_USER : 보호자 혹은 요양보호사
			query = "INSERT INTO MEMBER VALUES (?,?,?,?,?,?,?,?,SYSDATE,SYSDATE,DEFAULT)";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, member.getMemId());
			pstmt.setString(2, member.getMemRole());
			pstmt.setString(3, member.getMemName());
			pstmt.setString(4, member.getMemPwd());
			pstmt.setString(5, member.getMemEmail());
			pstmt.setString(6, member.getMemPhone());
			pstmt.setString(7, member.getMemAddr());
			pstmt.setString(8, member.getMemBirth());

			result = pstmt.executeUpdate();

			System.out.println(result);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}
	
	public Member findMemberById(Connection conn,String id){
	      Member member = null;
	      ResultSet rset = null;
	      PreparedStatement pstmt = null;

	      try {
//	    	  AND STATUS='Y'
	         pstmt = conn.prepareStatement("SELECT * FROM MEMBER WHERE MEM_ID = ? ");
	         
	         pstmt.setString(1, id);

	         rset = pstmt.executeQuery();
	         
	         if(rset.next()) {
	            member = new Member(    
		            rset.getString("MEM_ID"),
		            rset.getString("MEM_ROLE"),
		            rset.getString("MEM_NAME"),
		            rset.getString("MEM_PWD"),
		            rset.getString("MEM_EMAIL"),
		            rset.getString("MEM_PHONE"),
		            rset.getString("MEM_ADDR"),
		            rset.getString("MEM_BIRTH"),
		            rset.getDate("CREATE_DATE"),
		            rset.getDate("MODIFY_DATE"),
		            rset.getString("STATUS")
	            );
	         }
	      
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	    	  close(rset);
	    	  close(pstmt);
	      }
	      
	      return member;
	   }
	
	// 로그인
	public Member findMemberByIdAndPwd(Connection conn, String id, String pwd) {
		Member member = null;
		ResultSet rset = null;
		PreparedStatement pstmt = null;

		try {

			pstmt = conn.prepareStatement("SELECT * FROM MEMBER WHERE MEM_ID = ? AND MEM_PWD = ? AND STATUS = 'Y'");

			pstmt.setString(1, id);
			pstmt.setString(2, pwd);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				
				System.out.println(rset.getString("MEM_ID") + ", " + rset.getString("MEM_PWD"));
				
				member = new Member(
						rset.getString("MEM_ID"),
						rset.getString("MEM_ROLE"),
						rset.getString("MEM_NAME"),
						rset.getString("MEM_PWD"),
						rset.getString("MEM_EMAIL"),
						rset.getString("MEM_PHONE"), 
						rset.getString("MEM_ADDR"),
						rset.getString("MEM_BIRTH"),
						rset.getDate("CREATE_DATE"),
						rset.getDate("MODIFY_DATE"), 
						rset.getString("STATUS")
					);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			return member;
		}

	public int enrollupdateMember(Connection conn, Member member) {
	    int result = 0;
	    
	    PreparedStatement pstmt = null;

		try {
		
			pstmt = conn.prepareStatement("UPDATE MEMBER SET MEM_ROLE=?,MEM_EMAIL=?,MEM_PHONE=?,MEM_ADDR=?,MEM_BIRTH=?,MODIFY_DATE=SYSDATE WHERE MEM_ID=?");

			pstmt.setString(1, member.getMemRole());
			pstmt.setString(2, member.getMemEmail());
			pstmt.setString(3, member.getMemPhone());
			pstmt.setString(4, member.getMemAddr());
			pstmt.setString(5, member.getMemBirth());
			pstmt.setString(6, member.getMemId());
			
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}
}

