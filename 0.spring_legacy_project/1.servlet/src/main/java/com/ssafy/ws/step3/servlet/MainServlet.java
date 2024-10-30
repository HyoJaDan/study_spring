package com.ssafy.ws.step3.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import com.ssafy.ws.step3.dto.Book;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 이 서블릿이 호출되기 위해서는 url 상에 http://server_ip:port/context_name/main 이 필요하다.
@WebServlet("/main")
public class MainServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		//request.getParameter("name") 데이터 읽어오기
		//Book객체로 생성하기
		Book book=new Book(
				request.getParameter("isbn"),
				request.getParameter("author"),
				request.getParameter("title"),
				Integer.parseInt(request.getParameter("price")),
				request.getParameter("desc")
				);
		PrintWriter out = response.getWriter();
		out.println(book.toString());
	}
	
	
}
