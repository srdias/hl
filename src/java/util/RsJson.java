/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.jsp.JspWriter;
import org.json.JSONArray;

/**
 *
 * @author AdrianoNB
 */
public class RsJson {

    public static JSONArray getJsonBySQL(JspWriter out, String sql) throws IOException {
        JSONArray lista = null;
        try {
            Connection con = util.Conexao.conexao();
            java.sql.Statement s;
            java.sql.ResultSet rs;
            s = con.createStatement();
            rs = s.executeQuery(sql);
            lista = ResultSetConverter.convert(rs);
        } catch (SQLException e) {
            out.print(e.getStackTrace());
        }
        return lista;
    }

    public static void printJsonBySQL(JspWriter out, String sql) throws IOException {
        out.print(RsJson.getJsonBySQL(out, sql));

    }
}
