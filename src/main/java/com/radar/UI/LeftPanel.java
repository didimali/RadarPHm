package com.radar.UI;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class LeftPanel extends JPanel {

	/**
	 * 左侧面板
	 */
	public JButton home = new JButton("系统首页");
	private JButton dataManage;
	private JButton dataAnalyse;
	private JButton Manager;
	private JButton BasicInfo;
	public LeftPanel() {
		setOpaque(true);
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		
		home.setBackground(new Color(245, 245, 245));
		home.setFont(new Font("宋体", Font.PLAIN, 14));
		home.setIcon(new ImageIcon("G:\\GYWYWorkspace\\RadarPHM\\src\\main\\resources\\images\\ico01.png"));
		home.setBounds(8, 64, 134, 50);
		add(home);
		
		dataManage = new JButton("数据管理");
		dataManage.setIcon(new ImageIcon("G:\\GYWYWorkspace\\RadarPHM\\src\\main\\resources\\images\\ico04.png"));
		dataManage.setFont(new Font("宋体", Font.PLAIN, 14));
		dataManage.setBackground(new Color(199, 199, 199));
		dataManage.setBounds(8, 114, 134, 50);
		add(dataManage);
		
		dataAnalyse = new JButton("数据分析");
		dataAnalyse.setIcon(new ImageIcon("G:\\GYWYWorkspace\\RadarPHM\\src\\main\\resources\\images\\ico03.png"));
		dataAnalyse.setFont(new Font("宋体", Font.PLAIN, 14));
		dataAnalyse.setBackground(new Color(199, 199, 199));
		dataAnalyse.setBounds(8, 164, 134, 50);
		add(dataAnalyse);
		
		Manager = new JButton("部队管理");
		Manager.setIcon(new ImageIcon("G:\\GYWYWorkspace\\RadarPHM\\src\\main\\resources\\images\\ico05.png"));
		Manager.setFont(new Font("宋体", Font.PLAIN, 14));
		Manager.setBackground(new Color(199, 199, 199));
		Manager.setBounds(8, 214, 134, 50);
		add(Manager);
		
		BasicInfo = new JButton("型号管理");
		BasicInfo.setIcon(new ImageIcon("G:\\GYWYWorkspace\\RadarPHM\\src\\main\\resources\\images\\ico06.png"));
		BasicInfo.setFont(new Font("宋体", Font.PLAIN, 14));
		BasicInfo.setBackground(new Color(199, 199, 199));
		BasicInfo.setBounds(8, 264, 134, 50);
		add(BasicInfo);
		
		JPanel titleAndLogo = new JPanel();
		titleAndLogo.setBackground(new Color(0, 204, 255));
		titleAndLogo.setBounds(0, 0, 150, 60);
		add(titleAndLogo);
		titleAndLogo.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("雷达PHM");
		lblNewLabel.setBounds(18, 18, 114, 25);
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 18));
		titleAndLogo.add(lblNewLabel);

	}
	public JButton getHome() {
		return home;
	}
	public JButton getDataManage() {
		return dataManage;
	}
	public JButton getDataAnalyse() {
		return dataAnalyse;
	}
	public JButton getManager() {
		return Manager;
	}
	public JButton getBasicInfo() {
		return BasicInfo;
	}
}
