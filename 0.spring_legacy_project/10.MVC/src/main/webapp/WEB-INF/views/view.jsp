<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>	  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서등록결과</title>
</head>
<body>
<%@ include file="include/header.jsp" %>
	<h2>등록 도서 정보</h2>
	<c:if test="${!empty book}">

	<form method="post" action="${root}/books/update">
		<input type="hidden" name="isbn" value="${book.isbn}"/>
		<table border="1">
			<tr>
				<th>항목</th>
				<th>내용</th>
			</tr>
			<tr>
				<td>도서번호</td>
				<td>${book.isbn}</td>
			</tr>
			<tr>
				<td>도서명</td>
				<td>
					<input type="text" name="title" value="${book.title}"/>
				</td>
			</tr>	
			<tr>
				<td>저자</td>
				<td>
					<input type="text" name="author" value="${book.author}"/>
				</td>
			</tr>	
			<tr>
				<td>가격</td>
				<td>
					<input type="text" name="price" value="${book.price}"/>
				</td>
			</tr>	
			<tr>
				<td>설명</td>
				<td>
					<textarea name="desc">
						${book.desc}
					</textarea>	
				</td>
			</tr>			
		</table>
		<input type="submit" value="수정하기"/>
		<a href="${root}/books/delete?isbn=${book.isbn}">삭제하기</a>
		<a href="${root}/books/list">목록보기</a>
	</form>
	</c:if>
	<c:if test="${empty book}">
		등록된 도서가 없습니다.
	</c:if>	
	
</body>
</html>