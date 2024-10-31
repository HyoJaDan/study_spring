package com.ssafy.ws.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ws.model.dto.Book;
import com.ssafy.ws.model.service.BookService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/books")
@Slf4j
public class BookController {
	BookService bookService;
	
	//Contructor Injection
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}


	/**
	 * <pre>/list를 get 방식으로 요청할 때 도서 정보를 보여준다.</pre>
	 * 아직 MVC의 model 영역을 완성하지 않았기 때문에 여러 개의 Book을 직접 생성해서 List 형태로 전달한다.
	 * @return ResponseEntity<List<String>>
	 */
	@GetMapping("/")
	public ResponseEntity<List<Book>> list(Model model) {
		List<Book> books = bookService.getBooks();
		return ResponseEntity.status(HttpStatus.OK).body(books);
	}

	@GetMapping("/{isbn}")
	public ResponseEntity<Book> list(@PathVariable String isbn) {
		Book book = bookService.getBook(isbn);
		return ResponseEntity.status(HttpStatus.OK).body(book);
	}

	/**
	 * post 방식의 regist 요청이 오면 정보를 저장한 후 목록조회 요청한다.
	 * @param book
	 * @return redirect:list
	 */
	@PostMapping("/")
	public ResponseEntity<Book> save(@RequestBody Book book) {
		log.debug(book.toString());
		bookService.registBook(book);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(book);
	}
	/**
	 * url: /books/{isbn}  method:PUT 수정
	 * @param isbn  String  PathVariable
	 * @param book  Book    RequestBody
	 * @return book : Book
	 */
	@PutMapping("/{isbn}")
	public ResponseEntity<Book> update(@PathVariable String isbn , 
									   @RequestBody Book book) {
		bookService.modify(book);
		return ResponseEntity.status(HttpStatus.OK).body(book);
	}
	/**
	 *  url : /books/{isbn} method: DELETE 삭제
	 * @param isbn : String  PathVariable
	 * @return 성공 : delete success 실패: delete fail
	 */
	@DeleteMapping("/{isbn}")
	public ResponseEntity<String> delete(@PathVariable String isbn) {
		bookService.remove(isbn);
		return ResponseEntity.status(HttpStatus.OK).body("delete success!");
	}

	

}
