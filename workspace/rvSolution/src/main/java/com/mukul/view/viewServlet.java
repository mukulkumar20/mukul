package com.mukul.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class viewServlet
 */
@WebServlet("/viewServlet")
public class viewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		out.print("<table><tr><th>CircularNo</th><th>CircularNo</th><th>CircularNo</th><th>CircularNo</th></tr>");
	}

}
