package chat.model;
import chat.model.*;
import java.io.*;
import java.net.*;
import java.util.*;
import com.qq.common.*;
public class ClientUser {
	
	public boolean checkUser(User u)
	{
		
		boolean b ;
		MyClientConServer mcs= new MyClientConServer();
		b = mcs.SendLoginInfoToServer(u);
		System.out.println("clientuser£º"+b);
		return b;
	}

}
