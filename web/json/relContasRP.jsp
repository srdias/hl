<%@page import="util.ResultSetConverter"%>
<%@page import="util.ServicosHttp"%>
<%@page import="org.json.JSONObject"%><%@page import="org.json.JSONArray"%><%@page import="util.RsJson"%><%@page contentType="application/json" pageEncoding="UTF-8"%><%

    JSONObject post = ServicosHttp.getPostJson(request);
    System.out.println(post.toString());

    String filtro = ""
            + " and crp.dt_emissao>= '" + post.getString("dtInicio") + "' \n"
            + " and crp.dt_emissao<= '" + post.getString("dtFim") + "' \n";

    if (post.getBoolean("selecaoCliente")) {
        JSONObject empresa = post.getJSONObject("empresa_id");
        filtro += " and crp.empresa_id = " + empresa.getInt("id") + "\n ";
    }

    String sql = ""
            + "select c.nome as nomeCliente,\n"
            + "       crp.dt_emissao, \n"
            + "       cast( (case crp.tipo_movimento_id \n"
            + "            when 1 then crp.valor_total\n"
            + "            when 2 then - crp.valor_total\n"
            + "            when 3 then - crp.valor_total\n"
            + "            when 4 then crp.valor_total\n"
            + "        end) as decimal(13,2)) as cpValor,\n"
            + "        crp.observacao\n"
            + "from contasreceberpagar crp,\n"
            + "     clientes c\n"
            + "where crp.empresa_id = c.id\n"
            + filtro
            + "order by crp.dt_emissao, crp.id";

    System.out.println(sql);

    JSONArray lista = RsJson.getJsonBySQL(sql);

    Double acumulado = 0.00;
    
    

    for (int i = 0; i < lista.length(); i++) {
        JSONObject row = lista.getJSONObject(i);
        String sValor=row.getString("cpValor");
        sValor = sValor.replaceAll("\\.", "").replaceAll(",", "."); 
        Double valor = Double.valueOf(sValor);
        acumulado = acumulado + valor;
        row.put("acumulado", ResultSetConverter.formatDecimal(acumulado) );
    }

    JSONArray objClientes = RsJson.getJsonBySQL("select * from clientes");

    JSONObject obj = new JSONObject();
    obj.put("clientes", objClientes);
    obj.put("relatorio", lista);
    out.print(obj);


%>
