package com.radar.UI.Components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class BottomButtonForTable extends JLabel{
	
	/* 此按钮类为表格的换页设计 
	 * @param
	 * text: 标签文本
	 * x,y: 标签位置坐标
	 * width:宽度
	 * height:高度
	 * fontSize:字体大小*/

	public BottomButtonForTable(String text,int x,int y,int width,int height,int fontSize) {
		initUI(text,x,y,width,height,fontSize);
		Actions();
	}
	
	//监听事件
	private void Actions() {
		this.addMouseMotionListener(new MouseMotionAdapter() {
			//鼠标悬停按钮变色
			@Override
			public void mouseMoved(MouseEvent e) {
				setBackground(new Color(0, 153, 255));
			}
		});
		this.addMouseListener(new MouseAdapter() {
			//鼠标离开按钮颜色恢复
			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(new Color(102, 204, 255));
			}
		});
			}

	//UI初始化
	private void initUI(String text,int x,int y, int width, int height, int fontSize) {
		setText(text);
		setFont(new Font("宋体", Font.PLAIN, fontSize));
		setOpaque(true);
		setBackground(new Color(102, 204, 255));
		setHorizontalAlignment(SwingConstants.CENTER);
		setBounds(x, y, width, height);
		//setBounds(x, y, 50, 24);
	}

}
