package com.radar.UI.Components;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.SwingWorker;

import com.radar.SpringUtil;
import com.radar.Entity.Manager;
import com.radar.Entity.faultType;
import com.radar.ServiceImpl.FaultTypeServiceImpl;
import com.radar.ServiceImpl.ManagerServiceImpl;
import com.radar.UI.Components.JComoBoxForManager.SwingWorkerForJComoBoxForManager;

@SuppressWarnings({"serial","rawtypes"})
public class JComoBoxForFaultType extends JComboBox{
	private DefaultComboBoxModel mode = null;
	private String[] resultData = {""};
	private List<faultType> faultTypes;
	
	/**
	 * 部队下拉框
	 * @author madi
	 * @param x 下拉框x坐标
	 * @param y 下拉框y坐标
	 * */
	public JComoBoxForFaultType(int x,int y) {
		initUI(x,y);
		new SwingWorkerForJComoBoxForFaultType().execute();
	}

	@SuppressWarnings("unchecked")
	private void initUI(int x,int y) {
		setBounds(x, y, 98, 22);
		mode = new DefaultComboBoxModel(resultData);
		setModel(mode);
		
	}
	class SwingWorkerForJComoBoxForFaultType extends SwingWorker<String[],Void>{

		//向数据库请求雷达所属部队的数据
		@SuppressWarnings("static-access")
		@Override
		protected String[] doInBackground() throws Exception {
			SpringUtil s = new SpringUtil();
			FaultTypeServiceImpl f = (FaultTypeServiceImpl) s.getBean("FaultTypeServiceImpl");
			faultTypes = f.getAllFaultType();
			String[] result = new String[faultTypes.size()+1];
			result[0] = "";
			for(int i=0;i<faultTypes.size();i++) {
				result[i+1] = faultTypes.get(i).getFaultName();
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
