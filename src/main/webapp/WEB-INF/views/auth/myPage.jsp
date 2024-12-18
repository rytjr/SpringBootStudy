<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>My Page</title>
</head>
<body>
<form action = "/auth/myPage" method = "post">
    <div class = "col-6 offset-3 py-5 my-3 text-center">
        <h2 class = "text-center my-3">MyPage</h2>
        <div class="card" style="width:400px; margin:auto">
          <img class="card-img-top" src="/images/avatar.png" alt="Card image">
          <div class="card-body">
            이름<input value = "${user.getName()}" name = "name" id = "name"><br>
            아이디<input value = "${user.getUserId()}" name = "userId" id = "userId"><br>
            전화번호<input value = "${user.getHp1()}" name = "hp1" id = "hp1" style = "width:45px; height:30px"> -
            <input value = "${user.getHp2()}" name = "hp2" id = "hp2" style = "width:45px; height:30px"> -
            <input value = "${user.getHp3()}" name = "hp3" id = "hp3" style = "width:45px; height:30px"><br>
            가입날짜<input value = "${user.getIndate()}" name = "indate" id = "indate"><br>
            <input type = "hidden" value = "${user.getNo()}" name = "no" id = "no">
            <input type = "hidden" value = "${user.getPasswd()}" name = "passwd" id = "passwd">
            <button>Modify</button>
          </div>
        </div>
    </div>
</form>


</body>
</html>