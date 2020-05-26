package com.radar.UI.ContentPanel;

import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.SwingWorker;

import com.radar.SpringUtil;
import com.radar.Entity.Manager;
import com.radar.ServiceImpl.ManagerServiceImpl;
import com.radar.UI.Components.BottomButtonForTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.ExecutionException;

@SuppressWarnings("serial")
public class NewManager extends ContentPanel {
	
	/**
	 * 新建部队内容面板
	 * @author lhy
	 */
	
	private JLabel labelForTitle;
	private JTextField managerLocation;
	private JSeparator separator;
	private JLabel labelForManagerName;
	private JLabel labelForManagerLocation;
	private BottomButtonForTable submit;
	private BottomButtonForTable cancle;
	private JTextField managerName;
	
	public NewManager() {		
		initUI();
		Action();
	}
	
	private void initUI() {
		setLabelForTitle();
		panel.add(labelForTitle);
		
		setSeparator();		
		panel.add(separator);
		
		setLabelForManagerName();		
		panel.add(labelForManagerName);
		
		setLabelForMananger();
		
		setLabelForManagerLocation();		
		panel.add(labelForManagerLocation);
		
//		setTypes();
		
		setManagers();
		
		setManagerLocation();
		managerLocation.setColumns(10);
		
		
		setManagerName();
		managerName.setColumns(10);
		
		
		setButtoms();		
		panel.add(submit);		
		panel.add(cancle);
		
		

	}
	
	private void Action() {
		//提交按钮事件，新建部队信息
		submit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Manager r = new Manager();
		            r.setManagerStatus(0);
					r.setManagerName(managerName.getText());
					r.setManagerLocation(managerLocation.getText());
//					if(types.getSelectedItem().toString() == "I型雷达" 
//							|| types.getSelectedItem().toString().equals("I型雷达"))
//						type = 0;
//					else 
//						type = 1;
//					r.setType(type);
//					r.setRadarStatus(0);
//					String name = managerLocation.getText();
//					r.setRadarName(name);
//					List<Manager> ms = managers.getManager();
//					for(int i=0;i<ms.size();i++) {
//						if(ms.get(i).getManagerName() == managers.getSelectedItem().toString() 
//								||ms.get(i).getManagerName().equals(managers.getSelectedItem().toString())) {
//							r.setManagerId(ms.get(i));
//							break;
//						}
//					}
					SwingWorkerForNewManager s = new SwingWorkerForNewManager();
					s.setManager(r);
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

	private void setManagerLocation() {
		managerLocation = new JTextField();
		managerLocation.setBounds(210, 216, 157, 28);
		panel.add(managerLocation);
	}
	
	private void setManagerName() {
		managerName = new JTextField();
		managerName.setBounds(210, 114, 157, 28);
		panel.add(managerName);
	}
    
	private void setManagers() {
	}

	private void setLabelForManagerLocation() {
		labelForManagerLocation = new JLabel("部队所在位置：");
		labelForManagerLocation.setFont(new Font("宋体", Font.PLAIN, 16));
		labelForManagerLocation.setBounds(20, 218, 120, 21);
	}

	private void setLabelForMananger() {
	}

	private void setLabelForManagerName() {
		labelForManagerName = new JLabel("部队代号：");
		labelForManagerName.setFont(new Font("宋体", Font.PLAIN, 16));
		labelForManagerName.setBounds(20, 116, 120, 21);
	}

	private void setSeparator() {
		separator = new JSeparator();
		separator.setBounds(10, 50, 590, 2);
	}

	private void setLabelForTitle() {
		labelForTitle = new JLabel("新建部队信息");
		labelForTitle.setFont(new Font("宋体", Font.PLAIN, 20));
		labelForTitle.setBounds(20, 17, 120, 26);
	}
	
	class SwingWorkerForNewManager extends SwingWorker<Boolean,Void>{
		private Manager manager;		

		@SuppressWarnings("static-access")
		@Override
		protected Boolean doInBackground() throws Exception {
			Boolean result = false;
			SpringUtil s = new SpringUtil();
			ManagerServiceImpl managerServiceImpl = (ManagerServiceImpl) s.getBean("ManagerServiceImpl");
			result = managerServiceImpl.addManager(getManager());
			return result;
		}
		
		@Override
		protected void done() {
			try {
				Boolean addResult = get();
				if(addResult) {
					JOptionPane.showMessageDialog(null,"新建部队成功","提示",JOptionPane.PLAIN_MESSAGE);					
				}					
				else {
					JOptionPane.showMessageDialog(null,"新建部队失败","提示",JOptionPane.PLAIN_MESSAGE);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}

		public Manager getManager() {
			return this.manager;
		}
		public void setManager(Manager manager) {
			this.manager = manager;
		}
	}
}

