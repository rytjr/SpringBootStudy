<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h3 class="text-danger my-4 text-center"><a href="/admin/product">상품 등록하러 가기</a></h3>
<div>
    <form name="findF" id="findF" action="/admin/search">
        <input type="text" name="keyword" id="keyword" style="padding:4px" placeholder="상품명을 입력하세요" required>
        <button class="btn btn-info">검   색</button>
    </form>
</div>
<div class="row mt-5">
    <c:if test="${prodcutList eq null or empty prodcutList}">
        <div class="col-3 text-center">
            <h4>상품이 없습니다.</h4>
        </div>
    </c:if>
    <c:if test="${prodcutList ne null and not empty prodcutList}">
        <c:forEach var="item" items="${prodcutList}" begin="0" end="7" varStatus="state">
        <%-- varStatus: 반복문의 상태정보를 담을 변수를 지정
             반복문 횟수: ${state.count}
             반복문 인덱스: ${state.index}
             <h4>반복문 횟수: ${state.count} / 반복문 인덱스: ${state.index}</h4>
        --%>
            <div class="col-3 text-center">
                <c:choose>
                    <c:when test="${item.pimage1Fn ne null}">
                        <a href="prodDetail?pnum=<c:out value='${item.pnum}'/>">
                            <img src="/product_images/${item.pimage1Fn}" alt="${item.pname}" class="img-fluid" style="width:180px; height:180px; object-fit:contain">
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a href="prodDetail?pnum=<c:out value='${item.pnum}'/>">
                            <img src="/product_images/noimage.PNG" alt="${item.pname}" class="img-fluid" style="width:180px; height:180px; object-fit:contain">
                        </a>
                    </c:otherwise>
                </c:choose>
                <br><br>
                <h5>
                    <c:out value="${item.pname}"/>
                </h5>
                <h5>
                <del><c:out value="${item.price}" /></del>
                </h5>
                <h5 style="color: red;">
                    <c:out value="${item.salePrice}"/>
                </h5>
                <span class="badge bg-success">${item.point} Point</span><br>
                <a href="/admin/prodEdit?pnum=${item.pnum}">수정</a> |
                <a href="/admin/prodDelete?pnum=${item.pnum}">삭제</a>
            </div><!-- .col-3 end -->
            <c:if test="${state.count mod 4==0}">
                <!-- 행을 닫고 새로운 행 시작 -->
                </div>
                <div class="row mt-5">
            </c:if>
        </c:forEach>
    </c:if>
</div><!-- .row end -->