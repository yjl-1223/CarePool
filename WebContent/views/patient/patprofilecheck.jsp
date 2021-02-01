<%@page import="com.care.mvc.GuardAndPatient.model.vo.Patient"%>
<%@page import="com.care.mvc.GuardAndPatient.model.vo.Guard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Guard guard = (Guard)request.getAttribute("guard");
	Patient patient = (Patient)request.getAttribute("patient");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.pro_line {
	width: 100%;
	height: auto;
	text-align: center;
}

.profile_table {
    margin: 50px;
}

#pro_con {
	font-size: 13px;
	background-color: #8B4513;
	width: 40%;
	text-align: center;
	margin-bottom: 5%;
	margin: auto;
	display: relative;
	border-radius: 5px;
}

#pro_main {
	text-align: center;
	max-width: 600px;
	border-style: ridge;
	margin: auto;
	display: relative;
}

.empty {
	font-size: 15px;
	font-weight: 600;
	padding-bottom: 10px;
	padding-top: 15px;
	background-color: aliceblue;
	height: 30px;
}

#pro_main table {
	width: 100%;
	text-align: center;
	padding-left: 0%;
	border: black;
	table-layout: fixed;
	word-break: break-all;
}

#pro_main td {
	/* border-bottom: 1px solid #444444; */
	padding: 30px;
}

.pro_li {
	padding-top: 5px;
	width: 200px;
	height: 200px;
	margin: auto;
	display: relative;
	margin-bottom: -5%;
}

.pro_infor {
	cursor: pointer;
	line-height: 2.0;
	margin: auto;
	height: 100px;
}

#pro_btn button {
    border: 4px solid lightsteelblue;
	border-color: lightsteelblue;
	color: lightsteelblue;
	background-color: white;
	width: 200px;
	height: 50px;
	font-size: 1.5rem;
    font-weight: 900;
    border-radius: 10px;
    margin: 30px 50px;
    display: relative;
}

#pro_btn button:hover {
	background-color: lightsteelblue;
	color: white;
}


.pro_Table {
	width: 40%;
}

.profile_table td {
	font-size: 15px;
	border-bottom: 1px solid #444444;
	padding: 10px;
}
</style>
</head>
<body>
	<div id="pro_con">
		<!-- <h1>보호자&환자프로필 확인</h1> -->
    </div>
    
	<div class="pro_line">
		<!-- <div class="pro_li">
			<img src="../image/병아리당황.png">
		</div> -->
		<div class="profile_table">
			<table width="300" align="center">
				<tr>
					<td>이름 : <%=guard.getGuardName() %></td>
				</tr>
				<tr>
					<td>성별 : <%=guard.getGuard_gen() %></td>
				</tr>
				<tr>
					<% if(guard.getGuard_pat() != null) { %>
					<td><%=guard.getGuard_pat() %></td>
					<% }else { %>
						<td><b>보호자</b></td>
					<%} %>
				</tr>
			</table>
        </div>
        
			<div id="pro_main">
				<div class="empty">돌봄사항</div>
                <table>
                    <tbody>
                        <tr>
                            <td class="pro_Table">돌봄 장소</td>
                            <td><%=patient.getPat_place() %></td>
                        </tr>
                        <tr>
                            <td class="pro_Table">돌봄기간</td>
                            <td><%=patient.getPat_period() %></td>
                        </tr>
                        <tr>
                            <td class="pro_Table">희망시간대</td>
                            <td><%=patient.getPat_hop_time() %></td>
                        </tr>
                    </tbody>
                </table>
            <table>
                <div class="empty">환자정보</div>
                <thead>
                    <tr>
                        <td class="pro_Table">환자 이름</td>
                        <td><%=patient.getPat_name() %></td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td class="pro_Table">환자 나이/성별</td>
                        <td><%=patient.getPat_age()%> / <%=patient.getPat_gen() %></td>
                    </tr>
                    <tr>
                        <td class="pro_Table">몸무게</td>
                        <td><%=patient.getPat_kg() %></td>
                    </tr>
                    <tr>
                        <td class="pro_Table">감영성 질환</td>
                        <td><%=patient.getPat_infect() %></td>
                    </tr>
                    <tr>
                        <td class="pro_Table">장기요양등급</td>
                        <td><%=patient.getPat_grade() %></td>
                    </tr>
                    <tr>
                        <td class="pro_Table">개인위생관리</td>
                        <td><%=patient.getPat_sanit() %></td>
                    </tr>
                    <tr>
                        <td class="pro_Table">마비상태</td>
                        <td><%=patient.getPat_paral() %></td>
                    </tr>
                    <tr>
                        <td class="pro_Table">거동상태</td>
                        <td><%=patient.getPat_move() %></td>
                    </tr>
                    <tr>
                        <td class="pro_Table">욕창 환자 여부</td>
                        <td><%=patient.getPat_bed() %></td>
                    </tr>
                    <tr>
                        <td class="pro_Table">인지장애 여부</td>
                        <td><%=patient.getPat_cogdis() %></td>
                    </tr>
                    <tr>
                        <td class="pro_Table">화장실 이동시</td>
                        <td><%=patient.getPat_bathroom() %></td>
                    </tr>
                    <tr>
                        <td class="pro_Table">배변도구</td>
                        <td><%=patient.getPat_bowel_mn() %></td>
                    </tr>
                    <tr>
                        <td class="pro_Table">장루설치여부</td>
                        <td><%=patient.getPat_ostomy() %></td>
                    </tr>
                    <tr>
                        <td class="pro_Table">식사 도움 여부</td>
                        <td><%=patient.getPat_help_eat() %></td>
                    </tr>
                    <tr>
                        <td class="pro_Table">석션 사용 여부</td>
                        <td><%=patient.getPat_suction()%></td>
                    </tr>
                    <tr>
                        <td class="pro_Table">요양보호사 우대 성별</td>
                        <td><%=patient.getPat_guard_gen()%></td>
                    </tr>
                </tbody>
            </table>

            <table>
                <div class="empty">기타사항(선택)</div>
                <thead></thead>
                <tbody>
                    <tr>
                        <td>
                            <textarea cols="60" rows="7" readonly><%=patient.getPat_etc()%></textarea>
                        </td>
                    </tr>
                </tbody>
            </table>
            
        </div>
        <div id="pro_btn">
            <button onclick="opener.location.href='<%=request.getContextPath()%>/msg/write?memId=<%=guard.getMemId()%>'; self.close();">쪽지보내기</button>
        </div>
    </div>
</body>
</html>