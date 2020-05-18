package com.radar.UI;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

import com.radar.UI.Components.LoadingPanel;
import com.radar.UI.ContentPanel.ActivityRecord;
import com.radar.UI.ContentPanel.BasicInfo;
import com.radar.UI.ContentPanel.DynamicData;
import com.radar.UI.ContentPanel.FaultForecast;
import com.radar.UI.ContentPanel.FaultForecastIndex;
import com.radar.UI.ContentPanel.FaultRecord;
import com.radar.UI.ContentPanel.HealthAssess;
import com.radar.UI.ContentPanel.HealthAssessIndex;
import com.radar.UI.ContentPanel.InAndOut;
import com.radar.UI.ContentPanel.ManagerList;
import com.radar.UI.ContentPanel.NewManager;
import com.radar.UI.ContentPanel.NewRadar;
import com.radar.UI.ContentPanel.PartsRequirement;
import com.radar.UI.ContentPanel.RadarList;
import com.radar.UI.ContentPanel.RelationAnalyse;
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
	private RadarList radarList;
	private NewRadar newRadar;
	
	private BasicInfo basicInfo;
	private ActivityRecord activityRecord;
	private DynamicData dynamicData;
	private FaultForecast faultForecast;
	private FaultRecord faultRecord;
	private HealthAssessIndex healthAssessIndex;
	private FaultForecastIndex faultForecastIndex;
	private HealthAssess healthAssess;
	private InAndOut inAndOut;
	private ManagerList managerList;
	private NewManager newManager;
	private PartsRequirement partsRequirement;
	private RelationAnalyse relationAnalyse;
	private LoadingPanel loadingPanel;
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
//		setRadarList(); add(radarList);
		
//		setHealthAssess(); add(healthAssess);
		 
		
//		 setFaultForecast(); add(faultForecast);
//		 sethealthAssessIndex(); add(healthAssessIndex);
		 setfaultForecastIndex(); add(faultForecastIndex);
