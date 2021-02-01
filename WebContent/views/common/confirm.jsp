<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String confirm = (String)request.getAttribute("confirm");
	String role = (String)request.getAttribute("role");
	String userId = (String)request.getAttribute("userId");
	boolean is = role.contains("guardian");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.5.1.js"></script>
</head>
<body>
	<script>
	
     if(confirm("<%= confirm %>")){
    	 if(<%= role.contains("caregiver")%>){

    		 location.replace("<%=request.getContextPath()%>/enroll/profile/care?userId=<%= userId %>");
    		 // 여기서부터 등록하기, 취소하기 버튼을 눌렀을 때 어떻게 작동되는지부터 작업을 시작해야한다.
    		 
    	 } else if(<%= role.contains("guardian")%>){
    		 location.replace("<%=request.getContextPath()%>/enroll/profile/guardian?userId=<%= userId %>");
    		// 여기서부터 등록하기, 취소하기 버튼을 눌렀을 때 어떻게 작동되는지부터 작업을 시작해야한다.
    	 }
     }else {
    	 location.replace("<%=request.getContextPath()%>/");
     }
     
     </script>
</body>
</html>