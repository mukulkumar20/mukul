package com.mukul.cricular;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.BufferedReader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class circularServlet
 */
@WebServlet("/circularServlet")
public class circularServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
	
		
		PrintWriter out = response.getWriter();
		String CircularNo = request.getParameter("circularno");
		//String subject = request.getParameter("circularno");
		String usertype = request.getParameter("usertype");
		String userlang = request.getParameter("userlang");
		String filedesc = request.getParameter("filedesc");
		String issuedon = request.getParameter("issuedon");
		String issuedby = request.getParameter("issuedby");
//		out.println(CircularNo);
		//out.print(subject);
//		out.println(usertype);
//		out.println(userlang);
//		out.println(filedesc);
//		out.println(issuedon);
//		out.println(issuedby);
		RequestDispatcher dispatcher = null;
		Connection conn=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:/drishti", "root", "bansi@2000");
			PreparedStatement pst = conn.prepareStatement("insert into view_data(Circular_no,Category,File_language,File_Description,issued_on,issued_by) values(?,?,?,?,?,?)");
			pst.setString(1,CircularNo);
			pst.setString(2,usertype);
			pst.setString(3,userlang);
			pst.setString(4,filedesc);
			pst.setString(5,issuedon);
			pst.setString(6,issuedby);
			
			dispatcher = request.getRequestDispatcher("views.jsp");
			int rowCount = pst.executeUpdate();
			if(rowCount > 0) {
				 request.setAttribute("status", "success");
			}
			 else {
				  request.setAttribute("status","failed");
			  }
			dispatcher.forward(request, response);
		}
		catch(Exception e) {
			out.print(e);
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

//	private String getValue(Part part) throws IOException {
//	    BufferedReader reader = new BufferedReader(new InputStreamReader(part.getInputStream(), "UTF-8"));
//	    StringBuilder value = new StringBuilder();
//	    char[] buffer = new char[1024];
//	    for (int length = 0; (length = reader.read(buffer)) > 0;) {
//	        value.append(buffer, 0, length);
//	    }
//	    return value.toString();
//	}
}


