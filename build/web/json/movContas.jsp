<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="util.RsJson"%><%@page contentType="application/json" pageEncoding="UTF-8"%><%
    JSONArray objContas = RsJson.getJsonBySQL("select * from contas");
    JSONArray objMovContas = RsJson.getJsonBySQL("select * from movContas");
    
    JSONObject obj = new JSONObject();
    obj.put("contas", objContas);
    obj.put("movContas", objMovContas);
    out.print(obj);
%>
