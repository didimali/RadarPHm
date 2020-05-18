package com.radar.UI.Components;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.radar.UI.ContentPanel.DynamicData;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JScrollBar;

public class test extends JPanel {

	/**
	 * Create the panel.
	 */
	public test() {
		
		setLayout(new BorderLayout(0, 0));
		
		DynamicData d = new DynamicData();
		d.setPreferredSize(new Dimension(650,540));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setViewportView(d);
		scrollPane.setWheelScrollingEnabled(true);
		add(scrollPane,BorderLayout.CENTER);

	}

}
