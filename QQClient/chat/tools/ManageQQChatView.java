/***
 * ����һ�������û�����������
 */
package chat.tools;

import java.util.*;
import chat.view.*;

public class ManageQQChatView {
	
	//һ�����ѵ����������Ψһ�ģ�
	private static HashMap hm = new HashMap<String,ChatView>();
	
	//����
	public static void addQQChat(String loginIdAnFriendId,ChatView qqchat)
	{
		hm.put(loginIdAnFriendId, qqchat);
	}
	
	//ȡ��
	public static ChatView getQQChat(String loginIdAnFriendId)
	{
		return (ChatView)hm.get(loginIdAnFriendId);
	}

}
