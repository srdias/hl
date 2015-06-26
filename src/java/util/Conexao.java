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
}
