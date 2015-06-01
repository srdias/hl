
<%@page import="util.RsJson"%>
<%@page contentType="json" pageEncoding="UTF-8"%>

<%
    String sql = "select * from contasreceber";
    RsJson.printJsonBySQL(out, sql);
%>
