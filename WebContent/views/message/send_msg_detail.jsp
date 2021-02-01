<%@page import="com.care.mvc.message.model.vo.SendMessageImg"%>
<%@page import="com.care.mvc.message.model.vo.SendMessage"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.care.mvc.message.model.vo.ReceiveMessage"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<link rel="stylesheet" href="../../css/style.css">
<%
	SendMessage sendmessage = (SendMessage)request.getAttribute("sendmessage");
	SendMessageImg imgS = (SendMessageImg)request.getAttribute("imgS");
	
%>
<%@ include file="/views/common/header.jsp"%>
<section>
	<div id="msg_container">
		<form action="<%= request.getContextPath()%>/msg/get" method="POST">
			<div id="msg_1">
				<!-- <h1>CAREPOOL 쪽지</h1> -->
			</div>
			<div id="msg_2">
				<div id="msg_2-1">
					<div id="write_msg">
                        <a href="<%= request.getContextPath()%>/msg/write">쪽지쓰기</a>
                    </div>
					<div>
						<a href="<%= request.getContextPath()%>/msg/get?loginMember=<%= loginMember.getMemId() %>"> 받은쪽지함 </a>
					</div>
					<div>
						<a href="<%= request.getContextPath()%>/msg/send?loginMember=<%= loginMember.getMemId() %>"> 보낸쪽지함 </a>
					</div>
				</div>
				<div id="msg_2-2">
					<div id="msg_2-2-body">
                        <div id="msg_contents1"> 
                            <b>받는사람 : &nbsp;</b> <a href="#"></a><b><%=sendmessage.getRec_id()%></b></a>
                        </div>
                        <div id="msg_contents1">
                            <b>보낸파일 : &nbsp;</b> 
                            		<% if(imgS.getSend_img_name_org() != null) { %>
                            	<a href="javascript:fileDownload('<%= imgS.getSend_img_name_org() %>' , '<%= imgS.getSend_img_name_sav() %>');">
                            		<b><%=imgS.getSend_img_name_org()%></b>
                            		
                           		<script>
							 		function fileDownload(nameori, namesav) {
							 			const url = "<%=request.getContextPath() %>/sendFile/filedown";
							 			
							 			let oName = encodeURIComponent(nameori);
							 			let sName = encodeURIComponent(namesav);
							 			
							 			location.assign(url + "?nameori=" + oName + "&namesav=" + sName);
							 		}
							 	</script>
							
                            		<% } else { %>
										<b>파일 없음</b>
									<% } %>                     			
                            	</a>
                        </div>
                        <div id="msg_contents1">
                            <b>보낸날짜 : &nbsp;</b> <b><%=sendmessage.getSend_date()%></b>
                        </div>
					</div>
                            
                    <!-- 이게 보낸 내용 -->
                    <div id="msg_contents2">
                    <textarea name="receiveContents" cols="100" rows="25" readonly style="resize: none;"><%=sendmessage.getSend_body()%></textarea>
                    </div>
				</div>
			</div>
		</form>
	</div>
</section>
<%@ include file="/views/common/footer.jsp"%></>