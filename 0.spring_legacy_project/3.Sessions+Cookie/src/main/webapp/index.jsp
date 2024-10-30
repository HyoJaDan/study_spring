<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SSAFY 도서 관리</title>
</head>
<body>
	<%@include file="include/header.jsp" %>
	<!-- 메인화면을 구현하세요 -->
	<% if(session!=null && session.getAttribute("loginUser")!=null) {%>
	<h2><a href="books?action=registForm">도서등록</a></h2>
	<%}%>
	<h2><a href="books?action=list">도서목록</a></h2>
</body>
</html>