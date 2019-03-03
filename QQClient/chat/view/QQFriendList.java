/***
 * 好友列表界面
 * 1、卡片布局；
 * 2、我的好友卡片，BorderLayout布局;
 * 2、北部,我的好友是JButton；
 * 2、中间是JScrollPane，放了一个JPanel;
 * 2、布局是GridLayout;
 * 3、南面是JPanel,也是GridLayout布局（2,1），放入两个按钮
 */
package chat.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import chat.tools.*;

import javax.swing.*;
import com.qq.common.*;


public class QQFriendList extends JFrame implements ActionListener,MouseListener{
	
	CardLayout cl;
	//第一张卡片
	JPanel fulljp,friendjp,btlistjp;
	JScrollPane jsp;
	JButton full_button1,full_button2,full_button3;
	
	//第二章卡片
	JPanel jpmsr1,jpmsr2,jpmsr3;
	JScrollPane jsp2;
	JButton msr_button1,msr_button2,msr_button3;
	
	String owner;
	JLabel []jbls;

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		QQFriendList fl = new QQFriendList();
//	}
	
	//更新在线的好友情况
	public void updateFriend(Message m)
	{
		String onlineFriend[] = m.getContext().split(" ");
		
		for(int i =0;i<onlineFriend.length;i++)
		{
			jbls[Integer.parseInt(onlineFriend[i])-1].setEnabled(true);
		}
	}
	
	
	public QQFriendList(String ownerId)
	{
		this.owner = ownerId;
		//第一张卡片
		fulljp = new JPanel(new BorderLayout());
		full_button1 = new JButton("我的好友");
		full_button2 = new JButton("陌生人");
		full_button2.addActionListener(this);
		full_button3 = new JButton("黑名单");
		full_button3.addActionListener(this);
		
		//我的好友块
		friendjp = new JPanel(new GridLayout(50,1,4,4));
		//给我的好友，初始化50好友
		jbls = new JLabel[50];
		for(int i=0;i<jbls.length;i++)
		{
			jbls[i]=new JLabel(i+1+"",new ImageIcon("src\\images\\mm.png"),JLabel.LEFT);
			jbls[i].setEnabled(false);
			if(jbls[i].getText().equals(ownerId))
			{
				jbls[i].setEnabled(true);
			}
			jbls[i].addMouseListener(this);
			friendjp.add(jbls[i]);
		}
		
		jsp = new JScrollPane(friendjp);
		
		btlistjp = new JPanel(new GridLayout(2,1));
		btlistjp.add(full_button2);
		btlistjp.add(full_button3);
		
		
		fulljp.add(full_button1,"North");
		fulljp.add(jsp,"Center");
		fulljp.add(btlistjp,"South");
		
		//处理第二张卡片
		jpmsr1 = new JPanel(new BorderLayout());
		msr_button1 = new JButton("我的好友");
		msr_button1.addActionListener(this);
		msr_button2 = new JButton("陌生人");
		msr_button3 = new JButton("黑名单");
		msr_button3.addActionListener(this);
		jpmsr2 = new JPanel(new GridLayout(50,1,4,4));
		//给陌生人，初始化50个
		JLabel []jbls2 = new JLabel[50];
		for(int i=0;i<jbls2.length;i++)
		{
			jbls2[i]=new JLabel(i+1+"",new ImageIcon("src\\images\\mm.png"),JLabel.LEFT);
			jpmsr2.add(jbls2[i]);
		}
		
		jsp2 = new JScrollPane(jpmsr2);
		
		jpmsr2 = new JPanel(new GridLayout(2,1));
		jpmsr2.add(msr_button1);
		jpmsr2.add(msr_button2);
		
		jpmsr1.add(jpmsr2,"North");
		jpmsr1.add(jsp2,"Center");
		jpmsr1.add(msr_button3,"South");
		
		
		this.setSize(200,400);
		cl = new CardLayout();
		this.setLayout(cl);
		this.add(fulljp,"1");
		this.add(jpmsr1,"2");
		this.setTitle(ownerId);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==full_button2)
		{
			//不能直接对this监控，要调用getContentPane()
			cl.show(this.getContentPane(), "2");
		}else if(arg0.getSource()==msr_button1)
		{
			cl.show(this.getContentPane(), "1");
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//用户双击，并得到好友的编号
		if(arg0.getClickCount()==2)
		{
			//得到好友的编号
			String friendNo = ((JLabel)arg0.getSource()).getText();
			//System.out.println("你希望和"+friendNo+"聊天");
			//this.owner和owner的区别
			ChatView cv = new ChatView(this.owner,friendNo);
			//把聊天界面，加入到管理类
			ManageQQChatView.addQQChat(this.owner+" "+friendNo, cv);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		JLabel jl = (JLabel)arg0.getSource();
		jl.setForeground(Color.red);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		JLabel jl = (JLabel)arg0.getSource();
		jl.setForeground(Color.black);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
