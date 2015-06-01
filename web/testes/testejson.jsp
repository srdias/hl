<%-- 
    Document   : testejson
    Created on : 23/05/2015, 23:41:00
    Author     : AdrianoNB
--%>

<%@page import="org.json.JSONObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%

            //instancia um novo JSONObject
            JSONObject my_obj = new JSONObject();

            //preenche o objeto com os campos: titulo, ano e genero
            my_obj.put("titulo", "JSON x XML: a Batalha Final");
            my_obj.put("ano", 2012);
            my_obj.put("genero", "Ação");

            //serializa para uma string e imprime
            String json_string = my_obj.toString();
            out.println("objeto original -> " + json_string);
            out.println();

            //altera o titulo e imprime a nova configuração do objeto
            my_obj.put("titulo", "JSON x XML: o Confronto das Linguagens");
            json_string = my_obj.toString();
            out.println("objeto com o título modificado -> " + json_string);
            out.println();

            //recupera campo por campo com o método get() e imprime cada um
            String titulo = my_obj.getString("titulo");
            Integer ano = my_obj.getInt("ano");
            String genero = my_obj.getString("genero");

            out.println("titulo: " + titulo);
            out.println("ano: " + ano);
            out.println("genero: " + genero);
        %>

    </body>
</html>
