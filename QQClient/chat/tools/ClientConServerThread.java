/***
 * ���ǿͻ��˺ͷ������˱���ͨѶ���߳�
 */
package chat.tools;

import java.net.*;
import java.io.*;
import com.qq.common.*;
import chat.view.*;

public class ClientConServerThread extends Thread{

	private Socket s;
	
	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

	public ClientConServerThread(Socket s)
	{
		this.s=s;
	}
	
	public void run()
	{
		while(true)
		{
			//��ͣ�ض�ȡ�ӷ������˷�������Ϣ
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message m= (Message)ois.readObject();
				//System.out.println("��ȡ���ӷ�������������Ϣ"+m.getSender()+"��"+m.getGetter()+"����"+m.getContext());
				
				//�������ͨ����
				if(m.getMesType().equals(MessageType.message_comm_mes))
				{
					//�Ѵӷ�������õ���Ϣ����ʾ������ʾ���������
					ChatView qqChat = ManageQQChatView.getQQChat(m.getGetter()+" "+m.getSender());
					//��ʾ
					qqChat.ShowMessage(m);
				}else if(m.getMesType().equals(MessageType.message_ret_onlineFriend))
				{
					System.out.println("�ͻ��˽��յ�"+m.getContext());
					//������һ�����ߵ��˵��б�
					String con=m.getContext();
					String friends[] = con.split(" ");
					//������������ʱ�������getter
					String getter=m.getGetter();
					//�޸���Ӧ�ĺ����б�
					QQFriendList qqfriendlist = ManageQQFriendList.getQQFriendList(getter);
					
					//�������ߺ���;�ж�Ԥ���յ�½ʱ����
					if(qqfriendlist!=null)
					{
						qqfriendlist.updateFriend(m);
					}
				}
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
}
