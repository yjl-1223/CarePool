<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/views/common/header.jsp" %>

<section id="searchMatch">

    <h2><i class="fas fa-search"></i> 원하시는 요양보호사님을 찾아보세요</h2>

    <form id="searchMatchForm" action="<%=request.getContextPath()%>/match/list" method="POST">

        <div class="checkbox" id="careTime">
            <h3>돌봄시간</h3>
            <label><input type="checkbox" name="time" value="전체" id="allTimes">전체</label>
            <label><input type="checkbox" name="time" value="주중">주중</label>
            <label><input type="checkbox" name="time" value="주말">주말</label>
            <label><input type="checkbox" name="time" value="주간">주간</label>
            <label><input type="checkbox" name="time" value="야간">야간</label>
            <label><input type="checkbox" name="time" value="입주">입주</label>
        </div>

        <div class="radio" id="careGen">
            <h3>요양보호사 성별</h3>
            <label><input type="checkbox" name="gender" value="남">남</label>
            <label><input type="checkbox" name="gender" value="여">여</label>
        </div>

        <div class="checkbox" id="careQual">
            <h3>자격증 유무</h3>
            <label><input type="checkbox" name="qual" value="없음" id="noQual">없음</label>
            <label><input type="checkbox" name="qual" value="요양보호사">요양보호사</label>
            <label><input type="checkbox" name="qual" value="사회복지사">사회복지사</label>
            <label><input type="checkbox" name="qual" value="간호조무사">간호조무사</label>
            <label><input type="checkbox" name="qual" value="간호사">간호사</label>
            <label><input type="checkbox" name="qual" value="물리치료사">물리치료사</label>
            <label><input type="checkbox" name="qual" value="작업치료사">작업치료사</label>
        </div>

        <div class="dropdown" id="careYears">
            <h3>경력사항</h3>
            <label><input type="checkbox" name="years" value="전체" id="allYears">전체</label>
            <label><input type="checkbox" name="years" value="신규">신규</label>
            <label><input type="checkbox" name="years" value="1 ~ 2년">1 ~ 2년</label>
            <label><input type="checkbox" name="years" value="3 ~ 5년">3 ~ 5년</label>
            <label><input type="checkbox" name="years" value="5 ~ 10년">5 ~ 10년</label>
            <label><input type="checkbox" name="years" value="10년 이상">10년 이상</label>
        </div>

        <div class="dropdown" id="carePlace">
            <h3>희망 근무지역</h3>
            <label>
                <select name="sido1" id="sido1"></select>
                <select name="gugun1" id="gugun1"></select>
            </label>

            <label id="addr">
                <!-- <input type="hidden" name="addr"> -->
            </label>
        </div>

        <div class="checkbox" id="carePay">
            <h3>급여지급</h3>
            <label><input type="checkbox" name="pay" value="협의가능">협의가능</label>
            <label><input type="checkbox" name="pay" value="월급">월급</label>
            <label><input type="checkbox" name="pay" value="시급">시급</label>
        </div>

        <h3></h3>
        <div id="searchOptions"></div> <!-- 검색조건 추가/삭제 시 조건을 나열하는 박스 -->
        <h3></h3>

        <button type="submit" class="btn btn-default" id="searchBtn">검색</button>
    </form>

    <script type="text/javascript">
        $(document).ready(function () {
            console.log("doc loaded")
        });

        var arr = [];

        // checkbox
        $('input:checkbox:not("#noQual,#allTimes,#allYears")').change(function () { // :not("noQual") 추가
            let clicked = $(this).val();

            if ($(this).is(':checked')) { // 박스 체크하면 #searchOptions에 val 추가                
                arr.push(clicked);
                $('#searchOptions').html(arr.join(" "));

                console.log("show me");
            } else { // // 박스 체크풀면 #searchOptions에 val 삭제
                const index = arr.indexOf(clicked);

                if (index > -1) {
                    arr.splice(index, 1);
                }

                $('#searchOptions').html(arr.join(" "));

                console.log("hide me");
            }
        });

        // dropdown
        $('select[name="gugun1"]').click(function () { // change() 대신 click()
            let clicked = $(this).val(); // option selected

            let addr = $('select[name="sido1"]').val() + " " + clicked;

            console.log(addr);

            let index = arr.indexOf(addr);

            // 만일 선택한게 "시/도 선택" 혹은 "구/군 선택"이면 #searchOptions에 추가안함
            if (clicked.indexOf("선택") >= 0) {
                return;
            }

            if (index < 0) { // 없다            
                // hidden input 생성
                $('#addr').append('<input type="hidden" name="addr" value="' + addr + '">');

                arr.push(addr);
                $('#searchOptions').html(arr.join(" "));

                console.log("show me");
            } else { // 있다
                // hidden input 삭제
                $('#addr').find('input[value="' + addr + '"]').remove();

                arr.splice(index, 1);
                $('#searchOptions').html(arr.join(" "));

                console.log("hide me");
            }
        });

        // *자격증 없음 클릭시 기타 체크박스 자동 해제 구현 완료*
        // 남은 작업 
        $('#noQual').click(function () { // 없음 클릭
            let none = $(this);

            let others = $('input[name="qual"]:not("#noQual")');

            console.log(others);

            if (none.is(':checked')) { // 없음 체크 
                others.prop('checked', false);

                others.prop('disabled', true);

                console.log("qual none");

                // array
                var values = others.map(function() {
                    return this.value;
                }).get();

                console.log("values : " + values);

                values.forEach((elem) => {
                    const index = arr.indexOf(elem);

                    if (index > -1) {
                        arr.splice(index, 1);
                    }

                    $('#searchOptions').html(arr.join(" "));

                    console.log("hide qual(s)");
                });
            } else { // 없음 체크 해제

                others.prop('disabled', false);

                console.log("list more qual");
            }
        });





        $('#allTimes').click(function () { // 전체 클릭
            let all = $(this);

            let others = $('input[name="time"]:not("#allTimes")');

            console.log(others);

            if (all.is(':checked')) { // 전체 체크 
                others.prop('checked', true);

                console.log("all times selected");

            // array
            var values = others.map(function() {
                return this.value;
            }).get();

            console.log("values : " + values);

            values.forEach((elem) => {
            // 기존에 없으면 추가
                const index = arr.indexOf(elem);

                if (index < 0) { // 없다
                    arr.push(elem);
                } 
            });

            $('#searchOptions').html(arr.join(" "));
            } else { // 전체 체크 해제
                others.prop('checked', false);

                console.log("all times unselected");

                // array
                var values = others.map(function() {
                    return this.value;
                }).get();

                console.log("values : " + values);

                values.forEach((elem) => {
                // 기존에 있으면 삭제
                    const index = arr.indexOf(elem);

                    if (index > -1) { // 있다
                        arr.splice(index, 1);
                    }
                });

                $('#searchOptions').html(arr.join(" "));
            }
        });




        $('#allYears').click(function () { // 전체 클릭
            let all = $(this);

            let others = $('input[name="years"]:not("#allYears")');

            console.log(others);

            if (all.is(':checked')) { // 전체 체크 
                others.prop('checked', true);

                console.log("all Years selected");

            // array
            var values = others.map(function() {
                return this.value;
            }).get();

            console.log("values : " + values);

            values.forEach((elem) => {
            // 기존에 없으면 추가
                const index = arr.indexOf(elem);

                if (index < 0) { // 없다
                    arr.push(elem);
                } 
            });

            $('#searchOptions').html(arr.join(" "));
            } else { // 전체 체크 해제
                others.prop('checked', false);

                console.log("all years unselected");

                // array
                var values = others.map(function() {
                    return this.value;
                }).get();

                console.log("values : " + values);

                values.forEach((elem) => {
                // 기존에 있으면 삭제
                    const index = arr.indexOf(elem);

                    if (index > -1) { // 있다
                        arr.splice(index, 1);
                    }
                });

                $('#searchOptions').html(arr.join(" "));
            }
        });
    </script>

    <script type="text/javascript">
        $('document').ready(function () {
            var area0 = ["시/도 선택", "서울특별시", "인천광역시", "대전광역시", "광주광역시", "대구광역시", "울산광역시", "부산광역시", "경기도", "강원도", "충청북도", "충청남도", "전라북도", "전라남도", "경상북도", "경상남도", "제주도"];
            var area1 = ["강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "구로구", "금천구", "노원구", "도봉구", "동대문구", "동작구", "마포구", "서대문구", "서초구", "성동구", "성북구", "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구"];
            var area2 = ["계양구", "남구", "남동구", "동구", "부평구", "서구", "연수구", "중구", "강화군", "옹진군"];
            var area3 = ["대덕구", "동구", "서구", "유성구", "중구"];
            var area4 = ["광산구", "남구", "동구", "북구", "서구"];
            var area5 = ["남구", "달서구", "동구", "북구", "서구", "수성구", "중구", "달성군"];
            var area6 = ["남구", "동구", "북구", "중구", "울주군"];
            var area7 = ["강서구", "금정구", "남구", "동구", "동래구", "부산진구", "북구", "사상구", "사하구", "서구", "수영구", "연제구", "영도구", "중구", "해운대구", "기장군"];
            var area8 = ["고양시", "과천시", "광명시", "광주시", "구리시", "군포시", "김포시", "남양주시", "동두천시", "부천시", "성남시", "수원시", "시흥시", "안산시", "안성시", "안양시", "양주시", "오산시", "용인시", "의왕시", "의정부시", "이천시", "파주시", "평택시", "포천시", "하남시", "화성시", "가평군", "양평군", "여주군", "연천군"];
            var area9 = ["강릉시", "동해시", "삼척시", "속초시", "원주시", "춘천시", "태백시", "고성군", "양구군", "양양군", "영월군", "인제군", "정선군", "철원군", "평창군", "홍천군", "화천군", "횡성군"];
            var area10 = ["제천시", "청주시", "충주시", "괴산군", "단양군", "보은군", "영동군", "옥천군", "음성군", "증평군", "진천군", "청원군"];
            var area11 = ["계룡시", "공주시", "논산시", "보령시", "서산시", "아산시", "천안시", "금산군", "당진군", "부여군", "서천군", "연기군", "예산군", "청양군", "태안군", "홍성군"];
            var area12 = ["군산시", "김제시", "남원시", "익산시", "전주시", "정읍시", "고창군", "무주군", "부안군", "순창군", "완주군", "임실군", "장수군", "진안군"];
            var area13 = ["광양시", "나주시", "목포시", "순천시", "여수시", "강진군", "고흥군", "곡성군", "구례군", "담양군", "무안군", "보성군", "신안군", "영광군", "영암군", "완도군", "장성군", "장흥군", "진도군", "함평군", "해남군", "화순군"];
            var area14 = ["경산시", "경주시", "구미시", "김천시", "문경시", "상주시", "안동시", "영주시", "영천시", "포항시", "고령군", "군위군", "봉화군", "성주군", "영덕군", "영양군", "예천군", "울릉군", "울진군", "의성군", "청도군", "청송군", "칠곡군"];
            var area15 = ["거제시", "김해시", "마산시", "밀양시", "사천시", "양산시", "진주시", "진해시", "창원시", "통영시", "거창군", "고성군", "남해군", "산청군", "의령군", "창녕군", "하동군", "함안군", "함양군", "합천군"];
            var area16 = ["서귀포시", "제주시", "남제주군", "북제주군"];

            // 시/도 선택 박스 초기화
            $("select[name^=sido]").each(function () {
                $selsido = $(this);
                
                $.each(eval(area0), function () {
                    $selsido.append("<option value='" + this + "'>" + this + "</option>");
                });

                $selsido.next().append("<option value=''>구/군 선택</option>");
            });

            // 시/도 선택시 구/군 설정
            $("select[name^=sido]").change(function () {
                var area = "area" + $("option", $(this)).index($("option:selected", $(this))); // 선택지역의 구군 Array
                var $gugun = $(this).next(); // 선택영역 군구 객체
                $("option", $gugun).remove(); // 구군 초기화

                if (area == "area0")
                    $gugun.append("<option value=''>구/군 선택</option>");
                else {
                    $.each(eval(area), function () {
                        $gugun.append("<option value='" + this + "'>" + this + "</option>");
                    });
                }
            });
        });
    </script>
</section>

<%@ include file="/views/common/footer.jsp" %>