//		setLoadingPanel();add(loadingPanel);		
	}
	//页面交互，事件响应
	private void Action() {
		//左侧栏按钮点击事件
			//切换到首页、雷达列表
		leftPanel.getHome().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(topPanelName != "topPanelForHome") {					
					removeCurrentTopPanel();//移除目前顶部栏					
					removeCurrentContentPanel();//移除目前内容面板
					setTopPanelForHome();
					setRadarList();					
					add(topPanelForHome);
					add(radarList);
					repaint();
				}				
			}
		});
			//切换到数据管理、开机记录
		leftPanel.getDataManage().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(topPanelName != "topPanelForDataManage") {					
					removeCurrentTopPanel();//移除目前顶部栏					
					removeCurrentContentPanel();//移除目前内容面板
					setTopPanelForDataManage();
					setActivityRecord();					
					add(topPanelForDataManage);
					add(activityRecord);
					repaint();
				}				
			}
		});
			//切换到数据分析、健康评估
		leftPanel.getDataAnalyse().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(topPanelName != "topPanelForDataAnalyse") {					
					removeCurrentTopPanel();//移除目前顶部栏					
					removeCurrentContentPanel();//移除目前内容面板
					setTopPanelForDataAnalyse();
					/* setHealthAssess(); */					
					add(topPanelForDataAnalyse);
					add(healthAssess);
					repaint();
				}				
			}
		});
			//切换到部队管理、部队列表
		leftPanel.getManager().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(topPanelName != "topPanelForManager") {					
					removeCurrentTopPanel();//移除目前顶部栏					
					removeCurrentContentPanel();//移除目前内容面板
					setTopPanelForManager();
					setManagerList();					
					add(topPanelForManager);
					add(managerList);
					repaint();
				}				
			}
		});
			//切换到型号信息
		leftPanel.getBasicInfo().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(topPanelName != "topPanelForType") {					
					removeCurrentTopPanel();//移除目前顶部栏					
					removeCurrentContentPanel();//移除目前内容面板
					setTopPanelForType();
					setBasicInfo();					
					add(topPanelForType);
					add(basicInfo);
					repaint();
				}				
			}
		});
		//首页顶部栏事件
			//雷达列表按钮事件
		topPanelForHome.getRadarList().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(contentPanelName);
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
				System.out.println(contentPanelName);
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
		if(topPanelForHome == null) {
			topPanelForHome = new TopPanelForHome();
			topPanelForHome.setLocation(150, 0);
		}		
		topPanelName = "topPanelForHome";
				
	}
	private void setTopPanelForDataAnalyse() {
		if(topPanelForDataAnalyse == null) {
			topPanelForDataAnalyse = new TopPanelForDataAnalyse();
			topPanelForDataAnalyse.setLocation(150, 0);
		}
		topPanelName = "topPanelForDataAnalyse";
	}

	private void setTopPanelForDataManage() {
		if(topPanelForDataManage == null) {
			topPanelForDataManage = new TopPanelForDataManage();
			topPanelForDataManage.setLocation(150, 0);
		}
		topPanelName = "topPanelForDataManage";
	}

	private void setTopPanelForManager() {
		if(topPanelForManager == null) {
			topPanelForManager = new TopPanelForManager();
			topPanelForManager.setLocation(150, 0);
		}
		topPanelName = "topPanelForManager";
	}

	private void setTopPanelForType() {
		if(topPanelForType == null) {
			topPanelForType = new TopPanelForType();
			topPanelForType.setLocation(150, 0);
		}
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
	private void setBasicInfo() {
		basicInfo = new BasicInfo();
		basicInfo.setBounds(150, 60,650,540);
		contentPanelName = "basicInfo";
	}

	private void setActivityRecord() {
		activityRecord = new ActivityRecord();
		activityRecord.setBounds(150, 60,650,540);
		contentPanelName = "activityRecord";
	}

	private void setDynamicData() {
		dynamicData = new DynamicData();
		dynamicData.setBounds(150, 60,650,540);
		contentPanelName = "dynamicData";
	}

	/*
	 * private void setFaultForecast() { faultForecast = new FaultForecast();
	 * faultForecast.setBounds(150, 60,650,540); contentPanelName = "faultForecast";
	 * }
	 */

	private void setFaultRecord() {
		faultRecord = new FaultRecord();
		faultRecord.setBounds(150, 60,650,540);
		contentPanelName = "faultRecord";
	}
	
	private void sethealthAssessIndex() {
		healthAssessIndex = new HealthAssessIndex();
		healthAssessIndex.setBounds(150, 60,650,540);
		contentPanelName = "healthAssessIndex";
	}
	private void setfaultForecastIndex() {
		faultForecastIndex = new FaultForecastIndex();
		faultForecastIndex.setBounds(150, 60,650,540);
		contentPanelName = "faultForecastIndex";
	}
	/*
	 * private void setHealthAssess() { healthAssess = new HealthAssess();
	 * healthAssess.setBounds(150, 60,650,540); contentPanelName = "healthAssess"; }
	 */

	private void setInAndOut() {
		inAndOut = new InAndOut();
		inAndOut.setBounds(150, 60,650,540);
		contentPanelName = "inAndOut";
	}

	private void setManagerList() {
		managerList = new ManagerList();
		managerList.setBounds(150, 60,650,540);
		contentPanelName = "managerList";
	}

	private void setNewManager() {
		newManager = new NewManager();
		newManager.setBounds(150, 60,650,540);
		contentPanelName = "newManager";
	}

	private void setPartsRequirement() {
		partsRequirement = new PartsRequirement();
		partsRequirement.setBounds(150, 60,650,540);
		contentPanelName = "partsRequirement";
	}

	private void setRelationAnalyse() {
		relationAnalyse = new RelationAnalyse();
		relationAnalyse.setBounds(150, 60,650,540);
		contentPanelName = "relationAnalyse";
	}
	
	private void setLoadingPanel() {
		loadingPanel = new LoadingPanel();
		loadingPanel.setBounds(150, 60,650,540);
//		contentPanelName = "loadingPanel";
	}
	
	//根据topPanelName移除当前面板add的顶部面板
	private void removeCurrentTopPanel() {
		if(topPanelName == "topPanelForHome" || topPanelName.equals("topPanelForHome")) {
			remove(topPanelForHome);
		}
		else if(topPanelName == "topPanelForDataAnalyse" || topPanelName.equals("topPanelForDataAnalyse")) {
			remove(topPanelForDataAnalyse);
		}
		else if(topPanelName == "topPanelForDataManage" || topPanelName.equals("topPanelForDataManage")) {
			remove(topPanelForDataManage);
		}
		else if(topPanelName == "topPanelForManager" || topPanelName.equals("topPanelForManager")) {
			remove(topPanelForManager);
		}
		else if(topPanelName == "topPanelForType" || topPanelName.equals("topPanelForType")) {
			remove(topPanelForType);
		}		
	}
		
//	根据ContentPanelName移除目前内容面板
	private void removeCurrentContentPanel() {
		if(contentPanelName == "radarList" || contentPanelName.equals("radarList")) {
			remove(radarList);
		}
		else if(contentPanelName == "newRadar" || contentPanelName.equals("newRadar")) {
			remove(newRadar);
		}
		else if(contentPanelName == "basicInfo" || contentPanelName.equals("basicInfo")) {
			remove(basicInfo);
		}
		else if(contentPanelName == "activityRecord" || contentPanelName.equals("activityRecord")) {
			remove(activityRecord);
		}
		else if(contentPanelName == "dynamicData" || contentPanelName.equals("dynamicData")) {
			remove(dynamicData);
		}
		else if(contentPanelName == "faultForecast" || contentPanelName.equals("faultForecast")) {
			remove(faultForecast);
		}
		else if(contentPanelName == "faultRecord" || contentPanelName.equals("faultRecord")) {
			remove(faultRecord);
		}
		else if(contentPanelName == "healthAssess" || contentPanelName.equals("healthAssess")) {
			remove(healthAssess);
		}
		else if(contentPanelName == "inAndOut" || contentPanelName.equals("inAndOut")) {
			remove(inAndOut);
		}
		else if(contentPanelName == "managerList" || contentPanelName.equals("managerList")) {
			remove(managerList);
		}
		else if(contentPanelName == "newManager" || contentPanelName.equals("newManager")) {
			remove(newManager);
		}
		else if(contentPanelName == "partsRequirement" || contentPanelName.equals("partsRequirement")) {
			remove(partsRequirement);
		}
		else if(contentPanelName == "relationAnalyse" || contentPanelName.equals("relationAnalyse")) {
			remove(relationAnalyse);
		}
		
		
	}

	
	

}
