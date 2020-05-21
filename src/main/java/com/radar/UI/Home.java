package com.radar.UI;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
	@SuppressWarnings("unused")
	private FaultForecast faultForecast;
	private FaultRecord faultRecord;
	private HealthAssessIndex healthAssessIndex;
	private FaultForecastIndex faultForecastIndex;
	@SuppressWarnings("unused")
	private HealthAssess healthAssess;
	private InAndOut inAndOut;
	private ManagerList managerList;
	private NewManager newManager;
	private PartsRequirement partsRequirement;
	private RelationAnalyse relationAnalyse;
	@SuppressWarnings("unused")
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
		setRadarList();
		add(radarList);
	}
	
	
	
	//页面交互，事件响应
	private void Action() {	

		//左侧栏按钮点击事件-切换到首页、雷达列表
		leftPanel.getHome().addMouseListener(new PageChange(1, "topPanelForHome", "radarList"));
		//左侧栏按钮点击事件-切换到数据管理、开机记录
		leftPanel.getDataManage().addMouseListener(new PageChange(1, "topPanelForDataManage", "activityRecord"));
		//左侧栏按钮点击事件-切换到数据分析、健康评估
		leftPanel.getDataAnalyse().addMouseListener(new PageChange(1, "topPanelForDataAnalyse", "healthAssessIndex"));
		//左侧栏按钮点击事件-切换到部队管理、部队列表
		leftPanel.getManager().addMouseListener(new PageChange(1, "topPanelForManager", "managerList"));
		//左侧栏按钮点击事件-切换到型号信息
		leftPanel.getBasicInfo().addMouseListener(new PageChange(1, "topPanelForType", "basicInfo"));
		
	}

	//各组件初始化
	public void setTopPanelForHome() {
		topPanelForHome = new TopPanelForHome();
		topPanelForHome.setLocation(150, 0);
		//首页顶部栏事件-雷达列表按钮事件
		topPanelForHome.getRadarList().addMouseListener(new PageChange(0, null, "radarList"));
		//首页顶部栏事件-新建雷达按钮事件
		topPanelForHome.getNewRadar().addMouseListener(new PageChange(0, null, "newRadar"));
		topPanelName = "topPanelForHome";
				
	}
	public void setTopPanelForDataAnalyse() {
		topPanelForDataAnalyse = new TopPanelForDataAnalyse();
		topPanelForDataAnalyse.setLocation(150, 0);
		//数据分析-健康评估
		topPanelForDataAnalyse.getHealthAssess().addMouseListener(new PageChange(0, null, "healthAssessIndex"));
		//数据分析-故障预测
		topPanelForDataAnalyse.getFaultForecast().addMouseListener(new PageChange(0, null, "faultForecastIndex"));
		//数据分析-备件需求
		topPanelForDataAnalyse.getPartsRequirement().addMouseListener(new PageChange(0, null, "partsRequirement"));
		//数据分析-关联性分析
		topPanelForDataAnalyse.getRelationAnalyse().addMouseListener(new PageChange(0, null, "relationAnalyse"));
		topPanelName = "topPanelForDataAnalyse";
	}

	public void setTopPanelForDataManage() {
		topPanelForDataManage = new TopPanelForDataManage();
		topPanelForDataManage.setLocation(150, 0);
		//数据管理-开机记录
		topPanelForDataManage.getActivityRecord().addMouseListener(new PageChange(0, null, "activityRecord"));
		//数据管理-故障记录
		topPanelForDataManage.getFaultRecord().addMouseListener(new PageChange(0, null, "faultRecord"));
		//数据管理-监测数据
		topPanelForDataManage.getDynamicData().addMouseListener(new PageChange(0, null, "dynamicData"));
		//数据管理-导入导出
		topPanelForDataManage.getInAndOut().addMouseListener(new PageChange(0, null, "inAndOut"));
		topPanelName = "topPanelForDataManage";
	}

	public void setTopPanelForManager() {
		topPanelForManager = new TopPanelForManager();
		topPanelForManager.setLocation(150, 0);
		//部队管理-部队列表
		topPanelForManager.getManagerList().addMouseListener(new PageChange(0, null, "managerList"));
		//部队管理-新建部队
		topPanelForManager.getNewManager().addMouseListener(new PageChange(0, null, "newManager"));
		topPanelName = "topPanelForManager";
	}

	public void setTopPanelForType() {
		if(topPanelForType == null) {
			topPanelForType = new TopPanelForType();
			topPanelForType.setLocation(150, 0);
		}
		topPanelName = "topPanelForType";
	}
	public void setRadarList() {
		radarList = new RadarList();
		radarList.setBounds(150, 60,650,540);
		contentPanelName = "radarList";
	}

	public void setNewRadar() {
		newRadar = new NewRadar();
		newRadar.setBounds(150, 60,650,540);
		contentPanelName = "newRadar";
	}
	public void setBasicInfo() {
		basicInfo = new BasicInfo();
		basicInfo.setBounds(150, 60,650,540);
		contentPanelName = "basicInfo";
	}

	public void setActivityRecord() {
		activityRecord = new ActivityRecord();
		activityRecord.setBounds(150, 60,650,540);
		contentPanelName = "activityRecord";
	}

	public void setDynamicData() {
		dynamicData = new DynamicData();
		dynamicData.setBounds(150, 60,650,540);
		contentPanelName = "dynamicData";
	}
  
