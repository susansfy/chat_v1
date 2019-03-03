/***
 * 管理好友、黑名单等界面类
 */
package chat.tools;

import java.util.*;
import java.io.*;
import chat.view.*;

public class ManageQQFriendList {
	
	//一个qq号登录后只有一个好友列表界面
	private static HashMap hm = new HashMap<String,QQFriendList>();
	
	public static void addQQFriendList(String qqid,QQFriendList qqfriendlist)
	{
		hm.put(qqid, qqfriendlist);
	}
	
	public static QQFriendList getQQFriendList(String qqid)
	{
		return (QQFriendList)hm.get(qqid);
	}
	
}
