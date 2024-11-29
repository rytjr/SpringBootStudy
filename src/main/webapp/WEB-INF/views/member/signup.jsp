<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>singup.html</title>
    <style>
        @charset "UTF-8";
        .info{
            background-color: skyblue;
        }
        .name {
            background-color: yellow;
            text-align: right;
        }
        .text {
            color: blue;
            font-size: 0.9em;
        }
        .di{
            justify-content: center;
        }
        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            min-height: 100vh;
        }
        .form {
            width: 90%;
        }

        #div {
            display: flex;
            justify-content: center;
            gap: 10px; /* 버튼 간격 */
            margin-top: 5px;
        }
    </style>
</head>
<body>
    <!-- table태그를 이용해서 회원가입 폼 작성하기 -->
    <div class = "container">
    <!-- check()함수가 true를 반환하면 submit하고, false를 반화나면 submit하지 않음 -->
        <form action="signupProc" method = "post" class = "form" id = "form" onsubmit = "return check()">

            <table width = "90%" height = "280px">
                <tr>
                    <td colspan = "4" class = "info">아이디(ID)정보</td>
                </tr>
                <tr>
                    <td class = "name">* 아이디</td>
                    <td colspan = "3">
                        <input type = "text" name = "userId" id = "userId" readonly>
                        <button type = "button" onclick = "openWin()">중복검사</button><br>
                        <em class = "text">(4~5자 영자/숫자 가능, 한글,특수문자 ID는 사용할 수 없습니다.)</em>
                    </td>
                </tr>
                <tr>
                    <td class = "name">* 비밀번호</td>
                    <td colsapn = "3">
                        <input type="text" name = "passwd" id = "passwd">
                        <em class = "text">(4~8자 이내로 만들어 주세요)</em>
                    </td>
                </tr>
                <tr>
                    <td class = "name">* 비밀번호 확인</td>
                    <td colsapn = "3">
                        <input type="text" name = "passwd2" id = "passwd2">
                        <em class = "text">(위 번호와 같이 입력해 주세요)</em>
                    </td>
                </tr>
                <tr>
                    <td colspan = "4" class = "info">개인정보</td>
                </tr>
                <tr>
                    <td class = "name">* 이름(한글)</td>
                    <td colsapn = "3">
                        <input type="text" name = "name" id = "name">
                        <em class = "text">(예:박정현)</em>
                    </td>
                </tr>
                <tr>
                    <td class = "name">* 전화번호</td>
                    <td colsapn = "3">
                        <em>핸드폰</em>
                        <input type = "tel" name = "hp1" id = "hp1">
                        <em>-</em>
                        <input type = "tel" name = "hp2" id = "hp2">
                        <em>-</em>
                        <input type = "tel" name = "hp3" id = "hp3">
                    </td>
                </tr>

            </table>
            <div id = "div">
                <button type = "submit" class = "di">확인</button>
                <button type = "reset" class = "di">다시입력</button>
            </div>
        </form>

    </div>
    <script src = "/js/member.js"></script>
</body>
</html>