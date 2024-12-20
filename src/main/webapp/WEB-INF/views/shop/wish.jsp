<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>board List</title>
  <style>
    ul.boardList{
        margin: 20px auto;
    }
    ul.boardList li{
        list-style:none;
        border-bottom: 1px solid #ddd;
        height : 35px;
        line-height : 35px;
        float:left;
    }
    .boardList li:nth-child(4n + 1){
        width:20%;
    }
    .boardList li:nth-child(4n + 2){
        width:40%;
        text-align:left;
    }
    .boardList li:nth-child(4n + 3){
        width:20%;
    }
    .boardList li:nth-child(4n){
        width:20%;
    }
    .clear{
        clear:both;   /*float해제*/
    }
    .divTotal{
        margin:20px auto;
        display:flex;
        justify-content: space-around;
    }
    a:link, a:visited{
        text-decoration:none;
        color:gray;
    }
  </style>
</head>
<body>
    <div class = "col-10 offset-1 py-4">
        <h1 class = "text-center my-3">Spring Board List</h1>
        <br>
        <div class = "text-center">
        </div>
        <ul class = "boardList">
            <li>상품 번호</li>
            <li>상품 이름</li>
            <li>상품 원래 가격</li>
            <li>상품 할인 가격</li>
            <c:if test = "${wishList == null or empty wishList}">
                <li style = "width:100%">
                    데이터가 없습니다.
                </li>
            </c:if>
            <c:if test = "${wishList != null and not empty wishList}">
            <!--  --------- ------------------->
                <c:forEach var = "wish" items = "${wishList}">
                    <li><c:out value = "${wish.getPnum()}"/></li>
                    <li><a href = "/prodDetail?pnum = <c:out value = '${wish.getPnum()}'/>"><c:out value = "${wish.getPname()}"/></a></li>
                    <li><c:out value = "${wish.getPrice()}"/></li>
                    <li><c:out value = "${wish.getSalePrice()}"/></li>
                </c:forEach>
            <!--  --------- ------------------->
            </c:if>
        </ul>
    </div><!-- .col end -->
</body>
</html>