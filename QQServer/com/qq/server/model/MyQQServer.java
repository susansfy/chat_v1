/***
 * ����QQ�����������ڼ������ȴ�ĳ��QQ�ͻ��ˣ�������
 * �˺ź����룬�Զ����������䣻��������м����һ������������Ļ������������иպô���������ţ��ͻ�����⣻
 * ���湫˾��һ������XML�������--�ݲ�ʵ��
 * --��֪ʶ�����������䴫�ݶ�����
 */
package com.qq.server.model;

import java.net.*;
import java.io.*;
import java.util.*;

import com.qq.common.*;

public class MyQQServer {
	
	public MyQQServer()
	{
		try {
			System.out.println("����9999����");
			ServerSocket ss = new ServerSocket(9990);
			//�������ȴ�����
			while(true)
			{
				Socket s = ss.accept();
				
				//���տͻ��˷�������Ϣ
//				BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
//				
//				String info = br.readLine();
				
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				User u = (User)ois.readObject();
				Message m = new Message();
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				if(u.getPwd().equals("123456"))
				{
					//����һ���ɹ���½����Ϣ��
					m.setMesType("1");
					oos.writeObject(m);
					
					//����͵���һ���̣߳��ø��߳���ÿͻ��˱���ͨѶ
					ServerConClientThread scc = new ServerConClientThread(s);
					ManagerClientThread.addClientThread(u.getUserId(), scc);
					//������ÿͻ���ͨѶ���߳�
					scc.start();
					
					//��֪ͨ���������û�
					scc.notifyOther(u.getUserId());
					
				}else
				{
					m.setMesType("2");
					oos.writeObject(m);
					//�ر�����
					s.close();
				}
				
				
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
