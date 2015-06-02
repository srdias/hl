<%-- 
    Document   : createdb
    Created on : 01/06/2015, 21:43:27
    Author     : AdrianoNB
--%>


<%@page import="hl.database.DataBase"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            
            DataBase dataBase = new DataBase();
            dataBase.createDataBase(out);
            
        %>
    </body>
</html>
