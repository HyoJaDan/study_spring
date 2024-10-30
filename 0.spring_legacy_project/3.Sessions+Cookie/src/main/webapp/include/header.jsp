<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.ssafy.ws.step3.dto.User" %>
<h1><a href="users?action=home"> SSAFY 도서 관리 </a></h1>
<%
	if(session != null && session.getAttribute("loginUser")!= null){
		//login한 상태의 화면 - user.getName()님 반갑습니다. <a>로그아웃</a>
%>	
		<%= ((User)session.getAttribute("loginUser")).getName()%> 님 반갑습니다.
		<%-- 
			Cookie[] cookies = request.getCookies();
			for(Cookie c : cookies){
				if(c.getName().equals("loginUser"))
					out.print(c.getValue()+"님 반갑습니다.");		
				break;
			}
		--%>
		<a href="users?action=signout">로그아웃</a>	
<%	}else{
		//아이디 비밀번호 로그인버튼 입력화면 출력
%>
	<form method="post" action="users">
		<input type="hidden" name="action" value="signin"/>
		<input type="text" name="id" placeholder="아이디"/> &nbsp;
		<input type="password" name="pass" placeholder="비밀전호" /> &nbsp;
		<input type="submit" value="로그인" />
	</form>
<%		
	}
%>
	<hr/>