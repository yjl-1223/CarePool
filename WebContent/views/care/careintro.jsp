<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<title>Insert title here</title>
<%@ include file="/views/common/header.jsp" %>
<script src="https://kit.fontawesome.com/4d8c9a2b0b.js" crossorigin="anonymous"></script>
<section id="content">
<form id="patintroForm" action="<%= request.getContextPath()%>/careIntro" method="GET">

<h1 style="font-size: 30px;">요양보호사</h1>
    <hr>
     <img src="./image/nurse-2683782_1920.png" alt="" width="300px" height="300px" style="float: right;"> 
     
     <h1 style="font-size:23px;"><i class="fas fa-check-square "></i> &nbsp;케어풀에 가입하면...</h1> 
     

        <P>
            <i class="fas fa-angle-right"></i>&nbsp;검증된 보호자 프로필을 확인하여 
            케어할 환자 정보를 미리 확인해서 보호자랑 소통이 가능합니다.<br>
           <br>
           <i class="fas fa-angle-right"></i>&nbsp;요양보호사가 환자의 상태를 매시간 체크 하는 만큼 
              보호자및 환자와 신뢰감 상승효과가 있습니다.<br>
           <br>
           <i class="fas fa-angle-right"></i>&nbsp;내가 원하는 시간 만큼 원하는 날짜를 지정해서
           자유롭게 근무가 가능합니다.<br>
           <br>
           <i class="fas fa-angle-right"></i>&nbsp;케어풀에서 3개월에 한 번 교육을 통하여 차별화된 관리를 받을 수 있습니다.<br>
        </p>
        <br>
        <h4><i class="far fa-bookmark"></i>&lt;등록절차 안내&gt;</h4>
    <div class="wrap">

        <h4>요양보호사</h4>
            <li> 
                1. 요양보호사 프로필 등록
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