//	public void setFaultForecast() {
//		faultForecast = new FaultForecast();
//		faultForecast.setBounds(150, 60,650,540);
//		contentPanelName = "faultForecast";
//	}
  
	public void setFaultRecord() {
		faultRecord = new FaultRecord();
		faultRecord.setBounds(150, 60,650,540);
		contentPanelName = "faultRecord";
	}
	
	public void setHealthAssessIndex() {
		healthAssessIndex = new HealthAssessIndex();
		healthAssessIndex.setBounds(150, 60,650,540);
		contentPanelName = "healthAssessIndex";
	}
	public void setFaultForecastIndex() {
		faultForecastIndex = new FaultForecastIndex();
		faultForecastIndex.setBounds(150, 60,650,540);
		contentPanelName = "faultForecastIndex";
	}

//	public void setHealthAssess() {
//		healthAssess = new HealthAssess();
//		healthAssess.setBounds(150, 60,650,540);
//		contentPanelName = "healthAssess";
//	}
	/*
	 * private void setHealthAssess() { healthAssess = new HealthAssess();
	 * healthAssess.setBounds(150, 60,650,540); contentPanelName = "healthAssess"; }
	 */

	public void setInAndOut() {
		inAndOut = new InAndOut();
		inAndOut.setBounds(150, 60,650,540);
		contentPanelName = "inAndOut";
	}

	public void setManagerList() {
		managerList = new ManagerList();
		managerList.setBounds(150, 60,650,540);
		contentPanelName = "managerList";
	}

	public void setNewManager() {
		newManager = new NewManager();
		newManager.setBounds(150, 60,650,540);
		contentPanelName = "newManager";
	}

	public void setPartsRequirement() {
		partsRequirement = new PartsRequirement();
		partsRequirement.setBounds(150, 60,650,540);
		contentPanelName = "partsRequirement";
	}

	public void setRelationAnalyse() {
		relationAnalyse = new RelationAnalyse();
		relationAnalyse.setBounds(150, 60,650,540);
		contentPanelName = "relationAnalyse";
	}
		
	//根据topPanelName移除当前面板add的顶部面板
	private void addOrRemoveTopPanel(Boolean add,String topPanelName) {
		String panelName = topPanelName;
		if(panelName == "topPanelForHome" || panelName.equals("topPanelForHome")) {
			if(add) 
				add(topPanelForHome);
			else
				remove(topPanelForHome);
		}
		else if(panelName == "topPanelForDataAnalyse" || panelName.equals("topPanelForDataAnalyse")) {
			if(add)
				add(topPanelForDataAnalyse);
			else
				remove(topPanelForDataAnalyse);
		}
		else if(panelName == "topPanelForDataManage" || panelName.equals("topPanelForDataManage")) {
			if(add)
				add(topPanelForDataManage);
			else
				remove(topPanelForDataManage);
		}
		else if(panelName == "topPanelForManager" || panelName.equals("topPanelForManager")) {
			if(add)
				add(topPanelForManager);
			else
				remove(topPanelForManager);
		}
		else if(panelName == "topPanelForType" || panelName.equals("topPanelForType")) {
			if(add)
				add(topPanelForType);
			else
				remove(topPanelForType);
		}		
	}
	
	private String getCurrentContentPanelName() {
		return this.contentPanelName;
	}
	
	private String getCurrentTopPanelName() {
		return this.topPanelName;
		
	}
		
