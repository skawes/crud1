package com.awes.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.awes.model.Book;
import com.awes.repository.Dao;

/**
 * Servlet implementation class ListAllBooks
 */
@WebServlet("/")
public class ListAllBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Dao dao;
  
    public ListAllBooks() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		 dao=new Dao("jdbc:mysql://localhost:3306/bookstore","root","");
	}

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		try {
			//System.out.println("hi");
			List<Book> listAllBooks=dao.getAllbooks();
			HttpSession session=request.getSession();
			session.setAttribute("listbook", listAllBooks);
			RequestDispatcher rd=request.getRequestDispatcher("booklist.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
