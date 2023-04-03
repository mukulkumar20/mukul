package com.mukul.sign;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class signServlet
 */
@WebServlet("/signServlet")
public class signServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		RequestDispatcher dispatcher = null;
		Connection conn = null;
		
		PrintWriter out = response.getWriter();
//		out.print(utype);
//		out.print(name);
//		out.print(email);
//		out.print(number);
//		out.print(password);
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:/drishti", "root", "bansi@2000");
			String formtype=request.getParameter("formtype");
//			out.print(formtype);
			if(formtype.equals("signUp")) {
				PreparedStatement pst = conn.prepareStatement("insert into new_table(utype,name,email,mobile_number,password) values(?,?,?,?,?)");
				String utype = request.getParameter("usertype");
				String name = request.getParameter("name");
				String email = request.getParameter("email");
				String number = request.getParameter("number");
				String password = request.getParameter("pass");
				pst.setString(1,utype);
				pst.setString(2,name);
				pst.setString(3,email);
				pst.setString(4,number);
				pst.setString(5,password);
				int rowCount = pst.executeUpdate();
					
				dispatcher = request.getRequestDispatcher("sign.html");
				if(rowCount>0) {
					request.setAttribute("status", "success");
				}
				else {
					request.setAttribute("status","failed");
				}
				dispatcher.forward(request, response);
			}
			else if(formtype.equals("offical")){
				PreparedStatement pst = conn.prepareStatement("select * from  new_table where name=? and password=? and utype=? LIMIT 1");
				String name = request.getParameter("name");
				String password = request.getParameter("pass");

				pst.setString(1,name);
				pst.setString(2,password);
				pst.setString(3,"3");

				ResultSet rs = pst.executeQuery();

				if (!rs.isBeforeFirst()) {
//					out.print("Not valid user");
					response.sendRedirect("/rvSolution/offical.html");
				}
				while (rs.next()) {
					int  id = rs.getInt("id");
					
					if(id!=0) {
//						out.print("valid user");
						response.sendRedirect("/rvSolution/forms.html");
						break;
					}
					
				}

			}
			else if(formtype.equals("login")){
				PreparedStatement pst = conn.prepareStatement("select * from  new_table where mobile_number=? and password=? LIMIT 1 ");
				String number = request.getParameter("number");
				String password = request.getParameter("pass");
				pst.setString(1,number);
				pst.setString(2,password);

				ResultSet rs = pst.executeQuery();
				if (!rs.isBeforeFirst()) {
					response.sendRedirect("/rvSolution/login.html");
				}
				while (rs.next()) {
					int  id = rs.getInt("id");
					
					if(id!=0) {
						response.sendRedirect("/rvSolution/forms.html");
						break;
						
					}
					
				}

			}
			
			
			else if(formtype.equals("delete")){
				PreparedStatement pst = conn.prepareStatement("delete  from  view_data where id=? LIMIT 1 ");
				String Delete = request.getParameter("deleteId");
				/*
				 * out.println(formtype); out.println(Delete);
				 */
				pst.setString(1,Delete);
				

				int rowCount = pst.executeUpdate();
				
				dispatcher = request.getRequestDispatcher("views.jsp");
				if(rowCount>0) {
					request.setAttribute("status", "success");
				}
				else {
					request.setAttribute("status","failed");
				}
				dispatcher.forward(request, response);
			}
			
		}
		    
		catch(Exception e) {
			out.print(e);
			
			e.printStackTrace();
			
			
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		}

}
