<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>My Page</title>
</head>
<body>

<div class = "col-6 offset-3 py-5 my-3 text-center">
    <h2 class = "text-center my-3">MyPage</h2>
    <div class="card" style="width:400px; margin:auto">
      <img class="card-img-top" src="/images/avatar.png" alt="Card image">
      <div class="card-body">
        <h4 class="card-title">${user.getName()}</h4>
        <p class="card-text">
            회원번호 : ${loginUser.no}<br>
            아이디 : ${loginUser.userId}<br>
            연락처 : ${user.getAllHp()}<br>
            가입일 : ${user.getIndate()}<br>
        </p>
        <a href="#" class="btn btn-primary">Modify</a>
      </div>
    </div>
</div>



</body>
</html>