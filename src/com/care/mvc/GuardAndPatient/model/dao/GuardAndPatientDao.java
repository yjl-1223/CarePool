package com.care.mvc.GuardAndPatient.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.care.mvc.GuardAndPatient.model.vo.Guard;
import com.care.mvc.GuardAndPatient.model.vo.Patient;
import com.care.mvc.common.jdbc.JDBCTemplate;
import com.care.mvc.member.model.vo.Member;

public class GuardAndPatientDao {
	
	private int findGuardNo(Connection conn, Guard guard) {
		ResultSet rs = null;
		Statement stmt = null;
		String query = "";
		int guardNo = 0;
		
		query = "SELECT SEQ_GUARD_NO.NEXTVAL FROM DUAL";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				guardNo = Integer.parseInt(rs.getString(1)); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return guardNo;		
	}
	
	public int insertGuard(Connection conn, Guard guard) {
		int resultG = 0;
		PreparedStatement GPstmt = null;
		String GuardQuery = "INSERT INTO GUARDIAN_PROFILE VALUES(?, ?, ?, ?)";
		int guardNo = findGuardNo(conn, guard);
		
		try {
			GPstmt = conn.prepareStatement(GuardQuery);

			GPstmt.setInt(1, guardNo);
			GPstmt.setString(2, guard.getGuard_gen());
			GPstmt.setString(3, guard.getGuard_pat());
			GPstmt.setString(4, guard.getMemId());

			resultG = GPstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(GPstmt);
		}
		return resultG;
	}

	public int insertPatient(Connection conn, Patient patient, Guard guard) {
		int resultP = 0;
		PreparedStatement PPstmt = null;
		int guardNo = findGuardNo(conn, guard);
		
		String PatientQuery = "INSERT INTO PATIENT_DETAILS VALUES(SEQ_PAT_NO.NEXTVAL,"
				+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			PPstmt = conn.prepareStatement(PatientQuery);

			PPstmt.setInt(1, guardNo - 1);
			PPstmt.setString(2, patient.getPat_place());
			PPstmt.setString(3, patient.getPat_period());
			PPstmt.setString(4, patient.getPat_hop_time());
			PPstmt.setString(5, patient.getPat_name());
			PPstmt.setInt(6, patient.getPat_age());
			PPstmt.setString(7, patient.getPat_gen());
			PPstmt.setInt(8, patient.getPat_kg());
			PPstmt.setString(9, patient.getPat_infect());
			PPstmt.setString(10, patient.getPat_grade());
			PPstmt.setString(11, patient.getPat_sanit());
			PPstmt.setString(12, patient.getPat_paral());
			PPstmt.setString(13, patient.getPat_move());
			PPstmt.setString(14, patient.getPat_bed());
			PPstmt.setString(15, patient.getPat_cogdis());
			PPstmt.setString(16, patient.getPat_bathroom());
			PPstmt.setString(17, patient.getPat_bowel_mn());
			PPstmt.setString(18, patient.getPat_ostomy());
			PPstmt.setString(19, patient.getPat_help_eat());
			PPstmt.setString(20, patient.getPat_suction());
			PPstmt.setString(21, patient.getPat_guard_gen());
			PPstmt.setString(22, patient.getPat_etc());

			resultP = PPstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(PPstmt);
		}

