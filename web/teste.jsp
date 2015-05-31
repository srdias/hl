<%-- 
    Document   : teste
    Created on : 23/05/2015, 23:21:49
    Author     : AdrianoNB
--%>

<%@page import="org.json.JSONArray"%>
<%@page import="util.ResultSetConverter"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String sql = "select * from contasreceber";
            try {
                Connection con = util.Conexao.conexao();
                java.sql.Statement s;
                java.sql.ResultSet rs;
                s = con.createStatement();
                rs = s.executeQuery(sql);
                while (rs.next()) {
                    out.println(rs.getInt(1));
                    out.println(rs.getString(2));
                }
            } catch (SQLException e) {
               System.out.println(e.getStackTrace());
            }
            
        %>
    </body>
</html>
