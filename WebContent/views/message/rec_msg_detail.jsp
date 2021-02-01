<%@page import="com.care.mvc.message.model.vo.ReceiveMessageImg"%>
<%@page import="com.care.mvc.message.model.vo.SendMessage"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.care.mvc.message.model.vo.ReceiveMessage"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<link rel="stylesheet" href="../../css/style.css">
<%
	ReceiveMessage receivemessage = (ReceiveMessage)request.getAttribute("recmessage");
	ReceiveMessageImg imgR	= (ReceiveMessageImg)request.getAttribute("imgR");
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
					<div id="msg_2-2-body" class="writeToAttach">
                        <div id="msg_contents1">             <!-- 여기 a태그 없앨지 있을지 여부 -->
                            <b>보낸사람 : &nbsp;</b> <a href="#"></a><b><%=receivemessage.getSend_id()%></b></a>
                        </div>
                        <div id="msg_contents1">
                        
                            <b>받은파일 : &nbsp;</b> 
                            		<% if(imgR.getRec_img_name_org() != null) { %>
                            	<a href="javascript:fileDownload('<%= imgR.getRec_img_name_org() %>','<%= imgR.getRec_img_name_sav() %>');">  
                            		<b><%=imgR.getRec_img_name_org()%></b>
                            		
                            		<script>
                            			function fileDownload(nameori, namesav) {
                            				const url ="<%=request.getContextPath() %>/receiveFile/filedown";
                            				
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
                            <b>받은날짜 : &nbsp;</b> <b><%=receivemessage.getRec_date()%></b>
                        </div>
					</div>
                            
                    <!-- 이게 보낸 내용 -->
                    <div id="msg_contents2">
                    	<textarea name="receiveContents" cols="100" rows="25"readonly style="resize: none;display: block; margin-block: 10px;"><%=receivemessage.getRec_body()%></textarea>
                    	<div>
	                    	<input type="button" onclick="checkprofile('<%=receivemessage.getSend_id()%>')" style="border-radius: 20px; background: lightskyblue; margin-left: 685px;" value="매칭">
	                    </div>
                    </div>
				</div>
			</div>
		</form>
	</div>
</section>
<script>
	function checkprofile(id){
		url = "<%=request.getContextPath()%>/match/info?memId=" + id;
		specs = "width = 460px, height = 600px, top=200, left=200, resizable=yes";
		window.open(url, "", specs);
		return false;
	} 
</script>
<%@ include file="/views/common/footer.jsp"%></>