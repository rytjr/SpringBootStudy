<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Title</title>
  <style>
    #header,#footer,.navbar{
        display : none;
    }
  </style>
  <script>
    function setId(uid){
        //부모창의 input userId값에 uid값을 전달해야 한다.
        //window>document>forms>input,select,textarea
        //opener  --> 부모창을 참조(window객체)
        //self --> 자기창(팝업창 - window객체)
        //forms[0] --> 검색 폼
        //opener.document.getElementsById('userId').value = uid 이렇게 해도 됨
        opener.document.forms[1].userId.value = uid;
        self.close();
    }
  </script>
</head>
<body>
<div class = "col-8 offset-2 py-4">
    <div class = "alert alert-danger my-3">
        <h3>${message}</h3>
        <c:if test = "${result == 'ok'}">
            <button class = "btn btn-info" onclick = "setId('${userId}')">아이디 사용하기</button>
        </c:if>
    </div>
    <form id = "idf" action = "/idCheck" method = "post">

        <label for"userId">아이디</label>
        <input type = "text" name = "userId" id = "userId" class = "form-control"
                placeholder = "ID" autofocus = "autofocus">

        <button class = "btn btn-success">확인</button>
    </form>
</div>

</body>
</html>