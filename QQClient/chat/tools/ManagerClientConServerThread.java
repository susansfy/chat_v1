/***
 * ����ͻ��˺ͷ���������ͨѶ���߳���
 */
package chat.tools;

import java.net.*;
import java.util.*;

public class ManagerClientConServerThread {
	
	private static HashMap hm = new HashMap<String,ClientConServerThread>();
	
	//�Ѵ����õ�ClientConServerThread�̷߳��뵽hashmap
	public static void addClientconSeverThread(String qqId,ClientConServerThread ccst)
	{
		hm.put(qqId, ccst);
	}
	
	//����ͨ��qqIdȡ�ø��߳�
	public static ClientConServerThread getClientconSeverThread(String qqId)
	{
		return (ClientConServerThread)hm.get(qqId);
	}

}
