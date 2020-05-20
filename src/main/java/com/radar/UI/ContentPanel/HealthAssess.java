package com.radar.UI.ContentPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.title.Title;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.alibaba.fastjson.JSONArray;
import com.radar.SpringUtil;
import com.radar.ServiceImpl.RadarServiceImpl;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JSeparator;
//健康评估
@SuppressWarnings("serial")
public class HealthAssess extends ContentPanel {
	private JFreeChart jFreeChart;
	private JLabel level;
	private JLabel health;
	private JLabel suggestion;
	private JTextPane textPane;
	private JSeparator separator;
	private JFreeChart jFreeChart2;
	private JPanel historyPanel;

	
	
	
	public HealthAssess(String name) {
		initUI(name);
	}
	
	private void initUI(String name) {
		jFreeChart = createBarChart(createDataset());
		JPanel healthPanel = new ChartPanel(jFreeChart);
		healthPanel.setBounds(10, 10, 350, 221);
		panel.add(healthPanel);
		
		level = new JLabel("健康等级：");
		level.setFont(new Font("宋体", Font.PLAIN, 14));
		level.setBounds(392, 10, 97, 27);
		panel.add(level);
		
		health = new JLabel("亚健康");
		health.setFont(new Font("宋体", Font.PLAIN, 14));
		health.setBounds(499, 10, 66, 27);
		panel.add(health);
		
		suggestion = new JLabel("维保决策建议：");
		suggestion.setFont(new Font("宋体", Font.PLAIN, 14));
		suggestion.setBounds(392, 41, 196, 32);
		panel.add(suggestion);
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setText("光电分系统健康指数较低，应及时检修避免故障！ 雷达整体处于亚健康状态，尚未出现明显异常，按照正常维保周期维护!");
		textPane.setFont(new Font("宋体", Font.PLAIN, 14));
		textPane.setBounds(387, 72, 201, 159);
		panel.add(textPane);
		
		separator = new JSeparator();
		separator.setBounds(10, 241, 590, 2);
		panel.add(separator);
			
		jFreeChart2 = createXYChart(previousData(name)); //目前只有1雷达有数据
		historyPanel = new ChartPanel(jFreeChart2);
		historyPanel.setBounds(10, 253, 590, 226);
		panel.add(historyPanel);
	}
	
	
	
    //获取分析的结果(后续引入R程序)
	private static CategoryDataset  createDataset() {
		    DefaultCategoryDataset defaultCategoryDataset = new DefaultCategoryDataset();
		    String str = "健康指数HI";
		    defaultCategoryDataset.addValue(76.4, str, "雷达总系统");
		    defaultCategoryDataset.addValue(65.3, str, "光电分系统");
		    defaultCategoryDataset.addValue(78.6, str, "收发分系统");
		    defaultCategoryDataset.addValue(91.5, str, "火控分系统");
		    defaultCategoryDataset.addValue(86.5, str, "信号分系统");
		    defaultCategoryDataset.addValue(81.2, str, "辅助分系统");
		    return (CategoryDataset)defaultCategoryDataset;
		  }
	  //健康指数条形图
	  private static JFreeChart createBarChart(CategoryDataset paramCategoryDataset) {
	    JFreeChart jFreeChart = ChartFactory.createBarChart(null,null,null, paramCategoryDataset, PlotOrientation.HORIZONTAL, false,true, false);
	    jFreeChart.addSubtitle((Title)new TextTitle("雷达健康指数HI", new Font("黑体", Font.BOLD, 15)));
		CategoryPlot categoryPlot = (CategoryPlot)jFreeChart.getPlot(); 
		categoryPlot.getDomainAxis().setTickLabelFont(new Font("宋体",Font.BOLD,14));//设置x轴坐标上的字体
		categoryPlot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT); //坐标在下方		
		categoryPlot.getRangeAxis().setRange(50.0, 100.0); //x轴区间
		categoryPlot.setBackgroundAlpha(0.0f); //背景透明
		BarRenderer barRenderer = (BarRenderer)categoryPlot.getRenderer();
	    barRenderer.setItemLabelAnchorOffset(9.0D);    //上下间距
	    barRenderer.setBaseItemLabelsVisible(true);    //显示字 
	    barRenderer.setBaseItemLabelGenerator((CategoryItemLabelGenerator)new StandardCategoryItemLabelGenerator());   //显示字
	    barRenderer.setSeriesPaint(0, new GradientPaint(0.0F, 0.0F, Color.green, 0.0F, 0.0F, new Color(0,128, 0)));
	    return jFreeChart;	    
	  }

	  //最新5条历史健康指数
		  private static CategoryDataset previousData(String searchKey) {			  
			  RadarServiceImpl radarServiceImpl = (RadarServiceImpl) SpringUtil.getBean("RadarServiceImpl"); 
			  List<Object> HI = radarServiceImpl.getPreviousHI(searchKey);			  
			  Iterator<Object> HIobj = HI.iterator();
			  JSONArray datalist = new JSONArray();;
			  Object[] obj = null;
				while (HIobj.hasNext()) {
					obj = (Object[]) HIobj.next();
					datalist.add(obj[0]);
					datalist.add(obj[1]);
					datalist.add(obj[2]);				
				}			
			  DefaultCategoryDataset defaultCategoryDataset = new DefaultCategoryDataset();			  				  
			  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  // 设置日期格式
			  for(int i=(datalist.size()-1);i>0;i=i-3) {			//倒序加载	
				  defaultCategoryDataset.addValue(Double.valueOf(datalist.get(i).toString()), "HI",sdf.format(datalist.get(i-1)));							  
			  }
			    return (CategoryDataset)defaultCategoryDataset;
			  }
	//健康指数趋势图		  
	  private static JFreeChart createXYChart(CategoryDataset paramCategoryDataset) {
	    JFreeChart jFreeChart = ChartFactory.createLineChart(null, "时间", "雷达综合健康指数HI", paramCategoryDataset, PlotOrientation.VERTICAL,false, true, false);
	    jFreeChart.addSubtitle((Title)new TextTitle("雷达历史健康指数趋势图", new Font("黑体",Font.BOLD, 15))); 
	    CategoryPlot xYPlot =  (CategoryPlot)jFreeChart.getPlot(); 
	    ValueMarker valueMarker = new ValueMarker(60.0); 
	    valueMarker.setPaint(Color.red);
	    xYPlot.addRangeMarker(valueMarker); //设置阈值线
	    xYPlot.getRangeAxis().setRange(50.0, 100.0);  //x轴区间
   		xYPlot.getRangeAxis().setLabelFont(new Font("宋体",Font.BOLD, 14)); //x轴标签中文		   		
   		xYPlot.getDomainAxis().setLabelFont(new Font("宋体", Font.BOLD, 14));	 //y轴标签中文		   		 
   		xYPlot.setBackgroundAlpha(0.0f); //背景透明
   		LineAndShapeRenderer xylinerenderer = (LineAndShapeRenderer)xYPlot.getRenderer(); 
   		xylinerenderer.setBaseShapesVisible(true); //显示点
        CategoryItemRenderer xyitem =xYPlot.getRenderer();
   		xyitem.setSeriesPaint(0, Color.blue);
   		xyitem.setBaseItemLabelsVisible(true); 
   		xyitem.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
   		xyitem.setBaseItemLabelFont(new Font("宋体", Font.BOLD, 10)); //点对于字体大小	   					    
		return jFreeChart;
		  } 

}