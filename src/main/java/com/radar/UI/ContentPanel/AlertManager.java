package com.radar.UI.ContentPanel;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.radar.SpringUtil;
import com.radar.Entity.Manager;
import com.radar.ServiceImpl.ManagerServiceImpl;
import com.radar.UI.Components.BottomButtonForTable;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import java.awt.Font;
import java.awt.Image;
import java.util.concurrent.ExecutionException;

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

@SuppressWarnings("serial")
public class AlertManager extends JFrame {

	private JPanel contentPane;
	
	private JLabel title;
	private JLabel labelForManagerLocation;
	private JLabel labelForManagerName;
	private JTextField managerName;
	private JTextField managerLocation;
	
	private BottomButtonForTable submit;
	private BottomButtonForTable cancle;
	
	private Integer managerId;
	private String managerName1;
	private String managerLocation1;
	
	private Manager m;
	
	//添加一个标志位组件，暴露在父组件中，监听雷达信息更新是否完成
	private JLabel updateFinished = new JLabel("no");
	
	

	/**
	 * 部队修改信息面板
	 * @param managerLocation1 ：部队位置
	 * @param managerName ：部队代号 
	 * @param radarId  ：雷达id
	 */
	public AlertManager(Integer radarId, String managerName1, String managerLocation1) {
		initData(radarId,managerName1,managerLocation1);
		initUI();
		Action();
       
	}

	private void initData(Integer radarId, String managerName, String managerLocation) {
		this.managerId = radarId;
		this.managerName1 = managerName;
		this.managerLocation1 = managerLocation;
		
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
					
		
		setManagerLocation();
		contentPane.add(labelForManagerLocation);
		contentPane.add(managerLocation);
		
		setManagerName();
		contentPane.add(labelForManagerName);
		contentPane.add(managerName);
		
		setButtons();
		contentPane.add(submit);
		contentPane.add(cancle);
		
	
//		managerName = new JTextField();
//		managerName.setBounds(133, 82, 99, 21);
//		contentPane.add(managerName);
//		managerName.setColumns(10);
	}
	private void setTitle() {
		title = new JLabel("修改部队信息");
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

	private void setManagerLocation() {
		labelForManagerLocation = new JLabel("部队位置：");
		labelForManagerLocation.setFont(new Font("宋体", Font.PLAIN, 14));
		labelForManagerLocation.setBounds(40, 125, 84, 18);
		
		managerLocation = new JTextField();
		managerLocation.setText(managerLocation1);
		managerLocation.setBounds(134, 123, 98, 24);
	}

	private void setManagerName() {
		labelForManagerName = new JLabel("部队名称：");
		labelForManagerName.setFont(new Font("宋体", Font.PLAIN, 14));
		labelForManagerName.setBounds(40, 83, 84, 18);
		
		managerName = new JTextField();
		managerName.setText(managerName1);
		managerName.setBounds(133, 82, 99, 21);
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
					if(managerName.getText().equals("") || managerLocation.getText().equals(""))
						JOptionPane.showMessageDialog(null,"必填项不能为空","警告",JOptionPane.PLAIN_MESSAGE);
					else {
						m = new Manager();
						m.setManagerId(managerId);
						m.setManagerStatus(0);
						m.setManagerName(managerName.getText());
						m.setManagerLocation(managerLocation.getText());
						dispose();
						//异步更新部队信息
						new SwingWorkerForAlertManager().execute();
					}					
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

	class SwingWorkerForAlertManager extends SwingWorker<Boolean,Void> {

		@SuppressWarnings("static-access")
		@Override
		protected Boolean doInBackground() throws Exception {
			SpringUtil s = new SpringUtil();
			ManagerServiceImpl managerServiceImpl = (ManagerServiceImpl) s.getBean("ManagerServiceImpl");
			Boolean updateResult = managerServiceImpl.updateManager(m);
			return updateResult;
		}
		
		@Override
		protected void done(){
			try {
				Boolean updateResult = get();
				if(updateResult) {
					updateFinished.setText("yes");
					JOptionPane.showMessageDialog(null,"部队信息更新完毕","提示",JOptionPane.PLAIN_MESSAGE);
					
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
