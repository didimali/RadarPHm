package com.radar.UI.TopPanel;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;

@SuppressWarnings("serial")
public class TopPanelForManager extends TopPanel {
	private JButton managerList;
	private JButton newManager;

	/**
	 * 部队管理左侧栏
	 * @author madi
	 */
	public TopPanelForManager() {
		
		managerList = new JButton("部队列表");
		managerList.setOpaque(true);
		managerList.setBackground(new Color(255, 255, 255));
		managerList.setFont(new Font("宋体", Font.PLAIN, 14));
		managerList.setBounds(40, 5, 134, 50);
		add(managerList);
		
		newManager = new JButton("新  建");
		newManager.setOpaque(true);
		newManager.setBackground(new Color(255, 255, 255));
		newManager.setFont(new Font("宋体", Font.PLAIN, 14));
		newManager.setBounds(174, 5, 134, 50);
		add(newManager);

	}

	public JButton getManagerList() {
		return managerList;
	}
	public JButton getNewManager() {
		return newManager;
	}
}
