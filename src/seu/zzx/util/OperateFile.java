/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seu.zzx.util;

import java.io.FileInputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



/**
 *
 * @author zzx_seu
 */
public class OperateFile {
    private static Workbook rwb = null;
    private static int colCnt = 0;
    /**
     * 打开文件
     */
    public static boolean openExcel(String fileName) {
        boolean Rtn = false;
        try {
            //Excel 2007获取方法
            rwb = new XSSFWorkbook(new FileInputStream(fileName));
            Rtn = true;
        } catch (Exception e) {
            try {
                //Excel 2003获取方法
                rwb = new HSSFWorkbook(new FileInputStream(fileName));
                Rtn = true;
            } catch (Exception e1) {
                Rtn = false;
            }
        }
        return Rtn;
    }

    public static int getRowCount(int sheetIndex) {
        try {
            //Sheet rs = rwb.getSheet(sheetIndex);
            Sheet rs = rwb.getSheetAt(sheetIndex);
            //colCnt = rs.getRows();
            colCnt = rs.getLastRowNum();
        } catch (Exception e) {
            colCnt = 0;
        } finally {
            try {
            } catch (Exception e) {
                colCnt = 0;
            }
        }
        return colCnt;
    }

    public static int getColCount(int sheetIndex) {
        try {
            //Sheet rs = rwb.getSheet(sheetIndex);
            Sheet rs = rwb.getSheetAt(sheetIndex);
            //colCnt = rs.getColumns();
            Row row = rs.getRow(0);
            colCnt = row.getLastCellNum();
        } catch (Exception e) {
            colCnt = 0;
        } finally {
            try {

            } catch (Exception e) {
                colCnt = 0;
            }
        }
        return colCnt;
    }

    public static String getCellContent(int col, int row) {
        String cellContent = "";
        try {
            Sheet rs = rwb.getSheetAt(0);
            /*Cell c00 = rs.getCell(col, row);
                    cellContent = c00.getContents();*/
            Row row1 = rs.getRow(row);
            cellContent = row1.getCell(col).toString();
        } catch (Exception e) {
            cellContent = "";
        } finally {
            try {

            } catch (Exception e) {
                cellContent = "";
            }
        }
        return cellContent;
    }
    
    public static String[][] getDataContent(String fileName){
        openExcel(fileName);
        int rowCount = getRowCount(0);
        int colCount = getColCount(0);
        System.out.println(colCount);
        String[][] data = new String[rowCount+1][colCount];
        for(int i=0;i<rowCount+1;++i){
            for(int j=0;j<colCount;++j){
                data[i][j] = getCellContent(j, i);
            }
        }
        return data;
    }
}
