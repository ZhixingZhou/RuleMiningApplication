/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seu.zzx.constant;

import java.util.HashMap;
import java.util.Map;
import seu.zzx.panel.DataSourceInfoPanel;

/**
 *
 * @author zzx_seu
 */
public class ConstantLibrary {
    
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static String URL = "jdbc:mysql://localhost:3306/dataminingdb";
    public static String USERNAME = "root";
    public static String PASSWORD = "123456";
    
    public static int record = 0;
    public static String[][] data = null;
    public static String[][] data1 = null;
    public static String[] columnNames = null;
    public static String[] columnNameTemp = null;
    public static Map<String,String> columnMap = new HashMap<String,String>();
    public static Map<String,String> columnMap2 = new HashMap<String,String>();
    
    public static String MERGETABLENAME = "mergetable";
    public static String PREDICTTABLENAME = "predicttable";
    public static String PREDICTTABLENAME1 = "predicttable1";
    public static String lostedRuleTableName = "lostedruletable";
    public static String unlostedRuleTableName = "unlostedruletable";
    public static String ISLOSTEDFLAG = "";
    public static int ISLOSTEDFLAGINDEX = 0;
    public static String ISLOSTEDFLAGTEMP = "";
    public static String lostedFlagValue = "";      //已流失的标志值
    public static String unlostedFlagValue = "";    //未流失的标志值
    public static int lostedFlagValueLength = 0;
    public static int unlostedFlagValueLength = 0;
    
    public static String[][] predictData = null;
    public static String[][] predictData1 = null;
    public static String[] predictColumnName = null;
    public static String[] predictColumnName1 = null;
    public static Map<String,String> predictColumnMap = new HashMap<String,String>();
    public static Map<String,String> predictColumnMap1 = new HashMap<String,String>();
    
    public static String associationFilePath01 = "关联规则";
    public static String associationFilePath02 = "关联规则/已流失关联规则.xlsx";
    public static String associationFilePath03 = "关联规则/未流失关联规则.xlsx";
    public static String associationFilePath04 = "关联规则/已流失关联规则.txt";
    public static String associationFilePath05 = "关联规则/未流失关联规则.txt";
    
    public static DataSourceInfoPanel dsip = null;
}
