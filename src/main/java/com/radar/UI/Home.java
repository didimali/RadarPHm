package com.radar.UI;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

import com.radar.UI.ContentPanel.NewRadar;
import com.radar.UI.ContentPanel.RadarList;
import com.radar.UI.TopPanel.TopPanelForDataAnalyse;
import com.radar.UI.TopPanel.TopPanelForDataManage;
import com.radar.UI.TopPanel.TopPanelForHome;
import com.radar.UI.TopPanel.TopPanelForManager;
import com.radar.UI.TopPanel.TopPanelForType;


@SuppressWarnings("serial")
public class Home extends JPanel {

	/**
	 * Create the panel.
	 */
	//左侧栏，只有一个，在此实例化
	private LeftPanel leftPanel = new LeftPanel();
	//页面组件声明（不初始化）
	//顶部栏
	//首页
	private TopPanelForHome topPanelForHome;
	private TopPanelForDataAnalyse topPanelForDataAnalyse;
	private TopPanelForDataManage topPanelForDataManage;
	private TopPanelForManager topPanelForManager;
	private TopPanelForType topPanelForType;
	
	//内容面板
	//雷达列表
	private RadarList radarList;
	//新建雷达
	private NewRadar newRadar;
	
	private String topPanelName;
	private String contentPanelName;
	public Home() {
		intUI();
		Action();
	}

	//页面初始化
	private void intUI() {
		setLayout(null);
		setBackground(Color.white);
		
		//添加左侧栏
		leftPanel.setBounds(0, 0,150,600);		
		add(leftPanel);		
		
		//添加顶部栏	
		setTopPanelForHome();
		add(topPanelForHome);
		
		//添加内容面板
		setRadarList();
		add(radarList);
		
				
	}
	//页面交互，事件响应
	private void Action() {
		//左侧栏按钮点击事件
			//切换到首页、雷达列表
		leftPanel.getHome().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(topPanelName != "topPanelForHome") {
				}				
			}
		});
			//切换到数据管理、开机记录
			//切换到数据分析、健康评估
			//切换到部队管理、部队列表
			//切换到型号信息
		//首页顶部栏事件
			//雷达列表按钮事件
		topPanelForHome.getRadarList().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(contentPanelName != "radarList") {
					remove(newRadar);
					setRadarList();
					add(radarList);
					repaint();
				}				
			}
		});

			//新建雷达按钮事件
		topPanelForHome.getNewRadar().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {	
				if(contentPanelName != "newRadar") {
					remove(radarList);
					setNewRadar();
					add(newRadar);
					repaint();
				}
			}
		});
	}
	
	//各组件初始化
	private void setTopPanelForHome() {
		topPanelForHome = new TopPanelForHome();
		topPanelForHome.setLocation(150, 0);
		topPanelName = "topPanelForHome";
				
	}
	private void setTopPanelForDataAnalyse() {
		topPanelForDataAnalyse = new TopPanelForDataAnalyse();
		topPanelForDataAnalyse.setLocation(150, 0);
		topPanelName = "topPanelForDataAnalyse";
	}

	private void setTopPanelForDataManage() {
		topPanelForDataManage = new TopPanelForDataManage();
		topPanelForDataManage.setLocation(150, 0);
		topPanelName = "topPanelForDataManage";
	}

	private void setTopPanelForManager() {
		topPanelForManager = new TopPanelForManager();
		topPanelForManager.setLocation(150, 0);
		topPanelName = "topPanelForManager";
	}

	private void setTopPanelForType() {
		topPanelForType = new TopPanelForType();
		topPanelForType.setLocation(150, 0);
		topPanelName = "topPanelForType";
	}
	private void setRadarList() {
		radarList = new RadarList();
		radarList.setBounds(150, 60,650,540);
		contentPanelName = "radarList";
	}
	private void setNewRadar() {
		newRadar = new NewRadar();
		newRadar.setBounds(150, 60,650,540);
		contentPanelName = "newRadar";
	}

	

}
