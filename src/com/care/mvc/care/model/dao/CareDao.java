package com.care.mvc.care.model.dao;

import static com.care.mvc.common.jdbc.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.print.attribute.standard.MediaPrintableArea;

import com.care.mvc.care.model.vo.Care;
import com.care.mvc.care.model.vo.CareImage;
import com.care.mvc.care.model.vo.PatientWanted;
import com.care.mvc.common.jdbc.JDBCTemplate;


public class CareDao {

	public int insertcare(Connection conn, Care care) {
		int resultC = 0;
		String query = "";
		PreparedStatement pstmt = null;

		try {
			query = "INSERT INTO CAREGIVER_PROFILE VALUES (SEQ_CARE_NO.NEXTVAL,?,?,?,?,?,?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(query);
	
			pstmt.setString(1, care.getCareGen());
			pstmt.setString(2, care.getCareLicense());
			pstmt.setString(3, care.getCareYears());
			pstmt.setString(4, care.getCareHistory());
			pstmt.setString(5, care.getCarePlus());
			pstmt.setString(6, care.getCareTime());
			pstmt.setString(7, care.getCarePlace());
			pstmt.setString(8, care.getCareSal());
			pstmt.setString(9, care.getCareIntro());
			pstmt.setString(10, care.getMemId());

			resultC = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return resultC;
	}
	
	public int insertcarepatientwanted(Connection conn, PatientWanted patientwanted) {
		   int resultPW = 0;
			String query = "";
			PreparedStatement ppstmt = null;

			try {                   
				query = "INSERT INTO PATIENT_WANTED VALUES (?,SEQ_CARE_NO.NEXTVAL-1,?,?,?)";
				
				ppstmt = conn.prepareStatement(query);

				ppstmt.setString(1, patientwanted.getWantedGrade());
				ppstmt.setString(2, patientwanted.getWantedGen());
				ppstmt.setString(3, patientwanted.getWantedAge());
				ppstmt.setString(4, patientwanted.getWantedIll());
		
				resultPW = ppstmt.executeUpdate();
				
				System.out.println(resultPW);
			
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(ppstmt);
			}

			return resultPW;
		}


	public int insertCareImage(Connection conn, CareImage careImage) {
	      int resultI = 0;
	      PreparedStatement Ipstmt = null;
	      
	      
	      try {
	         String CareImageQuery = "INSERT INTO CARE_IMAGE VALUES (SEQ_IMG_NO.NEXTVAL,SEQ_CARE_NO.NEXTVAL+1,SYSDATE,?,?,?)";
	         
	         Ipstmt = conn.prepareStatement(CareImageQuery);
	         
	         Ipstmt.setString(1, careImage.getImgPath());
	         Ipstmt.setString(2, careImage.getImgNameOrg());
	         Ipstmt.setString(3, careImage.getImgNameSav());
	         
	         resultI = Ipstmt.executeUpdate();
	         
	         System.out.println(resultI);
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         JDBCTemplate.close(Ipstmt);
	      }
	      
	      return resultI;
	   }
	
	public Care checkCaregiver(Connection conn, String sendId) {
		Care caregiver = new Care();
		CareImage img = new CareImage();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT C.CARE_NO, C.MEM_ID, C.CARE_GEN, C.CARE_LICENSE, C.CARE_YEARS, C.CARE_HISTORY, C.CARE_PLUS, "
				+ "C.CARE_TIME, C.CARE_PLACE, C.CARE_SAL, C.CARE_INTRO, M.MEM_NAME AS MEM_NAME, M.MEM_BIRTH AS MEM_BIRTH "
				+ "FROM CAREGIVER_PROFILE C "
				+ "JOIN MEMBER M ON(M.MEM_ID = C.MEM_ID) "
				+ "WHERE C.MEM_ID = ?";
				
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, sendId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				caregiver.setCareNo(rset.getInt("CARE_NO"));
				caregiver.setMemId(rset.getString("MEM_ID"));
				caregiver.setCareGen(rset.getString("CARE_GEN"));
				caregiver.setCareLicense(rset.getString("CARE_LICENSE"));
				caregiver.setCareYears(rset.getString("CARE_YEARS"));
				caregiver.setCareHistory(rset.getString("CARE_HISTORY"));
				caregiver.setCarePlus(rset.getString("CARE_PLUS"));
				caregiver.setCareTime(rset.getString("CARE_TIME"));
				caregiver.setCarePlace(rset.getString("CARE_PLACE"));
				caregiver.setCareSal(rset.getString("CARE_SAL"));
				caregiver.setCareIntro(rset.getString("CARE_INTRO"));
				caregiver.setMemName(rset.getString("MEM_NAME"));
				caregiver.setMemBirth(rset.getString("MEM_BIRTH"));		
				
				img = getCareImage(conn, rset.getInt("CARE_NO"));
				caregiver.setCareImg(img);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return caregiver;
	}

	// 아래코드로 케어이미지 불러와서 위에 set...()!!!
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
		
		return img;
	}
	
