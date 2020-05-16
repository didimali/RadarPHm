package com.radar.UI.Components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.RenderingHints;
import java.util.concurrent.ExecutionException;

import javax.swing.JPanel;
import javax.swing.SwingWorker;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

public class BarChartForDynamicData extends JPanel {
	
	private static final long serialVersionUID = 1L;

	/**
	  * 监测数据柱状图
	 * @author madi
	 */
	private String title;
	
	private ChartPanel chartPanel;
	private DefaultCategoryDataset dataSet;
	private JFreeChart barChart;
	
	public BarChartForDynamicData(String title) {
		this.title = title;
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
		
		//柱状图初始化
		createBarChart();
		add(chartPanel);
		
	}

	private void Action() {
		
	}
	
	private void createBarChart() {
		
		//数据集初始化
//		dataSet = initDataset();
		//添加数据 
		dataSet = new DefaultCategoryDataset();
		dataSet.addValue(440, "数据1", "类型1"); 
		dataSet.addValue(360, "数据1", "类型2"); 
		dataSet.addValue(510, "数据2", "类型3"); 
		dataSet.addValue(390, "数据3", "类型4"); 
		
		//创建JFreeChart对象
		
		barChart = ChartFactory.createBarChart("类型统计图", "类型","数据额", dataSet, PlotOrientation.HORIZONTAL, true, false, false);
		
		barChart.setBorderVisible(false);
		
		LegendTitle legend = barChart.getLegend(0);
        legend.setFrame(new BlockBorder(Color.white));
        legend.setItemFont(new Font("宋体",Font.PLAIN,12));//修改图例的字体
		
		//修改高级属性
		CategoryPlot plot = barChart.getCategoryPlot();
		
		plot.setNoDataMessage("请选择一台雷达！"); // 没有数据的时候显示的内容 
		
		// 饼图的透明度
		plot.setForegroundAlpha(0.5f);
		// 饼图的背景全透明
		plot.setBackgroundAlpha(0.0f);
		
		//设置标题文字
		barChart.setTitle(new TextTitle(title,new Font("宋体", Font.BOLD, 24)));	
		//设置X轴坐标上的文字 
		plot.getDomainAxis().setTickLabelFont(new Font("宋体", Font.PLAIN, 12)); 
		//设置X轴的标题文字 
		plot.getDomainAxis().setLabelFont(new Font("宋体", Font.PLAIN, 14));
		//设置Y轴坐标上的文字 
		plot.getRangeAxis().setTickLabelFont(new Font("宋体", Font.PLAIN, 12)); 
		//设置Y轴的标题文字 
		plot.getRangeAxis().setLabelFont(new Font("黑体", Font.PLAIN, 14)); 
		
		/** 
		* renderer设置 柱子相关属性设置 
		*/
		BarRenderer renderer = new BarRenderer();//3D属性修改 
//		renderer.setBaseOutlinePaint(Color.ORANGE); //边框颜色 
		renderer.setDrawBarOutline(true); 
		renderer.setMaximumBarWidth(0.04); //设置柱子宽度 
		renderer.setMinimumBarLength(0.1); //设置柱子高度 
		renderer.setSeriesPaint(0,Color.BLUE); //设置柱的颜色 
		renderer.setItemMargin(-0.01); //设置每个地区所包含的平行柱的之间距离 
		renderer.setShadowVisible(false);//取消阴影效果
		//在柱子上显示相应信息 
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator()); 
		renderer.setBaseItemLabelsVisible(true); 
		renderer.setBaseItemLabelPaint(Color.BLACK);//设置数值颜色，默认黑色 
		//搭配ItemLabelAnchor TextAnchor 这两项能达到不同的效果，但是ItemLabelAnchor最好选OUTSIDE，因为INSIDE显示不出来 
//		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.CENTER_LEFT)); 
		//下面可以进一步调整数值的位置，但是得根据ItemLabelAnchor选择情况. 
		renderer.setItemLabelAnchorOffset(10); 
		plot.setRenderer(renderer);//将修改后的属性值保存到图中 

		
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
		
		chartPanel = new ChartPanel(barChart);
		chartPanel.setSize(590, 500);
	}

	private DefaultCategoryDataset initDataset() {
		new SwingWorkerForBarChart().execute();
		return null;
	}
	
	class SwingWorkerForBarChart extends SwingWorker<DefaultCategoryDataset,Void>{

		@Override
		protected DefaultCategoryDataset doInBackground() throws Exception {
			return null;
		}
		
		@Override
		protected void done() {
			try {
				dataSet = get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

	

}
