package com.awes.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.awes.model.Book;
import com.awes.repository.Dao;

/**
 * Servlet implementation class AddBook
 */
@WebServlet("/addbook")
public class AddBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Dao dao;
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		 dao=new Dao("jdbc:mysql://localhost:3306/bookstore","root","");
	}

       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String title=request.getParameter("title");
		String author=request.getParameter("author");
		float price=Float.parseFloat(request.getParameter("price"));
		int flag=-1;
		Book book=new Book(author,title,price);
		try {
			flag=dao.saveBook(book);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(flag>0)
		response.sendRedirect("");
		else
			response.sendRedirect("error.jsp");
		
		
		
	}

}
