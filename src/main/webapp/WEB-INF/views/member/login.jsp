<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

    <%--
        el 표현식을 이용한 쿠키 출력
        ${cookie}  --> Map객체
        ${cookie.키} --> cookie 객체
        ${cookie.키이름.value} --> String 쿠키에 저장한 value값
     --%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Login</title>
</head>
<body>
<div class = "login-wrap col-6 offset-3 py-4 my-4 ">
    <form name = "loginF" action="login" method = "post" class = "form" id = "form">
        <table width = "20%" height = "60px" class = "table">
            <tr>
                <td ><h1 class = "text-center my-2">Login</h1></td>

            </tr>
            <tr>
                <td >아이디</td>
                <td class = "form-control"><input type = "text" value = "${cookie.uid.value}"
                name = "userId" id = "userId" placeholder = "ID" required></td>
            </tr>
            <tr>
                <td >비밀번호</td>
                <td class = "form-control"><input type = "text" name = "passwd" id = "passwd" placeholder = "Password" required></td>
            </tr>



        </table>
        <div id = "div">
            <input type="checkbox" name = "saveId" id = "saveId"
            <c:if test = "${cookie.uid != null}">
            checked
            </c:if>
            >아이디 저장
            <button class = "btn btn-warning">Login</button>
        </div>
    </form>
</div>
</body>
</html>