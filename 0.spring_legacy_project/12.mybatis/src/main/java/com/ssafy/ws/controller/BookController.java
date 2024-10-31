package com.ssafy.ws.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.ws.model.dto.Book;
import com.ssafy.ws.model.service.BookService;

@Controller
@RequestMapping("/books")
public class BookController {
	BookService bookService;
	
	//Contructor Injection
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}



	/**
	 * <pre>/list를 get 방식으로 요청할 때 도서 정보를 보여준다.</pre>
	 * 아직 MVC의 model 영역을 완성하지 않았기 때문에 여러 개의 Book을 직접 생성해서 List 형태로 전달한다.
	 * 정보를 Model 객체에 저장 후 forward로 list를 호출한다.
	 * @return
	 */
	@GetMapping("/list")
	public String list(Model model) {
		List<Book> books = bookService.getBooks();
		model.addAttribute("books", books);
		return "list";
		
	}

	@GetMapping("/view")
	public String list(String isbn, Model model) {
		Book book = bookService.getBook(isbn);
		model.addAttribute("book", book);
		return "view";
		
	}


	/**
	 * get 방식의 regist 요청이 오면 regist 페이지를 forward로 연결한다.
	 * @return
	 */
	@GetMapping("/regist")
	public String registForm() {
		return "regist";
	}
	

	/**
	 * post 방식의 regist 요청이 오면 정보를 저장한 후 목록조회 요청한다.
	 * @param book
	 * @return redirect:list
	 */
	@PostMapping("/regist")
	public String save(Book book) {
		bookService.registBook(book);
		return "redirect:list";
	}
	/**
	 * post 방식의 update 요청이 오면 정보를 저장한 후 목록조회 요청한다.
	 * @param book
	 * @return redirect:list
	 */
	@PostMapping("/update")
	public String update(Book book) {
		bookService.modify(book);
		return "redirect:list";
	}
	/**
	 * post 방식의 delete 요청이 오면 정보를 저장한 후 목록조회 요청한다.
	 * @param isbn : String
	 * @return redirect:list
	 */
	@GetMapping("/delete")
	public String delete(String isbn) {
		bookService.remove(isbn);
		return "redirect:list";
	}

	/**
	 * <pre>
	 * /error/commonerr 요청이 get 방식으로 들어왔을 때 error/commonerr로 연결한다.
	 * </pre>
	 * 
	 * @return
	 */


	/**
	 * <pre>
	 * /error/404 요청이 get 방식으로 들어왔을 때 error/404로 연결한다.
	 * </pre>
	 * 
	 * @return
	 */


}
