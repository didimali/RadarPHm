package com.radar.UI;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.springframework.stereotype.Component;

@SuppressWarnings("serial")
@Component("SystemEntrance")
public class SystemEntrance extends JFrame{
	
	public SystemEntrance() {
		
	}
	public void initUI() {
		//调用Swing皮肤psg
    	try{
	   	  UIManager.setLookAndFeel("com.pagosoft.plaf.PgsLookAndFeel");
	   	  SwingUtilities.updateComponentTreeUI(this);
   	    }
   	    catch(Exception e)
   	    {
   	    	System.out.println(e);
   	    }
       	setTitle("雷达PHM系统-首页");
       	//设置窗口大小
       	setSize(806, 630);
       	//设置窗口屏幕居中
       	setLocationRelativeTo(null);
       	//关闭窗口即退出程序
       	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       	//窗口不可以拉伸放缩
       	setResizable(false);
       	Home home = new Home();
        // 添加面板
        add(home);
        // 设置界面可见
        setVisible(true);
	    }

}
