<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Product Registration</title>
</head>
<style>
    table{
        border : 2px solid gray;
    }
    table .info {
        background-color: lightgreen; /* 배경색 설정 */
        color: white; /* 글자 색을 하얀색으로 설정 */
        text-align: center; /* 텍스트를 수평 가운데 정렬 */
        vertical-align: middle; /* 텍스트를 수직 가운데 정렬 */
        font-weight: bold; /* 텍스트를 굵게 (선택 사항) */
        margin-top : 0;
    }
    select{
        width : 650px;
        height : 25px;
        margin-bottom : 0;
    }
</style>
<body>
    <div class = "login-wrap col-6 offset-3 py-4 my-4 ">
    <form method = "post" action = "/admin/product" onsubmit = "return check()">
        <h1 class = "text-center">상품 등록</h1>
        <select>
            <option>:카테고리 유형</option>
            <option value = 1>컴퓨터/디지털 가전</option>
            <option value = 2>생필품</option>
            <option value = 3>의류</option>
        </select><table width = "80%" height = 350px class = "table">
            <tr>
                <td colspan = "4" id = "productText" class = "info">:::Product Register:::</td>

            </tr>
            <tr>
                <td>상품명</td>
                <td colsapn = "3" class = "form-control"><input type = "text" name = "productName" id = "productName"></td>
            </tr>
            <tr>
                <td>상품판매가</td>
                <td colspan = "3" class = "form-control"><input type = "text" name = "productPrice" id = "productPrice">원</td>
            </tr>
            <tr>
                <td>상품이미지</td>
                <td class = "form-control" name = "productImage1" id = "productImage1"><button>파일 선택</button>선택된 파일 없음</td><br>
                <td class = "form-control" name = "productImage2" id = "productImage2"><button>파일 선택</button>선택된 파일 없음</td><br>
                <td class = "form-control" name = "productImage3" id = "productImage3"><button>파일 선택</button>선택된 파일 없음</td><br>
            </tr>
            <tr>
                <td>상품설명</td>
                <td colspan = "3" class = "form-control"><textarea name = "content" id = "content" placeholder = "Content" rows = "8" cols = "50" class = "form-control" name = "productContent" id = "productContent"></textarea></td>
            </tr>
            <tr>
                <td>
                    <button class = "btn btn-success">상품등록</button>
                    <button class = "btn btn-warning" type = "reset">다시쓰기</button>
                </td>
            </tr>

        </table>
    </form>
    </div>
    <script>
        function check(){
            let name = document.querySelector('#productName');
            let price = document.querySelector('#productPrice');
            let image = document.querySelector('#productImage1 input');
            let content = document.querySelector('#productContent');

            if(name.value.trim().length == 0) {
                alert("상품 명을 입력해 주세요");
                name.focus();
                return false;
            }
            if(price.value.trim().length == 0) {
                alert("상품 가격을 입력해 주세요");
                price.focus();
                return false;
            }
            if(image.trim().isEmpty()) {
                alert("첫 번째 사진을 추가해 주세요");
                image.focus();
                return false;
            }
            if(content.value.trim().length == 0) {
                alert("상품 설명을 입력해 주세요");
                content.focus();
                return false;
            }
        }
    </script>
</body>
</html>