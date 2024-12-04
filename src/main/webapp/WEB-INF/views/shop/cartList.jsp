<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


   <div class="py-5">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
           <h1 class="text-center">${loginUser.name}[${loginUser.userId}]님의 장바구니</h1>

        </div>
      </div>

      <div class="row">
        <div class="col-md-12">
        <!-- 주문 폼 시작-------------------------- -->
       <form name="orderF" id="orderF" action="order">

          <table class="table table-striped">
             <thead>
                <tr class="info text-center">
                   <th>상품번호</th>
                   <th>상품명</th>
                   <th>수량</th>
                   <th>단가</th>
                   <th>금액</th>
                   <th>삭제</th>
                </tr>
             </thead>
             <tbody>
                <!-- -------------------- -->
                <c:choose>
                <c:when test = "${cartArr eq null or empty cartArr}">
                    <tr>
                        <td colspan = "6">
                            <b>장바구니에 담긴 상품이 없습니다.</b>
                        </td>
                    </tr>
                </c:when>
                <c:otherwise>
                <c:forEach var = "cdto" items = "${cartArr}" varStatus = "state">
                <tr>
                   <td>
                      <label>
                      <input type="checkbox" name="pnum" id="pnum${state.index}"
                       value="${cdto.pnum}">
                      ${cdto.pnum}
                      </label>

                   </td>
                   <td>
                   <h4>${cdto.items[0].pname}</h4>
                   <br>
                   <a href="/prodDetail?pnum=${cdto.pnum}" target="_blank">
                   <img src="/product_images/${cdto.items[0].pimage1}"
                    class="img-thumbnail" style="width:140px"></a>
                   </td>
                   <td>
                   <input type="number" name="oqty" id="oqty${state.index}"
                    min="1" max="50" size="3" value = "${cdto.pqty}">
                    <button type="button" class="btn btn-success"
                     onclick="cartEdit(${cdto.cnum},${state.index})">
                     수정</button>
                   </td>
                   <td>
                   <fmt:formatNumber value = "${cdto.items[0].salePrice}" pattern = "###,###"/>
                   원<br>
                   <span class="badge bg-danger">${cdto.items[0].point}</span>POINT
                   </td>
                   <td>
                   <fmt:formatNumber value = "${cdto.items[0].totalPrice}" pattern = "###,###" />
                   원<br>
                   <span class="badge bg-danger">${cdto.items[0].totalPoint}</span>POINT
                   </td>
                   <td>
                   <a  href="#" onclick="cartDel('${cdto.cnum}')">삭제</a>
                   </td>
                </tr>
                </c:forEach>
                </c:otherwise>
                </c:choose>
                <!-- -------------------- -->
             </tbody>
             <tr>
                <td colspan="3">
                   <h5>장바구니 총   액: <span class="text-danger">
                   <fmt:formatNumber value = "${cartSum.cartTotalPrice}" pattern = "###,###" />
                   원</span>
                   </h5>

                   <h5>장바구니 총포인트: <span class="text-success">
                   ${cartSum.cartTotalPoint}
                   POINT</span>
                   </h5>
                </td>
                <td colspan="3">
                   <button type="button" onclick="goOrder()" class="btn btn-outline-info">주문하기</button>
                   <!-- form tag안에 버튼이 있으면 default 로 submit ==> order.jsp -->
                   <button type="button"
                    class="btn btn-outline-warning"
                     onclick="location.href='../mall'">계속쇼핑</button>
                </td>
             </tr>
          </table>
       </form>
       <!-- ----------------------------------- -->
        </div>
      </div>
      <!-- 삭제form------------------------- -->
      <form name="df" action="cartDel">
            <input type="hidden" name="cnum">
      </form>
      <!-- --------------------------------- -->

      <!-- 수정form ------------------------- -->
      <form name="ef" action="cartEdit">
            <input type="hidden" name="cnum">
            <input type="hidden" name="pqty">
      </form>
      <!-- --------------------------------- -->

      <script>
      //체크박스에 체크한 상품정보 (상품번호, 수량)을 가지고 주문폼 페이지로 이동
         function goOrder(pnum, oqty){


         }//--------goOrder()-----------------

         function cartEdit(num, index){
            //alert(num+"/ "+index);
            ef.cnum.value = num;
            let qty = document.querySelector('#oqty'+index).value; //수정한 수량값
            ef.pqty.value = qty;
            ef.method = 'post';
            ef.submit();


         }

         function cartDel(num){
            //alert(num);
            let yn = confirm('정말 삭제할까요?');
            if(!yn) return;
            df.cnum.value = num;
            df.method = 'post'
            df.submit();
         }

      </script>

    </div>
  </div>
