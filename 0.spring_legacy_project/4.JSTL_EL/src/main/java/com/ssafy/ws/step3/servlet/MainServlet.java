package com.ssafy.ws.step3.servlet;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import com.ssafy.ws.step3.dto.Book;
import com.ssafy.ws.step3.dto.User;
import com.ssafy.ws.step3.service.BookService;
import com.ssafy.ws.step3.service.BookServiceImpl;
import com.ssafy.ws.step3.service.UserService;
import com.ssafy.ws.step3.service.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class MainServlet
 * Presentation Layer
 * Controller : 입력데이터 check, business logic 호출, 일정 scope에 data 저장,view select
 */
@WebServlet(urlPatterns = {"/books", "/users"})
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookService bookService;
	private UserService userService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        bookService = BookServiceImpl.getInstance();
        userService = UserServiceImpl.getInstance();
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
		if(action.equals("signupForm")) {
			path = "/signup.jsp";
			redirect(request, response, path);
		}	
		if(action.equals("signup")) {
			path = doSignup(request, response);
			redirect(request, response, path);
		}	
		if(action.equals("signin")) {
			path = doSignin(request, response);
			forward(request, response, path);
		}	
		if(action.equals("signout")) {
			path = doSignout(request, response);
			redirect(request, response, path);
		}
		if(action.equals("home")) {
			path="/index.jsp";
			redirect(request, response, path);
		}
	}

	private String doSignup(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException  {
		//data check
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		User user = new User();
		//business logic 호출
		userService.signUp(user);
		//일정범위에 data 저장
		
		//view select
		return "/index.jsp";
	}

	private String doSignin(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		try {
			User user  =userService.signIn(id, pass);
			HttpSession session = request.getSession(); //session생성
			session.setAttribute("loginUser", user);
			
//			//cookie생성 및 정보저장
//			Cookie userName = new Cookie("loginUser", user.getName());
//			userName.setMaxAge(24*60*60);
////			userName.setHttpOnly(true); //http, https에서만 사용 가능 js사용 금지
//			userName.setSecure(true);//https에서만 보이게
//			//cookie setting
//			response.addCookie(userName);
			
			return "/index.jsp";
	
		}catch(NoSuchElementException loginError){
			request.setAttribute("message","아이디비밀번호 확인하세요");
			return "/error/error.jsp";
		}
		
	}

	private String doSignout(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//session check
		HttpSession session = request.getSession(false);
		if( session != null ) session.invalidate(); //session 소멸
		
//		Cookie[] cookies = request.getCookies();
//		for(Cookie cookie : cookies){
//			cookie.setMaxAge(0);
//			response.addCookie(cookie);				
//		}
		
		return "/index.jsp";
	}

	private String doRemove(HttpServletRequest request, HttpServletResponse response) {
		String isbn = request.getParameter("isbn");
		bookService.remove(isbn);
		return "/books?action=list";
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
				
		return "/books?action=list";
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
		
		return "/books?action=list";
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
