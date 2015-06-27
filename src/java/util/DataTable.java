/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author u0180759
 */
public class DataTable {

    JSONArray listaColunas;
    JSONArray listaValores;
    JSONArray listaLinhas;
    JSONObject objResultato;

    public DataTable() {
        objResultato = new JSONObject();
        listaLinhas = new JSONArray();
    }

    public void headerBegin() {
        listaColunas = new JSONArray();
    }

    public void headerEnd() {
    }

    public void headerPutColumn(String nome, String tipo) {
        JSONObject itemColuna = new JSONObject();
        itemColuna.put("label", nome);
        itemColuna.put("type", tipo);
        listaColunas.put(itemColuna);
    }

    public void rowBegin() {
        listaValores = new JSONArray();
    }

    public void rowPutValue(String value) {

        JSONObject objItem = new JSONObject();
        objItem.put("v", value);
        listaValores.put(objItem);

    }

    public void rowPutValue(int value) {

        JSONObject objItem = new JSONObject();
        objItem.put("v", value);
        listaValores.put(objItem);

    }

    public void rowPutValue(Date value) {

        JSONObject objItem = new JSONObject();
        objItem.put("v", value);
        listaValores.put(objItem);

    }

    public void rowPutValue(Timestamp value) {

        JSONObject objItem = new JSONObject();
        objItem.put("v", value);
        listaValores.put(objItem);

    }

    public void rowEnd() {

        JSONObject itemLinha = new JSONObject();
        itemLinha.put("c", listaValores);
        listaLinhas.put(itemLinha);

    }

    public JSONObject build() {
        objResultato.put("rows", listaLinhas);
        objResultato.put("cols", listaColunas);
        return objResultato;
    }

    @Override
    public String toString() {
        return objResultato.toString();
    }

}
