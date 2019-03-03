/***
 * 管理
 */
package com.qq.server.model;

import java.util.*;

public class ManagerClientThread {

	//不加static，到时会存在两份hashmap，肯定是不行的；秘钥规则只能有一套
	public static HashMap hm = new HashMap<String,ServerConClientThread>();
	
	//向hm中添加一个客户端通讯线程
	public static void addClientThread(String uid,ServerConClientThread ct)
	{
		hm.put(uid, ct);
		
	}
	
	public static ServerConClientThread getClientThread(String uid) 
	{
		return (ServerConClientThread)hm.get(uid);
	}
	
	//返回当前在线的人的情况
	public static String getAllOnlineUserid()
	{
		//使用迭代器完成
		Iterator it = hm.keySet().iterator();
		String res="";
		while(it.hasNext())
		{
			res+=it.next().toString()+" ";
		}
		return res;
	}
	
}
