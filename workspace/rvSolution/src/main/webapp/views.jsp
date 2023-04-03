<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*" %>
    
   
    <%
    Class.forName("com.mysql.jdbc.Driver");
   String dbUrl = "jdbc:mysql://localhost/drishti";
   String username = "root";
   String password = "bansi@2000";
   Connection connection = DriverManager.getConnection(dbUrl, username, password);

   // Execute a query to retrieve data from the database
   String sql = "SELECT * FROM view_data";
   Statement statement = connection.createStatement();
   ResultSet resultSet = statement.executeQuery(sql);

   // Iterate over the ResultSet and generate HTML to display the data
%>
    
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="/rvSolution/style.css">
    <style>
    table{
    border:2px solid black;
    width:1242px;
   margin:41px 30px;
   padding:3px 4px;
    height: 80px;
    }
    td{
    text-align:center;
    padding:5px;
    }
    
    </style>
</head>

<body>
    <div id="loginform">
        <div class="container10">
            <section class="container11">
                <h1 id="add"> <a href="/rvSolution/add.html">Add circular</a> </h1><br>
                <h1 id="view"> <a href="/rvSolution/views.jsp">View Circulars</a></h1>
            </section>
            <section class="container12">
                <nav class="navbar1">
                    <ul id="heading">
                        <li>
                            <h1>Meri Panchayat</h1>
                        </li>
                        <!-- <li><Button class='loginbtn' onclick="document.getElementById('login-form').style.display='block'"
                        style="width: auto;">login</Button> -->
                        </li>
                    </ul>
                </nav>
                <h1 class="navbar1" style="text-align:center;" >Circulars</h1>
               
                        <table class="tabe21">
                       <tr>
                       <th>S.No</th>
                       <th>Circular No</th>
                       <th>Category</th>
                       <th>File language</th>
                       <th>File Description</th>
                       <th>Issued On</th>
                       <th>Issued By</th>
                       <!-- <th>File</th>-->
                        <th>Action</th> 
                       </tr>
                       <% while (resultSet.next()) { %>
						   <tr>
						      <td><%= resultSet.getInt("id") %></td>
						      <td><%= resultSet.getString("Circular_no") %></td>      
						      <td><%= resultSet.getString("Category") %></td>
						       <td><%= resultSet.getString("File_language") %></td>
						        <td><%= resultSet.getString("File_Description") %></td>
						         <td><%= resultSet.getString("issued_on") %></td>
						         <td><%= resultSet.getString("issued_by") %></td>
						          <td>
						          <form action="signServlet" method="post">
                 						<input type='hidden' name= "formtype"  value="delete" > 
						           <input type="hidden" value="<%= resultSet.getInt("id") %>" name="deleteId">
						           <input  style="background-color:green; padding:10px 10px; color:white; border-radius:30px;;" type="submit" value="Delete"> 
						           </form>
						           </td>
						         
						   </tr>
   						<% } %>
                       
                      </table>   
			          <%
						   // Close the database connection
						   resultSet.close();
						   statement.close();
						   connection.close();
						%>   
              
            </section>
        </div>
    </div>
    
        
   
</body>

</html>