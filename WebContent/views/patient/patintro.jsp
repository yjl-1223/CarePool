<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>
<script src="https://kit.fontawesome.com/4d8c9a2b0b.js" crossorigin="anonymous"></script>
<section id="content">
<form id="patintroForm" action="<%= request.getContextPath()%>/guardianAndPatientIntro" method="GET">

<h1 style="font-size: 30px;">보호자&환자</h1>
    <hr>
     <img src="./image/social-service-1294853_1280.png" alt="" width="30%" height="300px" style="float: right;"> 
    <h1 style="font-size:23px;"><i class="fas fa-check-square "></i> &nbsp;케어풀에 가입하면...</h1> 
    
     
        <P>
           <i class="fas fa-angle-right"></i>&nbsp;검증된 요양보호사 프로필을 확인하여 
           맞춤 돌봄을 제공 받을 수 있습니다.<br>
           <br>
           <i class="fas fa-angle-right"></i>&nbsp; 요양보호사가 환자의 상태를 매시간 체크 하여 보호자에게 알림 서비스 제공이 가능합니다.
           <br>
           <br>
           <i class="fas fa-angle-right"></i>&nbsp;내가 원하는 시간 만큼 원하는 날짜를 지정해서
           돌봄이 가능합니다.<br>
           <br>
          <i class="fas fa-angle-right"></i>&nbsp; 나의 집 주변 근처에 거주 하고있는 요양 보호사를 찾을 수 있습니다.<br>
           <br>
           <i class="fas fa-angle-right"></i>&nbsp;케어풀에서 3개월에 한번 요양보호사 교육을 통하여 요양보호사와 보호자간의 의사소통 역할을 합니다.
      
        </p>
        <br>
        <h4><i class="far fa-bookmark"></i>&lt;등록절차 안내&gt;</h4>
    <div class="wrap">
         <h4>보호자&환자</h4>
            <li> 
                1. 환자 프로필 등록
            </li>
            <li>
                2. 관리자 심사
            </li>
            <li>
                3. 등록완료
            </li>
        
        
        </div>

</form>
</section>
<%@ include file="/views/common/footer.jsp" %>