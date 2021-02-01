<%@page import="com.care.mvc.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Member member = (Member)request.getAttribute("member");
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
	background-color: #8B4513;
	width: 40%;
	text-align: center;
	margin-bottom: 5%;
	margin: auto;
	display: relative;
	border-radius: 5px;
}

.pro_li {
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


</style>
</head>
<body>
<div id="pro_con">
        <h1>프로필 등록을</h1>
        <h1>하지 않았습니다.</h1>
    </div>
    <div class="pro_line">
        <div class="pro_li">
            <img src="../image/병아리당황.png">
            <div class="pro_infor">
                <div style="font-size: 20px; font-weight: 900;"><%=member.getMemName() %></div>
                <div style="font-size:15px;"><%=member.getMemBirth() %></div>
            </div>
        </div>
    </div>
</body>
</html>