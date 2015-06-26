<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="util.RsJson"%><%@page contentType="application/json" pageEncoding="UTF-8"%><%
    JSONArray objClientes = RsJson.getJsonBySQL("select * from clientes");
    JSONArray objCidades = RsJson.getJsonBySQL("select * from cidades");
    
    JSONObject obj = new JSONObject();
    obj.put("clientes", objClientes);
    obj.put("cidades", objCidades);
    
    out.print(obj);
%>
