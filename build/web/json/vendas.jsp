<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="util.RsJson"%><%@page contentType="application/json" pageEncoding="UTF-8"%><%
    JSONArray objClientes = RsJson.getJsonBySQL("select * from clientes");
    JSONArray objVendas = RsJson.getJsonBySQL("select * from vendas order by dt_venda desc");
    
    JSONObject obj = new JSONObject();
    obj.put("clientes", objClientes);
    obj.put("vendas", objVendas);
    out.print(obj);
%>
