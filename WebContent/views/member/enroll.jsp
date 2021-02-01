<%@page import="com.care.mvc.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Member userId = (Member)request.getAttribute("userId");
%>
<%@ include file="/views/common/header.jsp" %>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
<script src="<%=request.getContextPath()%>/js/jquery-3.5.1.js"></script>

	<section id="content">
		<div id="enroll-container" style="margin: 0 auto; text-align:center">
			<form name="memberEnrollFrm" action="<%= request.getContextPath()%>/member/enroll" method="post">
				<table class="table table-borderless" style="text-align:left; margin:auto;" >
					<tr>
						<th colspan="2">
							<h2 align="center" style="line-height: 3.0;">회원가입</h2>
						</th>
					</tr>
					<tr>
						<th>이름</th>
						<td><input type="text" name="userName" id="userName" required>
						</td>
					</tr>
					<tr>
						<th>아이디</th>
						<td>
						   <input type="text" name="userId" id="newId" placeholder="아이디(4글자이상)" required> 
						   <input type="button" id="checkDuplicate" value="중복확인">
						</td>
					</tr>
					<tr>
						<th>패스워드</th>
						<td><input type="password" name="userPwd" id="pass1" required>
						</td>
					</tr>
					<tr>
						<th>패스워드확인</th>
						<td><input type="password" id="pass2"></td>
					</tr>
					<tr>
						<th>이메일</th>
						<td><input type="email" name="email" id="email"></td>
					</tr>
					<tr>
						<th>핸드폰번호</th>
						<td><input type="tel" placeholder="e.g. 01012345678"
							name="phone" id="phone" maxlength="11"></td>
					</tr>
					<tr>
						<th>주소</th>
						<td>
							<span style="padding-right:34px">
								<input type="text" name="postalAddr" id="postalAddr" placeholder="우편번호" style="width:200px">
							</span>
							<input type="button" id="findPostalAddr" onclick="DaumPostcode()" value="우편번호 검색"><br>
							<div>
								<input type="text" name="addr1" id="addr1" placeholder="주소" style="width:360px">
							</div>
							<!-- 동이름 / 빌딩이름 등이 나온다 -->
							<input type="text" name="addr2" id="addr2" placeholder="상세주소">
							<input type="text" name="addr3" id="addr3" placeholder="참고항목" readonly>
						</td>
					</tr>
					<tr>
						<th>생년월일</th>
						<td><input type="date" name="birth" id="birth"
							min="1930-01-01" max="" value="mm/dd/yyyy"><br> <!-- max 값을 오늘 날짜로 지정 -->
						</td>
					</tr>
					<tr>
						<th>보호자/요양보호사</th>
						<td><select name="role" id="selrole">
								<option disabled selected>선택</option>
								<option value="guardian">보호자</option>
								<option value="caregiver">요양보호사</option>
						</select></td>
					</tr>
				</table>
				<div style="text-align : center;">
				<span style="padding-right : 3rem;">
					<input type="submit" id="enrollSubmit" value="회원가입"> 
				</span>
				<span>
					<input type="reset" value="새로고침">
				</span>
				</div>
			</form>
			<form name="checkIdForm">
				<input type="hidden" name="userId">
			</form>
		</div>
	</section>
	<script>
	//비밀번호, 비밀번호 확인 일치확인
	
	$(document).ready(() => {   
	
	$("#pass2").blur((e) => {
         let pass1 = $("#pass1").val();
         let pass2 = $(e.target).val();
         if(pass1.trim() != pass2.trim()){
            alert("비밀번호가 일치하지 않습니다.");
            $("#pass1").val("");
            $(e.target).val("");
            $("#pass1").focus();
         }
      });  
		
	 $("#checkDuplicate").on("click", () => {
	   let id = $("#newId").val().trim();
	     
	     if (id.length < 4) {
	    	 alert("아이디는 최소 4글자 이상 입력해주세요.")
	    	 
	    	 return;
	     	}
	     
	     const url = "<%=request.getContextPath()%>/member/checkId";
         const title = "duplicate";
         const status = "left=500px, top=100px, width=300px, height=200px";
         
         open("", title, status);
         
         checkIdForm.target = title; 
         checkIdForm.action = url;		
         checkIdForm.method = "post";
         checkIdForm.userId.value = id;
 
         checkIdForm.submit();
         
	 	});
	});
	
    function DaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수
                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }
                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("addr3").value = extraAddr;
                
                } else {
                    document.getElementById("addr3").value = '';
                }
                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('postalAddr').value = data.zonecode;
                document.getElementById("addr1").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("addr2").focus();
            }
        }).open();
    }
</script>

	<%@ include file="/views/common/footer.jsp" %>
