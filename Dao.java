package com.awes.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.awes.model.Book;

public class Dao {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	public Dao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		super();
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
		
	}
	public void connect() throws SQLException{
		if(jdbcConnection==null || jdbcConnection.isClosed()){
			 try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jdbcConnection=DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		
		}
		
	}
	public void disconnect() throws SQLException{
		if(jdbcConnection!=null || !jdbcConnection.isClosed()){
			jdbcConnection.close();
		}
	}
	public List<Book> getAllbooks() throws SQLException {
		// TODO Auto-generated method stub
		connect();
		Book book;
		List<Book> listbook=new ArrayList<Book>();
		PreparedStatement statement=jdbcConnection.prepareStatement("select * from book");
		ResultSet rs=statement.executeQuery();
		 while(rs.next()){
			 int id=rs.getInt("book_id");
			 String author=rs.getString("author");
			 String title=rs.getString("title");
			 float price=rs.getFloat("price");
			book=new Book(id,author,title,price) ;
			listbook.add(book);
		 }
		 jdbcConnection.close();
			disconnect();
		return listbook;
	}
	public int saveBook(Book book) throws SQLException {
		// TODO Auto-generated method stub
		connect();
		String InsertSql="INSERT INTO book (author,title, price) VALUES (?, ?, ?)";
		PreparedStatement statement=jdbcConnection.prepareStatement(InsertSql);
		statement.setString(1,book.getAuthor());
		statement.setString(2,book.getTitle());
		statement.setFloat(3,book.getPrice());
		int flag=statement.executeUpdate();
		jdbcConnection.close();
		disconnect();
		return flag;
		
		
	}
	public void deleteBook(String id) throws SQLException {
		// TODO Auto-generated method stub
		connect();
		String DeleteSql="delete from book where book_id=?";
		PreparedStatement statement=jdbcConnection.prepareStatement(DeleteSql);
		statement.setString(1,id);
		statement.execute();
		jdbcConnection.close();
		disconnect();
		
		
		
	}
	

}
