<%@page import="hl.database.Tabela"%>
<%@page import="hl.database.DataBase"%>
<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    Tabela tabela = DataBase.getClientes();
    tabela.parseJsonRequest(request);
    System.out.println("Aqui");
    out.print(tabela.retornoGravacao());    
%>

