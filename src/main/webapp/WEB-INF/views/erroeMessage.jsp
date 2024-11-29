<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>error page</title>
</head>
<body>
    <div class = "col-8 offset-2 alert alert-danger py-4 my-4">
        <p>${message}</p>
        <button class = "btn btn-secondary" onclick = "location.href='/main'">돌아가기</button>
    </div>
</body>
</html>