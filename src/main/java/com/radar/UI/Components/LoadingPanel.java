package com.radar.UI.Components;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class LoadingPanel extends JPanel {
	
	private Timer timer;
	//定时器启动延迟时间
	private int delay = 5;
	private int x = 0;
	private JTable table;

	//无参实例化
	public LoadingPanel() {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		table = new JTable();
		table.setBounds(105, 91, 40, 20);
		add(table);
		timer = new Timer(delay, new ReboundListener());
		timer.start();
	}
	
	//当java认为需要重新绘制组件的时候由java调用paintComponent(),
	//例如在程序中repaint();或者程序窗口最小化，然后恢复。或者程序窗口被遮挡，又显现的时候。

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawArc(g);
	}
	
	private void drawArc(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		//抗锯齿 
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int width = getWidth();
		int height = getHeight();
		//设置画笔颜色
		g2d.setColor(Color.WHITE);
		g2d.drawArc(width / 2 - 110, height / 2 - 110, 20 + 200, 20 + 200, 0, 360);
		g2d.setColor(new Color(0, 153, 255, 255));
		g2d.fillArc(width / 2 - 110, height / 2 - 110, 10 + 200, 10 + 200, x, 120);
		g2d.setColor(Color.WHITE);
		g2d.fillArc(width / 2 - 110, height / 2 - 110, 10 + 190, 10 + 190, 0, 360);
		g2d.dispose();
	}
	
	private class ReboundListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
				if (x < 360) {
					//控制每个DELAY周期旋转的角度，+ 为逆时针  - 为顺时针
					x = x + 5;
				} else {
					x = 0;
				}
				repaint();
			}
		}
}
