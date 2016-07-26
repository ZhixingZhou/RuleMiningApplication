/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seu.zzx.dbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.swing.JCheckBox;
import seu.zzx.constant.ConstantLibrary;
import seu.zzx.dbc.DatabaseConnection;

/**
 *
 * @author zzx_seu
 */
public class EditTable {
    public static int diffienceCount = 0;
    public static int uniqueCount = 0;
    public static String[][] dataValue = null;
    public static String[] columnValue = {"序号","值","个数"};
    public static DatabaseConnection dc = new DatabaseConnection();
    
    /**
     * 创建表
     */
    public static void createTable(String tableName, String[] colNames) {
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
        int i = 0;
        for (i = 0; i < colNames.length - 1; ++i) {
            sb.append(colNames[i] + " varchar(50),");
        }
        sb.append(colNames[i] + " varchar(50))");
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
    /*
    *获得列名数组
    */
    public static String[] getColumnNamesArray(String tableName){
        Connection conn = dc.getConnection();
        PreparedStatement stmt = null;
        String sql = "select * from " + tableName;
        try {
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            ConstantLibrary.columnNameTemp = new String[rsmd.getColumnCount()];
            ConstantLibrary.columnNames = new String[rsmd.getColumnCount()];
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                ConstantLibrary.columnNameTemp[i-1] = rsmd.getColumnName(i);
                ConstantLibrary.columnNames[i-1] = ConstantLibrary.columnMap.get(ConstantLibrary.columnNameTemp[i-1]);
            }
            dc.close();
        } catch (Exception e) {

        } finally {
            dc.close();
        }
        return ConstantLibrary.columnNames;
    }
    /*
    *获得表数据数组
    */
    public static String[][] getDataArray(String tableName){
        String[][] data = null;
        Connection conn = dc.getConnection();
        PreparedStatement pstmt = null;
        String sql = "select * from " + tableName;
        try{
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            rs.last();
            data = new String[rs.getRow()][rsmd.getColumnCount()];
            rs.beforeFirst();
            int row = 0;
            while(rs.next()){
                for(int i=1;i<=rsmd.getColumnCount();++i){
                    data[row][i-1] = rs.getString(i);
                }
                row++;
            }
            dc.close();
        }catch(Exception e){
            
        }
        return data;
    }
    /*
    *根据列名删除列
    */
    public static void deleteColumn(String columnName){
        Connection conn = dc.getConnection();
        PreparedStatement pstmt = null;
        String sql = "alter table " + ConstantLibrary.MERGETABLENAME +" drop " + columnName;
        try{
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            pstmt.close();
            dc.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    /*
    *根据列名求空值个数
    */
    public static int getNullCount(String columnName){
        int count = 0;
        Connection conn = dc.getConnection();
        PreparedStatement pstmt = null;
        String sql = "select count(*) from " + ConstantLibrary.MERGETABLENAME + " where " + columnName + "=''";
        try{
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                count = rs.getInt(1);
            }
            dc.close();
        }catch(Exception e){
            
        }
        return count;
    }
    /*
    *根据列名获得不同值的个数
    */
    public static void getDiffientAndUniqueValueCount(String columnName){
        Connection conn = dc.getConnection();
        PreparedStatement pstmt = null;
        String sql = "select "+ columnName + ",count(" + columnName + ") from " + ConstantLibrary.MERGETABLENAME + " group by " + columnName;
        try{
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            rs.last();
            EditTable.dataValue = new String[rs.getRow()][3];
            EditTable.diffienceCount = rs.getRow();
            rs.beforeFirst();
            int row = 0;
            EditTable.uniqueCount = 0;
            while(rs.next()){
                for(int i=1;i<=3;++i){
                    EditTable.dataValue[row][0] = String.valueOf(row+1);
                    EditTable.dataValue[row][1] = rs.getString(1);
                    EditTable.dataValue[row][2] = rs.getString(2);
                }
                if("1".equals(EditTable.dataValue[row][2])){
                    EditTable.uniqueCount++;
                }
                row++;
            }
            dc.close();
        }catch(Exception e){
        
        }
    }
    /*
    *获取是否流失标识值
    */
    public static void getIsFlowAwayValue(String columnName){
        Connection conn = dc.getConnection();
        PreparedStatement pstmt = null;
        String sql = "select distinct("+ columnName +  ") from " + ConstantLibrary.MERGETABLENAME ;
        try{
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            ConstantLibrary.lostedFlagValue = rs.getString(1);
            ConstantLibrary.lostedFlagValueLength = ConstantLibrary.lostedFlagValue.length();
            rs.next();
            ConstantLibrary.unlostedFlagValue = rs.getString(1);
            ConstantLibrary.unlostedFlagValueLength = ConstantLibrary.unlostedFlagValue.length();
            dc.close();
        }catch(Exception e){
        
        }
    }
    /*
    *获取指定列的已流失的数据
    */
    public static String[] getLostedDataColumnName(String columnName){
        String[] lostedData = null;
        Connection conn = dc.getConnection();
        PreparedStatement pstmt = null;
        String sql = "select " + columnName + " from " + ConstantLibrary.MERGETABLENAME + " where " + ConstantLibrary.ISLOSTEDFLAGTEMP + "='" + ConstantLibrary.lostedFlagValue + "'";
        try{
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            rs.last();
            lostedData = new String[rs.getRow()];
            rs.beforeFirst();
            int row = 0;
            while(rs.next()){
                lostedData[row++] = rs.getString(1);
            }
            dc.close();
        }catch(Exception e){
            
        }
        return lostedData;
    }
    /*
    *获取指定列的未流失的数据
    */
    public static String[] getUnLostedDataColumnName(String columnName){
        String[] unlostedData = null;
        Connection conn = dc.getConnection();
        PreparedStatement pstmt = null;
        String sql = "select " + columnName + " from " + ConstantLibrary.MERGETABLENAME + " where " + ConstantLibrary.ISLOSTEDFLAGTEMP + "='" + ConstantLibrary.unlostedFlagValue + "'";
        try{
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            rs.last();
            unlostedData = new String[rs.getRow()];
            rs.beforeFirst();
            int row = 0;
            while(rs.next()){
                unlostedData[row++] = rs.getString(1);
            }
            dc.close();
        }catch(Exception e){
            
        }
        return unlostedData;
    }
    
    /*
    *获得Temp列名数组
    */
    public static String[] getColumnNamesTempArray(String tableName){
        String[] str = null;
        Connection conn = dc.getConnection();
        PreparedStatement stmt = null;
        String sql = "select * from " + tableName;
        try {
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            str = new String[rsmd.getColumnCount()];
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                str[i-1] = rsmd.getColumnName(i);
                if(str[i-1].equals(ConstantLibrary.ISLOSTEDFLAGTEMP)){
                    ConstantLibrary.ISLOSTEDFLAGINDEX = i-1;
                }
            }
            dc.close();
        } catch (Exception e) {

        }
        return str;
    }
    public static Object[][] getRuleDataArray(String tableName){
        Object[][] data = null;
        Connection conn = dc.getConnection();
        PreparedStatement pstmt = null;
        String sql = "select * from " + tableName + " order by Confidence desc";
        try{
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            rs.last();
            data = new Object[rs.getRow()][rsmd.getColumnCount()+1];
            rs.beforeFirst();
            int row = 0;
            while(rs.next()){
                data[row][0] = new Boolean(false);
                for(int i=1;i<=rsmd.getColumnCount();++i){
                    data[row][i] = rs.getString(i);
                }
                row++;
            }
            dc.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return data;
    }
}
