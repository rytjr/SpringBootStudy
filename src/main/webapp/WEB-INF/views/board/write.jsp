<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Board</title>

  <link rel = "stylesheet" href = "/ckeditor/ckeditor.css">
  <script src="https://cdn.ckeditor.com/ckeditor5/39.0.1/super-build/ckeditor.js"></script>
  <script src = "/ckeditor/ckeditor.js"></script>

  <style>
    ul.boardUI>li{
        list-style:none;
        margin:5px 0;
    }
    .divBtn{
        text-align:center;
    }
  </style>
</head>
<body>
    <div class = "col-8 offset-2 py-4">
        <h1 class = "text-center">Spring Board</h1>
        <!-- 파일 업로드 : method = "post" enctype = "multipart/form-data"로 주어야 함에 주의하자
            multipart/form-data로 설정해야 파일명과 함께 파일 데이터가 서버에 전송된다
             스프링 ->  multipartResolver빈 등록-> 스프링부트에서는 자동회되어 있음 -->
        <form name = "bf" id = "bf" action = "write" method = "post" enctype = "multipart/form-data">

            <input type = "hidden" name = "mode" value = "write">
            <ul class = "boardUI">
                <li>제목</li>
                <li><input type = "text" name = "title" id = "title" placeholder = "title" class = "form-control" required></li>
                <li>글쓴이</li>
                <li><input type = "text" name = "userId" id = "userId" placeholder = "userId" class = "form-control" required></li>
                <li>글내용</li>
                <li>
                    <textarea name = "content" id = "content" placeholder = "Content" rows = "8" cols = "50" class = "form-control"></textarea>
                </li>
                <li>첨부파일</li>
                <li>
                    <input type = "file" name = "mfile" id = "mfile" class = "form-control">
                </li>
                <li>비밀번호</li>
                <li>
                    <input type = "password" name = "pwd" id = "pwd" class = "form-control" required>
                </li>
            </ul>
            <div class = "clear"></div>
            <div class = "divBtn">
                <button type = "submit" class = "btn btn-info">글쓰기</button>
                <button type = "reset" class = "btn btn-warning">다시 쓰기</button>
            </div
        </form>
    </div>
    <script>
        CKEDITOR.ClassicEditor.create( document.querySelector("#content"), option);
    </script>
</body>
</html>