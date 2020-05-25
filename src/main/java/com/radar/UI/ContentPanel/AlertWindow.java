package com.radar.UI.ContentPanel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.radar.SpringUtil;
import com.radar.Entity.Manager;
import com.radar.Entity.Radar;
import com.radar.ServiceImpl.RadarServiceImpl;
import com.radar.UI.Components.BottomButtonForTable;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import java.awt.Font;
import java.awt.Image;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

@SuppressWarnings("serial")
public class AlertWindow extends JFrame {

	private JPanel contentPane;
	
	private JLabel title;
	private JLabel labelForManagers;
	private JLabel labelForType;
	private JLabel labelForRadarName;
	private JTextField radarName;
	
	@SuppressWarnings("rawtypes")
	private JComboBox managers;
	@SuppressWarnings("rawtypes")
	private JComboBox types;
	
	private BottomButtonForTable submit;
	private BottomButtonForTable cancle;
	
	private Integer radarId;
	private String radarName1;
	private String manager;
	private String type;
	private List<Manager> managerData;
	
	private Radar r;
	
	//添加一个标志位组件，暴露在父组件中，监听雷达信息更新是否完成
	private JLabel updateFinished = new JLabel("no");
	

	/**
	 * 雷达修改信息面板
	 * @param managerData ：雷达所属部队下拉框数据
	 * @param type  ：雷达型号
	 * @param manager ：雷达所属部队
	 * @param radarName1 ：雷达编号 
	 * @param radarId  ：雷达id
	 */
	public AlertWindow(Integer radarId, String radarName1, String manager, String type, List<Manager> managerData) {
		initData(radarId,radarName1,manager,type,managerData);
		initUI();
		Action();
       
	}

	private void initData(Integer radarId, String radarName1, String manager, String type, List<Manager> managerData) {
		this.radarId = radarId;
		this.radarName1 = radarName1;
		this.manager = manager;
		this.type = type;
		this.managerData = managerData;
		
	}

	private void initUI() {
		//设置窗体大小
		setSize(299, 281);
       	//设置窗口屏幕居中
       	setLocationRelativeTo(null);
       	//窗口不可以拉伸放缩
       	setResizable(false);
        // 设置界面可见
        setVisible(true);
        
        InputStream inputStream=this.getClass().getResourceAsStream("/static/images/edit1.png") ;
		try {
			BufferedImage bi=ImageIO.read(inputStream);
			Image im=(Image)bi;
			//设置右上角图标
	       	setIconImage(im);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        setContentPane();
		getContentPane().add(contentPane);				
		
		setTitle();
		contentPane.add(title);
		
		setManager();
		contentPane.add(labelForManagers);
		contentPane.add(managers);		
		
		setType();
		contentPane.add(labelForType);
		contentPane.add(types);		
		
		setRadar();
		contentPane.add(labelForRadarName);
		contentPane.add(radarName);
		
		setButtons();
		contentPane.add(submit);
		contentPane.add(cancle);
	}
	private void setTitle() {
		title = new JLabel("修改雷达信息");
		title.setFont(new Font("宋体", Font.BOLD, 18));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(90, 20, 120, 23);
	}

	private void setButtons() {
		submit = new BottomButtonForTable("是",74, 211,60,22,12);
		submit.setLocation(74, 211);
		
		cancle = new BottomButtonForTable("否",155, 211,60,22,12);
		cancle.setLocation(155, 211);
		
	}

	private void setRadar() {
		labelForRadarName = new JLabel("雷达编号：");
		labelForRadarName.setFont(new Font("宋体", Font.PLAIN, 14));
		labelForRadarName.setBounds(54, 167, 70, 18);
		
		radarName = new JTextField();
		radarName.setText(radarName1);
		radarName.setBounds(136, 164, 98, 24);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setType() {
		labelForType = new JLabel("雷达型号：");
		labelForType.setFont(new Font("宋体", Font.PLAIN, 14));
		labelForType.setBounds(54, 120, 70, 18);
		
		types = new JComboBox();
		//下拉框加载数据
		String[] radarType = {"I型雷达","II型雷达"};
		DefaultComboBoxModel mode = new DefaultComboBoxModel(radarType);
		types.setModel(mode);
		for(int i=0;i<radarType.length;i++) {
			if(radarType[i] == this.type || radarType[i].equals(this.type)) {
				types.setSelectedIndex(i);
				break;
			}
		}		
		types.setBounds(136, 117, 98, 24);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setManager() {
		labelForManagers = new JLabel("所属部队：");
		labelForManagers.setFont(new Font("宋体", Font.PLAIN, 14));
		labelForManagers.setBounds(54, 73, 70, 18);
		
		
		managers = new JComboBox();
		managers.setBounds(136, 70, 98, 24);
		//下拉框加载数据
		String[] radarManager = new String[managerData.size()];
		for(int i=0;i<radarManager.length;i++) {
			radarManager[i] = managerData.get(i).getManagerName();
		}
		DefaultComboBoxModel mode = new DefaultComboBoxModel(radarManager);
		managers.setModel(mode);
		for(int i=0;i<radarManager.length;i++) {
			if(radarManager[i] == this.manager || radarManager[i].equals(this.manager)) {
				managers.setSelectedIndex(i);
				break;
			}
		}
	}

	private void setContentPane() {
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBounds(0, 0, 289, 251);
		contentPane.setLayout(null);
	}

	private void Action() {
		//提交按钮事件
		submit.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					r = new Radar();
					r.setRadarId(radarId);
					r.setRadarStatus(0);
					if(types.getSelectedItem().toString() == "I型雷达" || types.getSelectedItem().toString().equals("I型雷达"))
						r.setType(0);
					else
						r.setType(1);
					for(int i=0;i<managerData.size();i++) {
						if(managerData.get(i).getManagerName() == managers.getSelectedItem().toString() 
								||managerData.get(i).getManagerName().equals(managers.getSelectedItem().toString())) {
							r.setManagerId(managerData.get(i));
							break;
						}
					}
					r.setRadarName(radarName.getText());
					dispose();
					//异步更新雷达信息
					new SwingWorkerForAlertWindow().execute();
					
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});		
	}
		
	public JLabel getUpdateFinished() {
		return updateFinished;
	}

	class SwingWorkerForAlertWindow extends SwingWorker<Boolean,Void> {

		@SuppressWarnings("static-access")
		@Override
		protected Boolean doInBackground() throws Exception {
			SpringUtil s = new SpringUtil();
			RadarServiceImpl radarServiceImpl = (RadarServiceImpl) s.getBean("RadarServiceImpl");
			Boolean updateResult = radarServiceImpl.updateRadar(r);
			return updateResult;
		}
		
		@Override
		protected void done(){
			try {
				Boolean updateResult = get();
				if(updateResult) {
					updateFinished.setText("yes");
					JOptionPane.showMessageDialog(null,"雷达信息更新完毕","提示",JOptionPane.PLAIN_MESSAGE);
					
				}
					
				else {
					updateFinished.setText("no");
					JOptionPane.showMessageDialog(null,"更新失败","提示",JOptionPane.PLAIN_MESSAGE);
				}
					
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		
	}

}
