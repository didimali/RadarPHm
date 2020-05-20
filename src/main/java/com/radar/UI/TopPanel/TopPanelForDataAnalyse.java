package com.radar.UI.TopPanel;

import javax.swing.JButton;

import com.radar.UI.LeftPanel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class TopPanelForDataAnalyse extends TopPanel {
	private JButton healthAssess;
	private JButton faultForecast;
	private JButton partsRequirement;
	private JButton relationAnalyse;

	/**
	 * 数据分析顶部栏
	 * @author madi
	 */
	public TopPanelForDataAnalyse() {
		
		healthAssess = new JButton("健康评估");
		healthAssess.setIcon(LeftPanel.getIcon("forecast",this));
		healthAssess.setOpaque(true);
		healthAssess.setBackground(new Color(255, 255, 255));
		healthAssess.setFont(new Font("宋体", Font.PLAIN, 14));
		healthAssess.setBounds(40, 5, 134, 50);
		add(healthAssess);
		
		faultForecast = new JButton("故障预测");
		faultForecast.setIcon(LeftPanel.getIcon("activity",this));
		faultForecast.setOpaque(true);
		faultForecast.setBackground(new Color(255, 255, 255));
		faultForecast.setFont(new Font("宋体", Font.PLAIN, 14));
		faultForecast.setBounds(174, 5, 134, 50);
		add(faultForecast);
		
		partsRequirement = new JButton("备件需求");
		partsRequirement.setIcon(LeftPanel.getIcon("task",this));
		partsRequirement.setOpaque(true);
		partsRequirement.setBackground(new Color(255, 255, 255));
		partsRequirement.setFont(new Font("宋体", Font.PLAIN, 14));
		partsRequirement.setBounds(308, 5, 134, 50);
		add(partsRequirement);
		
		relationAnalyse = new JButton("关联性分析");
		relationAnalyse.setIcon(LeftPanel.getIcon("config",this));
		relationAnalyse.setOpaque(true);
		relationAnalyse.setBackground(new Color(255, 255, 255));
		relationAnalyse.setFont(new Font("宋体", Font.PLAIN, 14));
		relationAnalyse.setBounds(442, 5, 134, 50);
		add(relationAnalyse);

	}

	public JButton getHealthAssess() {
		return healthAssess;
	}
	public JButton getFaultForecast() {
		return faultForecast;
	}
	public JButton getPartsRequirement() {
		return partsRequirement;
	}
	public JButton getRelationAnalyse() {
		return relationAnalyse;
	}
}
