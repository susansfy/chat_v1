/***
 * 服务器和某个客户端的通讯线程
 */
package com.qq.server.model;

import java.io.*;
import java.net.*;
import com.qq.common.*;
import java.util.*;

public class ServerConClientThread extends Thread{
	
	Socket s;
	
	public ServerConClientThread(Socket s)
	{
		//把服务器和该客户端的连接赋值给s
		this.s = s;
	}
	
	//让该线程去通知其它用户
	public void notifyOther(String iam)
	{
		//得到所有在线的人的线程
		HashMap hm = ManagerClientThread.hm;
		Iterator it = hm.keySet().iterator();
		while(it.hasNext())
		{
			Message m = new Message();
			m.setContext(iam);
			m.setMesType(MessageType.message_ret_onlineFriend);
			//取出在线人的id
			String onlineUserid = it.next().toString();
			try {
				ObjectOutputStream oos = new ObjectOutputStream(ManagerClientThread.getClientThread(onlineUserid).s.getOutputStream());
				m.setGetter(onlineUserid);
				oos.writeObject(m);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void run()
	{
		while(true)
		{
			//这里该线程就可以接收客户端的信息
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message)ois.readObject();
				//System.out.println(m.getSender()+"给"+m.getGetter()+"说"+m.getContext());
				
				//对从客户端取得的消息，进行类型判断；然后做相应的处理
				if(m.getMesType().equals(MessageType.message_comm_mes)) {
					//完成转发
					//取得接收人的通讯线程
					ServerConClientThread sc = ManagerClientThread.getClientThread(m.getGetter());
					ObjectOutputStream oos = new ObjectOutputStream(sc.s.getOutputStream());
					oos.writeObject(m);
				}else if(m.getMesType().equals(MessageType.message_get_onlineFriend))
				{
					System.out.println(m.getSender()+"要他的好友");
					//把在服务器的好友给该客户端返回
					String res = ManagerClientThread.getAllOnlineUserid();
					Message m2 = new Message();
					m2.setMesType(MessageType.message_ret_onlineFriend);
					m2.setContext(res);
					m2.setGetter(m.getSender());
					ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
					oos.writeObject(m2);
				}
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
