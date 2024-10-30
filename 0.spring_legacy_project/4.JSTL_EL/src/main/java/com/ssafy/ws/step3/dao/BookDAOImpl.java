package com.ssafy.ws.step3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.ws.step3.dto.Book;
import com.ssafy.ws.step3.util.DBUtil;

public class BookDAOImpl implements BookDAO {
	private DBUtil db;
	private static BookDAO dao = new BookDAOImpl();
	//Constructor Injection
	private BookDAOImpl() {
		db = DBUtil.getInstance();
	}

	public static BookDAO getInstance() {return dao;}
	@Override
	public void createBook(Book book) {
		Connection con=null;
		PreparedStatement stmt=null;
		String sql = new StringBuilder()
					.append("INSERT INTO book(isbn, author, title, price, `desc`) \n")
					.append("VALUES(?,?,?,?,?)")
					.toString();
		try {
			con = db.getConnection();
			stmt = con.prepareStatement(sql);
			int index=1;
			stmt.setString(index++, book.getIsbn());
			stmt.setString(index++, book.getAuthor());
			stmt.setString(index++, book.getTitle());
			stmt.setInt(index++, book.getPrice());
			stmt.setString(index++, book.getDesc());

			stmt.executeUpdate();
		
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			db.close(stmt, con);
		}

	}

	@Override
	public Book findById(String isbn) {
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet result=null;
		Book book=null;
		String sql= new StringBuilder()
					.append("SELECT * FROM book\n")
					.append("WHERE isbn=?")
				.toString();
		
		try {
			con = db.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, isbn);
			result = stmt.executeQuery();
			
			if(result.next()) {
				book=new Book();
				book.setIsbn(result.getString("isbn"));
				book.setTitle(result.getString("title"));
				book.setAuthor(result.getString("author"));
				book.setPrice(result.getInt("price"));
				book.setDesc(result.getString("desc"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			db.close(result, stmt, con);
		}
		
		return book;
	}

	@Override
	public List<Book> findAll() {
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet result= null;
		String sql = new StringBuilder()
						.append("SELECT isbn, title, author, price, `desc`\n")
						.append("FROM book")
						.toString();
		List<Book> books = new ArrayList<>();
		
		try {
			con = db.getConnection();
			stmt = con.prepareStatement(sql);
			
			result = stmt.executeQuery();
			while(result.next()) {
				Book book = new Book();
				book.setIsbn(result.getString("isbn"));
				book.setAuthor(result.getString("author"));
				book.setTitle(result.getString("title"));
				book.setPrice(result.getInt("price"));
				book.setDesc(result.getString("desc"));
				books.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			db.close(result, stmt, con);
		}
		
		
		return books;
	}

	@Override
	public void delete(String isbn) {
		Connection con=null;
		PreparedStatement stmt=null;
		String sql=new StringBuilder()
						.append("DELETE FROM book\n")
						.append("WHERE isbn=?")
						.toString();
		try {
			con = db.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, isbn);
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			db.close(stmt, con);
		}
		
	}

	@Override
	public void update(Book book) {
		Connection con=null;
		PreparedStatement stmt=null;
		String sql = new StringBuilder()
					.append("UPDATE  book SET author=?, title=?, price=?, `desc`=? \n")
					.append("WHERE isbn=?")
					.toString();
		try {
			con = db.getConnection();
			stmt = con.prepareStatement(sql);
			int index=1;
			stmt.setString(index++, book.getAuthor());
			stmt.setString(index++, book.getTitle());
			stmt.setInt(index++, book.getPrice());
			stmt.setString(index++, book.getDesc());
			stmt.setString(index++, book.getIsbn());

			stmt.executeUpdate();
		
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			db.close(stmt, con);
		}
		
	}
	@Override
	public List<Book> findByTitle(String title) {
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet result= null;
		String sql = new StringBuilder()
						.append("SELECT isbn, title, author, price, `desc`\n")
						.append("FROM book\n")
						.append("WHERE title LIKE concat('%',?,'%')")
						.toString();
		List<Book> books = new ArrayList<>();
		
		try {
			con = db.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setString(1,title);
			
			result = stmt.executeQuery();
			while(result.next()) {
				Book book = new Book();
				book.setIsbn(result.getString("isbn"));
				book.setAuthor(result.getString("author"));
				book.setTitle(result.getString("title"));
				book.setPrice(result.getInt("price"));
				book.setDesc(result.getString("desc"));
				books.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			db.close(result, stmt, con);
		}		
		
		return books;
	}
	public static void main(String[] args) {
		
		BookDAO dao = new BookDAOImpl();
		Book book = new Book();
		book.setIsbn("111-111");
		book.setAuthor("조용준1");
		book.setTitle("자바프로그래밍1");
		book.setPrice(25000);
		book.setDesc("자바프로그래밍 기초1");
		dao.createBook(book);
		System.out.println("=== 111-111 생성 후 검색 ====");
		System.out.println(dao.findById("111-111"));
		book.setAuthor("SSAFY");
		dao.update(book);
		System.out.println("=== 111-111 수정 후 검색 ====");
		System.out.println(dao.findById("111-111"));		
		
		dao.delete("111-111");
		System.out.println("=== 삭제 후 목록검색 ====");
		List<Book> list = dao.findAll();
		for(Book b: list)
			System.out.println(b);
		
		System.out.println("=== 자바 책제목 검색 ===");
		list= dao.findByTitle("자바");
		for(Book b: list)
			System.out.println(b);	
	}


}
