/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seu.zzx.rule;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import seu.zzx.util.OperateFile;

/**
 *
 * @author zzx_seu
 */
public class ExportRuleToText {

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
