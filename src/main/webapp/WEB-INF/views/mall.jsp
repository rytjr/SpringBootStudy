<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Metabuild Shop</title>
  <style>
    .btn2{
        border-radius : 20px;
        color : white;
        background-color: red;
        border: none;
        width : 180px;
        height : 50px;
        font-size : 20px;
        float : right;
    }
  </style>
</head>
<body>
    <button class = "btn2" onclick = "location.href='/auth/wishList'">위시리스트 보기</button>
    <!-- Hit 상품 진열 -->
    <%@ include file = "/WEB-INF/views/inc/mallHit.jspf" %>
    <hr>
    <!-- 신 상품 진열 -->
    <%@ include file = "/WEB-INF/views/inc/mallNew.jspf" %>
</body>
</html>