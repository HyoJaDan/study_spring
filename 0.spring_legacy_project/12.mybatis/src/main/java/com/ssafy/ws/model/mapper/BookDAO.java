package com.ssafy.ws.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.ws.model.dto.Book;


@Mapper
public interface BookDAO {
	
	public void createBook(Book book);
	public Book findById(String isbn);
	public List<Book> findAll();
	public List<Book> findByTitle(String title);
	public void delete(String isbn);
	public void update(Book book);
	

}
