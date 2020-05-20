package com.radar.UI.ContentPanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSeparator;

import com.radar.SpringUtil;
import com.radar.ServiceImpl.RadarServiceImpl;
import com.radar.UI.Components.JComoBoxForHealth;
import com.radar.UI.Components.LoadingPanel;

@SuppressWarnings("serial")
public class FaultForecastIndex extends ContentPanel {

	/**
	 * 故障预测首页信息
	 */
	private JLabel lableForRadar;
	private JComoBoxForHealth radarName;
	private JLabel labelForEquip;
	@SuppressWarnings("rawtypes")
	private DefaultComboBoxModel mode = null;
	private String[] resultData = {"——未选择——"};
	@SuppressWarnings("rawtypes")
	private JComboBox equipName;
	private JButton startButton;
	private JSeparator separator;
	private JLabel warning;
	private LoadingPanel loading;
	private JLabel font;
	private FaultForecast faultForecast;
	
	public FaultForecastIndex() {
	initUI();
	Action();
		
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initUI() {
		lableForRadar = new JLabel("请选择评估的雷达:");
		lableForRadar.setFont(new Font("宋体", Font.PLAIN, 14));
		lableForRadar.setBounds(40, 20, 141, 21);
		panel.add(lableForRadar);
		//雷达下拉框
		radarName = new JComoBoxForHealth(88, 20);          
		radarName.setBounds(185, 20, 110, 21);
		panel.add(radarName);
		
		labelForEquip = new JLabel("部件：");
		labelForEquip.setFont(new Font("宋体", Font.PLAIN, 14));
		labelForEquip.setBounds(305, 20, 141, 21);
		panel.add(labelForEquip);
		
		equipName = new JComboBox();			
		mode = new DefaultComboBoxModel(resultData);
		equipName.setModel(mode);
		equipName.setBounds(348, 20, 110, 21);
		panel.add(equipName);
		
		startButton = new JButton("开始评估");
		startButton.setFont(new Font("宋体", Font.PLAIN, 14));
		startButton.setBounds(485, 20, 97, 21);
		panel.add(startButton);
		
		//分割线
		separator = new JSeparator();
		separator.setBounds(10, 50, 590, 2);	
		panel.add(separator);
		
		warning = new JLabel("暂无数据，请重新选择！");
		warning.setFont(new Font("宋体", Font.PLAIN, 20));
		warning.setBounds(201, 234, 259, 36);
		warning.setVisible(false);
		panel.add(warning);
	}
	
	public void Action() {	
	radarName.addActionListener(new ActionListener() {
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public void actionPerformed(ActionEvent e) {
			String Rname=radarName.getSelectedItem().toString();
			RadarServiceImpl radarServiceImpl = (RadarServiceImpl) SpringUtil.getBean("RadarServiceImpl"); 
			List<Object>  Ename = radarServiceImpl.getequipNameByRadar(Rname);
			resultData = new String[Ename.size()];						
			for(int i=0;i<Ename.size();i++) {	
				resultData[i] = (String) Ename.get(i);
			}
			mode = new DefaultComboBoxModel(resultData);
			equipName.setModel(mode);
			if(Ename.size()>0) {
			equipName.setSelectedIndex(0); //切换时默认选中
			}
		}
	});
	
	startButton.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			String Rname=radarName.getSelectedItem().toString();
			RadarServiceImpl radarServiceImpl = (RadarServiceImpl) SpringUtil.getBean("RadarServiceImpl"); 
			int num = radarServiceImpl.countDynamic(Rname); //后续会修改成根据equipid or name 查找
			if(num==0) {
			warning.setVisible(true);	
			}else {			
			warning.setVisible(false);	
			loading = new LoadingPanel();
			loading.setBounds(60, 60,497,336);
			panel.add(loading);
			font = new JLabel("数据分析中...");
			font.setBounds(203, 152, 100, 20);		
			font.setFont(new Font("宋体", Font.PLAIN, 14));
			loading.add(font);
			//完成数据分析后实现页面跳转，目前先使用延时
			javax.swing.Timer timer = new javax.swing.Timer(5000, new ActionListener() {
				public void actionPerformed(ActionEvent e) {     
					panel.remove(lableForRadar);
					panel.remove(radarName);
					panel.remove(labelForEquip);
					panel.remove(equipName);
					panel.remove(separator);
					panel.remove(startButton);
					panel.remove(loading);
					String Ename=equipName.getSelectedItem().toString();
					faultForecast = new FaultForecast(Ename);
					faultForecast.setBounds(-20,-20,610,500);
					panel.add(faultForecast);
					repaint();   
					}				
				});
			timer.setRepeats(false);
			timer.start();
			}
		}
	});
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
}
