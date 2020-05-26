package com.radar.UI.ContentPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.radar.UI.Components.BasicInfoTable;
import com.radar.UI.Components.BottomButtonForTable;
import com.radar.UI.Components.JComoBoxForManager;

public class BasicInfo extends ContentPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8302249038796830171L;
	/**
	 * 雷达列表内容面板
	 * @author ll
	 */
	//页面组件
	private  BasicInfoTable table;
	private JComoBoxForManager managers;
	@SuppressWarnings("rawtypes")
	private JComboBox types;
	private JSeparator separator;
	private JLabel lableForManager;
	private JLabel lableForType;
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
		
	public BasicInfo() {		
		initUI();
		Action();
	}
	//组件事件响应
	private void Action() {
				
		//底部按钮事件
			//首页
		firstPage.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				table.getFirstPage();
				//返回当前页
				DefaultTableModel model = new DefaultTableModel(table.getPageData(),
		                table.columnNames);
		        table.setModel(model);
		        table.setTablePreferredWidthAndPreferredHeight(table);
			}
		});
		//底部按钮事件
			//上一页
		previousPage.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//页数-1
				table.getPreviousPage();
				//返回当前页
				DefaultTableModel model = new DefaultTableModel(table.getPageData(),
		                table.columnNames);
		        table.setModel(model);
		        table.setTablePreferredWidthAndPreferredHeight(table);
			}
		});
		//底部按钮事件
			//下一页
		nextPage.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//页数+1
				table.getNextPage();
				//返回当前页
				DefaultTableModel model = new DefaultTableModel(table.getPageData(),
		                table.columnNames);
		        table.setModel(model);
		        table.setTablePreferredWidthAndPreferredHeight(table);
			}
		});
		//底部按钮事件
			//末页
		lastPage.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				table.getLastPage();
				//返回当前页
				DefaultTableModel model = new DefaultTableModel(table.getPageData(),
		                table.columnNames);
		        table.setModel(model);
		        table.setTablePreferredWidthAndPreferredHeight(table);
			}
		});
	}

	//页面初始化，添加组件
	private void initUI() {	
		
		//部队，部队标签，分割线
		setJComoBoxForManager();
		panel.add(lableForManager);
		panel.add(managers);
		panel.add(separator);
		
		//雷达型号标签，雷达型号
		setJComboBoxForType();
		panel.add(lableForType);
		panel.add(types);
		
		//表格标题
		setRadarTableHeads();
		panel.add(tHead1);	
		panel.add(tHead2);
		panel.add(tHead3);
		panel.add(tHead4);
		panel.add(tHead5);
		panel.add(tHead6);
		
		//表格内容
		setBasicInfoTable();
		panel.add(table);
		
		//底部操作按钮
		setBottomButtonsForTable();
		panel.add(firstPage);
		panel.add(previousPage);
		panel.add(nextPage);		
		panel.add(lastPage);
	}
	
	//表头初始化
	private void setRadarTableHeads() {
		
		tHead1 = new JLabel("参数ID");
		setTableHead(tHead1,20, 60, 60);
		
		tHead2 = new JLabel("最大参");
		setTableHead(tHead2,60, 60, 110);
		
		tHead3 = new JLabel("最小参");
		setTableHead(tHead3,160, 60, 110);
		
		tHead4 = new JLabel("参数名称");
		setTableHead(tHead4,250, 60, 130);
		
		tHead5 = new JLabel("参数比率");
		setTableHead(tHead5,380, 60, 150);
		
		tHead6 = new JLabel("操作");
		setTableHead(tHead6,530, 60, 60);
		
		
	}
	//表格初始化
	private void setBasicInfoTable(){
		table = new BasicInfoTable();
		table.setRowSelectionAllowed(true);
		table.setBounds(20, 110, 570, 300);
		
	}
	//部队标签、下拉框、分割线初始化
	private void setJComoBoxForManager() {
		//部队标签
		lableForManager = new JLabel("部队:");
		lableForManager.setFont(new Font("宋体", Font.PLAIN, 14));
		lableForManager.setBounds(40, 20, 48, 21);
		//部队下拉框
		managers = new JComoBoxForManager(88, 20);
		//分割线
		separator = new JSeparator();
		separator.setBounds(10, 50, 590, 2);		
	}
	//部队型号标签、下拉框初始化
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setJComboBoxForType() {
		//型号标签
		lableForType = new JLabel("型号:");
		lableForType.setFont(new Font("宋体", Font.PLAIN, 14));
		lableForType.setBounds(272, 20, 48, 21);		
		//型号下拉框
		types = new JComboBox();
		DefaultComboBoxModel typesMode;		
		String[] typesData = {"All","I型雷达","II型雷达"};
		typesMode = new DefaultComboBoxModel(typesData);
		types.setModel(typesMode);
		types.setBounds(320, 20, 98, 22);
	}
	//底部按钮初始化
	private void setBottomButtonsForTable() {
		firstPage = new BottomButtonForTable("首  页",190, 458,50,24,12);
		previousPage = new BottomButtonForTable("上一页",250, 458,50,24,12);
		nextPage = new BottomButtonForTable("下一页",310, 458,50,24,12);
		lastPage = new BottomButtonForTable("尾  页",370, 458,50,24,12);
	}
	
	//设置表格表头每一列样式
	private void setTableHead(JLabel label,int x,int y,int width) {
		label.setOpaque(true);
		label.setBackground(new Color(204, 204, 204));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("宋体", Font.BOLD, 14));
		label.setBounds(x, y, width, 50);
	}

}

