<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	boolean valid = (boolean)request.getAttribute("valid");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
	<style>
		div#checkId-container {
		text-align:center;
		padding-top:50px;
	}
	
	span#duplicated {
		color:red;
		font-weight:bolder;
	}
	</style>
<body>
	<div id="checkId-container">
		<% if(valid) { %>
		 [<span id="duplicated"><%= request.getParameter("userId") %></span>]는 사용중입니다.
		 
		 <br><br>
			
			<form action="<%= request.getContextPath() %>/member/checkId" method="post">
				<input type="text" name="userId" id="newId" placeholder="아이디(4글자이상)" required>
				<input type="submit" id="checkDuplicate" onclick="return validate();" value="중복검사" >
			</form>	
				
		<% } else { %>
		
			[<span><%= request.getParameter("userId") %></span>]는 사용 가능합니다.
		
			<br><br>
			
			<input type="button" onclick="setUserId()" value="닫기">		
		<% } %>
	</div>
	<script>
		function validate(){
			let id = document.getElementById("newId").value;
			
			if(id.trim().length<4){
				alert("아이디는 4글자 이상 입력하세요!");
				document.getElementById("userId").focus();
				return false;
			}
		}
		
		function setUserId() {
			const id = "<%= request.getParameter("userId") %>";
			
			opener.memberEnrollFrm.userId.value = id;  
			opener.memberEnrollFrm.userPwd.focus();		
			
			close();			
		}	
	</script>
</body>
</html>