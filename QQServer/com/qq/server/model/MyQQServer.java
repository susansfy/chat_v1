/***
 * 这是QQ服务器，它在监听，等待某个QQ客户端，来连接
 * 账号和密码，以对象流来传输；如果是以中间隔开一个符号来传输的话，可能密码中刚好存在这个符号，就会出问题；
 * 正规公司，一般是以XML来传输的--暂不实现
 * --新知识，如果在网络间传递对象流
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
			System.out.println("我在9999监听");
			ServerSocket ss = new ServerSocket(9990);
			//阻塞，等待连接
			while(true)
			{
				Socket s = ss.accept();
				
				//接收客户端发来的信息
//				BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
//				
//				String info = br.readLine();
				
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				User u = (User)ois.readObject();
				Message m = new Message();
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				if(u.getPwd().equals("123456"))
				{
					//返回一个成功登陆的信息报
					m.setMesType("1");
					oos.writeObject(m);
					
					//这里就单开一个线程，让该线程与该客户端保持通讯
					ServerConClientThread scc = new ServerConClientThread(s);
					ManagerClientThread.addClientThread(u.getUserId(), scc);
					//启动与该客户端通讯的线程
					scc.start();
					
					//并通知其它在线用户
					scc.notifyOther(u.getUserId());
					
				}else
				{
					m.setMesType("2");
					oos.writeObject(m);
					//关闭连接
					s.close();
				}
				
				
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
