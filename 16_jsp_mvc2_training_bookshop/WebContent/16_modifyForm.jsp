<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="${contextPath}/css/style.css"/>
<script src="${contextPath}/js/jquery-3.5.1.min.js"></script>

<c:if test="${empty sessionScope.id}">
  <meta http-equiv="Refresh" content="0;url=${contextPath}/index.do">
</c:if>

<div id="regForm" class="box">
   <ul>
      <li>
      	<p class="center">회원 정보 수정</p>
      </li>
      <li>
      	  <label for="id">아이디</label>
          <input id="id" name="id" type="email" size="20" maxlength="50" value="${id}" readonly disabled>
      </li>
      <li>
      	  <label for="passwd">비밀번호</label>
          <input id="passwd" name="passwd" type="password" size="20" placeholder="6~16자 숫자/문자" maxlength="16">
          <small class="cau">반드시 입력하세요.</small>
      </li>
      <li>
      	  <label for="name">이름</label>
          <input id="name" name="name" type="text" size="20" maxlength="10" value="${m.getName()}">
      </li>
      <li>
      	  <label for="address">주소</label>
          <input id="address" name="address" type="text" size="30" maxlength="50" value="${m.getAddress()}">
      </li>
      <li>
      	  <label for="tel">전화번호</label>
          <input id="tel" name="tel" type="tel" size="20" maxlength="20" value="${m.getTel()}">
      </li>
      <li class="label2">
          <input type="submit" value="수정">
      </li>
   </ul>
</div>