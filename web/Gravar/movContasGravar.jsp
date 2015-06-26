<%@page import="hl.database.Tabela"%>
<%@page import="hl.database.DataBase"%>
<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    Tabela tabela = DataBase.getMovContas();
    tabela.parseJsonRequest(request);
    out.print(tabela.retornoGravacao());    
%>

