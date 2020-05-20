package com.radar.UI.Components;

import java.util.concurrent.ExecutionException;

import javax.swing.JPanel;
import javax.swing.SwingWorker;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import java.awt.Color;
import java.awt.Font;

@SuppressWarnings("serial")
public class LineChartForDynamicData extends JPanel {

	/**
	 * 监测数据折线图（雷达某一监测参数历史数据图）
	 */
	private DefaultCategoryDataset data;
	private ChartPanel chart;
	public LineChartForDynamicData() {
		initUI();
		Action();
	}

	private void initUI() {
		
		//容器初始化
		setBackground(Color.WHITE);
		setLayout(null);
		setBorder(null);
		setOpaque(true);
		setSize(610,500);
		
		createLineChart();
		setLayout(null);
		add(chart);
		
	}

	private void Action() {
		
	}
	
	private void createLineChart() {
		//创建数据集
//		data = getData();
		data = new DefaultCategoryDataset();
		data.addValue( 15 , "schools" , "1970" );
		data.addValue( 30 , "schools" , "1980" );
		data.addValue( 60 , "schools" , "1990" );
		data.addValue( 120 , "schools" , "2000" );
		data.addValue( 240 , "schools" , "2010" ); 
		data.addValue( 300 , "schools" , "2014" );

		//创建JFreechart对象
		JFreeChart line = ChartFactory.createLineChart("测试折线图","Years", "Values", data,
				PlotOrientation.VERTICAL,true,true,false);
		//修改JFreeChart对象高级属性
		line.setBorderVisible(false);
		
		LegendTitle legend = line.getLegend(0);
        legend.setFrame(new BlockBorder(Color.white));
        legend.setItemFont(new Font("宋体",Font.PLAIN,12));//修改图例的字体
		
		//修改高级属性
		CategoryPlot plot = line.getCategoryPlot();
		
		plot.setNoDataMessage("请选择雷达的某一监控参数！"); // 没有数据的时候显示的内容 
		
		// 透明度
		plot.setForegroundAlpha(0.5f);
		// 背景全透明
		plot.setBackgroundAlpha(0.0f);
		
		//设置标题文字
		line.setTitle(new TextTitle("测试折线图",new Font("宋体", Font.BOLD, 24)));	
		//设置X轴坐标上的文字 
		plot.getDomainAxis().setTickLabelFont(new Font("宋体", Font.PLAIN, 12)); 
		//设置X轴的标题文字 
		plot.getDomainAxis().setLabelFont(new Font("宋体", Font.PLAIN, 14));
		//设置Y轴坐标上的文字 
		plot.getRangeAxis().setTickLabelFont(new Font("宋体", Font.PLAIN, 12)); 
		//设置Y轴的标题文字 
		plot.getRangeAxis().setLabelFont(new Font("黑体", Font.PLAIN, 14)); 
		
				
		//设置网格竖线 
		plot.setDomainGridlinesVisible(false); 
		//设置网格横线颜色 
		plot.setRangeGridlinesVisible(false); 
		//图片背景色 
		plot.setBackgroundPaint(Color.WHITE); 
		plot.setOutlineVisible(false); 
		//图边框颜色 
		plot.setOutlinePaint(Color.WHITE); 
		//设置柱的透明度 
		plot.setForegroundAlpha(1.0f); 
		
		//创建呈现媒介
		//创建呈现媒介
		chart = new ChartPanel(line);
		chart.setBounds(0, 0, 590, 500);
	}

	@SuppressWarnings("unused")
	private DefaultCategoryDataset getData() {
		new SwingWorkerForLineChartForDynamicData().execute();
		return null;
	}
	
	class SwingWorkerForLineChartForDynamicData extends SwingWorker<DefaultCategoryDataset,Void>{

		@Override
		protected DefaultCategoryDataset doInBackground() throws Exception {
			return null;
		}
		
		@Override
		protected void done() {
			try {
				data = get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		
	}

}
