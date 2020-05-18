package com.radar.UI.Components;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.SwingWorker;

import com.radar.SpringUtil;
import com.radar.Entity.Radar;
import com.radar.ServiceImpl.RadarServiceImpl;

@SuppressWarnings({ "serial", "rawtypes" })
public class JComoBoxForHealth extends JComboBox{
	
	
	
	private DefaultComboBoxModel mode = null;
	private String[] resultData = {"——未选择——"};
	private List<Radar> radars;
	
	/**
	 * 健康评估页面下拉框
	 * @author madi
	 * @param x 下拉框x坐标
	 * @param y 下拉框y坐标
	 * */
	public JComoBoxForHealth(int x,int y) {
		initUI(x,y);
		new SwingWorkerForJComoBoxForHealth().execute();
	}

	@SuppressWarnings("unchecked")
	private void initUI(int x,int y) {
		setBounds(x, y, 98, 22);
		mode = new DefaultComboBoxModel(resultData);
		setModel(mode);
		
	}
	
	class SwingWorkerForJComoBoxForHealth extends SwingWorker<String[],Void>{

		//向数据库请求雷达的数据
		@SuppressWarnings("static-access")
		@Override
		protected String[] doInBackground() throws Exception {
			SpringUtil s = new SpringUtil();
			RadarServiceImpl m = (RadarServiceImpl) s.getBean("RadarServiceImpl");
			radars = m.getAllRadars();
			String[] result = new String[radars.size()+1];			
			result[0] = "——未选择——";
			for(int i=0;i<radars.size();i++) {			
				result[i+1] = radars.get(i).getRadarName();
			}
			return result;
		}
		
		@SuppressWarnings("unchecked")
		@Override
		protected void done() {
			try {
				resultData = get();
				mode = new DefaultComboBoxModel(resultData);
				setModel(mode);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}



