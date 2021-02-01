<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/views/common/header.jsp" %>

 <section id="content">
 		<section>
        <div id="sec_container">
            <div id="btngroup">
                <div id="btn_1">
                    <span><b>[요양보호사] 하단 버튼을 클릭하세요!</b></span>
                    <input type="button" id="carbtn" value="요양보호사" onclick="location.href = '<%=request.getContextPath()%>/careIntro';" >
                </div>
                <div id="btn_2">
                    <span><b>[보호자/환자] 하단 버튼을 클릭하세요!</b></span>
                    <input type="button" id="guabtn" value="보호자/환자" onclick="location.href = '<%=request.getContextPath()%>/guardianAndPatientIntro';"  >
                </div>
            </div>
            <div id="sec_img">
                <img src="./image/old-age-957492_1920.jpg" alt="" width="100%" height="705px">
            </div>
        </div>
    </section>
 </section>
<%@ include file="/views/common/footer.jsp" %>