package com.radar.UI.ContentPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.radar.UI.Components.BottomButtonForTable;
import com.radar.UI.Components.CalendarPanel;
import com.radar.UI.Components.FaultRecordTable;
import com.radar.UI.Components.JComoBoxForFaultType;

@SuppressWarnings("serial")
public class FaultRecord extends ContentPanel {
	//雷达编号
	private JLabel RNumber;
	private JTextField RadarNumber;
	//故障类型
	private JLabel FaultType;
	private JComoBoxForFaultType faultTypes;
	//故障发生时刻
	private JLabel FaultDate;
	private JTextField FaultDateText;
	//搜搜按钮
	private JButton LookUpFault;
	//定义表格
	private FaultRecordTable faultRecordTable;
	private JLabel tHead1;
	private JLabel tHead2;
	private JLabel tHead3;
	private JLabel tHead4;
	private JLabel tHead5;
	private JLabel tHead6;
	//底部按钮
	private BottomButtonForTable firstPage;
	private BottomButtonForTable previousPage;
	private BottomButtonForTable nextPage;
	private BottomButtonForTable lastPage;
	/**
	 * 故障记录
	 */
	public FaultRecord() {
		initUI();
		Action();

	}


	private void Action() {
		// TODO Auto-generated method stub
		LookUpFault.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String	radarNumber =	RadarNumber.getText();
				String	faultDateText	= FaultDateText.getText();
				try{				
					faultRecordTable.getFaultRecordByRadarAndTime(radarNumber,faultTypes.getSelectedItem().toString(),faultDateText);
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
			faultRecordTable.getFirstPage();
			//返回当前页
			DefaultTableModel model = new DefaultTableModel(faultRecordTable.getPageData(),
					faultRecordTable.columnNames);
			faultRecordTable.setModel(model);
			faultRecordTable.setTablePreferredWidthAndPreferredHeight(faultRecordTable);
		}
	});
		//底部按钮事件
		//上一页
	previousPage.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			//页数-1
			faultRecordTable.getPreviousPage();
			//返回当前页
			DefaultTableModel model = new DefaultTableModel(faultRecordTable.getPageData(),
					faultRecordTable.columnNames);
			faultRecordTable.setModel(model);
			faultRecordTable.setTablePreferredWidthAndPreferredHeight(faultRecordTable);
		}
	});
	//底部按钮事件
		//下一页
	nextPage.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			//页数+1
			faultRecordTable.getNextPage();
			//返回当前页
			DefaultTableModel model = new DefaultTableModel(faultRecordTable.getPageData(),
					faultRecordTable.columnNames);
			faultRecordTable.setModel(model);
			faultRecordTable.setTablePreferredWidthAndPreferredHeight(faultRecordTable);
		}
	});
	//底部按钮事件
		//末页
	lastPage.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			faultRecordTable.getLastPage();
			//返回当前页
			DefaultTableModel model = new DefaultTableModel(faultRecordTable.getPageData(),
					faultRecordTable.columnNames);
			faultRecordTable.setModel(model);
			faultRecordTable.setTablePreferredWidthAndPreferredHeight(faultRecordTable);
		}
	});

	}


	private void initUI() {
		// TODO Auto-generated method stub
		//雷达编号
		setRadarNumber();
		panel.add(RNumber);
		panel.add(RadarNumber);
		//故障类型
		setFaultType();
		panel.add(FaultType);
		panel.add(faultTypes);
		//故障发生时刻
		setFaultTime();
		CalendarPanel q = new CalendarPanel(FaultDateText, "yyyy-MM-dd");
		q.initCalendarPanel();
		panel.add(FaultDate);
		panel.add(FaultDateText);
		panel.add(q);
		//搜索按钮
		setLookUpFaultType();
		panel.add(LookUpFault);
		//表格内容
		setFaultRecordTable();
		panel.add(faultRecordTable);
		//表头
		setFaultRecordTableHead();
		panel.add(tHead1);
		panel.add(tHead2);
		panel.add(tHead3);
		panel.add(tHead4);
		panel.add(tHead5);
		panel.add(tHead6);

		//底部操作按钮
		setBottomButtonsForTable();
		panel.add(firstPage);
		panel.add(previousPage);
		panel.add(nextPage);		
		panel.add(lastPage);

	}


	//底部按钮初始化
		private void setBottomButtonsForTable() {
			firstPage = new BottomButtonForTable("首  页",190, 458,50,24,12);
			previousPage = new BottomButtonForTable("上一页",250, 458,50,24,12);
			nextPage = new BottomButtonForTable("下一页",310, 458,50,24,12);
			lastPage = new BottomButtonForTable("尾  页",370, 458,50,24,12);
		}


	private void setFaultRecordTableHead() {
		// TODO Auto-generated method stub
		tHead1 = new JLabel("序号");
		setTableHead(tHead1,20, 60, 60);
		
		tHead2 = new JLabel("雷达型号");
		setTableHead(tHead2,80, 60, 100);
		
		tHead3 = new JLabel("故障类型");
		setTableHead(tHead3,180, 60, 120);
		
		tHead4 = new JLabel("发生时刻");
		setTableHead(tHead4,300, 60, 120);
		
		tHead5 = new JLabel("故障部位");
		setTableHead(tHead5,420, 60, 100);
		
		tHead6 = new JLabel("原因");
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
	


	private void setFaultRecordTable() {
		// TODO Auto-generated method stub
		faultRecordTable = new FaultRecordTable();
		faultRecordTable.setRowSelectionAllowed(true);
		faultRecordTable.setBounds(20, 110, 570, 300);
	}


	private void setLookUpFaultType() {
		// TODO Auto-generated method stub
		LookUpFault = new JButton("搜索");
		LookUpFault.setForeground(Color.BLACK);
		LookUpFault.setOpaque(true);
		LookUpFault.setBackground(new Color(255, 255, 255));
		LookUpFault.setFont(new Font("宋体", Font.PLAIN, 12));
		LookUpFault.setBackground(Color.WHITE);
		LookUpFault.setBounds(530, 18, 60, 24);
	}


	private void setFaultTime() {
		// TODO Auto-generated method stub
		FaultDate = new JLabel("发生时刻：");
		FaultDate.setFont(new Font("宋体", Font.PLAIN, 12));
		FaultDate.setBounds(360, 19, 67, 22);
		
		FaultDateText = new JTextField();
		FaultDateText.setBounds(430, 19, 90, 24);
		FaultDateText.setColumns(10);
	}


	private void setFaultType() {
		// TODO Auto-generated method stub
		FaultType = new JLabel("故障类型:");
		FaultType.setFont(new Font("宋体", Font.PLAIN, 12));
		FaultType.setHorizontalAlignment(SwingConstants.CENTER);
		FaultType.setBounds(180, 19, 67, 22);
		faultTypes = new JComoBoxForFaultType(250,19);
	}


	private void setRadarNumber() {
		// TODO Auto-generated method stub
		RNumber = new JLabel("编号:");
		RNumber.setFont(new Font("宋体", Font.PLAIN, 12));
		RNumber.setHorizontalAlignment(SwingConstants.CENTER);
		RNumber.setBounds(17, 18, 50, 24);
		RadarNumber = new JTextField();
		RadarNumber.setHorizontalAlignment(SwingConstants.CENTER);
		RadarNumber.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		RadarNumber.setBounds(60, 18, 90, 24);
	}

}
