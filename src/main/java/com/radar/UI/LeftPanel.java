package com.radar.UI;

import javax.swing.JPanel;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import java.awt.Color;
import javax.swing.JButton;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

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
	public LeftPanel(){
		setOpaque(true);
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		
		home.setBackground(new Color(245, 245, 245));
		home.setFont(new Font("宋体", Font.PLAIN, 14));
	    home.setIcon(getIcon("home",this));							
		home.setBounds(8, 64, 134, 50);
		add(home);
		
		dataManage = new JButton("数据管理");
		dataManage.setIcon(getIcon("organize",this));
		dataManage.setFont(new Font("宋体", Font.PLAIN, 14));
		dataManage.setBackground(new Color(199, 199, 199));
		dataManage.setBounds(8, 114, 134, 50);
		add(dataManage);
		
		dataAnalyse = new JButton("数据分析");
		dataAnalyse.setIcon(getIcon("advertising",this));
		dataAnalyse.setFont(new Font("宋体", Font.PLAIN, 14));
		dataAnalyse.setBackground(new Color(199, 199, 199));
		dataAnalyse.setBounds(8, 164, 134, 50);
		add(dataAnalyse);
		
		Manager = new JButton("部队管理");
		Manager.setIcon(getIcon("office",this));
		Manager.setFont(new Font("宋体", Font.PLAIN, 14));
		Manager.setBackground(new Color(199, 199, 199));
		Manager.setBounds(8, 214, 134, 50);
		add(Manager);
		
		BasicInfo = new JButton("型号管理");
		BasicInfo.setIcon(getIcon("document",this));
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
		lblNewLabel.setIcon(getIcon("radar1",this));
		lblNewLabel.setBounds(18, 14, 114, 32);
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
	
	/*
	 * @author :madi
	 * @param: image name
	 * @return: Image
	 */
	public  static  ImageIcon getIcon(String imageName,Object c){		
		
		try {	
			InputStream inputStream=c.getClass().getResourceAsStream("/static/images/"+imageName+".png");
			if(inputStream != null) {
				BufferedImage bi=ImageIO.read(inputStream);
				Image im=(Image)bi;
				ImageIcon icon = new ImageIcon(im);
				return icon;
			}
			else
				return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
}
