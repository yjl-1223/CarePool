<%@page import="com.care.mvc.care.model.vo.PatientWanted"%>
<%@page import="com.care.mvc.care.model.vo.Care"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Care caregiver = (Care)request.getAttribute("care");
	PatientWanted patWanted = (PatientWanted)request.getAttribute("patWanted");	
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

#pro_con {
	font-size: 13px;
	/* background-color: #8B4513; */
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
	text-align: left;
	padding-left: 0%;
	/* border: black; */
	table-layout: fixed;
	word-break: break-all;
}

#pro_main th, td {
	/* border-bottom: 1px solid #444444; */
	padding: 30px;
}

.pro_li {
	padding-top: 5px;
	width: 200px;
	height: 200px;
	margin: auto;
	display: relative;
	margin-bottom: 10%;
}

.pro_infor {
	cursor: pointer;
	margin-right: 300%;
	margin-top: 80%;
	line-height: 2.0;
	margin: auto;
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
	width: 25%;
}

.pro_li img {
    height: 150px;
    width: 150px;
    border-radius: 50%;
}

table textarea {
    margin: 0 auto;
    width: 100%;
}
</style>
</head>
<body>
<div id="pro_con">
        <!-- <h1>요양보호사님 프로필</h1> -->
    </div>
    <div class="pro_line">
        <div class="pro_li">
           	<img src="<%= request.getContextPath() %>/upload/carephoto/<%= caregiver.getCareImg().getImgNameSav() %>" alt="">            
            <div class="pro_infor">
                <div style="font-size: 20px; font-weight: 900;"><%=caregiver.getMemName() %></div>
                <div style="font-size:15px;"><%=caregiver.getCareGen() %></div>
                <div style="font-size:15px;"><%=caregiver.getMemBirth() %></div>
            </div>
        </div>
        <div id="pro_main">
            <div class="empty">
                세부사항
            </div>
            <table>
                <tbody>
                    <tr>
                        <td class="pro_Table">자격증</td>
                        <td><%=caregiver.getCareLicense() %></td>
                    </tr>
                    <tr>
                        <td class="pro_Table">경력</td>
                        <td><%=caregiver.getCareYears()%></td>
                    </tr>
                    <tr>
                        <td class="pro_Table" id="detail_">세부경력</td>
                        <td><%=caregiver.getCareHistory()%></td>
                    </tr>
                    <tr>
                        <td class="pro_Table">장점</td>
                        <td><%=caregiver.getCarePlus()%></td>
                    </tr>
                    <tr>
                        <td class="pro_Table">희망근무시간</td>
                        <td><%=caregiver.getCareTime()%></td>
                    </tr>
                    <tr>
                        <td class="pro_Table">희망근무위치</td>
                        <td><%=caregiver.getCarePlace()%></td>
                    </tr>
                    <tr>
                        <td class="pro_Table">희망급여</td>
                        <td><%=caregiver.getCareSal()%></td>
                    </tr>

                </tbody>
            </table>

            <table>
                <div class="empty">
                    케어가능환자
                </div>
                <thead>
                    <tr>
                        <td class="pro_Table">환자상태1:<br>성별 및 나이</td>
                        <td><%=patWanted.getWantedGen()%> / <%=patWanted.getWantedAge()%></td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td class="pro_Table">환자상태2:<br>질환</td>
                        <td><%=patWanted.getWantedIll()%></td>
                    </tr>
                    <tr>
                        <td class="pro_Table">환자상태:<br>장기요양등급</td>
                        <td><%=patWanted.getWantedGrade()%></td>
                    </tr>
                </tbody>

            </table>

            <table>
                <div class="empty">자기소개</div>
                <thead></thead>
                <tbody>
                    <tr>
                        <td>
                            <textarea cols="60" rows="7" readonly><%=caregiver.getCareIntro()%></textarea>
                        </td>
                    </tr>
                </tbody>
            </table>

        </div>
        <div id="pro_btn">
            <button onclick='opener.location.href="<%=request.getContextPath()%>/msg/write?memId=<%=caregiver.getMemId()%>"; self.close();'>쪽지보내기</button>
        </div>
    </div>
</body>
</html>
