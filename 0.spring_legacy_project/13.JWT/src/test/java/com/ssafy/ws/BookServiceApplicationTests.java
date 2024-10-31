package com.ssafy.ws;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.ws.model.mapper.BookDAO;
import com.ssafy.ws.model.service.BookService;

@SpringBootTest
class BookServiceApplicationTests {
	@Autowired
	BookDAO bookDAO;
	
	@Autowired
	BookService bookService;
	
	@Test
	void contextLoads() {
		assertNotNull(bookDAO);
		assertNotNull(bookService);
	}

}
