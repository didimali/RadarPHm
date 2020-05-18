package com.radar.UI.ContentPanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JSeparator;

import com.radar.SpringUtil;
import com.radar.ServiceImpl.RadarServiceImpl;
import com.radar.UI.Components.JComoBoxForHealth;
import com.radar.UI.Components.LoadingPanel;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class HealthAssessIndex extends ContentPanel {

	/**
	 * 健康评估首页信息
	 */
	private JLabel lableForRadar;
	private JComoBoxForHealth radarName;
	private JSeparator separator;
	private JButton startButton;
	private LoadingPanel loading;
	private JLabel font;
	private HealthAssess healthAssess;
	private JLabel warning;
	
	public HealthAssessIndex() {
		initUI();
		Action();
		
	}
	private void initUI() {
		lableForRadar = new JLabel("请选择评估的雷达:");
		lableForRadar.setFont(new Font("宋体", Font.PLAIN, 14));
		lableForRadar.setBounds(40, 20, 141, 21);
		panel.add(lableForRadar);
		//雷达下拉框
		radarName = new JComoBoxForHealth(88, 20);          
		radarName.setBounds(185, 20, 147, 21);
		panel.add(radarName);
		
		//分割线
		separator = new JSeparator();
		separator.setBounds(10, 50, 590, 2);	
		panel.add(separator);
		
		startButton = new JButton("开始评估");
		startButton.setFont(new Font("宋体", Font.PLAIN, 14));
		startButton.setBounds(400, 20, 141, 21);
		panel.add(startButton);
		
		warning = new JLabel("暂无数据，请重新选择！");
		warning.setFont(new Font("宋体", Font.PLAIN, 20));
		warning.setBounds(201, 234, 259, 36);
		warning.setVisible(false);
		panel.add(warning);
		
		
	}

	private void Action() {
	startButton.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			String name=radarName.getSelectedItem().toString();
			RadarServiceImpl radarServiceImpl = (RadarServiceImpl) SpringUtil.getBean("RadarServiceImpl"); 
			int num = radarServiceImpl.countDynamic(name);
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
					panel.remove(separator);
					panel.remove(startButton);
					panel.remove(loading);
					String name=radarName.getSelectedItem().toString();
					healthAssess = new HealthAssess(name);
					healthAssess.setBounds(-20,-20,610,500);
					panel.add(healthAssess);
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
