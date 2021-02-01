<%@page import="com.care.mvc.message.model.vo.SendMessage"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String sendTo = (String) request.getAttribute("sendTo");
%>   
 
<%@ include file="/views/common/header.jsp" %>
<link rel="stylesheet" href="../../css/style.css">
<section>
    <div id="msg_container">
        <form action="<%=request.getContextPath()%>/msg/write" method="post"
                enctype="multipart/form-data">
            <div id="msg_1">
                <!-- <h1>CAREPOOL 쪽지</h1> -->
            </div>
            <div id="msg_2">
                <div id="msg_2-1">
                    <div id="write_msg">
                        <a href="<%= request.getContextPath()%>/msg/write">
                            쪽지쓰기
                        </a>
                    </div>
                    <div>
                        <a href="<%= request.getContextPath()%>/msg/get">
                            받은쪽지함
                        </a>
                    </div>
                    <div>
                        <a href="<%= request.getContextPath()%>/msg/send">
                            보낸쪽지함
                        </a>
                    </div>
                </div>
                <div id="msg_2-2">

                    <div class="writeToAttach">
                        <!-- <div id="msg_2-2-header"> -->
                        <div id="recipient">
                            <label style="font-size: large;">받는 사람</label>
                            <% if (sendTo != null && sendTo.length() > 0) { %>
                            <input type="text" name="rev_id" value="<%= sendTo %>">
                            <% } else { %>
                            <input type="text" name="rev_id" placeholder="아이디를 입력해주세요">
                            <% } %>
                        </div>
                        <!-- <div id="msg_2-3-header"> -->
                        <div id="attachFile">
                            <label for="formFile" class="form-label">파일 첨부</label>
                            <input class="wirte_file" type="file" id="writeimg" name="messageimg" style="width: fit-content;">
                        </div>
                    </div>

                    <div id="msg_2-2-section">
                        <div id="msgBody" style="margin-left: 35px;">
                            <textarea name="msg_contents" id="msg_content" cols="100" rows="25" placeholder="내용을 입력해주세요" style="resize: none;"></textarea>
                            <input type="submit" value="보내기">
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</section>
    <%@ include file="/views/common/footer.jsp" %>
