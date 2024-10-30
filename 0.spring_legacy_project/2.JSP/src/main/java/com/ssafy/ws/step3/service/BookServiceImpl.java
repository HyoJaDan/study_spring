package com.ssafy.ws.step3.service;

import java.util.List;

import com.ssafy.ws.step3.dao.BookDAO;
import com.ssafy.ws.step3.dao.BookDAOImpl;
import com.ssafy.ws.step3.dto.Book;

/**
 * Service Layer : Transaction 처리나 일반 Business Logic 처리
 */
public class BookServiceImpl implements BookService {
	private BookDAO dao;
	private static BookService service=new BookServiceImpl();
	private BookServiceImpl() {
		dao = BookDAOImpl.getInstance();
	}

	public static BookService getInstance() {return service;}
	
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