	public PatientWanted checkPatWanted(Connection conn, String sendId) {
		PatientWanted patWanted = new PatientWanted();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT P.WANTED_GRADE, P.WANTED_GEN, P.WANTED_AGE, P.WANTED_ILL "
				+ "FROM PATIENT_WANTED P "
				+ "JOIN CAREGIVER_PROFILE C ON (P.CARE_NO = C.CARE_NO) "
				+ "JOIN MEMBER M ON (C.MEM_ID = M.MEM_ID) "
				+ "WHERE M.MEM_ID = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, sendId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				patWanted.setWantedGrade(rset.getString("WANTED_GRADE"));
				patWanted.setWantedGen(rset.getString("WANTED_GEN"));
				patWanted.setWantedAge(rset.getString("WANTED_AGE"));
				patWanted.setWantedIll(rset.getString("WANTED_ILL"));
				
				System.out.println(patWanted.getWantedAge());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return patWanted;
	}

	public int updateCareImage(Connection conn, CareImage careImage, String careNo) {
		int resultI = 0;
	      PreparedStatement Ipstmt = null;
	      
	      try {
	         String CareImageQuery = "UPDATE CARE_IMAGE SET IMG_DATE = SYSDATE, IMG_PATH = ?, IMG_NAME_ORG = ?, IMG_NAME_SAV = ?"
	         		+ " WHERE CARE_NO=? ";
	         
	         Ipstmt = conn.prepareStatement(CareImageQuery);
	         
	         Ipstmt.setString(1, careImage.getImgPath());
	         Ipstmt.setString(2, careImage.getImgNameOrg());
	         Ipstmt.setString(3, careImage.getImgNameSav());
	         Ipstmt.setString(4, careNo);
	         
	         resultI = Ipstmt.executeUpdate();
	         
	         System.out.println(resultI);
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         JDBCTemplate.close(Ipstmt);
	      }
	      return resultI;
	   }

	public int updateCare(Connection conn, Care care) {
		int resultC = 0;
		String query = "UPDATE CAREGIVER_PROFILE SET CARE_GEN=?, CARE_LICENSE=?, CARE_YEARS=?, CARE_HISTORY=?, CARE_PLUS=?, CARE_TIME=?, "
				+ "CARE_PLACE=?, CARE_SAL=?, CARE_INTRO=? WHERE MEM_ID = ?";
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(query);
	
			pstmt.setString(1, care.getCareGen());
			pstmt.setString(2, care.getCareLicense());
			pstmt.setString(3, care.getCareYears());
			pstmt.setString(4, care.getCareHistory());
			pstmt.setString(5, care.getCarePlus());
			pstmt.setString(6, care.getCareTime());
			pstmt.setString(7, care.getCarePlace());
			pstmt.setString(8, care.getCareSal());
			pstmt.setString(9, care.getCareIntro());
			pstmt.setString(10, care.getMemId());

			resultC = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return resultC;
	}

	public int updatecarepatientwanted(Connection conn, PatientWanted patientwanted, String careNo) {
		 	int resultPW = 0;
			String query = "UPDATE PATIENT_WANTED SET WANTED_GRADE=?, WANTED_GEN=?, WANTED_AGE=?, WANTED_ILL=? "
					+ " WHERE CARE_NO =?";
			PreparedStatement ppstmt = null;

			try {                   
				ppstmt = conn.prepareStatement(query);

				ppstmt.setString(1, patientwanted.getWantedGrade());
				ppstmt.setString(2, patientwanted.getWantedGen());
				ppstmt.setString(3, patientwanted.getWantedAge());
				ppstmt.setString(4, patientwanted.getWantedIll());
				ppstmt.setString(5, careNo);
				
				resultPW = ppstmt.executeUpdate();
				
				System.out.println(resultPW);
			
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(ppstmt);
			}
			return resultPW;
		}
}








