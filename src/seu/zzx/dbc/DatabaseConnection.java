/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seu.zzx.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import seu.zzx.constant.ConstantLibrary;

/**
 *
 * @author zzx_seu
 */
public class DatabaseConnection {

    private Connection conn = null;

    public Connection getConnection() {
        try {
            Class.forName(ConstantLibrary.DRIVER);
            conn = DriverManager.getConnection(ConstantLibrary.URL, ConstantLibrary.USERNAME, ConstantLibrary.PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
