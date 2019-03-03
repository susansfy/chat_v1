/***
 * 登录界面
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
		
		
		//qq号码界面
		qqjp = new JPanel(new GridLayout(3,3));
		qqnamejl = new JLabel("QQ号码",JLabel.CENTER);
		qqpwdjl = new JLabel("QQ密码",JLabel.CENTER);
		forgetjl = new JLabel("忘记密码");
		qqnamejtf = new JTextField();
		//qqnamejtf.setSize(new Dimension(10,5));
		qqpwdjtf = new JPasswordField();
		clearjl = new JLabel("清除号码");
		hidejcb = new JCheckBox("隐身登录");
		remjcb = new JCheckBox("记住密码");
		savepwdjl = new JLabel("申请密码保护");
		
		qqjp.add(qqnamejl);
		qqjp.add(qqnamejtf);
		qqjp.add(clearjl);
		qqjp.add(qqpwdjl);
		qqjp.add(qqpwdjtf);
		qqjp.add(forgetjl);
		qqjp.add(hidejcb);
		qqjp.add(remjcb);
		qqjp.add(savepwdjl);
		
		//手机号码界面
		phonenum  = new JLabel("手机号码",JLabel.CENTER);
		phonepwd  = new JLabel("手机验证码",JLabel.CENTER);
		phonenumjtf = new JTextField();
		phonepwdjtf = new JTextField();
		phonepwdjb = new JButton("获取验证码");
		phonejp = new JPanel(new GridLayout(2,3,0,15));
		phonejp.add(phonenum);
		phonejp.add(phonenumjtf);
		phonejp.add(phonepwdjb);
		phonejp.add(phonepwd);
		phonejp.add(phonepwdjtf);
		
		//电子邮箱界面
		mailnum = new JLabel("邮箱号",JLabel.CENTER);
		mailpwd = new JLabel("邮箱密码",JLabel.CENTER);
		mailnumjtf = new JTextField();
		mailpwdjtf = new JTextField();
		mailjp = new JPanel(new GridLayout(2,2,0,10));
		mailjp.add(mailnum);
		mailjp.add(mailnumjtf);
		mailjp.add(mailpwd);
		mailjp.add(mailpwdjtf);
		
		//按钮jpanel
		loginjb = new JButton("登录");
		loginjb.addActionListener(this);
		canceljb = new JButton("取消");
		canceljb.addActionListener(this);
		buttonjp = new JPanel();
		buttonjp.add(loginjb);
		buttonjp.add(canceljb);
		this.add(buttonjp,"South");
		
		//创建选项卡窗口
		jtp = new JTabbedPane();
		jtp.add("QQ号码",qqjp);
		jtp.add("手机号码",phonejp);
		jtp.add("电子邮箱",mailjp);
		this.add(jtp,"Center");
		
		this.setSize(400,200);
		this.setTitle("聊天登录界面");
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
//				JOptionPane.showMessageDialog(null, "请输入账号或密码");
//			}else {
//				QQLoginModel lm = new QQLoginModel();
//				String userid = qqnamejtf.getText().toString();
//				String pwd = qqpwdjtf.getText().toString();
//				String qqname=lm.checkUser(userid, pwd);
//				if(qqname.length()!=0)
//				{
//					//new chatview();
//					System.out.println("登录成功");
//				}else {
//					JOptionPane.showMessageDialog(null, "不存在账号");
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
			System.out.println("获取账号密码");
			boolean b = cu.checkUser(u);
			System.out.println(b);
			if(b)
			{
				
				try {
					//把创建好友列表的语句提前
					QQFriendList qfl = new QQFriendList(u.getUserId());
					ManageQQFriendList.addQQFriendList(u.getUserId(), qfl);
					
					//发送一个要求返回在线好友的请求包
					ObjectOutputStream oos = new ObjectOutputStream(
							ManagerClientConServerThread.getClientconSeverThread(u.getUserId()).getS().getOutputStream());
					//做一个Message
					Message m  = new Message();
					m.setMesType(MessageType.message_get_onlineFriend);
					//指明我要的是这个QQ号的好友情况；因此可能一个电脑登录了两个QQ号
					m.setSender(u.getUserId());
					oos.writeObject(m);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				this.dispose();
				
			}else {
				JOptionPane.showMessageDialog(null, "用户或密码错误");
			}
		}else if(arg0.getSource()==canceljb){
			this.dispose();
		}
	}

}
