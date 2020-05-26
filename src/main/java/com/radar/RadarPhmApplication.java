package com.radar;

import javax.swing.SwingUtilities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.radar.UI.SystemEntrance;

@SpringBootApplication
public class RadarPhmApplication{

		public static void main(String[] args) {

			System.setProperty("java.awt.headless", "false");
	    	SpringApplication.run(RadarPhmApplication.class, args);	   
	    	// 启动完成,调用自定义方法,在EDT启动主页面
	    	 SwingUtilities.invokeLater(new Runnable() {
	             public void run() {
	            	 try {
	            		 SystemEntrance systemEntrance = new SystemEntrance();
	 	     	    	systemEntrance.initUI();
	            	 }
	            	 catch(Exception e) {
	            		 e.printStackTrace();
	            	 }
	             }
	         });
	    }
	

}
