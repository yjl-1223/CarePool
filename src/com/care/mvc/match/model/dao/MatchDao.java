package com.care.mvc.match.model.dao;

import static com.care.mvc.common.jdbc.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.care.mvc.care.model.vo.Care;
import com.care.mvc.care.model.vo.CareImage;

public class MatchDao {
	
	public CareImage getCareImage(Connection conn, int careNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		CareImage img = new CareImage();
		
		String query = "SELECT IMG_NAME_SAV "
				+ "FROM CARE_IMAGE I "
				+ "JOIN CAREGIVER_PROFILE P ON (I.CARE_NO = P.CARE_NO) "
				+ "WHERE P.CARE_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, careNo);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				img.setImgNameSav(rs.getString(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs); 
			close(pstmt);
		}
		
		return img; // 저장이름만을 포함하는 CareImage 객체 반환
	}

	public List<Care> searchProfiles(Connection conn, Map<String, String[]> options) {
//		List<Member> profiles = new ArrayList<>();
		List<Care> profiles = new ArrayList<>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		// with REGEXP_LIKE (?000)
		StringBuffer query = new StringBuffer(
				 "SELECT M.MEM_NAME, M.MEM_ID, C.CARE_NO "
				+ "FROM MEMBER M "
				+ "JOIN CAREGIVER_PROFILE C ON (M.MEM_ID = C.MEM_ID) "
				+ "WHERE M.STATUS = 'Y' AND "
				+ 		"M.MEM_ROLE = 'caregiver' ");

		if (options.containsKey("time")){			
			String[] time = options.get("time");	
			String timeString = String.join("|", time);
			
			query.append(" AND REGEXP_LIKE (C.CARE_TIME, '(" + timeString + ")') ");
		}
		
		if (options.containsKey("gender")){			
			String[] gender = options.get("gender");	
			String genderString = String.join("|", gender);
			
			query.append(" AND REGEXP_LIKE (C.CARE_GEN, '(" + genderString + ")') ");
		}
		
		if (options.containsKey("qual")){			
			String[] qual = options.get("qual");	
			String qualString = String.join("|", qual);
			
			query.append(" AND REGEXP_LIKE (C.CARE_LICENSE, '(" + qualString + ")') ");
		}
	
		if (options.containsKey("years")){			
			String[] years = options.get("years");	
			String yearsString = String.join("|", years);
			
			query.append(" AND REGEXP_LIKE (C.CARE_YEARS, '(" + yearsString + ")') ");
		}
		
		if (options.containsKey("addr")){			
			String[] addr = options.get("addr");	
			String addrString = String.join("|", addr);
			
			query.append(" AND REGEXP_LIKE (C.CARE_PLACE, '(" + addrString + ")') ");
		}
		
		if (options.containsKey("pay")){			
			String[] pay = options.get("pay");	
			String payString = String.join("|", pay);
			
			query.append(" AND REGEXP_LIKE (C.CARE_SAL, '(" + payString + ")') ");
		}
				
		System.out.println(query);
		
		try {
			pstmt = conn.prepareStatement(query.toString()); // toString()
						
	        // 쿼리문 실행
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Care care = new Care(); 
				CareImage img = new CareImage();
				
				care.setMemName((rs.getString("MEM_NAME")));
				care.setMemId(rs.getString("MEM_ID"));
				
				img = getCareImage(conn, rs.getInt("CARE_NO"));
				
				// 불러온 img 객체를 care의 멤버변수로 set
				care.setCareImg(img);
				
				profiles.add(care);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs); 
			close(pstmt);
		}
		
		System.out.println(profiles);
		
		return profiles;
	}
	
	
}
