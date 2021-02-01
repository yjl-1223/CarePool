<%@page import="com.care.mvc.GuardAndPatient.model.vo.Guard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String memId = (String)request.getAttribute("memId");    	
	Guard guard = (Guard)request.getAttribute("guard");
%>
    
<%@ include file="/views/common/header.jsp" %>
    <section>
        <div class="patient_details">
            <form action="<%=request.getContextPath()%>/modify/guardProfile" method="POST">
                    <div>
                        <h1>보호자용 프로필 등록</h1>
                        <img src="../image/병아리당황.png" style="width:200px; height: 200px;">
                    </div>
                    <div>
                        <label><input type="checkbox" name="same" value="보호자이자 환자입니다.">보호자이자 환자입니다.</label>
                    </div>
                    <div>
                        <div><h3>보호자 성별</h3></div>
                        <div>
                            <label><input type="radio" name="gender" value="남">남</label>
                            <label><input type="radio" name="gender" value="여">여</label>
                        </div>
                    </div>
                    <div>
                    	<div><h3>돌봄 장소</h3></div>
                        <div id="check1">
                            <label><input type="radio" name="place" value="자택">자택</label>
                            <input type="text" name="place1" id="place_home" placeholder="주소를 입력해주세요" required>
                            <input type="button" id="findPostalAddr" onclick="DaumPostcode()" value="찾기" style="margin:10px">
                        </div>
                    </div>
                    <div>
                        <div id="check2">
                            <label><input type="radio" name="place" value="병원">병원</label>
                            <input type="text" name="place1" id="place_hospital" placeholder="병원명을 입력해주세요">
                            <input type="button" id="findPostalAddr" onclick="DaumPostcode1()" value="찾기" style="margin:10px">
                        </div>
                    </div>
                    <div>
                        <h3>돌봄기간</h3>
                        <div>
                        <input type="date" name="period1">
                        <span> - </span>
                        <input type="date" name="period2">
                        </div>
                    </div>
                    <div>
                        <h4>희망시간대 :</h4>
                        <input type="time" name ="hopetime">
                    </div>
                    <div>
                        <h3>환자 정보</h3>
                       	<div>
                           	<input type="text" name="patName" placeholder="이름:" style="width:100px">
                           	<input type="text" name="patAge" placeholder="나이" style="width:80px">
                            <label><input type="text" name="patKg" placeholder="몸무게" style="width:100px"><b>kg</b></label>
                       	</div>
                        <div>
                            <label><input type="radio" name="patGen" value="남">남</label>
                            <label><input type="radio" name="patGen" value="여">여</label>
                        </div>
                    </div>
                    <div>
                        <h3>감염성 질환 체크</h3>
                        <!-- 없음 체크했을때 나머지 disabled 되게끔 만들기 -->
                        <label><input type="checkbox" name="patInfact" value="없음"></label>없음
                        <div>
                            <label><input type="checkbox" name="patInfact" value="VRE">VRE</label>
                            <label><input type="checkbox" name="patInfact" value="CRE">CRE</label>
                            <label><input type="checkbox" name="patInfact" value="결핵">결핵</label>
                        </div>
                        <div>
                            <label><input type="checkbox" name="patInfact" value="움">움</label>
                            <label><input type="checkbox" name="patInfact" value="독감">독감</label>
                            <label><input type="checkbox" name="patInfact" value="감기">감기</label>
                        </div>
                        <div>
                            <textarea name="patInfact1" cols="40" rows="3" placeholder="내용을 입력해주세요"></textarea>
                        </div>
                    </div>
                    <div>
                        <h3>장기요양등급</h3>
                            <select name="patGrade" id="patGrade">
                                <option>-----</option>
                                <option value="등급없음">등급없음</option>
                                <option value="1등급">1등급</option>
                                <option value="2등급">2등급</option>
                                <option value="3등급">3등급</option>
                                <option value="4등급">4등급</option>
                                <option value="5등급">5등급</option>
                                <option value="인지자원등급">인지자원등급</option>
                                <option value="등급신청 중">등급신청 중</option>
                            </select>
                    </div>
                    <div>
                        <h3>개인위생관리</h3>
                        <div>
                            <label><input type="checkbox" name="patSanit" value="스스로 가능">스스로 가능</label>
                            <label><input type="checkbox" name="patSanit" value="화장실에서 도움 필요">화장실에서 도움 필요</label>
                        </div>
                        <div>
                            <label><input type="checkbox" name="patSanit" value="전적인 도움필요">전적인 도움필요</label>
                            <label><input type="checkbox" name="patSanit" value="침대에서 도움 필요">침대에서 도움 필요</label>
                        </div>
                    </div>
                    <div>
                        <h3>마비상태</h3>
                        <div>
                            <label><input type="radio" name="patParal" value="전신마비">전신마비</label>
                            <td><label><input type="radio" name="patParal" value="편마비">편마비</label></td>
                            <td><label><input type="radio" name="patParal" value="없음">없음</label></td>
                        </div>
                    </div>
                    <div>
                    <h3>거동상태</h3>
                        <label><input type="radio" name="patMove" value="불가능">불가능</label>
                        <label><input type="radio" name="patMove" value="부축 필요">부축 필요</label>
                        <label><input type="radio" name="patMove" value="홀로 가능">홀로 가능</label>
                    </div>
                    <div>
                        <h3>욕창 환자 여부</h3>
                        <label><input type="radio" name="patBed" value="네">네</label>
                        <label><input type="radio" name="patBed" value="아니요">아니요</label>
                    </div>
                    <div>
                        <h3>인지장애 여부</h3>
                        <label><input type="checkbox" name="patCogdis" value="치매">치매</label>
                        <label><input type="checkbox" name="patCogdis" value="섬망">섬망</label>
                        <label><input type="checkbox" name="patCogdis" value="없음">없음</label>
                    </div>
                    <div>
                        <h3>화장실 이동 시</h3>
                        <label><input type="radio" name="patBathroom" value="부축 필요">부축 필요</label>
                        <label><input type="radio" name="patBathroom" value="스스로 이동 가능">스스로 이동가능</label>
                    </div>
                    <div>
                        <h3>배변도구</h3>
                        <label><input type="checkbox" name="patBowelMn" value="기저귀">기저귀</label>
                        <label><input type="checkbox" name="patBowelMn" value="소변줄">소변줄</label>
                        <label><input type="checkbox" name="patBowelMn" value="없음">없음</label>
                    </div>
                    <div>
                        <h3>장루 설치 여부</h3>
                        <label><input type="radio" name="patOstomy" value="네">네</label>
                        <label><input type="radio" name="patOstomy" value="아니요">아니요</label>
                    </div>
                    <div>
                        <h3>식사 도움 여부</h3>
                        <label><input type="radio" name="patHelpEat" value="도움필요">도움 필요</label>
                        <label><input type="radio" name="patHelpEat" value="스스로 가능">스스로 가능</label>
                    </div>
                    <div>
                        <h3>석션 사용 여부</h3>
                        <label><input type="radio" name="patSuction" value="사용">사용</label>
                        <label><input type="radio" name="patSuction" value="사용안함">사용안함</label>
                    </div>
                    <div>
                        <h3>요양보호사 우대 성별</h3>
                        <label><input type="radio" name="patGuardGen" value="남">남</label>
                        <label><input type="radio" name="patGuardGen" value="여">여</label>
                    </div>
                    <div>
                        <h3>기타사항(선택)</h3>
                        <textarea name="patEtc" id="paptEtc" cols="50" rows="4"></textarea>
                    </div>
                <input type="submit" value="등록하기">
                <input type="reset" value="취소하기">
                <input type="hidden" name="userId" value=<%= memId %>>
                <input type="hidden" name="guardNo" value=<%= guard.getGuard_no()%>>
            </form>
        </div>
    </section>
    <script>
    
    function DaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }
                
                document.getElementById("place_home").value = addr;
            }
        }).open();
    }
    function DaumPostcode1() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }
                
                document.getElementById("place_hospital").value = addr;
            }
        }).open();
    }
    
    $(document).ready(function() {    
        $('#check1 input:radio').change(function() {
            $('#check2 input:text').attr("disabled", true);
            $('#check2 input:button').attr("disabled", true);
            $('#check1 input:text').attr("disabled", false);
            $('#check1 input:button').attr("disabled", false);
        });
        $('#check2 input:radio').change(function() {
            $('#check2 input:text').attr("disabled", false);
            $('#check2 input:button').attr("disabled", false);
            $('#check1 input:text').attr("disabled", true);
            $('#check1 input:button').attr("disabled", true);
        });
    });
    </script>


<%@ include file="/views/common/footer.jsp" %>