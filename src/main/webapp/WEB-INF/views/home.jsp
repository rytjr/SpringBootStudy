<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class = "container">
    <h1>Home</h1>
    <h2>안녕 spring mybatis</h2>

    <hr>
    <h1 style = "color:red">${message}</h1>
    <h1 class = "text-success">전체 회원수 : ${userCount}</h1>

    <h1 class = "text-danger">로그인한 회원 명 : ${loginUser.getName()}</h1>
    <h1 class = "text-primary">JSessionId : ${pageContext.session.id}</h1>
</div>