/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AdrianoNB
 */
public class Conexao {

    private static Connection conn = null;

    public static Connection conexao() {

        if (conn != null) {
            return Conexao.conn;
        }

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hidrolive?zeroDateTimeBehavior=convertToNull&user=root&password=");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/teste?zeroDateTimeBehavior=convertToNull&user=root&password=");


        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (ClassNotFoundException | IllegalAccessException ex) {
            System.out.println("Aqui 1");
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException | UnsatisfiedLinkError ex) {

        }

        return Conexao.conn;
    }
	
	  public static String getValue(ResultSetMetaData metaData, ResultSet rs, int coluna) throws SQLException {
        String valor;
        switch (metaData.getColumnType(coluna)) {
            case java.sql.Types.CHAR:
            case java.sql.Types.VARCHAR :
            case java.sql.Types.LONGVARCHAR :
                valor = "'" + rs.getString(coluna) + "'";
                break;

            case java.sql.Types.DATE:
                valor = "'" + rs.getDate(coluna) + "'";
                break;

            case java.sql.Types.TIMESTAMP:
                valor = "'" + rs.getTimestamp(coluna) + "'";
                break;

            default: {
//                System.out.println("Nome:"+metaData.getColumnName(coluna));
//                System.out.println("Tipo:"+metaData.getColumnType(coluna));
                valor = String.valueOf(rs.getInt(coluna));
//                System.out.println("ok");
            }
        }
        return valor;
    }

    public static String getData(int banco, String sql) {

        Connection theConn = null;
        StringBuilder lsRetorno = new StringBuilder();
        try {
            theConn = Conexao.getConnection(banco);
            if (theConn == null) {
                return "";
            }

            ResultSet rs;
            Statement stmt;
            stmt = theConn.createStatement();
            rs = stmt.executeQuery(sql);

            lsRetorno.append("[");

            ResultSetMetaData metaData = rs.getMetaData();

            int count = metaData.getColumnCount();
            lsRetorno.append("[");
            for (int i = 1; i <= count; i++) {

                lsRetorno.append("'")
                        .append(metaData.getColumnName(i))
                        .append("'");
                if (i != count) {
                    lsRetorno.append(",");
                }
            }
            lsRetorno.append("]");

            while (rs.next()) {
                lsRetorno.append(",\r");

                lsRetorno.append("[");
                for (int i = 1; i <= count; i++) {
                    lsRetorno.append(getValue(metaData, rs, i));
                    if (i != count) {
                        lsRetorno.append(",");
                    }
                }
                lsRetorno.append("]");

            }
            lsRetorno.append("]");

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("SQL: " + sql);
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (theConn != null) {
                    theConn.close();
                }
            } catch (Exception e) {
            }
        }

        return lsRetorno.toString();

    }

    public static void getValueDataTable(ResultSetMetaData metaData, ResultSet rs, int coluna, DataTable dataTable) throws SQLException {
        switch (metaData.getColumnType(coluna)) {
            case java.sql.Types.CHAR:
            case java.sql.Types.VARCHAR :
            case java.sql.Types.LONGVARCHAR :
                dataTable.rowPutValue(rs.getString(coluna));
                break;

            case java.sql.Types.DATE:
                dataTable.rowPutValue(rs.getDate(coluna));
                break;

            case java.sql.Types.NUMERIC:
                dataTable.rowPutValue(String.valueOf(rs.getDouble(coluna)));
                break;

            case java.sql.Types.TIMESTAMP:
                dataTable.rowPutValue(rs.getTimestamp(coluna));
                break;

            default: {
                dataTable.rowPutValue(rs.getInt(coluna));
            }
        }
    }

    public static String getTypeDataTable(ResultSetMetaData metaData, ResultSet rs, int coluna) throws SQLException {
        String valor;
        switch (metaData.getColumnType(coluna)) {
            case java.sql.Types.CHAR:
                valor = "string";
                break;

            case java.sql.Types.DATE:
                //valor = "date";
                valor = "string";
                break;

            case java.sql.Types.TIMESTAMP:
//                valor = "datetime";
                valor = "string";
                break;

            default: {
                valor = "number";
            }
        }
        return valor;
    }

    public static String getDataTable(int banco, String sql) {

        Connection theConn = null;
        DataTable dataTable = new DataTable();
        try {
            theConn = Conexao.getConnection(banco);
            if (theConn == null) {
                return "";
            }

            ResultSet rs;
            Statement stmt;
            stmt = theConn.createStatement();
            rs = stmt.executeQuery(sql);

            ResultSetMetaData metaData = rs.getMetaData();

            dataTable.headerBegin();
            int count = metaData.getColumnCount();
            for (int i = 1; i <= count; i++) {

                dataTable.headerPutColumn(
                        metaData.getColumnName(i),
                        Conexao.getTypeDataTable(metaData, rs, i)
                );
            }
            dataTable.headerEnd();

            while (rs.next()) {

                dataTable.rowBegin();

                for (int i = 1; i <= count; i++) {
                    getValue(metaData, rs, i);
                    Conexao.getValueDataTable(metaData, rs, i, dataTable);
                }

                dataTable.rowEnd();
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("SQL: " + sql);
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (theConn != null) {
                    theConn.close();
                }
            } catch (Exception e) {
            }
        }

        dataTable.build();
        
        return dataTable.toString();

    }

}
