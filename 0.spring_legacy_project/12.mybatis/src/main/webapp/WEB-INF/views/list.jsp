<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<h1>도서목록</h1>

	<table>
		<thead>
			<th> 번호</th>
			<th> 제목 </th>
			<th> 저자</th>
			<th> 가격 </th>			
		</thead>
		<tbody>

			<c:forEach items="${books}" var="book" varStatus="vs">
				<tr>
					<td> ${vs.count}</td>
					<td><a href="${root}/books/view?isbn=${book.isbn}">
							 ${book.title}
						</a>
					</td>
					<td> ${book.author}</td>
					<td> ${book.price}</td>
				</tr>
			</c:forEach>	

		</tbody>
	</table>
</body>
</html>