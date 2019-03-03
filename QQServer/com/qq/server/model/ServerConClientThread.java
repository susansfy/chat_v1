/***
 * ��������ĳ���ͻ��˵�ͨѶ�߳�
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
		//�ѷ������͸ÿͻ��˵����Ӹ�ֵ��s
		this.s = s;
	}
	
	//�ø��߳�ȥ֪ͨ�����û�
	public void notifyOther(String iam)
	{
		//�õ��������ߵ��˵��߳�
		HashMap hm = ManagerClientThread.hm;
		Iterator it = hm.keySet().iterator();
		while(it.hasNext())
		{
			Message m = new Message();
			m.setContext(iam);
			m.setMesType(MessageType.message_ret_onlineFriend);
			//ȡ�������˵�id
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
			//������߳̾Ϳ��Խ��տͻ��˵���Ϣ
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message)ois.readObject();
				//System.out.println(m.getSender()+"��"+m.getGetter()+"˵"+m.getContext());
				
				//�Դӿͻ���ȡ�õ���Ϣ�����������жϣ�Ȼ������Ӧ�Ĵ���
				if(m.getMesType().equals(MessageType.message_comm_mes)) {
					//���ת��
					//ȡ�ý����˵�ͨѶ�߳�
					ServerConClientThread sc = ManagerClientThread.getClientThread(m.getGetter());
					ObjectOutputStream oos = new ObjectOutputStream(sc.s.getOutputStream());
					oos.writeObject(m);
				}else if(m.getMesType().equals(MessageType.message_get_onlineFriend))
				{
					System.out.println(m.getSender()+"Ҫ���ĺ���");
					//���ڷ������ĺ��Ѹ��ÿͻ��˷���
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
