<%@page import="com.care.mvc.GuardAndPatient.model.vo.Patient"%>
<%@page import="com.care.mvc.GuardAndPatient.model.vo.Guard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Guard guard = (Guard)request.getAttribute("guard");
	Patient patient = (Patient)request.getAttribute("patient");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="./05.jQuery/js/jquery-3.5.1.js"></script>
    <style>
        body input[type=text]{
            border: none;
            text-align: center;
            font-size:30px
        }
        .match-container{
            text-align: center;
             margin: auto;
             box-sizing: border-box;
        }

        .match-container h2{
            background: beige
        }

        #checkProfile{
            display: block;
        }
    </style>
</head>
<body>
    <div class="match-container">
        <form action="<%=request.getContextPath()%>/match/info" method="post">
            <table style="margin:auto; font-size: 25px; width: 100%;">
                    <tr>
                        <td>
                        	<h2>보호자성함</h2>
                        </td>
                    </tr>
                    <tr>
                        <td>
                        	<input type="text" name="guardName" readonly value='<%=guard.getGuardName()%>'>
                        </td>
                    </tr>
                    <tr>
                        <td><h2>돌봄장소</h2></td>
                    </tr>
                    <tr>
                        <td><input type="text" name="pat_place" readonly value='<%=patient.getPat_place()%>'></td>
                    </tr>
                    <tr>
                        <td><h2>돌봄시작일</h2></td>
                    </tr>
                    <tr>
                        <td>
                            <input type="date" name="care_start">
                        </td>
                    </tr>
                    <tr>
                        <td><h2>돌봄 종료일</h2></td>
                    </tr>
                    <tr>
                        <td>
                            <input type="date" name="care_end">
                        </td>
                    </tr>
                    <tr>
                        <td>
                        	<h2>기타 요청사항</h2>
                       	</td>
                    </tr>
                    <tr>
                        <td>
                            <textarea name="etc" id="" cols="25" rows="5" style="resize: none;"></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="button" id="checkProfile" value="프로필 확인" style="width: 200px; background: rgb(255, 229, 190); margin: auto; height: 40px; border-radius: 20px; cursor: pointer;">
                      	</td>
                    </tr>
            </table>
            <div style="line-height: 100px;">
         	<input type="button" value="취소" style= "color:red;">
         	<span style="padding-right:30px"></span>
           	<input type="submit" value="매칭요청">
           	</div>
        </form>
    </div>
</body>
</html>