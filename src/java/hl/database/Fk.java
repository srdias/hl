/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hl.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import util.ResultSetConverter;

/**
 *
 * @author AdrianoNB
 */
public class Fk {

    private Tabela tabelaPrincipal;
    private Tabela tabelaReferenciada;

    private String colunaTabelaPrincipal;
    private String colunaTabelaReferenciada;

    public Tabela getTabelaPrincipal() {
        return tabelaPrincipal;
    }

    public void setTabelaPrincipal(Tabela principal) {
        this.tabelaPrincipal = principal;
    }

    public Tabela getTabelaReferenciada() {
        return tabelaReferenciada;
    }

    public void setTabelaReferenciada(Tabela referenciada) {
        this.tabelaReferenciada = referenciada;
    }

    public String getColunaTabelaPrincipal() {
        return colunaTabelaPrincipal;
    }

    public void setColunaTabelaPrincipal(String colunaTabelaPrincipal) {
        this.colunaTabelaPrincipal = colunaTabelaPrincipal;
    }

    public String getColunaTabelaReferenciada() {
        return colunaTabelaReferenciada;
    }

    public void setColunaTabelaReferenciada(String colunaTabelaReferenciada) {
        this.colunaTabelaReferenciada = colunaTabelaReferenciada;
    }

    public void putDataFk(Connection conn, JSONArray json) {

        String sql = getTabelaReferenciada().getCmdSelect();

        sql += " WHERE "
                + getTabelaReferenciada().getNome() + "." + getColunaTabelaReferenciada()
                + "=";
        System.out.println("FK: " + sql);
        for (int i = 0; i < json.length(); i++) {

            JSONObject objRegistro = null;
            JSONObject obj = json.getJSONObject(i);
            int valor = obj.getInt(getColunaTabelaPrincipal());

            ResultSet rs = getTabelaPrincipal().getResultSetBySQL(conn, sql + valor);
            if (rs != null) {
                try {
                    objRegistro = ResultSetConverter.convertRegistro(rs);
                    rs.close();
                } catch (SQLException | JSONException ex) {
                    Logger.getLogger(Fk.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (objRegistro != null) {
                System.out.println("objRegistro: " + objRegistro.toString());
            }else{
                objRegistro = new JSONObject();
            }
            obj.put(getColunaTabelaPrincipal(), objRegistro);
        }

        /*
         Iterator<?> keys = json.keys();

         while (keys.hasNext()) {

         JSONObject obj = (JSONObject) keys;
         String key = (String) keys.next();

         if (key.equals(getColunaTabelaPrincipal())) {
         String sqlExec = sql + ((JSONObject) key).getInt();
         ResultSet rs = getTabelaPrincipal().getResultSetBySQL(conn, sql + "")
         JSONObject objRec = 
                
         }
         }
         */
    }
}
