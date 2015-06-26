<%@page import="hl.database.Tabela"%>
<%@page import="hl.database.DataBase"%>
<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String nomeTabela = request.getParameter("tabela");
    System.out.println("nomeTabela:"+nomeTabela);
    Tabela tabela = DataBase.getTabelaByName(nomeTabela);
    tabela.parseJsonRequest(request);
    tabela.retornoGravacao();
    out.print(tabela.getJson());
%>

