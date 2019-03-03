package com.qq.server.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.qq.server.model.*;

public class MyServerFrame extends JFrame implements ActionListener{

	JPanel jp1;
	JButton startserverjb,closeserverjb;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyServerFrame msf = new MyServerFrame();
	}
	
	public MyServerFrame()
	{
		jp1 = new JPanel();
		startserverjb = new JButton("启动服务器");
		startserverjb.addActionListener(this);
		closeserverjb = new JButton("关闭服务器");
		closeserverjb.addActionListener(this);
		jp1.add(startserverjb);
		jp1.add(closeserverjb);
		
		this.add(jp1);
		this.setSize(400,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==startserverjb)
		{
			new MyQQServer();
		}
	}
	
	

}
