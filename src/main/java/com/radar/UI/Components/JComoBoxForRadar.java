package com.radar.UI.Components;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.SwingWorker;

import com.radar.SpringUtil;
import com.radar.Entity.Radar;
import com.radar.ServiceImpl.RadarServiceImpl;

import java.awt.Font;

@SuppressWarnings({ "serial", "rawtypes" })
public class JComoBoxForRadar extends JComboBox{
	
	/**
	 * 下拉框-雷达
	 * @author madi 
	 * @param x x坐标
	 * @param y y坐标
	 * @param width 宽度
	 * @param height 高度
	 * @param font-size 字体大小
	 * */
	private List<Radar> radars;
	private DefaultComboBoxModel mode = null;
	private String[] resultData = {"All"};
	
	public JComoBoxForRadar(int x,int y,int width,int height,int fontSize) {		
		initUI(x,y,width,height,fontSize);
		Action();
		
	}

	//UI初始化
	private void initUI(int x, int y, int width, int height, int fontSize) {
		setBounds(x,y,width,height);
		setFont(new Font("宋体", Font.PLAIN, fontSize));
		new SwingWorkerForJComoBoxForRadar().execute();
		initData();
		
	}
	
	//下拉框监听事件
	private void Action() {
		
	}
	
	//下拉框加载数据
	@SuppressWarnings({ "unchecked" })
	private void initData() {
		if(radars != null) {
			resultData = new String[1+radars.size()];
			resultData[0] = "All";
			for(int i=0;i<radars.size();i++) {
				Radar r = radars.get(i);
				resultData[i+1] = r.getRadarName();				
			}
		}
		mode = new DefaultComboBoxModel(resultData);
		setModel(mode);		
	}
	//根据雷达的部队加载下拉框数据
	@SuppressWarnings("unchecked")
	public void initData(String managerName) {
		if(managerName != null) {
			if(managerName == "All" || managerName.equals("All")) 
				initData();
			else {
				String[] data = new String[radars.size()+1];
				data[0] = "All";
				int dataCounts = 1;
				for(int i=0;i<radars.size();i++) {
					Radar r = radars.get(i);
					if(r.getManagerId().getManagerName() == managerName 
							|| r.getManagerId().getManagerName().equals(managerName)) {
						data[dataCounts] = r.getRadarName();
						dataCounts++;
					}									
				}
				resultData = new String[dataCounts];
				for(int i=0;i<dataCounts;i++) {
					resultData[i] = data[i];
				}
				mode = new DefaultComboBoxModel(resultData);
				setModel(mode);	
			}			
		}		
	}

	public List<Radar> getRadars() {
		return radars;
	}
	public String[] getResultData() {
		return this.resultData;
	}
	
	class SwingWorkerForJComoBoxForRadar extends SwingWorker<List<Radar>,Void>{

		@SuppressWarnings("static-access")
		@Override
		protected List<Radar> doInBackground() throws Exception {
			SpringUtil s = new SpringUtil();
			RadarServiceImpl radarServiceImpl = (RadarServiceImpl) s.getBean("RadarServiceImpl");
			List<Radar> result = radarServiceImpl.getAllRadars();
			return result;
		}
		
		protected void done() {
			try {
				radars = get();
				initData();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}		
	}


}
