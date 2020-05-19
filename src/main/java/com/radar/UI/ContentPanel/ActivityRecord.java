package com.radar.UI.ContentPanel;

import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.radar.UI.Components.ActivityRecordTable;
import com.radar.UI.Components.BottomButtonForTable;
import com.radar.UI.Components.CalendarPanel;

import javax.swing.JButton;
import javax.swing.JLabel;

public class ActivityRecord extends ContentPanel {
	private JLabel RNumber;
	private JTextField RadarNumber;
	private JButton LookUpRadar;
	private JLabel StartTime;
	private JTextField StartTimeText;
	private JLabel EndTime;
	private JTextField EndTimeText;
	
	private ActivityRecordTable activityRecordTable;
	private JLabel tHead1;
	private JLabel tHead2;
	private JLabel tHead3;
	private JLabel tHead4;
	private JLabel tHead5;
	private JLabel tHead6;

	private BottomButtonForTable firstPage;
	private BottomButtonForTable previousPage;
	private BottomButtonForTable nextPage;
	private BottomButtonForTable lastPage;

	/**
	 * 开机记录内容面板
	 */
	//页面组件
	public ActivityRecord() {
		initUI();
		Action();
	}


	private void Action() {
		// TODO Auto-generated method stub
		LookUpRadar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String	radarNumber =	RadarNumber.getText();
				String startTimeText = StartTimeText.getText();
				String	endTimeText	= EndTimeText.getText();
				System.out.println("检索测试");
				System.out.println(radarNumber);
				try{				
					activityRecordTable.getRecordByRadarAndTime(radarNumber,startTimeText,endTimeText);
						}
				catch(Exception execption) {
					execption.printStackTrace();
				}

			}
		});

		//底部按钮事件
				//首页
			firstPage.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					activityRecordTable.getFirstPage();
					//返回当前页
					DefaultTableModel model = new DefaultTableModel(activityRecordTable.getPageData(),
							activityRecordTable.columnNames);
					activityRecordTable.setModel(model);
					activityRecordTable.setTablePreferredWidthAndPreferredHeight(activityRecordTable);
				}
			});
				//底部按钮事件
				//上一页
			previousPage.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					//页数-1
					activityRecordTable.getPreviousPage();
					//返回当前页
					DefaultTableModel model = new DefaultTableModel(activityRecordTable.getPageData(),
							activityRecordTable.columnNames);
					activityRecordTable.setModel(model);
					activityRecordTable.setTablePreferredWidthAndPreferredHeight(activityRecordTable);
				}
			});
			//底部按钮事件
				//下一页
			nextPage.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					//页数+1
					activityRecordTable.getNextPage();
					//返回当前页
					DefaultTableModel model = new DefaultTableModel(activityRecordTable.getPageData(),
							activityRecordTable.columnNames);
					activityRecordTable.setModel(model);
					activityRecordTable.setTablePreferredWidthAndPreferredHeight(activityRecordTable);
				}
			});
			//底部按钮事件
				//末页
			lastPage.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					activityRecordTable.getLastPage();
					//返回当前页
					DefaultTableModel model = new DefaultTableModel(activityRecordTable.getPageData(),
							activityRecordTable.columnNames);
					activityRecordTable.setModel(model);
					activityRecordTable.setTablePreferredWidthAndPreferredHeight(activityRecordTable);
				}
			});

	}


	//页面初始化添加组件
	private void initUI() {
		// TODO Auto-generated method stub
		
		//雷达编号文本框、x
		setRadarNumber();
		panel.add(RadarNumber);
		panel.add(RNumber);
		
		//查询按钮
		setLookUp();
		panel.add(LookUpRadar);

		//雷达开机时间标签、时间承载控件
		setStartTime();
		// 定义日历控件面板类
		CalendarPanel p = new CalendarPanel(StartTimeText, "yyyy-MM-dd");
		p.initCalendarPanel();
		panel.add(StartTime);
		panel.add(StartTimeText);
		panel.add(p);

		
		//关机时间标签、时间承载控件
		setEndTime();
		// 定义日历控件面板类
		CalendarPanel q = new CalendarPanel(EndTimeText, "yyyy-MM-dd");
		q.initCalendarPanel();
		panel.add(EndTime);
		panel.add(EndTimeText);
		panel.add(q);
		
		
		//表格表头
		setActivityRecordTableHead();
		panel.add(tHead1);
		panel.add(tHead2);
		panel.add(tHead3);
		panel.add(tHead4);
		panel.add(tHead5);
		panel.add(tHead6);

		//表格内容
		setActivityRecordTable();
		panel.add(activityRecordTable);
		
		
		//底部操作按钮
				setBottomButtonsForTable();
				panel.add(firstPage);
				panel.add(previousPage);
				panel.add(nextPage);		
				panel.add(lastPage);

	}


	private void setLookUp() {
		// TODO Auto-generated method stub
		LookUpRadar = new JButton("搜索");
		LookUpRadar.setForeground(Color.BLACK);
		LookUpRadar.setOpaque(true);
		LookUpRadar.setBackground(new Color(255, 255, 255));
		LookUpRadar.setFont(new Font("仿宋", Font.PLAIN, 12));
		LookUpRadar.setBackground(Color.WHITE);
		LookUpRadar.setBounds(530, 18, 60, 24);
	}


	//底部按钮初始化
	private void setBottomButtonsForTable() {
		firstPage = new BottomButtonForTable("首  页",190, 458,50,24,12);
		previousPage = new BottomButtonForTable("上一页",250, 458,50,24,12);
		nextPage = new BottomButtonForTable("下一页",310, 458,50,24,12);
		lastPage = new BottomButtonForTable("尾  页",370, 458,50,24,12);
	}


	private void setActivityRecordTable() {
		// TODO Auto-generated method stub
		activityRecordTable = new ActivityRecordTable();
		activityRecordTable.setRowSelectionAllowed(true);
		activityRecordTable.setBounds(20, 110, 570, 300);
	}



	private void setActivityRecordTableHead() {
		// TODO Auto-generated method stub
		tHead1 = new JLabel("序号");
		setTableHead(tHead1,20, 60, 60);
		
		tHead2 = new JLabel("雷达型号");
		setTableHead(tHead2,80, 60, 100);
		
		tHead3 = new JLabel("开机时间");
		setTableHead(tHead3,180, 60, 120);
		
		tHead4 = new JLabel("关机时间");
		setTableHead(tHead4,300, 60, 120);
		
		tHead5 = new JLabel("活动目的");
		setTableHead(tHead5,420, 60, 100);
		
		tHead6 = new JLabel("是否故障");
		setTableHead(tHead6,520, 60, 70);
		
	}


		//设置表格表头每一列样式
		private void setTableHead(JLabel label,int x,int y,int width) {
			label.setOpaque(true);
			label.setBackground(new Color(204, 204, 204));
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setFont(new Font("宋体", Font.BOLD, 14));
			label.setBounds(x, y, width, 50);
		}
	


	private void setEndTime() {
		// TODO Auto-generated method stub
		EndTime = new JLabel("关机时间：");
		EndTime.setFont(new Font("仿宋", Font.BOLD, 12));
		EndTime.setBounds(360, 19, 67, 22);
		
		EndTimeText = new JTextField();
		EndTimeText.setBounds(430, 19, 90, 24);
		EndTimeText.setColumns(10);
	}


	private void setStartTime() {
		// TODO Auto-generated method stub
		StartTime = new JLabel("开机时间:");
		StartTime.setFont(new Font("仿宋", Font.BOLD, 12));
		StartTime.setHorizontalAlignment(SwingConstants.CENTER);
		StartTime.setBounds(180, 19, 67, 22);
		
		StartTimeText = new JTextField();
		StartTimeText.setBounds(250, 19, 90, 24);
		StartTimeText.setColumns(10);
	}


	private void setRadarNumber() {
		// TODO Auto-generated method stub
		RadarNumber = new JTextField();
		RadarNumber.setHorizontalAlignment(SwingConstants.CENTER);
		RadarNumber.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		RadarNumber.setBounds(60, 18, 90, 24);
		RNumber = new JLabel("编号:");
		RNumber.setFont(new Font("仿宋", Font.BOLD, 12));
		RNumber.setHorizontalAlignment(SwingConstants.CENTER);
		RNumber.setBounds(17, 18, 50, 24);

	}
	
}
