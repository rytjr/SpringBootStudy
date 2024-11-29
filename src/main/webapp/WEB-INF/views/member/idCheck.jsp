<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
</head>
<body>
<div class = "col-8 offset-2 py-4">
    <form id = "idf" action = "/idCheck" method = "post">
        <label for"userId">아이디</label>
        <input type = "text" name = "userId" id = "userId" class = "form-control"
                placeholder = "ID" autofocus = "autofocus">

        <button class = "btn btn-success">확인</button>
    </form>
</div>

</body>
</html>