//	根据ContentPanelName移除内容面板
	private void addOrRemoveContentPanel(Boolean add,String contentPanelName) {
		String panelName = contentPanelName;
		if(panelName == "radarList" || panelName.equals("radarList")) {
			if(add) add(radarList);
			else	remove(radarList);
		}
		else if(panelName == "newRadar" || panelName.equals("newRadar")) {
			if(add)	add(newRadar);
			else	remove(newRadar);
		}
		else if(panelName == "basicInfo" || panelName.equals("basicInfo")) {
			if(add)	add(basicInfo);
			else	remove(basicInfo);
		}
		else if(panelName == "activityRecord" || panelName.equals("activityRecord")) {
			if(add) add(activityRecord);
			else	remove(activityRecord);
		}
		else if(panelName == "dynamicData" || panelName.equals("dynamicData")) {
			if(add)	add(dynamicData);
			else	remove(dynamicData);
		}
		else if(panelName == "faultForecastIndex" || panelName.equals("faultForecastIndex")) {
			if(add)	add(faultForecastIndex);
			else	remove(faultForecastIndex);
		}
		else if(panelName == "faultRecord" || panelName.equals("faultRecord")) {
			if(add)	add(faultRecord);
			else	remove(faultRecord);
		}
		else if(panelName == "healthAssessIndex" || panelName.equals("healthAssessIndex")) {
			if(add)	add(healthAssessIndex);
			else	remove(healthAssessIndex);
		}
		else if(panelName == "inAndOut" || panelName.equals("inAndOut")) {
			if(add)	add(inAndOut);
			else	remove(inAndOut);
		}
		else if(panelName == "managerList" || panelName.equals("managerList")) {
			if(add)	add(managerList);
			else	remove(managerList);
		}
		else if(panelName == "newManager" || panelName.equals("newManager")) {
			if(add)	add(newManager);
			else	remove(newManager);
		}
		else if(panelName == "partsRequirement" || panelName.equals("partsRequirement")) {
			if(add)	add(partsRequirement);
			else	remove(partsRequirement);
		}
		else if(panelName == "relationAnalyse" || panelName.equals("relationAnalyse")) {
			if(add)	add(relationAnalyse);
			else	remove(relationAnalyse);
		}		
	}
	//动态调用setContentPanel()方法，初始化组件topPanel和ContentPanel
	private void setPanel(String ObjectName) {
		try {
			Method method = this.getClass().getMethod("set"+
					ObjectName.replaceFirst(ObjectName.substring(0, 1),ObjectName.substring(0, 1).toUpperCase()));
			try {
				method.invoke(this);
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (IllegalArgumentException e1) {
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				e1.printStackTrace();
			}
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		} catch (SecurityException e1) {
			e1.printStackTrace();
		}
	}

class PageChange implements MouseListener{
		
		int levels = 0;//0:更换contentPanel;1:更换topPanel和contentPanel
		String currentPage1;//当前页面topPanel名称
		String currentPage2;//当前页面contentPanel名称
		String toPage1;//目标页面topPanel名称
		String toPage2;//目标页面contentPanel名称

		/**
		 * @author madi
		 * @param levels 0:更换contentPanel;1:更换topPanel和contentPanel
		 * @param currentPage1 当前页面topPanel名称
		 * @param currentPage2 当前页面contentPanel名称
		 * @param toPage1 目标页面topPanel名称
		 * @param toPage2 目标页面contentPanel名称
		 */
		protected PageChange(int levels,String toPage1,String toPage2) {
			this.levels = levels;
			this.toPage1 = toPage1;
			this.toPage2 = toPage2;
		};
		
		@Override
		public void mouseClicked(MouseEvent e) {
			this.currentPage1 = getCurrentTopPanelName();
			this.currentPage2 = getCurrentContentPanelName();
			//更换contentPanel
			if(levels == 0) {
				if(getCurrentContentPanelName() != toPage2) {
					//移除目前内容面板
					addOrRemoveContentPanel(false,currentPage2);
					//动态调用setContentPanel()方法，初始化组件ContentPanel
					setPanel(toPage2);
					addOrRemoveContentPanel(true,toPage2);
					validate(); 
					repaint();
				}
			}
			//更换contentPanel和topPanel
			else if(levels == 1) {
				if(getCurrentTopPanelName() != toPage1) {
					//移除目前的顶部栏面板
					addOrRemoveTopPanel(false,currentPage1);
					//移除目前的内容面板
					addOrRemoveContentPanel(false,currentPage2);
					//初始化顶部面板
					setPanel(toPage1);
					//初始化内容面板
					setPanel(toPage2);
					//添加目前的顶部栏面板
					addOrRemoveTopPanel(true,toPage1);
					//添加目前的内容面板
					addOrRemoveContentPanel(true,toPage2);
					//页面刷新
					validate(); 
					repaint();
				}				
			}		
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
		
	}
}
