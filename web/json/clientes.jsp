<%@page import="util.RsJson"%><%@page contentType="application/json" pageEncoding="UTF-8"%><%
    String sql = "select clientes.id, clientes.nome, clientes.endereco, "
            + " clientes.cidade_id, clientes.preco, clientes.taxas, "
            + " cidades.nome as nomeCidade, 1 as teste "
            + " from clientes, cidades "
            + " where cidades.id = clientes.cidade_id "
            + " order by clientes.id";
    RsJson.printJsonBySQL(out, sql);
%>
