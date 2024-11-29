<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core"%>
<div class = "py-5">
    <h1 class = "text-center my-4">회원 목록 [관리자 페이지 - Admin]</h1>
    <table class = "table table-striped table-hover">
        <tr>
            <th>번호</th>
            <th>이름</th>
            <th>아이디</th>
            <th>연락처</th>
            <th>가입일</th>
            <th>삭제</th>
        </tr>
        <!-- ---------------------------------->
        <c:if test = "${userList == null or empty userList}">
            <tr colspan = "6">
            데이터가 없습니다.
            </tr>
        </c:if>
        <c:if test = "${userList != null and not empty userList}">
        <c:forEach var = "user" items = "${userList}">
            <tr>
                <td>${user.no}</td>
                <td>${user.name}</td>
                <td>${user.userId}</td>
                <td>${user.hp1}-${user.hp2}-${user.hp3}</td>
                <td>${user.indate}</td>
                <td><a href = "javascript:userRemove('${user.no}')">삭제</a></td>
            </tr>
        </c:forEach>
        </c:if>
        <!-- ---------------------------------------------->
    </table>
</div>
<script>
    function userRemove(no) {
        //var b = window.confirm("message") : 확인 다이얼로그 띄우기
        let yn = confirm(no + "번 회원정보를 정말 삭제할까요?");
        if(!yn) return;
        location.href = "/admin/userDelete?no=" + no;
    }
</script>