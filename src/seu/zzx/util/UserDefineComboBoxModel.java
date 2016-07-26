/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seu.zzx.util;

import javax.swing.DefaultComboBoxModel;
import seu.zzx.constant.ConstantLibrary;

/**
 *
 * @author zzx_seu
 */
public class UserDefineComboBoxModel extends DefaultComboBoxModel {

   public UserDefineComboBoxModel(){
        this.addElement("请选择属性");
        for(int i=0;i<ConstantLibrary.columnNames.length;++i){
            this.addElement(ConstantLibrary.columnNames[i]);
        }
    }

}
