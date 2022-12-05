<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>

<c:if test="${empty sessionScope.id}">
  <div id="mList">
  	<p>로그인 하세요.</p>
  </div>
</c:if>

<c:if test="${!empty sessionScope.id}">
  <div id="mList">
     <ul>
        <li>상품관련 작업</li>
        <li><button onclick="location.href='${contextPath}/mg/bookRegister.do'">상품등록</button></li>
        <li><button onclick="location.href='${contextPath}/mg/bookList.do?book_kind=all'">상품수정/삭제</button></li>
     </ul>
     <ul>
        <li>구매된 상품관련 작업</li>
        <li><button onclick="location.href='${contextPath}/mg/orderList.do'">전체구매목록 확인</button></li>
     </ul>
     <ul>
        <li>상품 QnA 작업</li>
        <li><button onclick="location.href='${contextPath}/mg/qnaList.do'" >상품 QnA답변</button></li>
     </ul>
  </div>
</c:if>    