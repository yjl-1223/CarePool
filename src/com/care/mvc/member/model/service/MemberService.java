package com.care.mvc.member.model.service;

import java.sql.Connection;

import com.care.mvc.common.jdbc.JDBCTemplate;
import com.care.mvc.member.model.dao.MemberDao;
import com.care.mvc.member.model.vo.Member;
import static com.care.mvc.common.jdbc.JDBCTemplate.getConnection;
import static com.care.mvc.common.jdbc.JDBCTemplate.close;
import static com.care.mvc.common.jdbc.JDBCTemplate.commit;
import static com.care.mvc.common.jdbc.JDBCTemplate.rollback;

public class MemberService {
	private MemberDao dao = new MemberDao();	

	public int enrollMember(Member member) {
		Connection conn = getConnection();
		
		int result = dao.insertMember(conn, member);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result;
	}

	public Member login(String id, String pwd) {
		Connection conn = getConnection();
		
		Member member = dao.findMemberByIdAndPwd(conn, id, pwd);
		
		close(conn);
		
		return member;
	}
							
	public boolean validate(String memId) {
		Connection conn = getConnection();
		
		Member member = dao.findMemberById(conn, memId);
		
		close(conn);

		return member != null;     // 멤버가 있으면 true, 없으면 false
	}
	
	public Member findMemberById(String userId) {
		Connection conn = getConnection();
		
        Member member = dao.findMemberById(conn, userId);
    
		close(conn);

		return member;
	}

	public int enrollupdateMember(Member member) {
        Connection conn = getConnection();
		int result = dao.enrollupdateMember(conn, member);
		
	       if (result > 0) {
				commit(conn);
			} else {
				rollback(conn);
			}
			
	        close(conn);
	        
			return result;
	}
}