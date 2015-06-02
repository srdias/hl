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

    public static Connection conexao() {
        Connection conn = null;

        try {
//            Class.forName("com.mysql.jdbc.Driver").newInstance();
//
//            conn = DriverManager.getConnection("jdbc:mysql://localhost/hidrolive?"
//                    + "user=root&password=");

            
             Class.forName("org.sqlite.JDBC");
             conn = DriverManager.getConnection("jdbc:sqlite:\\fontes\\hl\\databaseSqLite\\hl.db");
             
            // Do something with the Connection
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return conn;
    }
}
