package com.ssafy.ws.step3.servlet;

import java.io.IOException;
import java.util.List;

import com.ssafy.ws.step3.dto.Book;
import com.ssafy.ws.step3.service.BookService;
import com.ssafy.ws.step3.service.BookServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainServlet
 * Presentation Layer
 * Controller : 입력데이터 check, business logic 호출, 일정 scope에 data 저장,view select
 */
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookService bookService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        bookService = BookServiceImpl.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String path="index.jsp";
		if(action.equals("registForm")) {
			path = doRegistForm(request, response);
			redirect(request, response, path);
		}
		if(action.equals("regist")) {
			path =doRegist(request, response);
		    redirect(request, response, path);
		}
		if(action.equals("list")) {
			path = doList(request,response);
			forward(request, response, path);
		}
		if(action.equals("view")) {
			path = doView(request, response);
			forward(request, response, path);
		}
		if(action.equals("update")) {
			path = doUpdate(request, response);
			redirect(request, response, path);
		}
		if(action.equals("delete")) {
			path = doRemove(request, response);
			redirect(request, response, path);
		}		
	}

	private String doRemove(HttpServletRequest request, HttpServletResponse response) {
		String isbn = request.getParameter("isbn");
		bookService.remove(isbn);
		return "/main?action=list";
	}

	private String doUpdate(HttpServletRequest request, HttpServletResponse response) {
		String isbn=request.getParameter("isbn");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		int price = Integer.parseInt(request.getParameter("price"));
		String desc = request.getParameter("desc");
		
		Book book = new Book();
		book.setIsbn(isbn);
		book.setAuthor(author);
		book.setTitle(title);
		book.setPrice(price);
		book.setDesc(desc);
		
		bookService.modify(book);
				
		return "/main?action=list";
	}

	private String doView(HttpServletRequest request, 
			HttpServletResponse response)throws ServletException, IOException {
		//data check
		String isbn = request.getParameter("isbn");
		//service delegation
		Book book = bookService.getBook(isbn);
		//일정범위에 저장
		request.setAttribute("book", book);
		//view select
		return "/view.jsp";
	}

	private String doList(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException{
		//data check
		//business method 호출
		List<Book> books = bookService.getBooks();
		//일정범위에 data 저장
		request.setAttribute("books", books);
		//view select
		return "/list.jsp";
	}

	/**
	 * 도서 등록(저장)
	 * @param request
	 * @param response
	 */
	private String doRegist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//data check
		String isbn = request.getParameter("isbn");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		int price = Integer.parseInt(request.getParameter("price"));
		String desc = request.getParameter("desc");
	
		Book book = new Book(isbn, title, author, price, desc);
		bookService.registBook(book);
		
		return "/main?action=list";
	}

	/**
	 * 도서 등록 화면 제공
	 * @param request
	 * @param response
	 */
	private String doRegistForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "/regist.jsp";
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void redirect(HttpServletRequest request, 
				HttpServletResponse response, String path) 
			throws ServletException, IOException {
		response.sendRedirect(request.getContextPath()+path);
	
	}
	
	private void forward(HttpServletRequest request, 
			HttpServletResponse response, String path) 
		throws ServletException, IOException {
		request.getRequestDispatcher(path).forward(request, response);
	}
}
