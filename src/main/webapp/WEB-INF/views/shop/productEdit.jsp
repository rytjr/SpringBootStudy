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
            <h1 class = "text-center">상품정보 수정[ADMIN모드]</h1>
            <table width = "80%" height = 350px class = "table">
                <tr>
                    <td colspan = "4" id = "productText" class = "info" style="height: 50px;">:::Product Edit:::</td>

                </tr>
                <tr>
                    <td width="20%">카테고리</td>

                      <td colspan="3"><select>
                            <option>:카테고리 유형</option>
                            <option value=1>컴퓨터/디지털 가전</option>
                            <option value=2>생필품</option>
                            <option value=3>의류</option>
                        </select></td>
                </tr>
                <tr>
                    <td>상품번호</td>
                    <td colsapn = "3" class = "form-control" name = "pnum" id = "pnum"><input type = "text" value = "<c:out value = '${productList.pnum}'/>" readonly></td>
               </tr>
                <tr>
                    <td>상품명</td>
                    <td colsapn = "3" class = "form-control" name = "pname" id = "pname"><input type = "text"></td>
                </tr>

                <tr>
                    <td>상품이미지</td>
                    <td class="form-control" name="productImage1" id="productImage1">
                        <img src="${image1Path != null ? image1Path : '/default-image.jpg'}" alt="Product Image 1">
                        <img src="${image1Path != null ? image1Path : '/default-image.jpg'}" alt="Product Image 2">
                        <img src="${image1Path != null ? image1Path : '/default-image.jpg'}" alt="Product Image 3">
                    </td>

                    <td class = "form-control" name = "productImage1" id = "productImage1"><input type="file" name="pimage"><br><input type="file" name="pimage"><br><input type="file" name="pimage"></td><br>

                </tr>
                <tr>
                    <td>상품판매가</td>
                    <td colspan = "3" class = "form-control" name = "price" id = "price"><input type = "text">원</td>
                </tr>
                <tr>
                    <td>상품 할인된 판매가</td>
                    <td colspan = "3" class = "form-control" name = "salePrice" id = "salePrice"><input type = "text">원</td>
                </tr>
                <tr>
                    <td>상품 개수</td>
                    <td colspan = "3" class = "form-control" name = "pqty" id = "pqty"><input type = "text">개</td>
                </tr>
                <tr>
                    <td>제조사</td>
                    <td colspan = "3" class = "form-control" name = "pcompany" id = "pcompany"><input type = "text"></td>
                </tr>
                <tr>
                    <td>상품설명</td>
                    <td colspan = "3" class = "form-control" name = "pcontents" id = "pcontents"><textarea name = "content" id = "content" placeholder = "Content" rows = "8" cols = "50" class = "form-control"></textarea></td>
                </tr>
                <tr>
                    <td colspan="4">
                        <button class="btn btn-success" style="display: inline-block; background-color: #00bcd4; border-color: #00bcd4; color: white;">상품수정</button>
                        <button class="btn btn-warning" style="display: inline-block; background-color: #DC143C; border-color: #DC143C; color: white;">다시쓰기</button>
                    </td>
                </tr>

            </table>
        </div>
</body>
</html>