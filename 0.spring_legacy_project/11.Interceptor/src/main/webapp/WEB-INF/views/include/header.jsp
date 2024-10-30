<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%-- jstl의 core 라이브러리를 사용하기 위해 taglib를 이용한다. --%>
<%@ page import="com.ssafy.ws.model.dto.User" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="root" value="${pageContext.request.contextPath}"/>

	<h1><a href="${root}/"> SSAFY 도서 관리 </a></h1>
	<c:if test="${!empty loginUser}"> 
		${loginUser.name} 님 반갑습니다.
		<a href="${root}/users/logout">로그아웃</a>	
	</c:if>
	<c:if test="${empty loginUser}">
		<form method="post" action="${root}/users/login">
			<input type="text" name="id" placeholder="아이디"/> &nbsp;
			<input type="password" name="pass" placeholder="비밀번호" /> &nbsp;
			<input type="submit" value="로그인" />
			<a href="${root}/users/regist">회원가입</a>
		</form>
	</c:if>
	<hr/>
