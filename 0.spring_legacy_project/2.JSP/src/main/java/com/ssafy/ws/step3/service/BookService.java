package com.ssafy.ws.step3.service;

import java.util.List;

import com.ssafy.ws.step3.dto.Book;

public interface BookService {
	
	public void registBook(Book book);
	public Book getBook(String isbn);
	public List<Book> getBooks();
	public void remove(String isbn);
	public void modify(Book book);
	public List<Book> searchTitle(String title);

}
