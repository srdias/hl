<%@page import="java.util.Iterator"%>
<%@page import="util.ServicosHttp"%>
<%@page import="util.Arquivo"%>
<%@page import="util.Conexao"%>
<%@page import="util.DataTable"%>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%><%@page contentType="application/json" pageEncoding="UTF-8"%><%

    JSONObject jsonPost = ServicosHttp.getPostJson(request);

    System.out.println(jsonPost);

    String arquivo = request.getParameter("arquivo");
    String sql = Arquivo.ler(arquivo);

    Iterator<String> it = jsonPost.keys();

    while (it.hasNext()) {
        String key = it.next();
        String o = jsonPost.getString(key);
        sql = sql.replaceAll("--:" + key+"--", "");
        sql = sql.replaceAll(":" + key, o);
        System.out.println(key + " : " + o);
    }

    System.out.println(sql);
    if (sql != null) {
        out.print(Conexao.getDataTable(Conexao.BANCO_PBCVS, sql));
    }

%>
