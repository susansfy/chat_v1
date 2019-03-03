/***
 * 这是一个管理用户聊天界面的类
 */
package chat.tools;

import java.util.*;
import chat.view.*;

public class ManageQQChatView {
	
	//一个好友的聊天界面是唯一的，
	private static HashMap hm = new HashMap<String,ChatView>();
	
	//加入
	public static void addQQChat(String loginIdAnFriendId,ChatView qqchat)
	{
		hm.put(loginIdAnFriendId, qqchat);
	}
	
	//取出
	public static ChatView getQQChat(String loginIdAnFriendId)
	{
		return (ChatView)hm.get(loginIdAnFriendId);
	}

}
