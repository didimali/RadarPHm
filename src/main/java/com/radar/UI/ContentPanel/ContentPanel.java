package com.radar.UI.ContentPanel;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JPanel;
import java.awt.BorderLayout;

public class ContentPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 内容面板
	 */
	protected JPanel panel = new JPanel();
	public ContentPanel() {
		setOpaque(true);
		setBackground(SystemColor.control);
		setLayout(null);
				
		//装载内容
		panel.setBackground(Color.WHITE);
		panel.setBounds(20, 20, 610, 500);
		add(panel);
		panel.setLayout(null);
		
	}

}
