package com.radar.UI.ContentPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.radar.UI.LeftPanel;
import com.radar.UI.Components.BarChartForDynamicData;
import com.radar.UI.Components.BottomButtonForTable;
import com.radar.UI.Components.Chooser;
import com.radar.UI.Components.DynamicDataTable;
import com.radar.UI.Components.JComoBoxForManager;
import com.radar.UI.Components.JComoBoxForRadar;
import com.radar.UI.Components.LineChartForDynamicData;
import com.radar.UI.Components.PieChartForDynamicData;

import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class DynamicData extends ContentPanel {

	/**
	 * 监测数据面板
	 * @author madi
	 */
	//页面组件
	private JLabel labelForManager;
	private JLabel labelForRadar;
	private JLabel labelForCollectTime;
	private JComoBoxForManager manager;
	private JComoBoxForRadar radar;
	private DynamicDataTable dynamicDataTable;
	private JTextField startDate;
	private JTextField endDate;
	private JLabel refreshTable;
	private JLabel tHead1;
	private JLabel tHead2;
	private JLabel tHead3;
	private JLabel tHead4;
	private JLabel tHead5;
	private JSeparator separator;
	
	private PieChartForDynamicData pie;
	private BarChartForDynamicData bar;
	private LineChartForDynamicData line;
	
	//表格按钮
	private BottomButtonForTable firstPage;
	private BottomButtonForTable previousPage;
	private BottomButtonForTable nextPage;
	private BottomButtonForTable lastPage;
	
	private JPanel contentPanel;
	private JScrollPane scrollPane;
	public DynamicData() {
		initUI();
		Action();

	}

	private void initUI() {
		panel.setLayout(new BorderLayout(0, 0));
		
		setContaniner();
		panel.add(scrollPane,BorderLayout.CENTER);
		
		setManagers();
		contentPanel.add(labelForManager);
		contentPanel.add(manager);
		
		setRadars();
		contentPanel.add(labelForRadar);
		contentPanel.add(radar);
		
		setCollectDate();
		contentPanel.add(labelForCollectTime);
		contentPanel.add(startDate);
		contentPanel.add(endDate);
		
		setSeparator();
		contentPanel.add(separator);
		
		settHead();
		contentPanel.add(tHead1);
		contentPanel.add(tHead2);
		contentPanel.add(tHead3);
		contentPanel.add(tHead4);
		contentPanel.add(tHead5);
		
		setDynamicDataTable();
		contentPanel.add(dynamicDataTable);
		
		//底部操作按钮
		setBottomButtonsForTable();
		contentPanel.add(firstPage);
		contentPanel.add(previousPage);
		contentPanel.add(nextPage);		
		contentPanel.add(lastPage);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(457, 29, 11, 1);
		contentPanel.add(separator_1);
		
		setRefreshTable();
		contentPanel.add(refreshTable);
		
		setPieChart();
		contentPanel.add(pie);
		
		setBarChart();
		contentPanel.add(bar);
		
		setLineChart();
		contentPanel.add(line);
		
	}
	
	//折线图初始化
	private void setLineChart() {
		line = new LineChartForDynamicData();
		line.setBounds(0,1500,590,500);
	}

	//柱状图初始化
	private void setBarChart() {
		bar = new BarChartForDynamicData("测试柱状图");
		bar.setBounds(0,1000,590,500);
	}

	//饼图初始化
	private void setPieChart() {
		pie = new PieChartForDynamicData();
		pie.setBounds(0,500,590,500);
	}
	//容器初始化
	private void setContaniner() {
		contentPanel = new JPanel();
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setPreferredSize(new Dimension(610,2000));
		contentPanel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportBorder(null);
		scrollPane.setBorder(null);
		scrollPane.setViewportView(contentPanel);
		scrollPane.setWheelScrollingEnabled(true);
	}

	private void setRefreshTable() {
		refreshTable = new JLabel();
		refreshTable.setBounds(569, 19, 24, 24);
		refreshTable.setToolTipText("刷新表格");
		refreshTable.setIcon(LeftPanel.getIcon("refresh1",this));
	}

	private void Action() {
		//部队下拉框事件（更新雷达下拉框数据）
		manager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					radar.initData(manager.getSelectedItem().toString());
				}
				catch(Exception execption) {
					execption.printStackTrace();
				}
			}
		});
		//刷新表格事件
		refreshTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					String sDate = startDate.getText();
					String eDate = endDate.getText();
					String r = radar.getSelectedItem().toString();
					if(r != "All")
						dynamicDataTable.refreshTable(sDate, eDate,null,r);
					else
						dynamicDataTable.refreshTable(sDate, eDate,radar.getResultData(),null);
				}catch(Exception execption) {
					execption.printStackTrace();
				}
			}
		});
		//底部按钮事件
		//首页
		firstPage.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dynamicDataTable.getFirstPage();
				//返回当前页
				DefaultTableModel model = new DefaultTableModel(dynamicDataTable.getPageData(),
						dynamicDataTable.columnNames);
				dynamicDataTable.setModel(model);
				dynamicDataTable.setTablePreferredWidthAndPreferredHeight(dynamicDataTable);
			}
		});
		//底部按钮事件
			//上一页
		previousPage.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//页数-1
				dynamicDataTable.getPreviousPage();
				//返回当前页
				DefaultTableModel model = new DefaultTableModel(dynamicDataTable.getPageData(),
						dynamicDataTable.columnNames);
				dynamicDataTable.setModel(model);
				dynamicDataTable.setTablePreferredWidthAndPreferredHeight(dynamicDataTable);
			}
		});
		//底部按钮事件
			//下一页
		nextPage.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//页数+1
				dynamicDataTable.getNextPage();
				//返回当前页
				DefaultTableModel model = new DefaultTableModel(dynamicDataTable.getPageData(),
						dynamicDataTable.columnNames);
				dynamicDataTable.setModel(model);
				dynamicDataTable.setTablePreferredWidthAndPreferredHeight(dynamicDataTable);
			}
		});
		//底部按钮事件
			//末页
		lastPage.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dynamicDataTable.getLastPage();
				//返回当前页
				DefaultTableModel model = new DefaultTableModel(dynamicDataTable.getPageData(),
						dynamicDataTable.columnNames);
				dynamicDataTable.setModel(model);
				dynamicDataTable.setTablePreferredWidthAndPreferredHeight(dynamicDataTable);
			}
		});
		
	}
	//初始化部队下拉框及其标签
	private void setManagers() {
		labelForManager = new JLabel("部队：");
		labelForManager.setFont(new Font("宋体", Font.PLAIN, 14));
		labelForManager.setBounds(20,20,42,18);
		
		manager = new JComoBoxForManager(62, 20,80,20);
	}
	
    //初始化雷达下拉框及其标签
	private void setRadars() {
		labelForRadar = new JLabel("雷达：");
		labelForRadar.setFont(new Font("宋体", Font.PLAIN, 14));
		labelForRadar.setBounds(164,20,42,18);
		
		radar = new JComoBoxForRadar(206,20,80,20,12);
	}
    //初始化采集时段及其标签
	private void setCollectDate() {
		labelForCollectTime = new JLabel("采集时段：");
		labelForCollectTime.setFont(new Font("宋体", Font.PLAIN, 14));
		labelForCollectTime.setBounds(308,20,90,21);
		
		Chooser chooser1 = Chooser.getInstance();
		startDate = new JTextField(getFirstDayOfThisMonth().toString());
		startDate.setToolTipText("起始时间");
		startDate.setHorizontalAlignment(SwingConstants.CENTER);
		startDate.setBounds(372, 20, 80, 21);
	    chooser1.register(startDate);
	    
	    Chooser chooser2 = Chooser.getInstance();
		endDate = new JTextField(getMaxDayOfThisMonth().toString());
		endDate.setToolTipText("截止时间");
		endDate.setHorizontalAlignment(SwingConstants.CENTER);
		endDate.setBounds(473, 20, 80, 21);
	    chooser2.register(endDate);
		
	}

	private void setDynamicDataTable() {
		String sDate = startDate.getText();
		String eDate = endDate.getText();
		dynamicDataTable = new DynamicDataTable(sDate,eDate);
		dynamicDataTable.setRowSelectionAllowed(true);
		dynamicDataTable.setBounds(20, 110, 570, 300);
	}

	private void settHead() {
		tHead1 = new JLabel("序号");
		setTableHead(tHead1,20, 60, 60);
		
		tHead2 = new JLabel("雷达编号");
		setTableHead(tHead2,80, 60, 110);
		
		tHead3 = new JLabel("参数");
		setTableHead(tHead3,190, 60, 110);
		
		tHead4 = new JLabel("参数值");
		setTableHead(tHead4,300, 60, 90);
		
		tHead5 = new JLabel("采集时间");
		setTableHead(tHead5,390, 60, 200);
	}
	
	private void setSeparator() {
		//分割线
		separator = new JSeparator();
		separator.setBounds(10, 50, 590, 2);
	}
	//底部按钮初始化
	private void setBottomButtonsForTable() {
		firstPage = new BottomButtonForTable("首  页",190, 458,50,24,12);
		previousPage = new BottomButtonForTable("上一页",250, 458,50,24,12);
		nextPage = new BottomButtonForTable("下一页",310, 458,50,24,12);
		lastPage = new BottomButtonForTable("尾  页",370, 458,50,24,12);
	}

	
	/**
	 *设置表格每一列表头
	 * @author madi
	 * @param label 表格每一列表头
	 * @param x x坐标
	 * @param y y坐标
	 * @param width 宽度 */
	private void setTableHead(JLabel label, int x, int y, int width) {
		label.setOpaque(true);
		label.setBackground(new Color(204, 204, 204));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("宋体", Font.BOLD, 14));
		label.setBounds(x, y, width, 50);
	}
	/**
     * 获取本月第一天
     * @return
     */
	private String getFirstDayOfThisMonth() {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return myFormatter.format(cal.getTime());}

    /**
     * 获取本月最后一天
     * @return
     */
	private String getMaxDayOfThisMonth() {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1);
		//主要就是这个roll方法
		cal.roll(Calendar.DATE, -1);
		return myFormatter.format(cal.getTime());
	}
}
