<%-- 
    Document   : list
    Created on : Nov 20, 2017, 5:40:26 PM
    Author     : The Dark Coder
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>ALL BOOKS DETAILS </h1>
        <table border="1">
            <thead>
                <tr>
                    <th>BOOK ID</th>
                    <th>BOOK NAME</th>
                    <th>PRICE</th>
                    <th>OFFER</th>
                    <th>IMAGE</th>
                </tr>
            </thead>
            <tbody>
                <%
                       try{
                           Class.forName("com.mysql.jdbc.Driver");
                           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
                           String sql = "SELECT * from books";
                           PreparedStatement ps  = con.prepareStatement(sql);
                           ResultSet rs= ps.executeQuery();
                            while(rs.next()){
                %>
                <tr>
                    <td><%=rs.getString(2)%></td>
                    <td><%=rs.getString(3)%></td>
                    <td><%=rs.getString(4)%></td>
                    <td><%=rs.getString(5)%></td>
                    <td><a href="<%="Uploaded/"+rs.getString(5)%>" download="download"><img src="<%="Uploaded/"+rs.getString(5)%>" style="height: 100px;"></a></td>
                </tr>
                <%
                           }
                           
                       }catch(Exception e){out.println(e);}
                %>
                
            </tbody>
        </table>

    </body>
</html>
