package com.radar.UI;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

//@SuppressWarnings("serial")
//@Component("SystemEntrance")
public class SystemEntrance extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -928078467780229316L;
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
       	InputStream inputStream=this.getClass().getResourceAsStream("/static/images/logo2.png") ;
		try {
			BufferedImage bi=ImageIO.read(inputStream);
			Image im=(Image)bi;
			//设置右上角图标
	       	setIconImage(im);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
