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
        <c:if test = "${paging.findKeyword != ''}">
            <h3 class = "text-center text-primary">검색어:<c:out value="${paging.findKeyword}"/></h3>
        </c:if>
        <br>
        <div class = "text-center">
            <a href = "form">글쓰기</a> | <a href = "/main">Home</a>
        </div>
        <!--검색 form ----------------------------------------------------------------->
        <div id = "divFind" class = "text-center py-4 my-4">
            <form name = "findF" id = "findF" action = "list">
                <select name = "findType" id = "findType" style = "padding:5px">
                    <option value = "0">::검색 유형::</option>
                    <option value = "1"    <c:if test = "${paging.findType eq 1}">SELECTED</c:if>    >제 목</option>
                    <option value = "2"    <c:if test = "${paging.findType eq 2}">SELECTED</c:if>    >작성자</option>
                    <option value = "3"    <c:if test = "${paging.findType eq 3}">SELECTED</c:if>    >글내용</option>
                </select>
                <input type = "text" name = "findKeyword" id = "findKeyword" placeholder = "검색어를 입력하세요"
                        required style = "padding:4px">
                <button class = "btn btn-info">검 색</button>
            </form>
        </div>
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
                    <li><c:out value = "${board.getId()}"/></li>
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
            <span>  <b style = "color:red">${paging.pageNum}</b> page / ${paging.pageCount} pages </span>
        </div>
        <div class = "pageNavi text-center">
            <!-- 여기에 페이지 네비게이션 문자열 들어올 예정 -->
            ${pageNavi}
        </div>
    </div><!-- .col end -->
</body>
</html>