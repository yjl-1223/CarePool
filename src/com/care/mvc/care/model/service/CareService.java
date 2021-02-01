package com.care.mvc.care.model.service;

import static com.care.mvc.common.jdbc.JDBCTemplate.commit;
import static com.care.mvc.common.jdbc.JDBCTemplate.getConnection;
import static com.care.mvc.common.jdbc.JDBCTemplate.rollback;

import java.sql.Connection;

import com.care.mvc.care.model.dao.CareDao;
import com.care.mvc.care.model.vo.Care;
import com.care.mvc.care.model.vo.CareImage;
import com.care.mvc.care.model.vo.PatientWanted;
import com.care.mvc.common.jdbc.JDBCTemplate;

public class CareService {

	public int enrollcare(Care care) {  
		Connection conn = getConnection();
		
		int resultC = 0;
		
		resultC = new CareDao().insertcare(conn, care);
		
		
		if(resultC > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return resultC;
	}

	public int insertimage(CareImage careImage) {
		
		Connection conn = getConnection();
		
		int resultI = 0;
		
		resultI = new CareDao().insertCareImage(conn, careImage);
		
		if(resultI > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return resultI;
		
	}

	public int enrollPatientWanted(PatientWanted patientwanted) {
        Connection conn = getConnection();
		
		int resultPW = new CareDao().insertcarepatientwanted(conn, patientwanted);
		
		if(resultPW > 0) {
			commit(conn);        
		} else {
			rollback(conn);
		}
		
		return resultPW;

	}

	public Care checkCare(String sendId) {
		
		Connection conn = getConnection();
		
		Care caregiver = new CareDao().checkCaregiver(conn, sendId);
		
		JDBCTemplate.close(conn);
		
		return caregiver;
	}
	
	public PatientWanted checkPatWanted(String sendId) {
		
		Connection conn = getConnection();
		
		PatientWanted patWanted = new CareDao().checkPatWanted(conn, sendId);
		
		JDBCTemplate.close(conn);
		
		return patWanted;
	}

	public int Updateimage(CareImage careImage, String careNo) {
		Connection conn = getConnection();
		
		int resultI = 0;
		
		resultI = new CareDao().updateCareImage(conn, careImage, careNo);
		
		if(resultI > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return resultI;
	}

	public int UpdateCare(Care care) {
		Connection conn = getConnection();
		
		int resultC = 0;
		
		resultC = new CareDao().updateCare(conn, care);
		
		if(resultC > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return resultC;
	}

	public int UpdatePatientWanted(PatientWanted patientwanted, String careNo) {
		Connection conn = getConnection();
		System.out.println(careNo + "000");
		int resultPW = new CareDao().updatecarepatientwanted(conn, patientwanted ,careNo);
		
		if(resultPW > 0) {
			commit(conn);        
		} else {
			rollback(conn);
		}
		return resultPW;
	}
}
