package com.radar.UI.TopPanel;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class TopPanel extends JPanel {

	/**
	 * 顶部工具栏
	 */
	
	public JButton radarList;
	public JButton newRadar;
	public TopPanel() {
		setOpaque(true);
		setBackground(Color.WHITE);
		setSize(650, 60);
		setLayout(null);
		
	}
}
