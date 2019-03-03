/***
 * 这是客户端和服务器端保持通讯的线程
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
			//不停地读取从服务器端发来的消息
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message m= (Message)ois.readObject();
				//System.out.println("读取到从服务器发来的消息"+m.getSender()+"给"+m.getGetter()+"内容"+m.getContext());
				
				//如果是普通包，
				if(m.getMesType().equals(MessageType.message_comm_mes))
				{
					//把从服务器获得的消息，显示到该显示的聊天界面
					ChatView qqChat = ManageQQChatView.getQQChat(m.getGetter()+" "+m.getSender());
					//显示
					qqChat.ShowMessage(m);
				}else if(m.getMesType().equals(MessageType.message_ret_onlineFriend))
				{
					System.out.println("客户端接收到"+m.getContext());
					//返回了一堆在线的人的列表
					String con=m.getContext();
					String friends[] = con.split(" ");
					//当服务器发送时，你就是getter
					String getter=m.getGetter();
					//修改相应的好友列表
					QQFriendList qqfriendlist = ManageQQFriendList.getQQFriendList(getter);
					
					//更新在线好友;判断预防刚登陆时报错
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
