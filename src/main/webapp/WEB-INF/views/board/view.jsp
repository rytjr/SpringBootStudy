<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>board view</title>
</head>
<body>
    <c:if test = "${board eq null}">
        <script>
            alert("해당 글은 존재하지 않습니다")
            location.href="list";
        </script>
    </c:if>
    <div class = "col-10 offset-1 py-4">
        <h1 class = "text-center">Spring Board View</h1>
        <div class = "text-center">
            <a href = "/board/form">글쓰기</a>
            <a href = "/board/list">글목록</a>
        </div>
        <table class="table table-striped my-4">
            <tr>
                <td width="20%"><b>글번호</b></td>
                <td width="30%">
                <c:out value = "${board.id}" />
                </td>
                <td width="20%"><b>작성일</b></td>
                 <td width="30%">
                    <fmt:formatDate value = "${board.wdate}" pattern = "yyyy-MM-dd HH:mm:ss" />
                 </td>
            </tr>
            <tr>
                <td width="20%"><b>글쓴이</b></td>
                <td width="30%">
                    <c:out value = "${board.userId}" />
                </td>
                <td width="20%"><b>조회수</b></td>
                 <td width="30%">
                    <c:out value = "${board.readNum}" />
                 </td>
            </tr>
            <tr>
                <td width="20%"><b>첨부파일</b></td>
                <td colspan="3">
              <%--  <a href="/upload/<c:out value = '${board.fileName}'/>" download>   --%>
                    <a href = "#" onclick = "fileDown()">
                    <c:out value = "${board.originFileName}" />
                </a>
                    [<c:out value = "${board.fileSize}" /> bytes ]
                </td>
            </tr>
            <tr>
                <td width="20%"><b>제  목</b></td>
                <td colspan="3">
                    <c:out value = "${board.title}" />
                </td>
            </tr>
            <tr>
                <td width="20%"><b>글 내용</b></td>
                <td colspan="3" id="content">
                   ${board.content}
                </td>
            </tr>
            <tr>
                <td colspan="4" style="text-align:center">
                    <a href="/board/form">글쓰기</a>
                    | <a href="/board/list">글목록</a>
                    <!--로그인한 사람이 글을 쓴 사람이라면 -->

                        <a href="javascript:goEdit()">글수정</a> |
                        <a href="javascript:goDel()">글삭제</a>

                </td>
            </tr>
        </table>
    </div>
    <!-- 파일 다운로드 form -->
    <form name = "frm" id = "frm" method = "post" action = "/fileDownload">
        <input type = "hidden" name = "fileName" value = "${board.fileName}">
        <input type = "hidden" name = "originFileName" value = "${board.originFileName}">
    </form>
    <!-- 수정 또는 삭제 관련 form -->
    <div id = "divPwd" class = "col-8 offset-2 py-4 text-center" style = "display:none">
        <form name = "frm2" id = "frm2" method = "post">
           <input type="hidden" name="id" value="<c:out value='${board.id}' />">
            <label>글 비밀번호</label>
            <input type = "password" name= "pwd" id = "pwd" placeholder = "글 비밀번호" required>
            <button id = "btn" class = "btn btn-info">text</button>
        </form>
    </div>
    <script>
        const div = document.querySelector("#divPwd");
        const btn = document.querySelector("#btn");
        const frm2 = document.querySelector("#frm2");

        function goEdit(){
            btn.innerText = "글수정";
            frm2.action = "/board/edit";
            div.style.display = 'block';
        }

        function goDel(){
            const yn = confirm("게시글을 삭제할까요?");
            if(!yn) return;
            frm2.action = "/board/delete";
            btn.innerText = "글삭제";
            div.style.display = "block";
        }

        function fileDown(){
            frm.submit();
        }
    </script>
</body>
</html>