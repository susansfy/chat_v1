/***
 * ������ѡ��������Ƚ�����
 */
package chat.tools;

import java.util.*;
import java.io.*;
import chat.view.*;

public class ManageQQFriendList {
	
	//һ��qq�ŵ�¼��ֻ��һ�������б����
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
