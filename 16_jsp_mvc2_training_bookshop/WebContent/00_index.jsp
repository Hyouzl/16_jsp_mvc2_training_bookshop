<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="${contextPath}/css/style.css"/>


<%--


==================== 시작주소 ===================
# 관리자 화면
localhost:8080/16_jsp_mvc2_training_bookshop/mg/managerMain.do

# 사용자 화면
localhost:8080/16_jsp_mvc2_training_bookshop/index.do
===============================================

# context.xml 파일에 커넥션 풀 설정
			
	<Resource 
		auth="Container" 
		driverClassName="com.mysql.cj.jdbc.Driver"
		loginTimeout="10" 
		maxWait="5000" 
		name="jdbc/pool/bookshop" 
		username="root"
		password="1234" 
		type="javax.sql.DataSource"
		url="jdbc:mysql://localhost:3306/bookshop?serverTimezone=UTC"
	/> 

 --%>


<div id="header">
  <div id="logo" class="box"> 
    <a href="${contextPath}/index.do"> 
      <img class="noborder" id="logo" src="${contextPath}/images/logo.png"/>
   	</a>
  </div>
  <div id="auth" class="box">
    <c:if test="${type == 0}">
      <jsp:include page="02_managerLogin.jsp"/>
    </c:if>
    <c:if test="${type == 1}">
     <jsp:include page="12_login.jsp"/>
    </c:if>
  </div>
</div>
<div id="content" class="box2">
  <jsp:include page="${cont}"/>
</div>

