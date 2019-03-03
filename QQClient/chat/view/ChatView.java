/***
 * 与好友聊天界面
 * 因为客户端，要处在读取的状态，因此我们要把它做成一个线程
 */
package chat.view;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import com.qq.common.*;
import chat.model.*;
import java.io.*;
import chat.tools.*;

public class ChatView extends JFrame implements ActionListener{
	
	JTextArea jta;
	JTextField jtf;
	JButton jb;
	JPanel jp;
	String ownerId;
	String friendId;
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		ChatView cv = new ChatView("3");
//	}
	
	public ChatView(String ownerId,String friend)
	{
		this.ownerId=ownerId;
		this.friendId=friend;
		jta = new JTextArea();
		jtf = new JTextField(15);
		jb = new JButton("发送");
		jb.addActionListener(this);
		jp = new JPanel();
		jp.add(jtf);
		jp.add(jb);
		
		this.add(jta,"Center");
		this.add(jp,"South");
		this.setTitle(ownerId+"正在和"+friend+"聊天");
		this.setIconImage(new ImageIcon("src\\images\\icon.png").getImage());
		this.setSize(500,200);
		this.setVisible(true);
		
		
		
	}
	
	//写一个方法，让它显示消息
	public void ShowMessage(Message m)
	{
		String info = m.getSender()+"对"+m.getGetter()+"说"+"\r\n"+m.getContext()+"\r\n";
		this.jta.append(info);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==jb)
		{
			Message m = new Message();
			m.setMesType(MessageType.message_comm_mes);
			m.setSender(ownerId);
			m.setGetter(friendId);
			m.setContext(jtf.getText());
			m.setSendTime(new java.util.Date().toString());
			//发送给服务器
			try {
				ObjectOutputStream oos = new ObjectOutputStream(
						ManagerClientConServerThread.getClientconSeverThread(ownerId).getS().getOutputStream());
				oos.writeObject(m);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	//不在界面上读取线程，线程的工作交给thread线程类完成
//	public void run() {
//		// TODO Auto-generated method stub
//		while(true)
//		{
//			//读取，如果读不到就等待
//			try {
//				ObjectInputStream ois = new ObjectInputStream(MyClientConServer.s.getInputStream());
//				Message m = (Message)ois.readObject();
//				//显示
//				String info = m.getSender()+"对"+m.getGetter()+"说"+"\r\n"+m.getContext()+"\r\n";
//				this.jta.append(info);
//				
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}
//	}

}
