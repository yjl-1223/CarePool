<%@page import="com.care.mvc.care.model.vo.Care"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>

<%		
	List<Care> profiles = (ArrayList) request.getAttribute("profiles");
%>

<style>
</style>

<section id="matchPage">

    <h2><i class="far fa-envelope"></i> 검색하신 요양보호사님께 문의해보세요</h2>
    <div id="searchResult">
    <% if (profiles == null || profiles.isEmpty()) { %>
    		<!-- 검색결과 없음 -->
    <% } else {
    		for (Care care : profiles) { %>
    			<div class="profile">    				    				
                    <img class="profPic" onclick="loadProf('<%= care.getMemId() %>');" 
                    	src="<%= request.getContextPath() %>/upload/carephoto/<%= care.getCareImg().getImgNameSav() %>" alt="">

                    <h5><%= care.getMemName() %> (<%= care.getMemId() %>)</h5>
                    <button class="sendMsg" onclick='location.href="<%= request.getContextPath() %>/msg/write?memId=<%= care.getMemId() %>"'>쪽지보내기</button>
                </div>
   		 <% }
      } %>
    </div>

    <button id="backBtn" onclick='location.replace("<%= request.getContextPath() %>/match/search");'>검색페이지로</button>

    <script type="text/javascript">
        $(document).ready(function () {
            console.log("doc loaded");
        });        
    </script>
</section>

<%@ include file="/views/common/footer.jsp" %>

<script>
    $('document').ready(function () {
        console.log('doc loaded');
    });

    function loadProf(memId) {
        const url = "<%=request.getContextPath()%>/check/profile?memId=" + memId;
        const specs = "width = 800px, height = 600px, top=200, left=200, resizable=yes";
        window.open(url, "matched profile", specs);
    }
</script>