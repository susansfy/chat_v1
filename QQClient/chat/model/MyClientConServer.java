/***
 * �ͻ������ӷ������ĺ�̨
 */
package chat.model;

import java.util.*;
import java.io.*;
import java.net.*;
import com.qq.common.*;
import chat.tools.*;

public class MyClientConServer {
	
	//static��ʾֻ��һ����
	//���¶�����ڶԻ�ʱ���ᱨ�����ֲ������⣻
	//public static Socket s;
	
	public  Socket s;
	
	//���͵�һ������
	public boolean SendLoginInfoToServer(Object o)
	{
		boolean b = false;
		try {
			s = new Socket("127.0.0.1",9990);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Message ms = (Message)ois.readObject();
			//���������֤�û���¼�ĵط�
			if(ms.getMesType().equals("1"))
			{
				//�ʹ���һ����QQ�źͷ������˱���ͨѶ���ӵ��߳�
				ClientConServerThread ccst = new ClientConServerThread(s);
				//������ͨѶ�߳�
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
