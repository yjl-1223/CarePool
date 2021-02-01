<%@page import="org.apache.jasper.tagplugins.jstl.core.If"%>
<%@page import="com.care.mvc.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>

<%
	String saveId = null;
	Member loginMember = (Member)session.getAttribute("loginMember");
	Cookie[] cookies = request.getCookies();
	
	// 쿠키값 받아오기
	if (cookies != null) {
		for (Cookie c : cookies) {
			if (c.getName().equals("saveId")) {
				saveId = c.getValue();
				
				break;
			}
		}
	}
%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
<title>Insert title here</title>
<link rel="stylesheet"href="<%=request.getContextPath()%>/css/style.css">
<script src="<%=request.getContextPath()%>/js/jquery-3.5.1.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>
	<header>
		<div id="wrapper">
            <div id="logo">
               	 <a href="<%=request.getContextPath()%>" >
                    <i class="fas fa-hand-holding-heart"></i>
                </a>
            </div>
			<div id="page">
				 <a href="<%=request.getContextPath()%>" style="text-decoration: none;">
					<h1>CAREPOOL</h1>
				</a>
			</div>
			<div id="login">
			<% if (loginMember == null ) { %>	
				<div style = "margin:auto; padding-top: 45px;">
				<input type="button" id="loginbtn" value="로그인"
					onclick="location.href = '<%=request.getContextPath()%>/member/login';">
				<input type="button" id="enrollbtn" value="회원가입"
					onclick="location.href = '<%=request.getContextPath()%>/member/enroll';">
				</div>
			<% } else { %>
				<h4><%=loginMember.getMemId()%> 님 안녕하세요 <i class="far fa-grin-alt"></i></h4>
				<input type="button" value="마이페이지"
					onclick="location.href = '<%= request.getContextPath() %>/member/mypage?userId=<%= loginMember.getMemId()%>';">			
				<input type="button" id="authbtn" value="개인정보수정" onclick="location.href = '<%= request.getContextPath() %>/member/enrollview?userId=<%= loginMember.getMemId()%>';"  >					
				<input type="button" id="logoutbtn" value="로그아웃" 
					onclick="location.replace('<%= request.getContextPath() %>/logout');">
			<% } %>
			</div>
		</div>
		<nav>

			<ul id="main-menu">
				<li><a href="<%=request.getContextPath()%>/intro">소개</a></li>
				<li><a href="#">매칭</a>
					<ul id="sub-menu">
						<li><a href="<%=request.getContextPath()%>/match/search">매칭검색</a></li>
					</ul>
				</li>
				<li><a href="#">서비스</a>
					<ul id="sub-menu">
						<li><a id="msg" href="<%=request.getContextPath()%>/msg/get">쪽지</a></li>
						<li id="sub-li"><a href="#">지도</a></li>
					</ul>
				</li>
			</ul>
		</nav>
	</header>
