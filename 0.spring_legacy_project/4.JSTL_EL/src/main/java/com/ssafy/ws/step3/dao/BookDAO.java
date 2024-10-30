package com.ssafy.ws.step3.dao;

import java.util.List;

import com.ssafy.ws.step3.dto.Book;

public interface BookDAO {
	
	public void createBook(Book book);
	public Book findById(String isbn);
	public List<Book> findAll();
	public List<Book> findByTitle(String title);
	public void delete(String isbn);
	public void update(Book book);
	

}
