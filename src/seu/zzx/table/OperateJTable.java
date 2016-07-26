/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seu.zzx.table;

import seu.zzx.constant.ConstantLibrary;
import seu.zzx.dbc.EditTable;

/**
 *
 * @author zzx_seu
 */
public class OperateJTable {
    public static Object[][] getTableData() {
        Object[][] data = null;
        String[] str = EditTable.getColumnNamesArray(ConstantLibrary.MERGETABLENAME);
        data = new Object[str.length][3];
        for (int i = 0; i < str.length; ++i) {
            data[i][0] = i+1;
            data[i][1] = new Boolean(false);
            data[i][2] = str[i];
        }
        return data;
    }
    public static Object[] getTableColNames() {
        Object[] colNames = new Object[3];
        colNames[0] = "编号";
        colNames[1] = "选择";
        colNames[2] = "列名";
        return colNames;
    }
    public static Object[][] getPredictTableData(){
        Object[][] data = new Object[ConstantLibrary.predictData.length][ConstantLibrary.predictColumnName.length+2];
        for(int i=0;i<data.length;++i){
            data[i][0] = new Boolean(false);
            data[i][ConstantLibrary.predictColumnName.length+1] = "";
        }
        for(int i=0;i<ConstantLibrary.predictData.length;++i){
            for(int j=0;j<ConstantLibrary.predictData[i].length;++j){
                data[i][j+1] = ConstantLibrary.predictData[i][j];
            }
        }
        return data;
    }
    public static String[] getPredictColumnNames(){
        String[] colNames = null;

        int colCount = ConstantLibrary.predictColumnName.length;
        colNames = new String[colCount + 2];
        colNames[0] = "选择";
        for (int j = 1; j <= colCount; ++j) {
            colNames[j] = ConstantLibrary.predictColumnName[j - 1];
        }
        colNames[colCount + 1] = "结果";

        return colNames;
    }
}
