<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="util.RsJson"%><%@page contentType="application/json" pageEncoding="UTF-8"%><%
    JSONArray objContas = RsJson.getJsonBySQL("select * from contas");
    JSONArray objClientes = RsJson.getJsonBySQL("select * from clientes");
    JSONArray objContasRP = RsJson.getJsonBySQL("select * from contasReceberPagar");
    
    JSONObject obj = new JSONObject();
    obj.put("clientes", objClientes);
    obj.put("contas", objContas);
    obj.put("contasReceberPagar", objContasRP);
    out.print(obj);
%>
