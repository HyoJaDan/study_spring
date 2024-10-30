<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="com.ssafy.ws.step3.dto.Book" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서등록결과</title>
</head>
<body>
<%@ include file="include/header.jsp" %>
	<h2>등록 도서 정보</h2>
	<% Book book = (Book)request.getAttribute("book"); %>
	<%if(book == null){ %>
		등록된 도서가 없습니다.
	<%}else { %>	
	<form method="post" action="books">
		<input type="hidden" name="action" value="update"/>
		<input type="hidden" name="isbn" value="<%= book.getIsbn() %>"/>
		<table border="1">
			<tr>
				<th>항목</th>
				<th>내용</th>
			</tr>
			<tr>
				<td>도서번호</td>
				<td><%= book.getIsbn() %></td>
			</tr>
			<tr>
				<td>도서명</td>
				<td>
					<input type="text" name="title" value="<%= book.getTitle() %>"/>
				</td>
			</tr>	
			<tr>
				<td>저자</td>
				<td>
					<input type="text" name="author" value="<%= book.getAuthor() %>"/>
				</td>
			</tr>	
			<tr>
				<td>가격</td>
				<td>
					<input type="text" name="price" value="<%= book.getPrice() %>"/>
				</td>
			</tr>	
			<tr>
				<td>설명</td>
				<td>
					<textarea name="desc">
						<%= book.getDesc() %>
					</textarea>	
				</td>
			</tr>			
		</table>
		<input type="submit" value="수정하기"/>
		<a href="books?action=delete&isbn=<%= book.getIsbn()%>">삭제하기</a>
		<a href="books?action=list">목록보기</a>
		
	</form>
	<%} %>
</body>
</html>