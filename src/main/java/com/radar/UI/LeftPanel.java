package com.radar.UI;

import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

import sun.awt.shell.ShellFolder;

import java.awt.Color;
import javax.swing.JButton;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
	    home.setIcon(getIcon("home"));							
		home.setBounds(8, 64, 134, 50);
		add(home);
		
		dataManage = new JButton("数据管理");
		dataManage.setIcon(getIcon("organize"));
		dataManage.setFont(new Font("宋体", Font.PLAIN, 14));
		dataManage.setBackground(new Color(199, 199, 199));
		dataManage.setBounds(8, 114, 134, 50);
		add(dataManage);
		
		dataAnalyse = new JButton("数据分析");
		dataAnalyse.setIcon(getIcon("advertising"));
		dataAnalyse.setFont(new Font("宋体", Font.PLAIN, 14));
		dataAnalyse.setBackground(new Color(199, 199, 199));
		dataAnalyse.setBounds(8, 164, 134, 50);
		add(dataAnalyse);
		
		Manager = new JButton("部队管理");
		Manager.setIcon(getIcon("office"));
		Manager.setFont(new Font("宋体", Font.PLAIN, 14));
		Manager.setBackground(new Color(199, 199, 199));
		Manager.setBounds(8, 214, 134, 50);
		add(Manager);
		
		BasicInfo = new JButton("型号管理");
		BasicInfo.setIcon(getIcon("document"));
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
		lblNewLabel.setIcon(getIcon("radar1"));
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
	public static ImageIcon getIcon(String imageName){		
		try {
			Resource resource = new ClassPathResource("images/"+imageName+".png");
			File file;
			file = resource.getFile();
			InputStream is=new FileInputStream(file);
			BufferedImage bi=ImageIO.read(is);
			Image im=(Image)bi;
			ImageIcon icon = new ImageIcon(im);
		    file.delete();
			return icon;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
}
