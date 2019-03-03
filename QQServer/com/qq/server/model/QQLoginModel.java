/***
 * QQµÇÂ¼±í
 */
package com.qq.server.model;

import java.sql.*;
import com.qq.server.model.*;

public class QQLoginModel {
	
	public String checkUser(String userid,String pwd)
	{
		String qqname="";
		SQLModel sm=null;
		String sql = "select qqname from login where qqnumber=? and qqpwd=?";
		String[] params = {userid,pwd};
		sm = new SQLModel();
		ResultSet rs = sm.QuerySQL(sql, params);
		try {
			if(rs.next())
			{
				qqname = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			sm.close();
		}
		
		return qqname;
	}

}
