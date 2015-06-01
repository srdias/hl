<%-- 
    Document   : teste
    Created on : 23/05/2015, 23:21:49
    Author     : AdrianoNB
--%>

<%@page import="hl.database.Tabela"%>
<%@page import="hl.database.DataBase"%>
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
            Tabela tabela;
            tabela = DataBase.getContasReceber();
            out.print("\n"
                    + tabela.getCmdCreateTable()
                    .replaceAll("\n", "<br>")
                    .replaceAll("\t", "&nbsp;&nbsp;&nbsp;"));
            
            tabela = DataBase.getContasReceberDetalhamento();
            out.print("<BR><BR>\n"
                    + tabela.getCmdCreateTable()
                    .replaceAll("\n", "<br>")
                    .replaceAll("\t", "&nbsp;&nbsp;&nbsp;"));

        %>
    </body>
</html>
