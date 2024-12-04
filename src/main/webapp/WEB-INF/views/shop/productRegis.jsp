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
        <select name = "cg_code" id = "cg_code">
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
                <td colsapn = "3" class = "form-control"><input type = "text" name = "pname" id = "pname"></td>
            </tr>
            <tr>
                <td>상품판매가</td>
                <td colspan = "3" class = "form-control"><input type = "text" name = "price" id = "price">원</td>
            </tr>
            <tr>
                <td>할인된 판매가</td>
                <td colspan = "3" class = "form-control"><input type = "text" name = "salePrice" id = "salePrice">원</td>
            </tr>
            <tr>
                <td>상품 수량</td>
                <td colspan = "3" class = "form-control"><input type = "text" name = "pqty" id = "pqty">개</td>
            </tr>
            <tr>
                <td>상품이미지</td>
                <td class = "form-control" name = "pimage1" id = "pimage1"><button>파일 선택</button>선택된 파일 없음</td><br>
                <td class = "form-control" name = "pimage2" id = "pimage2"><button>파일 선택</button>선택된 파일 없음</td><br>
                <td class = "form-control" name = "pimage3" id = "pimage3"><button>파일 선택</button>선택된 파일 없음</td><br>
            </tr>
            <tr>
                <td>상품 제조사</td>
                <td colspan = "3" class = "form-control"><input type = "text" name = "pcompany" id = "pcompany"></td>
            </tr>
            <tr>
                <td>상품설명</td>
                <td colspan = "3" class = "form-control"><textarea name = "pcontents" id = "pcontents" placeholder = "Content" rows = "8" cols = "50" class = "form-control" name = "productContent" id = "productContent"></textarea></td>
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
        let name = document.querySelector('#pname');
        let price2 = document.querySelector('#price');
        let sale = document.querySelector('#salePrice');
        let count = document.querySelector('#pqty');

        // 상품명 검사
        if(name.value.trim().length == 0) {
            alert("상품 명을 입력해 주세요");
            name.focus();
            return false;
        }

        // 상품 가격 검사
        if(price2.value.trim().length == 0) {
            alert("상품 가격을 입력해 주세요");
            price2.focus();
            return false;
        }
        if(isNaN(price2.value) || price2.value <= 0) { // 숫자 여부와 양수 여부 확인
            alert("상품 가격은 숫자로 입력해 주세요");
            price2.focus();
            return false;
        }

        // 할인된 가격 검사
        if(sale.value.trim().length == 0) {
            alert("상품 할인된 가격을 입력해 주세요");
            sale.focus();
            return false;
        }
        if(isNaN(sale.value) || sale.value <= 0) { // 숫자 여부와 양수 여부 확인
            alert("상품 할인된 가격은 숫자로 입력해 주세요");
            sale.focus();
            return false;
        }

        // 상품 수량 검사
        if(count.value.trim().length == 0) {
            alert("상품 수량을 입력해 주세요");
            count.focus();
            return false;
        }
        if(isNaN(count.value) || count.value <= 0 || !Number.isInteger(Number(count.value))) {
            // 숫자, 양수, 정수 여부 확인
            alert("상품 수량은 정수로 입력해 주세요");
            count.focus();
            return false;
        }

        // 모든 검사를 통과하면 true 반환
        return true;
    }
</script>

</body>
</html>