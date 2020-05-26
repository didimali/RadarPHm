package com.radar.UI.ContentPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.radar.UI.Components.BottomButtonForTable;
import com.radar.UI.Components.ManagerTable;

public class ManagerList extends ContentPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8302249038796830171L;
	/**
	 * 部队列表内容面板
	 * @author lhy
	 */
	//页面组件
	private  ManagerTable table;
	private JSeparator separator;
	private JLabel labelForTitle;
	private JLabel tHead1;
	private JLabel tHead2;
	private JLabel tHead3;
	private JLabel tHead4;
	private JLabel tHead5;
	
	private BottomButtonForTable firstPage;
	private BottomButtonForTable previousPage;
	private BottomButtonForTable nextPage;
	private BottomButtonForTable lastPage;
	
	//雷达信息修改弹出面板
	private AlertManager alertManager;
	
	public ManagerList() {		
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
		
		//表格行点击事件
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					//选中的表格的行数
					int selectedRow = table.getSelectedRow();
					//部队id
					Integer managerId = (Integer) table.getValueAt(selectedRow, 0);					
					//所选行包含部队信息，弹出修改面板
					if(managerId != null) {
						//部队代号
						String managerName = (String)table.getValueAt(selectedRow, 2);
						//部队位置
						String managerLocation = (String)table.getValueAt(selectedRow, 3);
						
						//打开修改雷达信息面板
						alertManager = new AlertManager(managerId,managerName,managerLocation);
						//添加监听事件
						addPropertyChangeListenerForRadarUpdate();
					}
					//所选行不包含雷达信息，为空白行，弹出提示框，提醒用户重新选择
					else {
						
					}
				}
				catch(Exception execption) {
					execption.printStackTrace();
				}
			}
		});
		
		
		
	}

	//页面初始化，添加组件
	private void initUI() {	
		
		//部队，部队标签，分割线
		setLabelForTitle();
		panel.add(labelForTitle);
		
		setJSeparator();
		panel.add(separator);
		
		//表格标题
		setManagerTableHeads();
		panel.add(tHead1);	
		panel.add(tHead2);
		panel.add(tHead3);
		panel.add(tHead4);
		panel.add(tHead5);
		
		//表格内容
		setManagerTable();
		panel.add(table);
		
		//底部操作按钮
		setBottomButtonsForTable();
		panel.add(firstPage);
		panel.add(previousPage);
		panel.add(nextPage);		
		panel.add(lastPage);
	}
	
	//标题初始化
	private void setLabelForTitle() {
		labelForTitle = new JLabel("部队信息列表");
		labelForTitle.setFont(new Font("宋体", Font.PLAIN, 20));
		labelForTitle.setBounds(20, 17, 120, 26);
		
	}
	
	//表头初始化
	private void setManagerTableHeads() {
		
		tHead1 = new JLabel("序号");
		setTableHead(tHead1,20, 60, 60);
		
		tHead2 = new JLabel("部队代号");
		setTableHead(tHead2,80, 60, 110);
		
		tHead3 = new JLabel("布放位置");
		setTableHead(tHead3,190, 60, 110);
		
		tHead4 = new JLabel("管辖雷达数量");
		setTableHead(tHead4,300, 60, 130);
		
		tHead5 = new JLabel("操作");
		setTableHead(tHead5,430, 60, 160);
		
	}
	//表格初始化
	private void setManagerTable(){
		table = new ManagerTable();
		table.setRowSelectionAllowed(true);
		table.setBounds(20, 110, 570, 300);
		
	}
	//分割线初始化
	private void setJSeparator() {
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
	
	//设置表格表头每一列样式
	private void setTableHead(JLabel label,int x,int y,int width) {
		label.setOpaque(true);
		label.setBackground(new Color(204, 204, 204));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("宋体", Font.BOLD, 14));
		label.setBounds(x, y, width, 50);
	}
	
	//添加监听事件，监听雷达信息是否修改成功
	private void addPropertyChangeListenerForRadarUpdate() {
		//雷达信息修改完成后，更新雷达数据列表
		alertManager.getUpdateFinished().addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				String status = alertManager.getUpdateFinished().getText();
				if(status == "yes" || status.equals("yes")) {
					//表格刷新，重新获取数据
					table.initTable();
				}
			}
		});
	}

}

