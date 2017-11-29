package com.jason.db;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.alibaba.druid.pool.DruidDataSource;

public class DbUtils {
	
	private static DruidDataSource ds=null;
	
	static
	{
		try {
			Context initContext=new InitialContext();
			ds=(DruidDataSource)initContext.lookup("java:comp/env/jdbc/ProjectDataSource");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection() throws SQLException
	{
		return ds.getConnection();
	}
	
	public static void closeConnection(ResultSet rs,PreparedStatement pstmt,Connection conn) throws SQLException
	{
		if(rs!=null)
		{
			rs.close();
			rs=null;
		}
		
		if(pstmt!=null)
		{
			pstmt.close();
			pstmt=null;
		}
		
		if(conn!=null)
		{
			conn.close();
			conn=null;
		}
	}

}
