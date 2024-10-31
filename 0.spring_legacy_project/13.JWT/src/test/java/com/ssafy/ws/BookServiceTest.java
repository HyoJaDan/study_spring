package com.ssafy.ws;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.ws.model.dto.Book;
import com.ssafy.ws.model.mapper.BookDAO;
import com.ssafy.ws.model.service.BookService;

@SpringBootTest
class BookServiceTest {
	
	@Autowired
	BookService bookService;
	
	@Test
	@Transactional
	void test() {
		Book book = Book.builder()
				.isbn("test")
				.author("author")
				.title("title")
				.price(0)
				.desc("desc")
				.build();
		bookService.registBook(book);
		book = bookService.getBook("test");
		assertEquals(book.getIsbn(), "test");
		
	}

}
