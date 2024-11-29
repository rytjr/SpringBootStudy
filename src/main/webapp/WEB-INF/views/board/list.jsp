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
    .boardList li:nth-child(5n + 1){
        width:10%;
    }
    .boardList li:nth-child(5n + 2){
        width:40%;
        text-align:left;
    }
    .boardList li:nth-child(5n + 3){
        width:20%;
    }
    .boardList li:nth-child(5n + 4){
        width:20%;
    }
    .boardList li:nth-child(5n){
        width:10%;
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
            <a href = "form">글쓰기</a> | <a href = "/main">Home</a>
        </div>
        <!--검색 form ----------------------------------------------------------------->

        <!--검색 form ----------------------------------------------------------------->
        <ul class = "boardList">
            <li>글번호</li>
            <li>글제목</li>
            <li>작성자</li>
            <li>작성일</li>
            <li>조회수</li>
            <c:if test = "${boardList == null or empty boardList}">
                <li style = "width:100%">
                    데이터가 없습니다.
                </li>
            </c:if>
            <c:if test = "${boardList != null and not empty boardList}">
            <!--  --------- ------------------->
                <c:forEach var = "board" items = "${boardList}">
                    <li><c:out value = "${board.id}"/></li>
                    <li>
                    <a href = "view?id=<c:out value = '${board.id}'/>"><c:out value = "${board.title}"/></a>
                    <!-- eq : ==, ne : != -->
                    <c:if test = "${board.fileName ne null}">
                        <img src = "/images/attach.png style = :width:24px">
                    </c:if>
                    </li>
                    <li><c:out value = "${board.userId}"/></li>
                    <li>
                    <%-- yy : 연도 MM : 월 dd : 일 HH : 시 mm : 분 ss : 초 --%>
                    <%-- oracle 날짜 포맷 -> yy : 연도 mm : 월 dd : 일 hh : 시 mi : 분 ss : 초 --%>
                    <%-- mysql 날짜 포맷 -> %Y(년도4자리) %y(년도 2자리) %m : 월 %d : 일 %H : 시 %i : 분 %s : 초 --%>
                        <fmt:formatDate value = "${board.wdate}" pattern = "yy-MM-dd" />
                    </li>
                    <li><c:out value = "${board.readNum}"/></li>
                </c:forEach>
            <!--  --------- ------------------->
            </c:if>
        </ul>
        <div class = "clear"></div>
        <div class = "divTotal">
            <span>총 게시글 수 : ${totalCount}개</span>
            <span>  1 page / 3 pages </span>
        </div>
        <div></div>
    </div><!-- .col end -->
</body>
</html>