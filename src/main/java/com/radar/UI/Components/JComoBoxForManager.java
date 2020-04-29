package com.radar.UI.Components;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.SwingWorker;

import com.radar.SpringUtil;
import com.radar.Entity.Manager;
import com.radar.ServiceImpl.ManagerServiceImpl;

@SuppressWarnings({ "serial", "rawtypes" })
public class JComoBoxForManager extends JComboBox{
	
	
	
	private DefaultComboBoxModel mode = null;
	private String[] resultData = {"All"};
	private List<Manager> managers;
	
	/**
	 * 部队下拉框
	 * @author madi
	 * @param x 下拉框x坐标
	 * @param y 下拉框y坐标
	 * */
	public JComoBoxForManager(int x,int y) {
		initUI(x,y);
		new SwingWorkerForJComoBoxForManager().execute();
	}

	@SuppressWarnings("unchecked")
	private void initUI(int x,int y) {
		setBounds(x, y, 98, 22);
		mode = new DefaultComboBoxModel(resultData);
		setModel(mode);
		
	}
	
	class SwingWorkerForJComoBoxForManager extends SwingWorker<String[],Void>{

		//向数据库请求雷达所属部队的数据
		@SuppressWarnings("static-access")
		@Override
		protected String[] doInBackground() throws Exception {
			SpringUtil s = new SpringUtil();
			ManagerServiceImpl m = (ManagerServiceImpl) s.getBean("ManagerServiceImpl");
			managers = m.getAllManager();
			String[] result = new String[managers.size()+1];
			result[0] = "All";
			for(int i=0;i<managers.size();i++) {
				result[i+1] = managers.get(i).getManagerName();
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
	
	public List<Manager> getManager(){
		return managers;
		
	}
}
