<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.ArrayList,com.ssafy.ws.step3.dto.Book" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="include/header.jsp" %>
<h1>도서목록</h1>

<%
	List<Book> books =(List<Book>)request.getAttribute("books");
%>
	<table>
		<thead>
			<th> 번호</th>
			<th> 제목 </th>
			<th> 저자</th>
			<th> 가격 </th>			
		</thead>
		<tbody>
			<%
			int num=0;
			for(Book book : books){
			%>
				<tr>
					<td> <%= ++num %></td>
					<td><a href="books?action=view&isbn=<%=book.getIsbn()%>"> <%= book.getTitle() %> </a></td>
					<td> <%= book.getAuthor() %></td>
					<td> <%= book.getPrice() %></td>
				</tr>
			<%
			}
			%>
		
		</tbody>
	</table>
</body>
</html>