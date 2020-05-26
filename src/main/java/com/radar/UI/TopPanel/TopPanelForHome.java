package com.radar.UI.TopPanel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

import com.radar.UI.LeftPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class TopPanelForHome extends TopPanel {
	private JButton radarList;
	private JButton newRadar;

	/**
	 * 首页顶部栏
	 */
	public TopPanelForHome() {
		radarList = new JButton("雷达列表");
		radarList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		radarList.setOpaque(true);
		radarList.setBackground(new Color(245, 245, 245));
		radarList.setIcon(LeftPanel.getIcon("list3",this));
		radarList.setFont(new Font("宋体", Font.PLAIN, 14));
		radarList.setBounds(40, 5, 134, 50);
		add(radarList);
		
		newRadar = new JButton("新  建");
		newRadar.setOpaque(true);
		newRadar.setBackground(new Color(245, 245, 245));
		newRadar.setIcon(LeftPanel.getIcon("new4",this));
		newRadar.setFont(new Font("宋体", Font.PLAIN, 14));
		newRadar.setBackground(Color.WHITE);
		newRadar.setBounds(174, 5, 134, 50);
		add(newRadar);

	}

	//暴露两个按钮
	public JButton getRadarList() {
		return radarList;
	}
	public JButton getNewRadar() {
		return newRadar;
	}
}
