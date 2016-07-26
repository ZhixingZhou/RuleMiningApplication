/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seu.zzx.rule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import seu.zzx.dbc.DatabaseConnection;
import seu.zzx.util.OperateFile;

/**
 *
 * @author zzx_seu
 */
public class ImportRuleToDB {

    public static DatabaseConnection dc = new DatabaseConnection();

    /**
     * 创建表
     */
    public static void createTable(String tableName) {
        Connection conn = dc.getConnection();
        PreparedStatement pstmt = null;
        StringBuffer sb = new StringBuffer();
        sb.append("DROP TABLE IF EXISTS " + tableName + ";");
        String sql = sb.toString();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sb = new StringBuffer();
        sb.append("CREATE TABLE " + tableName + "(");
        sb.append("Conditions text,");
        sb.append("result varchar(50),");
        sb.append("Support varchar(50),");
        sb.append("Confidence varchar(50))");
        sql = sb.toString();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            pstmt.close();
            dc.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将数据导入数据库
     */
    public static void importDataToDB(String tableName, String[][] data) {
        Connection conn = dc.getConnection();
        PreparedStatement pstmt = null;
        StringBuffer sb = new StringBuffer();
        int j = 0;
        String sql = "";
        sql = "DELETE FROM " + tableName;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (Exception e) {

        }
        try {
            Statement stmt = conn.createStatement();
            conn.setAutoCommit(false);
            for (int i = 0; i < data.length; ++i) {
                sb.append("INSERT INTO " + tableName + " values(");
                for (j = 0; j < data[i].length - 1; ++j) {
                    sb.append("'" + data[i][j] + "',");
                }
                sb.append("'" + data[i][j] + "')");
                sql = sb.toString();
                stmt.addBatch(sql);
                sb = new StringBuffer();
            }
            int[] row = stmt.executeBatch();
            if (row.length == data.length) {
                conn.commit();
                System.out.println("导入成功！");
            }
            dc.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public static String[][] getRuleFromExcel(String fileName) {
        OperateFile.openExcel(fileName);
        int rowCount = OperateFile.getRowCount(0);
        int colCount = OperateFile.getColCount(0);
        String[][] data = new String[rowCount][colCount];
        for (int i = 1; i <= rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                data[i - 1][j] = OperateFile.getCellContent(j, i);
            }
        }
        return data;
    }
}
