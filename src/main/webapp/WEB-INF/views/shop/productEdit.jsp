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
        margin-top: 0;
    }
    table .info {
        background-color: gold; /* 배경색 설정 */
        color: white; /* 글자 색을 하얀색으로 설정 */
        text-align: center; /* 텍스트를 수평 가운데 정렬 */
        vertical-align: middle; /* 텍스트를 수직 가운데 정렬 */
        font-weight: bold; /* 텍스트를 굵게 (선택 사항) */
        margin-top : 0;
    }
    select{
        width : 100%;
        height : 25px;
        margin-bottom : 0;
    }
    h1{
        margin-bottom: 0px;
    }
</style>
<body>
    <c:if test = "productList eq null">
        <script>
            alert("해당 상품은 존재하지 않습니다");
            location.href = "/admin/productList";
        </script>
    </c:if>
    <div class = "login-wrap col-6 offset-3 py-4 my-4 ">
        <form action = "/admin/product" method = "post" enctype = "multipart/form-data">
            <input type = "hidden" value = "edit" name = "mode" id = "mode">
            <h1 class = "text-center">상품정보 수정[ADMIN모드]</h1>
            <table width = "80%" height = 350px class = "table">
                <tr>
                    <td colspan = "4" id = "productText" class = "info" style="height: 50px;">:::Product Edit:::</td>

                </tr>
                <tr>
                    <td width="20%">카테고리</td>

                      <td colspan="3"><select id="cg_code" name = "cg_code">
                            <option>:카테고리 유형</option>
                            <option value=1>컴퓨터/디지털 가전</option>
                            <option value=2>생필품</option>
                            <option value=3>의류</option>
                        </select></td>
                </tr>
                <tr>
                    <td>상품번호</td>
                    <td colsapn = "3" class = "form-control"><input name = "pnum" id = "pnum" type = "text" value = "<c:out value = '${productList.pnum}'/>" readonly></td>
               </tr>
                <tr>
                    <td>상품명</td>
                    <td colsapn = "3" class = "form-control"><input name = "pname" id = "pname" type = "text" value = "<c:out value = '${productList.pname}'/>"></td>
                </tr>

                <tr>
                    <td>상품이미지</td>
                    <td class="form-control">
                        <img src="/product_images/${productList.file1}" alt="Product Image 1" width = "150px" height = "150px"><input type = "hidden" name = "file1" value = "${productList.file1}">
                        <img src="/product_images/${productList.file2}" alt="Product Image 2" width = "150px" height = "150px"><input type = "hidden" name = "file2" value = "${productList.file2}">
                        <img src="/product_images/${productList.file3}" alt="Product Image 3" width = "150px" height = "150px"><input type = "hidden" name = "file3" value = "${productList.file3}">
                    </td>

                    <td class = "form-control"><input type="file" name="fi1" id = "pimage1"><br><input type="file" name="fi2" id = "fi2"><br><input type="file" name="fi3" id = "fi3"></td><br>

                </tr>
                <tr>
                    <td>상품판매가</td>
                    <td colspan = "3" class = "form-control"><input name = "price" id = "price" type = "text" value = "<c:out value = '${productList.price}'/>">원</td>
                </tr>
                <tr>
                    <td>상품 할인된 판매가</td>
                    <td colspan = "3" class = "form-control"><input name = "salePrice" id = "salePrice" type = "text" value = "<c:out value = '${productList.salePrice}'/>">원</td>
                </tr>
                <tr>
                    <td>상품 개수</td>
                    <td colspan = "3" class = "form-control"><input name = "pqty" id = "pqty" type = "text" value = "<c:out value = '${productList.pqty}'/>">개</td>
                </tr>
                <tr>
                    <td>제조사</td>
                    <td colspan = "3" class = "form-control"><input name = "pcompany" id = "pcompany" type = "text" value = "<c:out value = '${productList.pcompany}'/>"></td>
                </tr>
                <tr>
                    <td>상품설명</td>
                    <td colspan = "3" class = "form-control"><textarea  name = "pcontents" id = "pcontents" placeholder = "Content" rows = "8" cols = "50" class = "form-control">${productList.pcontents}</textarea></td>
                </tr>
                <tr>
                    <td colspan="4">
                        <button class="btn btn-success" style="display: inline-block; background-color: #00bcd4; border-color: #00bcd4; color: white;">상품수정</button>
                        <button type = "reset" class="btn btn-warning" style="display: inline-block; background-color: #DC143C; border-color: #DC143C; color: white;">다시쓰기</button>
                    </td>
                </tr>

            </table>
            </form>
        </div>

        <script>
            // 기본 선택할 값 설정
            const selectedValue = "${productList.cg_code}"; // 동적으로 설정하려는 값
            const selectElement = document.getElementById("cg_code");
            selectElement.value = selectedValue;
        </script>
</body>
</html>