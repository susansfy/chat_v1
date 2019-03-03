/***
 * ������������
 * ��Ϊ�ͻ��ˣ�Ҫ���ڶ�ȡ��״̬���������Ҫ��������һ���߳�
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
		jb = new JButton("����");
		jb.addActionListener(this);
		jp = new JPanel();
		jp.add(jtf);
		jp.add(jb);
		
		this.add(jta,"Center");
		this.add(jp,"South");
		this.setTitle(ownerId+"���ں�"+friend+"����");
		this.setIconImage(new ImageIcon("src\\images\\icon.png").getImage());
		this.setSize(500,200);
		this.setVisible(true);
		
		
		
	}
	
	//дһ��������������ʾ��Ϣ
	public void ShowMessage(Message m)
	{
		String info = m.getSender()+"��"+m.getGetter()+"˵"+"\r\n"+m.getContext()+"\r\n";
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
			//���͸�������
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

	//���ڽ����϶�ȡ�̣߳��̵߳Ĺ�������thread�߳������
//	public void run() {
//		// TODO Auto-generated method stub
//		while(true)
//		{
//			//��ȡ������������͵ȴ�
//			try {
//				ObjectInputStream ois = new ObjectInputStream(MyClientConServer.s.getInputStream());
//				Message m = (Message)ois.readObject();
//				//��ʾ
//				String info = m.getSender()+"��"+m.getGetter()+"˵"+"\r\n"+m.getContext()+"\r\n";
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
