/***
 * 管理客户端和服务器保持通讯的线程类
 */
package chat.tools;

import java.net.*;
import java.util.*;

public class ManagerClientConServerThread {
	
	private static HashMap hm = new HashMap<String,ClientConServerThread>();
	
	//把创建好的ClientConServerThread线程放入到hashmap
	public static void addClientconSeverThread(String qqId,ClientConServerThread ccst)
	{
		hm.put(qqId, ccst);
	}
	
	//可以通过qqId取得该线程
	public static ClientConServerThread getClientconSeverThread(String qqId)
	{
		return (ClientConServerThread)hm.get(qqId);
	}

}
