<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Metabuild Shop</title>
</head>
<body>
    <h1>Shop</h1>
    <!-- Hit 상품 진열 -->
    <%@ include file = "/WEB-INF/views/inc/mallHit.jspf" %>
    <hr>
    <!-- 신 상품 진열 -->
    <%@ include file = "/WEB-INF/views/inc/mallNew.jspf" %>
</body>
</html>