package com.ssafy.ws.step3.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

import com.ssafy.ws.step3.dto.Book;
import com.ssafy.ws.step3.service.BookService;
import com.ssafy.ws.step3.service.BookServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BookController implements Controller {

    private final BookService bookService;

    public BookController() {
    	bookService = BookServiceImpl.getInstance();
	}
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String nextPage="/index.jsp";

		try {
			Class bookClass= Class.forName("com.ssafy.ws.step3.controller.BookController");
			BookController bookObject = (BookController)bookClass.getDeclaredConstructor().newInstance();
			Class [] parameterTypes = {jakarta.servlet.http.HttpServletRequest.class,
					jakarta.servlet.http.HttpServletResponse.class};
			Object [] parameters = {request, response};

			Method m= bookClass.getDeclaredMethod(action, parameterTypes);
		
			 
			 nextPage=  (String) m.invoke(bookObject, parameters);
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return nextPage;
	}
	
	private String registForm(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException, SQLException{
		return "/regist.jsp";
	}
	
	//data check - business method call - scope에 data save - view select
	private String regist(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException{
		String isbn = request.getParameter("isbn");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		int price = Integer.parseInt(request.getParameter("price"));
		String desc = request.getParameter("desc");
		Book book = Book.builder()
					.isbn(isbn)
					.title(title)
					.author(author)
					.price(price)
					.desc(desc)
					.img(null)
					.build();
		bookService.registBook(book);
		
		return "/books?action=list";
	
	
	}

	private String list(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException{
		List<Book> books = bookService.getBooks();
		request.setAttribute("books", books);
		return "/list.jsp";
		
	}

	private String view(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException{
		String isbn = request.getParameter("isbn");
		Book book = bookService.getBook(isbn);
		request.setAttribute("book", book);
		return "/view.jsp";
		
	}

	private String delete(HttpServletRequest request, HttpServletResponse response) 
			 throws ServletException, IOException{
		String isbn = request.getParameter("isbn");
		bookService.remove(isbn);
	
		return "/books?action=list";
		
	}

	private String update(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException{
		String isbn = request.getParameter("isbn");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		int price = Integer.parseInt(request.getParameter("price"));
		String desc = request.getParameter("desc");
		Book book = Book.builder()
					.isbn(isbn)
					.title(title)
					.author(author)
					.price(price)
					.desc(desc)
					.img(null)
					.build();		
		bookService.modify(book);

		
		return "/books?action=list";
			
	}


}
