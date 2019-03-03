/***
 * ��¼����
 */
package chat.view;

import javax.swing.*;

import com.qq.common.User;

import java.awt.*;
import java.awt.event.*;
import chat.model.*;
import chat.tools.ManageQQFriendList;
import chat.tools.ManagerClientConServerThread;

import com.qq.common.*;
import java.io.*;
import com.qq.common.*;

public class Login extends JFrame implements ActionListener{

	JPanel iconjp;
	JButton loginjb,canceljb;
	JTabbedPane jtp;
	JPanel qqjp,phonejp,mailjp,buttonjp;
	JLabel qqnamejl,qqpwdjl,forgetjl,savepwdjl,clearjl;
	JTextField qqnamejtf;
	JPasswordField qqpwdjtf;
	JCheckBox hidejcb,remjcb;
	JLabel phonenum,phonepwd;
	JButton phonepwdjb;
	JTextField phonenumjtf,phonepwdjtf;
	JLabel mailnum,mailpwd;
	JTextField mailnumjtf,mailpwdjtf;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Login l = new Login();
	}
	
	public Login()
	{
		
		
		//qq�������
		qqjp = new JPanel(new GridLayout(3,3));
		qqnamejl = new JLabel("QQ����",JLabel.CENTER);
		qqpwdjl = new JLabel("QQ����",JLabel.CENTER);
		forgetjl = new JLabel("��������");
		qqnamejtf = new JTextField();
		//qqnamejtf.setSize(new Dimension(10,5));
		qqpwdjtf = new JPasswordField();
		clearjl = new JLabel("�������");
		hidejcb = new JCheckBox("�����¼");
		remjcb = new JCheckBox("��ס����");
		savepwdjl = new JLabel("�������뱣��");
		
		qqjp.add(qqnamejl);
		qqjp.add(qqnamejtf);
		qqjp.add(clearjl);
		qqjp.add(qqpwdjl);
		qqjp.add(qqpwdjtf);
		qqjp.add(forgetjl);
		qqjp.add(hidejcb);
		qqjp.add(remjcb);
		qqjp.add(savepwdjl);
		
		//�ֻ��������
		phonenum  = new JLabel("�ֻ�����",JLabel.CENTER);
		phonepwd  = new JLabel("�ֻ���֤��",JLabel.CENTER);
		phonenumjtf = new JTextField();
		phonepwdjtf = new JTextField();
		phonepwdjb = new JButton("��ȡ��֤��");
		phonejp = new JPanel(new GridLayout(2,3,0,15));
		phonejp.add(phonenum);
		phonejp.add(phonenumjtf);
		phonejp.add(phonepwdjb);
		phonejp.add(phonepwd);
		phonejp.add(phonepwdjtf);
		
		//�����������
		mailnum = new JLabel("�����",JLabel.CENTER);
		mailpwd = new JLabel("��������",JLabel.CENTER);
		mailnumjtf = new JTextField();
		mailpwdjtf = new JTextField();
		mailjp = new JPanel(new GridLayout(2,2,0,10));
		mailjp.add(mailnum);
		mailjp.add(mailnumjtf);
		mailjp.add(mailpwd);
		mailjp.add(mailpwdjtf);
		
		//��ťjpanel
		loginjb = new JButton("��¼");
		loginjb.addActionListener(this);
		canceljb = new JButton("ȡ��");
		canceljb.addActionListener(this);
		buttonjp = new JPanel();
		buttonjp.add(loginjb);
		buttonjp.add(canceljb);
		this.add(buttonjp,"South");
		
		//����ѡ�����
		jtp = new JTabbedPane();
		jtp.add("QQ����",qqjp);
		jtp.add("�ֻ�����",phonejp);
		jtp.add("��������",mailjp);
		this.add(jtp,"Center");
		
		this.setSize(400,200);
		this.setTitle("�����¼����");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
//		if(arg0.getSource()==loginjb)
//		{
//			if(qqnamejtf.getText().length()==0 || qqpwdjtf.getText().length()==0)
//			{
//				JOptionPane.showMessageDialog(null, "�������˺Ż�����");
//			}else {
//				QQLoginModel lm = new QQLoginModel();
//				String userid = qqnamejtf.getText().toString();
//				String pwd = qqpwdjtf.getText().toString();
//				String qqname=lm.checkUser(userid, pwd);
//				if(qqname.length()!=0)
//				{
//					//new chatview();
//					System.out.println("��¼�ɹ�");
//				}else {
//					JOptionPane.showMessageDialog(null, "�������˺�");
//				}
//			}
//			
//		}
		if(arg0.getSource()==loginjb)
		{
			ClientUser cu =new ClientUser();
			User u = new User();
			u.setUserId(qqnamejtf.getText().trim());
			u.setPwd(new String(qqpwdjtf.getPassword()));
			System.out.println("��ȡ�˺�����");
			boolean b = cu.checkUser(u);
			System.out.println(b);
			if(b)
			{
				
				try {
					//�Ѵ��������б�������ǰ
					QQFriendList qfl = new QQFriendList(u.getUserId());
					ManageQQFriendList.addQQFriendList(u.getUserId(), qfl);
					
					//����һ��Ҫ�󷵻����ߺ��ѵ������
					ObjectOutputStream oos = new ObjectOutputStream(
							ManagerClientConServerThread.getClientconSeverThread(u.getUserId()).getS().getOutputStream());
					//��һ��Message
					Message m  = new Message();
					m.setMesType(MessageType.message_get_onlineFriend);
					//ָ����Ҫ�������QQ�ŵĺ����������˿���һ�����Ե�¼������QQ��
					m.setSender(u.getUserId());
					oos.writeObject(m);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				this.dispose();
				
			}else {
				JOptionPane.showMessageDialog(null, "�û����������");
			}
		}else if(arg0.getSource()==canceljb){
			this.dispose();
		}
	}

}
