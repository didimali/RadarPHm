package com.radar.UI.TopPanel;

import javax.swing.JButton;

import com.radar.UI.LeftPanel;

import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;

@SuppressWarnings("serial")
public class TopPanelForDataManage extends TopPanel {
	private JButton activityRecord;
	private JButton faultRecord;
	private JButton dynamicData;
	private JButton inAndOut;

	/**
	 * 数据管理顶部栏
	 * @author madi
	 */
	public TopPanelForDataManage() {
		
		activityRecord = new JButton("开机记录");
		activityRecord.setOpaque(true);
		activityRecord.setBackground(new Color(255, 255, 255));
		activityRecord.setIcon(LeftPanel.getIcon("list3"));
		activityRecord.setFont(new Font("宋体", Font.PLAIN, 14));
		activityRecord.setBounds(40, 5, 134, 50);
		add(activityRecord);
		
		faultRecord = new JButton("故障记录");
		faultRecord.setOpaque(true);
		faultRecord.setBackground(new Color(255, 255, 255));
		faultRecord.setIcon(LeftPanel.getIcon("list4"));
		faultRecord.setFont(new Font("宋体", Font.PLAIN, 14));
		faultRecord.setBounds(174, 5, 134, 50);
		add(faultRecord);
		
		dynamicData = new JButton("监测数据");
		dynamicData.setOpaque(true);
		dynamicData.setBackground(new Color(255, 255, 255));
		dynamicData.setIcon(LeftPanel.getIcon("monitoring"));
		dynamicData.setFont(new Font("宋体", Font.PLAIN, 14));
		dynamicData.setBounds(308, 5, 134, 50);
		add(dynamicData);
		
		inAndOut = new JButton("导入导出");
		inAndOut.setIcon(LeftPanel.getIcon("communication"));
		inAndOut.setOpaque(true);
		inAndOut.setBackground(new Color(255, 255, 255));
		inAndOut.setFont(new Font("宋体", Font.PLAIN, 14));
		inAndOut.setBounds(442, 5, 134, 50);
		add(inAndOut);

	}

	public JButton getActivityRecord() {
		return activityRecord;
	}
	public JButton getFaultRecord() {
		return faultRecord;
	}
	public JButton getDynamicData() {
		return dynamicData;
	}
	public JButton getInAndOut() {
		return inAndOut;
	}
}
