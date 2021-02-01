
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>

<!-- <script src="https://kit.fontawesome.com/4d8c9a2b0b.js" crossorigin="anonymous"></script> -->
<section id="content">
<form id="introForm" action="<%= request.getContextPath()%>/intro" method="GET">
<div id="introro">
<h2>케어풀 <i class="far fa-clipboard"></i></h2>
   <h4 style="font-size: 25px;">careful & care-pool </h4>
<ul>
    <pre style="font-size: 17px;">
        나의 가족같은 마음으로, 세심함으로...
        케어풀은 돌봄을 최우선으로 하여 다가갑니다.
    </pre>

<hr>
<br>
<ul>

    <img src="./image/reaching-3754844_1280.png"  width="200px" height="200px" style="float: left;" alt="이미지">
    <p style="font-size: 20px; font-weight: 900;">든든한 가족 케어풀<p>

   <p>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;인구고령화가 빠르게 진행되고 있는 만큼 케어풀은 더 많은 요양보호사와 좋은 서비스를 제공합니다.
   </p>
    <p >
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'케어풀'은 요양서비스의 전문화와 환자의 맞춤 서비스를 제공 할수 있도록 돕겠습니다.

    </p>
    <p>
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;환자의 상태와 환경에 따른 맟춤 요양서비스를 연결합니다.
    </p>
    </ul>
    <i class="far fa-address-card fa-5x" style="float: right"></i>&nbsp;<i class="fas fa-hands-helping fa-5x" style="float: right"></i>
<ul>

 
    <p style="font-size: 20px; font-weight: 900;">돌봄이 필요한 분들에게 제일 먼저 찾고 싶은 매칭 서비스.</p>

   <p>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1. 언제 어디서나 돌봄이 필요한 곳에서 원하는 기간동안 필요한 시간만큼 돌봄서비스 신청이 가능합니다.
   </p>
    <p >
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.상세한 문항의 프로필 등록으로 돌봄이 필요한 분들의 현황을 정확히 파악하여 
    맞는 돌봄이 가능한 매칭을 제공합니다.
    </p>
    <p>
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.원하는 시간대에 원하는 시간만큼 원하는 돌봄을 제공합니다.
    </p>
    <p>
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.실시간 인증을 통하여 요양보호사와 보호자간의 신뢰감 상승합니다.
    </p>
    <p>
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5.내 집 주변에 거주 하고 있는 요양보호사를 찾아보세요!!
    </p>
    </ul>
</div>
</form>
</section>
<%@ include file="/views/common/footer.jsp" %>
