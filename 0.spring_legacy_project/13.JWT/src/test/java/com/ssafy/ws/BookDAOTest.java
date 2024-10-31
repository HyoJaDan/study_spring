package com.ssafy.ws;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.ws.model.dto.Book;
import com.ssafy.ws.model.mapper.BookDAO;

@SpringBootTest
class BookDAOTest {

	@Autowired
	BookDAO bookDAO;
	
	@Test
	@Transactional
	void test() {
		Book book = Book.builder()
					.isbn("isbn")
					.author("author")
					.title("title")
					.price(0)
					.desc("desc")
					.build();
		
//		bookDAO.createBook(book);
		book = bookDAO.findById("isbn");
		assertEquals(book.getIsbn(), "isbn");
	}

}
