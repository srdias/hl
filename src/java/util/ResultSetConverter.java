/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ResultSetConverter {

    public static JSONArray convert(ResultSet rs)
            throws SQLException, JSONException {
        JSONArray json = new JSONArray();
        ResultSetMetaData rsmd = rs.getMetaData();

        int numColumns = rsmd.getColumnCount();

        while (rs.next()) {
            JSONObject obj = new JSONObject();

            for (int i = 1; i < numColumns + 1; i++) {
                String column_name = rsmd.getColumnLabel(i);

                switch (rsmd.getColumnType(i)) {
                    case java.sql.Types.DATE: {
                        String lsData = formatDate(rs.getObject(column_name).toString());
                        obj.put(column_name, lsData);
                        break;
                    }
                    case java.sql.Types.DECIMAL: {
                        String lsData = formatDecimal(rs.getDouble(column_name));
                        obj.put(column_name, lsData);
                        break;
                    }
                    case java.sql.Types.INTEGER: {
                        String lsData = formatDecimal(rs.getInt(column_name));
                        obj.put(column_name, lsData);
                        break;
                    }
                    default: {
                        obj.put(column_name, rs.getObject(column_name));
                    }
                }
            }
            json.put(obj);
        }

        return json;
    }

    public static String formatDecimal(int valor) {
        DecimalFormat df = new DecimalFormat(",##0");
        String dx = df.format(valor);
        return dx;
    }

    public static String formatDecimal(double valor) {
        DecimalFormat df = new DecimalFormat(",##0.00");
        String dx = df.format(valor);
        return dx;
    }

    public static String formatDate(String date_s) {
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd");
        Date date;
        String retorno = "";
        try {
            date = dt.parse(date_s);
            SimpleDateFormat dt1 = new SimpleDateFormat("dd/mm/yyyy");
            retorno = dt1.format(date);
        } catch (ParseException ex) {
            Logger.getLogger(ResultSetConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public static JSONObject convertRegistro(ResultSet rs)
            throws SQLException, JSONException {
        JSONObject obj = null;
        ResultSetMetaData rsmd = rs.getMetaData();

        int numColumns = rsmd.getColumnCount();

        if (rs.next()) {
            obj = new JSONObject();

            for (int i = 1; i < numColumns + 1; i++) {
                String column_name = rsmd.getColumnLabel(i);
                obj.put(column_name, rs.getObject(column_name));
            }
        }

        return obj;
    }

}
