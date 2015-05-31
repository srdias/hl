<%@page import="util.RsJson"%><%@page contentType="application/json" pageEncoding="UTF-8"%><%
    String sql = "select * from contas";
    RsJson.printJsonBySQL(out, sql);
%>