		return resultP;
	}

	public Guard checkguard(Connection conn, String sendId) {
		Guard guard = new Guard();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = " SELECT G.GUARD_NO, G.GUARD_GEN, G.GUARD_PAT, G.MEM_ID, M.MEM_NAME AS MEM_NAME"
				+ " FROM GUARDIAN_PROFILE G "
				+ " JOIN MEMBER M ON(G.MEM_ID = M.MEM_ID) "
				+ " WHERE G.MEM_ID = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, sendId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				guard.setGuard_no(rset.getInt("GUARD_NO"));
				guard.setGuard_gen(rset.getString("GUARD_GEN"));
				guard.setGuard_pat(rset.getString("GUARD_PAT"));
				guard.setMemId(rset.getString("MEM_ID"));
				guard.setGuardName(rset.getString("MEM_NAME"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return guard;
	}

	public Patient checkpatient(Connection conn, String sendId) {
		Patient patient = new Patient();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query ="SELECT G.MEM_ID, P.PAT_NO, P.GUARD_NO, P.PAT_PLACE, P.PAT_PERIOD, P.PAT_HOP_TIME,"
				+ " P.PAT_NAME, P.PAT_AGE, P.PAT_GEN, P.PAT_KG, P.PAT_INFECT, P.PAT_GRADE, P.PAT_SANIT, P.PAT_PARAL, P.PAT_MOVE,"
				+ " P.PAT_BED, P.PAT_COGDIS, P.PAT_BATHROOM, P.PAT_BOWEL_MN, P.PAT_OSTOMY, P.PAT_HELP_EAT, P.PAT_SUCTION, P.PAT_GUARD_GEN, P.PAT_ETC "
				+ " FROM PATIENT_DETAILS P"
				+ " JOIN GUARDIAN_PROFILE G ON (P.GUARD_NO = G.GUARD_NO)"
				+ " JOIN MEMBER M ON (M.MEM_ID = G.MEM_ID)"
				+ " WHERE G.MEM_ID = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, sendId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				patient.setPat_no(rset.getInt("PAT_NO"));
				patient.setGuard_no(rset.getInt("GUARD_NO"));
				patient.setPat_place(rset.getString("PAT_PLACE"));
				patient.setPat_period(rset.getString("PAT_PERIOD"));
				patient.setPat_hop_time(rset.getString("PAT_HOP_TIME"));
				patient.setPat_name(rset.getString("PAT_NAME"));
				patient.setPat_age(rset.getInt("PAT_AGE"));
				patient.setPat_gen(rset.getString("PAT_GEN"));
				patient.setPat_kg(rset.getInt("PAT_KG"));
				patient.setPat_infect(rset.getString("PAT_INFECT"));
				patient.setPat_grade(rset.getString("PAT_GRADE"));
				patient.setPat_sanit(rset.getString("PAT_SANIT"));
				patient.setPat_paral(rset.getString("PAT_PARAL"));
				patient.setPat_move(rset.getString("PAT_MOVE"));
				patient.setPat_bed(rset.getString("PAT_BED"));
				patient.setPat_cogdis(rset.getString("PAT_COGDIS"));
				patient.setPat_bathroom(rset.getString("PAT_BATHROOM"));
				patient.setPat_bowel_mn(rset.getString("PAT_BOWEL_MN"));
				patient.setPat_ostomy(rset.getString("PAT_OSTOMY"));
				patient.setPat_help_eat(rset.getString("PAT_HELP_EAT"));
				patient.setPat_suction(rset.getString("PAT_SUCTION"));
				patient.setPat_guard_gen(rset.getString("PAT_GUARD_GEN"));
				patient.setPat_etc(rset.getString("PAT_ETC"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return patient;
	}

	public int updateGuard(Connection conn, Guard guard) {
		int resultG = 0;
		PreparedStatement GPstmt = null;
		String GuardQuery = "UPDATE GUARDIAN_PROFILE SET GUARD_GEN=?, GUARD_PAT=? WHERE MEM_ID = ?";
		
		try {
			GPstmt = conn.prepareStatement(GuardQuery);

			GPstmt.setString(1, guard.getGuard_gen());
			GPstmt.setString(2, guard.getGuard_pat());
			GPstmt.setString(3, guard.getMemId());
			System.out.println(guard.getMemId());

			resultG = GPstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(GPstmt);
		}
		return resultG;
	}

	public int updatepatient(Connection conn, Patient patient, String guardNo) {
		int resultP = 0;
		PreparedStatement PPstmt = null;
		String PatientQuery = "UPDATE PATIENT_DETAILS SET PAT_PLACE=?, PAT_PERIOD=?, PAT_HOP_TIME=?, PAT_NAME=?, "
				+ "PAT_AGE=?, PAT_GEN=?, PAT_KG=?, PAT_INFECT=?, PAT_GRADE=?, PAT_SANIT=?, PAT_PARAL=?, PAT_MOVE=?, "
				+ "PAT_BED=?, PAT_COGDIS=?, PAT_BATHROOM=?, PAT_BOWEL_MN=?, PAT_OSTOMY=?, PAT_HELP_EAT=?, PAT_SUCTION=?, PAT_GUARD_GEN=?, "
				+ "PAT_ETC=? WHERE GUARD_NO=?";
		try {
			PPstmt = conn.prepareStatement(PatientQuery);

			PPstmt.setString(1, patient.getPat_place());
			PPstmt.setString(2, patient.getPat_period());
			PPstmt.setString(3, patient.getPat_hop_time());
			PPstmt.setString(4, patient.getPat_name());
			PPstmt.setInt(5, patient.getPat_age());
			PPstmt.setString(6, patient.getPat_gen());
			PPstmt.setInt(7, patient.getPat_kg());
			PPstmt.setString(8, patient.getPat_infect());
			PPstmt.setString(9, patient.getPat_grade());
			PPstmt.setString(10, patient.getPat_sanit());
			PPstmt.setString(11, patient.getPat_paral());
			PPstmt.setString(12, patient.getPat_move());
			PPstmt.setString(13, patient.getPat_bed());
			PPstmt.setString(14, patient.getPat_cogdis());
			PPstmt.setString(15, patient.getPat_bathroom());
			PPstmt.setString(16, patient.getPat_bowel_mn());
			PPstmt.setString(17, patient.getPat_ostomy());
			PPstmt.setString(18, patient.getPat_help_eat());
			PPstmt.setString(19, patient.getPat_suction());
			PPstmt.setString(20, patient.getPat_guard_gen());
			PPstmt.setString(21, patient.getPat_etc());
			PPstmt.setString(22, guardNo);

			resultP = PPstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(PPstmt);
		}

		return resultP;
	}

}
