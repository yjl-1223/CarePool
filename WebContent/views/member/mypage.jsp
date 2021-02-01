<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Member member = (Member)request.getAttribute("member");

%>
<%@ include file="/views/common/header.jsp" %>
<section id="content">

	<form id="page_container" action="<%= request.getContextPath()%>/member/mypage"
		method="get">
			<div id="my_text">
				<h1></h1>
			</div>
			<div class="outer">
				<div class="my_page" id="my_im">
					<img src="../image/병아리당황.png">
					<div id="my_name"><%=member.getMemName()%> 
					<%if(member.getMemRole().equals("guardian")) { %>
					보호자님</div>
					<% }else if(member.getMemRole().equals("caregiver")) { %>
					보호사님</div>
					<% } else { %>
					님</div>
					<%} %>
					<div>
					<input type="button" value="프로필 수정하기" onclick="location.href='<%=request.getContextPath()%>/profile/view?memId=<%=member.getMemId()%>';">
					</div>
				</div>

				<div class="my_page" id="my_infor">
					<div class="my_page" id="my_em">이메일 : <%=member.getMemEmail()%></div>
					<div class="my_page" id="my_num">전화번호 : <%=member.getMemPhone().substring(0,2)%> -
					<%=member.getMemPhone().substring(2,6)%> - <%=member.getMemPhone().substring(6,10)%></div>
					<div class="my_page" id="my_addr">주소 : <%=member.getMemAddr() %></div>
				</div>
				<div id="my_match">
					<h3>최근매칭내역</h3>
					<table>
						<thead>
							<tr>
								<th>매칭날짜</th>
								<th>이름</th>
								<th>이름</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><a href="#">date</a></td>
								<td>name</td>
								<td>name</td>
							</tr>
							<tr>
								<td><a href="#">date</a></td>
								<td>name</td>
								<td>name</td>
							</tr>
							<tr>
								<td><a href="#">date</a></td>
								<td>name</td>
								<td>name</td>
							</tr>
							<tr>
								<td><a href="#">date</a></td>
								<td>name</td>
								<td>name</td>
							</tr>
							<tr>
								<td><a href="#">date</a></td>
								<td>name</td>
								<td>name</td>
							</tr>
						</tbody>
					</table>
				</div>
	</form>
</section>
<script type="text/javascript">
    var member;
    
    function addMember() {
        const name = prompt("닉네임 또는 한줄자기소개 :");
        
        member = {
            name,
            toString: function() {
              return `$(this.name) <br>`;
            }
        }
    }

    function printMember() {
        if(member === null) {
            alert("입력을 먼저 진행해주세요.");
        } else {
            document.getElementById("print").innerHTML = member;
        }
    }
    </script>

    <%@ include file="/views/common/footer.jsp" %>