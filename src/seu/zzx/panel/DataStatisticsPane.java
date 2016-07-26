/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seu.zzx.panel;

import java.awt.Component;
import java.awt.Font;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.RectangleEdge;
import seu.zzx.constant.ConstantLibrary;
import seu.zzx.dbc.EditTable;

/**
 *
 * @author zzx_seu
 */
public class DataStatisticsPane {
    private static String titleName="";
    private static int columnIndex = 0;
    public static Component CreatePane(String title){
        titleName = title;
        String columnName = ConstantLibrary.columnMap2.get(title);
        CategoryDataset dataset = getBarData(columnName);        
        JFreeChart chart = createJFreeChart(dataset);
        return new ChartPanel(chart);
    }
    private static JFreeChart createJFreeChart(CategoryDataset dataset) {
        //这里的"name"参数；是什么意思我也不知道，反正这样可以用
        StandardChartTheme standardChartTheme = new StandardChartTheme("name");
        //可以改变轴向的字体
        standardChartTheme.setLargeFont(new Font("楷体",Font.BOLD, 12));
        //可以改变图例的字体
        standardChartTheme.setRegularFont(new Font("宋体",Font.BOLD, 8));
        //可以改变图标的标题字体
        standardChartTheme.setExtraLargeFont(new Font("隶书",Font.BOLD, 20));
        ChartFactory.setChartTheme(standardChartTheme);//设置主题
        // 图表标题
        JFreeChart chart = ChartFactory.createStackedBarChart(titleName+"统计图",
                "使用变化", // 目录轴的显示标签
                "用户数量", // 数值轴的显示标签
                dataset, // 数据集
                PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                true, // 是否显示图例(对于简单的柱状图必须是false)
                false, // 是否生成工具
                false // 是否生成URL链接
                );
        chart.getLegend().setPosition(RectangleEdge.RIGHT); // 图例居右
        return chart;
    }
    public static CategoryDataset getBarData(String columnName) {
        double[] lostedStatistics=new double[21];
        double[] unlostedStatistics=new double[21];
        double[][] data = new double[][] {lostedStatistics,unlostedStatistics};
        String[] columnKeys = {"-100 ","-90 ","-80 ","-70 ","-60 ","-50 ","-40 ","-30 ","-20 ","-10 ","0 ","10 ","20 ","30 ","40 ","50","60 ","70 ","80 ","90 ","100"};
        String[] lostedData = EditTable.getLostedDataColumnName(columnName);
        String[] unlostedData = EditTable.getUnLostedDataColumnName(columnName);
        double temp = 0;
        for(int i=0;i<lostedData.length;++i){
            try{
                temp = Double.parseDouble(lostedData[i]);
            }catch(Exception e){
                continue;
            }
            if(temp<=-100){
                lostedStatistics[0]++;
            }else if(temp>-100&&temp<=-90){
                lostedStatistics[1]++;
            }else if(temp>-90&&temp<=-80){
                lostedStatistics[2]++;
            }else if(temp>-80&&temp<=-70){
                lostedStatistics[3]++;
            }else if(temp>-70&&temp<=-60){
                lostedStatistics[4]++;
            }else if(temp>-60&&temp<=-50){
                lostedStatistics[5]++;
            }else if(temp>-50&&temp<=-40){
                lostedStatistics[6]++;
            }else if(temp>-40&&temp<=-30){
                lostedStatistics[7]++;
            }else if(temp>-30&&temp<=-20){
                lostedStatistics[8]++;
            }else if(temp>-20&&temp<=-10){
                lostedStatistics[9]++;
            }else if(temp>-10&&temp<=0){
                lostedStatistics[10]++;
            }else if(temp>0&&temp<=10){
                lostedStatistics[11]++;
            }else if(temp>10&&temp<=20){
                lostedStatistics[12]++;
            }else if(temp>20&&temp<=30){
                lostedStatistics[13]++;
            }else if(temp>30&&temp<=40){
                lostedStatistics[14]++;
            }else if(temp>40&&temp<=50){
                lostedStatistics[15]++;
            }else if(temp>50&&temp<=60){
                lostedStatistics[16]++;
            }else if(temp>60&&temp<=70){
                lostedStatistics[17]++;
            }else if(temp>70&&temp<=80){
                lostedStatistics[18]++;
            }else if(temp>80&&temp<=90){
                lostedStatistics[19]++;
            }else if(temp>90){
                lostedStatistics[20]++;
            }
        }
        for(int i=0;i<unlostedData.length;++i){
            try{
                temp = Double.parseDouble(unlostedData[i]);
            }catch(Exception e){
                continue;
            }
            if(temp<=-100){
                unlostedStatistics[0]++;
            }else if(temp>-100&&temp<=-90){
                unlostedStatistics[1]++;
            }else if(temp>-90&&temp<=-80){
                unlostedStatistics[2]++;
            }else if(temp>-80&&temp<=-70){
                unlostedStatistics[3]++;
            }else if(temp>-70&&temp<=-60){
                unlostedStatistics[4]++;
            }else if(temp>-60&&temp<=-50){
                unlostedStatistics[5]++;
            }else if(temp>-50&&temp<=-40){
                unlostedStatistics[6]++;
            }else if(temp>-40&&temp<=-30){
                unlostedStatistics[7]++;
            }else if(temp>-30&&temp<=-20){
                unlostedStatistics[8]++;
            }else if(temp>-20&&temp<=-10){
                unlostedStatistics[9]++;
            }else if(temp>-10&&temp<=0){
                unlostedStatistics[10]++;
            }else if(temp>0&&temp<=10){
                unlostedStatistics[11]++;
            }else if(temp>10&&temp<=20){
                unlostedStatistics[12]++;
            }else if(temp>20&&temp<=30){
                unlostedStatistics[13]++;
            }else if(temp>30&&temp<=40){
                unlostedStatistics[14]++;
            }else if(temp>40&&temp<=50){
                unlostedStatistics[15]++;
            }else if(temp>50&&temp<=60){
                unlostedStatistics[16]++;
            }else if(temp>60&&temp<=70){
                unlostedStatistics[17]++;
            }else if(temp>70&&temp<=80){
                unlostedStatistics[18]++;
            }else if(temp>80&&temp<=90){
                unlostedStatistics[19]++;
            }else if(temp>90){
                unlostedStatistics[20]++;
            }
        }
        /*
        for(int i=0;i<20;i++){
            columnKeys[i]="["+(-100+i*10)+","+(-100+(i+1)*10)+")";
        }
*/
        String[] rowKeys = { ConstantLibrary.lostedFlagValue, ConstantLibrary.unlostedFlagValue };   
        System.out.println("asdfasdf");
        return DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data);
    }
}
