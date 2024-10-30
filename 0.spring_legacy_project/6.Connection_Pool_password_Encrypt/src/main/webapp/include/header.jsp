<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.ssafy.ws.step3.dto.User" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="root" value="${pageContext.request.contextPath}"/>

<h1><a href="${root}/users?action=home"> SSAFY 도서 관리 </a></h1>
<c:if test="${!empty loginUser}"> 
	${loginUser.name} 님 반갑습니다.
	<a href="${root}/users?action=signout">로그아웃</a>	
</c:if>
<c:if test="${empty loginUser}">
	<form method="post" action="${root}/users">
		<input type="hidden" name="action" value="signin"/>
		<input type="text" name="id" placeholder="아이디"/> &nbsp;
		<input type="password" name="pass" placeholder="비밀전호" /> &nbsp;
		<input type="submit" value="로그인" />
	</form>
	<a href="${root}/users?action=signupForm">회원가입</a>
</c:if>
	<hr/>