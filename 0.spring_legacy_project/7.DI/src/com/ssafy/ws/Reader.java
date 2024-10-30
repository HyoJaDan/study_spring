package com.ssafy.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Reader {
	//field injection
//	@Autowired
//	@Qualifier("comic")
	private Book book;
//constructor injection
	public Reader(@Qualifier("magazine") Book book) {
		super();
		this.book = book;
	}


//	public Book getBook() {
//		return book;
//	}
//
//	//setter injection
//	@Autowired
//	@Qualifier("comic")	
//	public void setBook(Book book) {
//		this.book = book;
//	}

	public void read() {
		System.out.println(book.getInfo()+"을(를) 열심히 읽습니다.");
	}



}
