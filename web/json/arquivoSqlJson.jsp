<%@page import="org.json.JSONException"%>
<%@page import="util.RsJson"%>
<%@page import="java.util.Iterator"%>
<%@page import="util.ServicosHttp"%>
<%@page import="util.Arquivo"%>
<%@page import="util.Conexao"%>
<%@page import="util.DataTable"%>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%><%@page contentType="application/json" pageEncoding="UTF-8"%><%
    
    System.out.println("Inicio ");
    
    JSONObject jsonPost = ServicosHttp.getPostJson(request);
    
//    System.out.println("jsonPost: " + jsonPost);
    
    String arquivo = request.getParameter("arquivo");
    String sql = Arquivo.ler(arquivo);
    
//    System.out.println("Antes alteração argumentos: " + sql);
    
    try {
        Iterator<String> it = jsonPost.keys();
        
        while (it.hasNext()) {
            String key = it.next();
            String o = jsonPost.getString(key);
            System.out.println(key + " : " + o);
            sql = sql.replaceAll(":" + key, o);
        }
    } catch (JSONException e) {
        System.out.println(e.getMessage());
    }
    
//    System.out.print("Apos alteração argumentos: ");
//    System.out.println(sql);
    if (sql != null) {
        out.print(RsJson.getJsonBySQL(sql));
    }
    
%>
