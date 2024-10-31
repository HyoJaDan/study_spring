package com.ssafy.ws.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.ws.model.dto.Book;
import com.ssafy.ws.model.mapper.BookDAO;

/**
 * Service Layer : Transaction 처리나 일반 Business Logic 처리
 */
@Service
public class BookServiceImpl implements BookService {
	private BookDAO dao;

	public BookServiceImpl(BookDAO dao) {
		this.dao = dao;
	}

	@Override
	public void registBook(Book book) {
		dao.createBook(book);
	}

	@Override
	public Book getBook(String isbn) {
		return dao.findById(isbn);
	}

	@Override
	public List<Book> getBooks() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public void remove(String isbn) {
		dao.delete(isbn);
		
	}

	@Override
	public void modify(Book book) {
		dao.update(book);
		
	}

	@Override
	public List<Book> searchTitle(String title) {
		
		return dao.findByTitle(title);
	}

}
