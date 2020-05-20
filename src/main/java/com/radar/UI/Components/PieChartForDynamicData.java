package com.radar.UI.Components;

import javax.swing.SwingWorker;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import com.radar.SpringUtil;
import com.radar.ServiceImpl.DynamicDataServiceImpl;
import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.concurrent.ExecutionException;
import javax.swing.JPanel;

public class PieChartForDynamicData extends JPanel {

	/**
	 * 监测数据饼图
	 * @author madi
	 */
	private static final long serialVersionUID = 1L;
	/**
	  *  饼图
	 * @author madi
	 */
	private ChartPanel chartPanel;
	private JFreeChart pieChart;
	private DefaultPieDataset data;
	public PieChartForDynamicData() {
		
		initUI();
		Action();

	}

	private void initUI() {	
		setBackground(Color.WHITE);
		setLayout(null);
		setBorder(null);
		setOpaque(true);
		setSize(610,500);
		
		setPieChart();
		setChartPanel();
		
		add(chartPanel);		
	}

	private void Action() {
		
	}

	private void setPieChart() {
		String title = "各部队雷达数量统计（台）";
		//创建数据集对象
		data = getPieChartData();
		
		//创建JFreeChart对象
		pieChart = ChartFactory.createPieChart(title, data, true, true, true);
		pieChart.setBorderVisible(false);
		pieChart.setBackgroundPaint(null);
		pieChart.setBackgroundImageAlpha(0.0f);
		
		pieChart.setTitle(new TextTitle(title,new Font("宋体", Font.BOLD, 24)));		

        LegendTitle legend = pieChart.getLegend(0);
        legend.setFrame(new BlockBorder(Color.white));
        legend.setItemFont(new Font("宋体",Font.PLAIN,12));//修改图例的字体
        PiePlot p = (PiePlot) pieChart.getPlot();
        
        p.setLabelFont(new Font("宋体", Font.PLAIN,12));     //水平底部标题
        p.setNoDataMessage("暂时无数据可供显示！"); // 没有数据的时候显示的内容 
        p.setLabelGenerator(new StandardPieSectionLabelGenerator(("{0}:{1}"), NumberFormat.getNumberInstance(),new DecimalFormat("0.00"))); 
        
        p.setOutlinePaint(Color.WHITE); // 设置绘图面板外边的填充颜色
        p.setShadowPaint(Color.WHITE); // 设置绘图面板阴影的填充颜色
		// 饼图的透明度
		p.setForegroundAlpha(0.5f);
		// 饼图的背景全透明
		p.setBackgroundAlpha(0.0f);
		
		// 设置标题颜色
		pieChart.getTitle().setPaint(ChartColor.blue);
	}
	
	private void setChartPanel() {
		chartPanel = new ChartPanel(pieChart);
		chartPanel.setDismissDelay(1000);
		chartPanel.setBorder(null);
		chartPanel.setOpaque(true);
		chartPanel.setBackground(Color.WHITE);
		chartPanel.setBounds(0, 0, 590, 500);
	}
	
	private DefaultPieDataset getPieChartData() {
		new SwingWorkerForPieChart().execute();
		return null;		
	}
	
	class SwingWorkerForPieChart extends SwingWorker<DefaultPieDataset,Void>{

		@SuppressWarnings("static-access")
		@Override
		protected DefaultPieDataset doInBackground() throws Exception {
			SpringUtil s = new SpringUtil();
			DynamicDataServiceImpl dynamicDataServiceImpl = 
					(DynamicDataServiceImpl) s.getBean("DynamicDataServiceImpl");
			DefaultPieDataset result = dynamicDataServiceImpl.getRadarCountsGroupByManager();
			return result;
		}
		
		@Override
		protected void done() {
			try {
				data = get();
				PiePlot p = (PiePlot) pieChart.getPlot();
				p.setDataset(data);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		
	}
}
