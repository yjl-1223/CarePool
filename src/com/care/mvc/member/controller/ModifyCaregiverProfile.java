package com.care.mvc.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.care.mvc.care.model.service.CareService;
import com.care.mvc.care.model.vo.Care;
import com.care.mvc.care.model.vo.CareImage;
import com.care.mvc.care.model.vo.PatientWanted;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/modify/caregiver")
public class ModifyCaregiverProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ModifyCaregiverProfile() {
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = getServletContext().getRealPath("upload/carephoto");
		int maxSize = 1024 * 1024 * 10;  // 10mb
		
		String encoding = "UTF-8";
		
		// 이 아래가 문제 발생 (파일을 불러올때는 이미 멀티파트 안에 request 등이 이미 들어있기 때문에 불러올때 request나 다른 값이 아닌 멀티파트인 mr로 불러오는것이 맞다)
		MultipartRequest mr = new MultipartRequest(request, path, maxSize, encoding, new DefaultFileRenamePolicy());
		String careNo = mr.getParameter("careNo");
		System.out.println(careNo);
		
		String fileName = mr.getFilesystemName("upfile");       // 실제 이름
		String upfileName = mr.getOriginalFileName("upfile");
		String url = mr.getParameter(path);
		System.out.println(url);
		
		CareImage careImage = new CareImage();
		
		// 사진 등록 외 나머지 부분
		careImage.setImgPath(path);
		careImage.setImgNameOrg(fileName);
		careImage.setImgNameSav(upfileName);
		
		int resultI = new CareService().Updateimage(careImage,careNo);
		
		//-------------------------------------------------------------------------------------------
		// 보호사 등록 서블릿
		
		String msg = "";
		String loc = "";
		int resultC = 0;
		int resultPW = 0;
		Care care = new Care();
		PatientWanted patientwanted = new PatientWanted();

		care.setCareGen(mr.getParameter("caregender"));
		care.setCareLicense(String.join(",",mr.getParameterValues("careLicense")));
		care.setCareYears(mr.getParameter("careYears"));
		care.setCareHistory("근무병원 : " + mr.getParameter("careHistory1") +" / "
		+ "업무 : " +  mr.getParameter("careHistory2") + " / "
		+ "기간 : " +  mr.getParameter("careHistoryDate1") + " ~ " + mr.getParameter("careHistoryDate2") );
		care.setCarePlus(String.join(",",mr.getParameterValues("carePlus")));
		care.setCareTime(String.join(",",mr.getParameterValues("careTime")));
		care.setCarePlace(mr.getParameter("carePlace"));
		care.setCareSal(String.join(",", mr.getParameter("careSal")));
		care.setCareIntro(mr.getParameter("careIntro"));
		care.setMemId(mr.getParameter("memId"));
		
		resultC = new CareService().UpdateCare(care);

		//----------------------------------------------------------------------------------
		// 희망환자 등록 서블릿
		
		patientwanted.setWantedGen(mr.getParameter("wantedgen"));
		patientwanted.setWantedAge(mr.getParameter("age"));
		patientwanted.setWantedIll(String.join(",", mr.getParameter("wantedill")));
		patientwanted.setWantedGrade(String.join(",", mr.getParameter("wantedgra")));
		System.out.println(patientwanted);
	    resultPW = new CareService().UpdatePatientWanted(patientwanted,careNo);
		
		if(resultC > 0 && resultPW > 0 && resultC > 0) {
			msg = "프로필 수정 성공";
			loc = "/";
		} else {
			msg = "프로필 수정 실패";
			loc = "/";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}
}
