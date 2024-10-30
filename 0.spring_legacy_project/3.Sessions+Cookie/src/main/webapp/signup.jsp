<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<%@ include file="include/header.jsp" %>
<h2>회원가입</h2>
		<form method="post" action="users"> 
		<fieldset>
            <input type="hidden" name="action" value="signup"> 
            <label for="name">이름</label> 
		 	<input type="text" id="name" name="name"><br> 
			<label for="id">아이디</label> 
			<input type="text" id="id" name="id"><br> 
			<label for="pass">암호</label> 
			<input type="password" id="pass" name="pass"><br>
			<input type="submit" value="회원가입"> 
			<input type="reset" value="취소"> 
		</fieldset>	
		</form> 
</body>
</html>