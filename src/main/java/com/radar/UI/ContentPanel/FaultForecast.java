package com.radar.UI.ContentPanel;

import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PieLabelLinkStyle;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.title.Title;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JSeparator;

@SuppressWarnings("serial")
public class FaultForecast extends ContentPanel {
	private JFreeChart jFreeChart;
	private JPanel faultPanel;
	private JSeparator separator1;
	private JLabel label1;
	private JTable table;
	private JScrollPane JSP;
	private JSeparator separator2;
	private JLabel label2;
	private JTextPane textPane;
	
	/**
	 * 故障预测
	 */
	public FaultForecast(String name) {
		initUI(name);
	}
	public void initUI(String name) {
		jFreeChart = createPieChart(createDataset(),name);
		faultPanel = new ChartPanel(jFreeChart);
		faultPanel.setBounds(75, 10, 460, 254);
		panel.add(faultPanel);
		
		separator1 = new JSeparator();
		separator1.setBounds(10, 272, 590, 2);
		panel.add(separator1);
		
		label1 = new JLabel(name+"未来一周故障预测详情");
		label1.setFont(new Font("宋体", Font.BOLD, 15));
		label1.setBounds(187, 274, 266, 31);
		panel.add(label1);

		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"1","预燃故障",name, "预燃电路老化", "30.4%", "3"},
				{"2","超温故障",name, "冷却元件损坏", "61.5%", "7"},
			},
			new String[] {
				"编号", "故障名称", "故障部位/板卡", "故障原因", "故障概率", "故障级别"
			}
		));
		table.setFont(new Font("宋体", Font.PLAIN, 14));		
		JSP= new JScrollPane(table);
		JSP.setBounds(10, 304, 590, 91);
		panel.add(JSP);
		
		separator2 = new JSeparator();
		separator2.setBounds(10, 405, 590, 2);
		panel.add(separator2);
		
		label2 = new JLabel("维修建议");
		label2.setFont(new Font("宋体", Font.BOLD, 15));
		label2.setBounds(271, 405, 72, 31);
		panel.add(label2);
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setText("下一周内"+name+"易发生预燃故障，超温故障，请及时通知维护人员进行更换维修！");
		textPane.setFont(new Font("宋体", Font.PLAIN, 14));
		textPane.setBounds(10, 436, 590, 54);
		panel.add(textPane);

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static PieDataset createDataset() {
	    DefaultPieDataset defaultPieDataset = new DefaultPieDataset();
	    defaultPieDataset.setValue("正常状态", 1.5);
	    defaultPieDataset.setValue("接收故障", 1.1);
	    defaultPieDataset.setValue("预燃故障", 30.4);
	    defaultPieDataset.setValue("超温故障", 61.5);
	    defaultPieDataset.setValue("无输出故障", 1.7);
	    defaultPieDataset.setValue("无主波故障", 2.3);
	    defaultPieDataset.setValue("无回主波故障", 1.5);
	    return (PieDataset)defaultPieDataset;
	  }
	  
	  private static JFreeChart createPieChart(PieDataset paramPieDataset,String name) {
	    JFreeChart jFreeChart = ChartFactory.createPieChart(null, paramPieDataset, true, true, false);
	    jFreeChart.addSubtitle((Title)new TextTitle(name+"未来一周状态概率图", new Font("黑体", Font.BOLD, 15)));
	    jFreeChart.getLegend().setItemFont(new Font("宋体",Font.PLAIN,14));
	    PiePlot piePlot = (PiePlot)jFreeChart.getPlot();				  
	    piePlot.setBackgroundAlpha(0.0f);
	    piePlot.setCircular(false);
	    piePlot.setLabelGap(0.02D);
	    piePlot.setLabelLinkStyle(PieLabelLinkStyle.CUBIC_CURVE);
	    piePlot.setLabelFont(new Font("宋体",Font.PLAIN,10));
	    return jFreeChart;
	  }
}
