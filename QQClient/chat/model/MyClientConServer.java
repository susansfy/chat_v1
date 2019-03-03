/***
 * 客户端连接服务器的后台
 */
package chat.model;

import java.util.*;
import java.io.*;
import java.net.*;
import com.qq.common.*;
import chat.tools.*;

public class MyClientConServer {
	
	//static表示只有一个；
	//导致多个窗口对话时，会报错，出现并发问题；
	//public static Socket s;
	
	public  Socket s;
	
	//发送第一次请求
	public boolean SendLoginInfoToServer(Object o)
	{
		boolean b = false;
		try {
			s = new Socket("127.0.0.1",9990);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Message ms = (Message)ois.readObject();
			//这里就是验证用户登录的地方
			if(ms.getMesType().equals("1"))
			{
				//就创建一个该QQ号和服务器端保持通讯连接的线程
				ClientConServerThread ccst = new ClientConServerThread(s);
				//启动该通讯线程
				ccst.start();
				ManagerClientConServerThread.addClientconSeverThread(((User)o).getUserId(),ccst);
				b=true;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}
		return b;
	}
	
	public void SendInfoToServer(Object o)
	{
//		try {
//			Socket s = new Socket("127.0.0.1",9999);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			
//		}
	}

}
