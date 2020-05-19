package com.radar.UI.Components;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.SwingWorker;

import com.radar.SpringUtil;
import com.radar.Entity.Radar;
import com.radar.ServiceImpl.RadarServiceImpl;

@SuppressWarnings({"serial","rawtypes"})
public class JComBoxForRadarNumber extends JComboBox	 {
	private DefaultComboBoxModel mode = null;
	private String[] resultData = {""};
	private List<Radar> radarNumbers;


public  JComBoxForRadarNumber (int x,int y) {
	initUI(x,y);
	new SwingWorkerForJComoBoxForRadarNumber().execute();
}
@SuppressWarnings("unchecked")
private void initUI(int x,int y) {
	setBounds(x, y, 157, 25);
	mode = new DefaultComboBoxModel(resultData);
	setModel(mode);
	
}
class SwingWorkerForJComoBoxForRadarNumber extends SwingWorker<String[],Void>{
	//向数据库请求雷达所属部队的数据
			@SuppressWarnings("static-access")
			@Override
			protected String[] doInBackground() throws Exception {
				SpringUtil s = new SpringUtil();
				RadarServiceImpl r = (RadarServiceImpl) s.getBean("RadarServiceImpl");
				radarNumbers = r.getAllRadars();
				String[] result = new String[radarNumbers.size()+1];
				result[0] = "";
				for(int i=0;i<radarNumbers.size();i++) {
					result[i+1] = radarNumbers.get(i).getRadarName();
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
public List<Radar> getRadar(){
	return radarNumbers;
	
}

}
