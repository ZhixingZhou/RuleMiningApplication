/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seu.zzx.rule;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.text.DecimalFormat;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import seu.zzx.constant.ConstantLibrary;
import seu.zzx.util.OperateFile;

/**
 *
 * @author zzx_seu
 */
public class ExportRuleToFile {
    public static void exportRuleToExcel(List<StrongAssociationRule> rule){
        DecimalFormat dfmSupport = new DecimalFormat("#.#####");
        DecimalFormat dfmConfidence = new DecimalFormat("#.##");
        OutputStream lostedOut = null;
        OutputStream unlostedOut = null;
        Workbook lostedBook = new XSSFWorkbook();
        Workbook unlostedBook = new XSSFWorkbook();
        Sheet lostedSheet = lostedBook.createSheet("已流失关联规则");
        Sheet unlostedSheet = unlostedBook.createSheet("未流失关联规则");
        Row lostedRow = null;
        Row unlostedRow = null;
        int lostedRowCount = 0,unlostedRowCount = 0,lostedColumnCount = 0,unlostedColumnCount = 0;
        int tempInt = 0;
        String[] tempString = null;
        String tempString1 = "";
        StringBuffer tempString2 = new StringBuffer();
        try{
            lostedOut = new FileOutputStream(ConstantLibrary.associationFilePath02);
            unlostedOut = new FileOutputStream(ConstantLibrary.associationFilePath03);
            for(StrongAssociationRule sar : rule){
                tempString2 = new StringBuffer();
                if(sar.result.equals(ConstantLibrary.lostedFlagValue+"#"+ConstantLibrary.ISLOSTEDFLAGINDEX)){
                    lostedRow = lostedSheet.createRow(lostedRowCount++);
                    lostedColumnCount = 0;
                    for(String item : sar.condition){
                        tempString = item.split("#");
                        try{
                            if(tempString.length==1){
                                tempInt = Integer.parseInt(tempString[0]);
                                tempString1 = ConstantLibrary.columnNames[tempInt];
                                tempString2.append(tempString1).append("=").append(",");
                            }else{
                                tempInt = Integer.parseInt(tempString[1]);
                                tempString1 = ConstantLibrary.columnNames[tempInt];
                                tempString2.append(tempString1).append("=").append(tempString[0]).append(",");
                            }
                        }catch(Exception e1){
                            e1.printStackTrace();
                        }
                    }
                    lostedRow.createCell(lostedColumnCount++).setCellValue(tempString2.toString());
                    lostedRow.createCell(lostedColumnCount++).setCellValue("是");
                    lostedRow.createCell(lostedColumnCount++).setCellValue(dfmSupport.format(sar.support*1.0/ConstantLibrary.record));
                    lostedRow.createCell(lostedColumnCount++).setCellValue(dfmConfidence.format(sar.confidence));
                }else{
                    unlostedRow = unlostedSheet.createRow(unlostedRowCount++);
                    unlostedColumnCount = 0;
                    for(String item : sar.condition){
                        tempString = item.split("#");
                        try{
                            if(tempString.length==1){
                                tempInt = Integer.parseInt(tempString[0]);
                                tempString1 = ConstantLibrary.columnNames[tempInt];
                                tempString2.append(tempString1).append("=").append(",");
                            }else{
                                tempInt = Integer.parseInt(tempString[1]);
                                tempString1 = ConstantLibrary.columnNames[tempInt];
                                tempString2.append(tempString1).append("=").append(tempString[0]).append(",");
                            }
                        }catch(Exception e1){
                            e1.printStackTrace();
                        }
                    }
                    unlostedRow.createCell(unlostedColumnCount++).setCellValue(tempString2.toString());
                    unlostedRow.createCell(unlostedColumnCount++).setCellValue("否");
                    unlostedRow.createCell(unlostedColumnCount++).setCellValue(dfmSupport.format(sar.support*1.0/ConstantLibrary.record));
                    unlostedRow.createCell(unlostedColumnCount++).setCellValue(dfmConfidence.format(sar.confidence));
                }
            }
            lostedBook.write(lostedOut);
            unlostedBook.write(unlostedOut);
            lostedOut.close();
            unlostedOut.close();
        }catch(Exception e){
            
        }
    }
    public static void exportRuleToText(String fileName1, String fileName2) {
        OperateFile.openExcel(fileName1);
        int rowCount = OperateFile.getRowCount(0);
        int colCount = OperateFile.getColCount(0);
        Writer out = null;
        try {
            out = new FileWriter(fileName2);
            for (int i = 0; i < rowCount + 1; ++i) {
                out.write(OperateFile.getCellContent(0, i));
                out.write("Support="+OperateFile.getCellContent(2, i)+",");
                out.write("Confidence="+OperateFile.getCellContent(3, i));
                out.write("\r\n");
            }
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
