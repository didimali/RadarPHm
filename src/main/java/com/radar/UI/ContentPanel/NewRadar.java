package com.radar.UI.ContentPanel;

import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import com.radar.SpringUtil;
import com.radar.Entity.Manager;
import com.radar.Entity.Radar;
import com.radar.ServiceImpl.RadarServiceImpl;
import com.radar.UI.Components.BottomButtonForTable;
import com.radar.UI.Components.JComoBoxForManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.concurrent.ExecutionException;

@SuppressWarnings("serial")
public class NewRadar extends ContentPanel {
	
	/**
	 * 新建雷达内容面板
	 * @author madi
	 */
	
	private JLabel labelForTitle;
	private JTextField radarName;
	private JSeparator separator;
	private JLabel labelForType;
	private JLabel labelForMananger;
	private JLabel labelForRadarName;
	@SuppressWarnings("rawtypes")
	private JComboBox types;
	private JComoBoxForManager managers;
	private BottomButtonForTable submit;
	private BottomButtonForTable cancle;
	
	public NewRadar() {		
		initUI();
		Action();
	}
	
	private void initUI() {
		setLabelForTitle();
		panel.add(labelForTitle);
		
		setSeparator();		
		panel.add(separator);
		
		setLabelForType();		
		panel.add(labelForType);
		
		setLabelForMananger();		
		panel.add(labelForMananger);
		
		setLabelForRadarName();		
		panel.add(labelForRadarName);
		
		setTypes();		
		panel.add(types);
		
		setManagers();		
		panel.add(managers);
		
		setRadarName();
		radarName.setColumns(10);
		
		setButtoms();		
		panel.add(submit);		
		panel.add(cancle);
	}
	
	private void Action() {
		//提交按钮事件，新建雷达信息
		submit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Radar r = new Radar();
					Integer type;
					if(types.getSelectedItem().toString() == "I型雷达" 
							|| types.getSelectedItem().toString().equals("I型雷达"))
						type = 0;
					else 
						type = 1;
					r.setType(type);
					r.setRadarStatus(0);
					String name = radarName.getText();
					r.setRadarName(name);
					List<Manager> ms = managers.getManager();
					for(int i=0;i<ms.size();i++) {
						if(ms.get(i).getManagerName() == managers.getSelectedItem().toString() 
								||ms.get(i).getManagerName().equals(managers.getSelectedItem().toString())) {
							r.setManagerId(ms.get(i));
							break;
						}
					}
					SwingWorkerForNewRadar s = new SwingWorkerForNewRadar();
					s.setRadar(r);
					s.execute();
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
	}

	private void setButtoms() {
		submit = new BottomButtonForTable("提交",223,420,70,28,14);
		cancle = new BottomButtonForTable("取消",315,420,70,28,14);
	}

	private void setRadarName() {
		radarName = new JTextField();
		radarName.setBounds(210, 216, 157, 25);
		panel.add(radarName);
	}

	private void setManagers() {		
		managers = new JComoBoxForManager(210, 151);
		managers.setSize(157, 25);		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setTypes() {
		types = new JComboBox();
		DefaultComboBoxModel typesMode;		
		String[] typesData = {"I型雷达","II型雷达"};
		typesMode = new DefaultComboBoxModel(typesData);
		types.setModel(typesMode);
		types.setBounds(210, 84, 157, 25);		
	}

	private void setLabelForRadarName() {
		labelForRadarName = new JLabel("雷达编号：");
		labelForRadarName.setFont(new Font("宋体", Font.PLAIN, 16));
		labelForRadarName.setBounds(20, 216, 120, 21);
	}

	private void setLabelForMananger() {
		labelForMananger = new JLabel("雷达所属部队：");
		labelForMananger.setFont(new Font("宋体", Font.PLAIN, 16));
		labelForMananger.setBounds(20, 151, 120, 21);
	}

	private void setLabelForType() {
		labelForType = new JLabel("雷达型号：");
		labelForType.setFont(new Font("宋体", Font.PLAIN, 16));
		labelForType.setBounds(20, 86, 120, 21);
	}

	private void setSeparator() {
		separator = new JSeparator();
		separator.setBounds(10, 50, 590, 2);
	}

	private void setLabelForTitle() {
		labelForTitle = new JLabel("新建雷达信息");
		labelForTitle.setFont(new Font("宋体", Font.PLAIN, 20));
		labelForTitle.setBounds(20, 17, 120, 26);
	}
	
	class SwingWorkerForNewRadar extends SwingWorker<Boolean,Void>{
		private Radar radar;		

		@SuppressWarnings("static-access")
		@Override
		protected Boolean doInBackground() throws Exception {
			Boolean result = false;
			SpringUtil s = new SpringUtil();
			RadarServiceImpl radarServiceImpl = (RadarServiceImpl) s.getBean("RadarServiceImpl");
			result = radarServiceImpl.addRadar(getRadar());
			return result;
		}
		
		@Override
		protected void done() {
			try {
				Boolean addResult = get();
				if(addResult) {
					JOptionPane.showMessageDialog(null,"新建雷达成功","提示",JOptionPane.PLAIN_MESSAGE);					
				}					
				else {
					JOptionPane.showMessageDialog(null,"新建雷达失败","提示",JOptionPane.PLAIN_MESSAGE);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}

		public Radar getRadar() {
			return this.radar;
		}
		public void setRadar(Radar radar) {
			this.radar = radar;
		}
	}
	
}
