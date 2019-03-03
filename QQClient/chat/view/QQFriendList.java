/***
 * �����б����
 * 1����Ƭ���֣�
 * 2���ҵĺ��ѿ�Ƭ��BorderLayout����;
 * 2������,�ҵĺ�����JButton��
 * 2���м���JScrollPane������һ��JPanel;
 * 2��������GridLayout;
 * 3��������JPanel,Ҳ��GridLayout���֣�2,1��������������ť
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
	//��һ�ſ�Ƭ
	JPanel fulljp,friendjp,btlistjp;
	JScrollPane jsp;
	JButton full_button1,full_button2,full_button3;
	
	//�ڶ��¿�Ƭ
	JPanel jpmsr1,jpmsr2,jpmsr3;
	JScrollPane jsp2;
	JButton msr_button1,msr_button2,msr_button3;
	
	String owner;
	JLabel []jbls;

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		QQFriendList fl = new QQFriendList();
//	}
	
	//�������ߵĺ������
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
		//��һ�ſ�Ƭ
		fulljp = new JPanel(new BorderLayout());
		full_button1 = new JButton("�ҵĺ���");
		full_button2 = new JButton("İ����");
		full_button2.addActionListener(this);
		full_button3 = new JButton("������");
		full_button3.addActionListener(this);
		
		//�ҵĺ��ѿ�
		friendjp = new JPanel(new GridLayout(50,1,4,4));
		//���ҵĺ��ѣ���ʼ��50����
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
		
		//����ڶ��ſ�Ƭ
		jpmsr1 = new JPanel(new BorderLayout());
		msr_button1 = new JButton("�ҵĺ���");
		msr_button1.addActionListener(this);
		msr_button2 = new JButton("İ����");
		msr_button3 = new JButton("������");
		msr_button3.addActionListener(this);
		jpmsr2 = new JPanel(new GridLayout(50,1,4,4));
		//��İ���ˣ���ʼ��50��
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
			//����ֱ�Ӷ�this��أ�Ҫ����getContentPane()
			cl.show(this.getContentPane(), "2");
		}else if(arg0.getSource()==msr_button1)
		{
			cl.show(this.getContentPane(), "1");
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//�û�˫�������õ����ѵı��
		if(arg0.getClickCount()==2)
		{
			//�õ����ѵı��
			String friendNo = ((JLabel)arg0.getSource()).getText();
			//System.out.println("��ϣ����"+friendNo+"����");
			//this.owner��owner������
			ChatView cv = new ChatView(this.owner,friendNo);
			//��������棬���뵽������
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
