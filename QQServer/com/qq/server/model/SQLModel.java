/***
 * ���ݱ�ͨ����
 */
package com.qq.server.model;

import java.sql.*;

public class SQLModel {
	
	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/chat?characterEncoding=UTF-8&serverTimezone=UTC";
	String user = "root";
	String password = "123";
	
	//���캯��
	public SQLModel()
	{
		try {
			Class.forName(driver);
			ct = DriverManager.getConnection(url,user,password);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//�������ݱ�
	public boolean UpdateSQL(String sql,String[] params)
	{
		boolean b = true;
		try {
			ps = ct.prepareStatement(sql);
			for(int i=0;i<params.length;i++)
			{
				ps.setString(i+1, params[i]);
			}
			if(ps.executeUpdate()!=1)
			{
				b=false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close();
		}
		return b;
	}
	
	//��ѯ���ݱ�
	public ResultSet QuerySQL(String sql,String[] params)
	{
		try {
			ps = ct.prepareStatement(sql);
			
			for(int i = 0; i<params.length;i++)
			{
				ps.setString(i+1, params[i]);
			}
			rs=ps.executeQuery();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}
	
	//�ر����ӵ�
	public void close()
	{
	
		try {
			if(rs!=null)rs.close();
			if(ps!=null)ps.close();
			if(ct!=null)ct.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
