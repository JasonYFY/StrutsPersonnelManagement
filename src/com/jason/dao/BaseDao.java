package com.jason.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jason.db.DbUtils;

/*
 * 用于获取Connection对象和关闭
 */
public class BaseDao {
	
	
	public Connection getConnection() throws SQLException
	{
		Connection conn=DbUtils.getConnection();
		return conn;
	}
	
	public void closeConnection(ResultSet rs,PreparedStatement pstmt,Connection conn) throws SQLException
	{
		DbUtils.closeConnection(rs, pstmt, conn);
	}

}
