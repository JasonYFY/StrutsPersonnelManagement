package com.jason.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.jason.dao.Interface.IUserDao;
import com.jason.domain.PageModel;
import com.jason.domain.User;

public class UserDao extends BaseDao implements IUserDao{

	@Override
	public int insertUser(User user) {
		// 获取数据库的连接对象
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into user_inf(loginname,PASSWORD,STATUS,createdate,username) values(?,?,?,?,?)");

			pstmt.setString(1, user.getLoginname());
			pstmt.setString(2, user.getPassword());
			pstmt.setInt(3, user.getStatus());
			pstmt.setTimestamp(4, new Timestamp(user.getCreatedate().getTime()));
			pstmt.setString(5, user.getUsername());

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection(null,pstmt,conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return 0;
	}

	@Override
	public int deleteUser(User user) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("delete from user_inf where ID=?");
			
			pstmt.setInt(1, user.getId());
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection(null,pstmt,conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("update user_inf set loginname=?,PASSWORD=?,STATUS=?,username=? where ID=?");

			pstmt.setString(1, user.getLoginname());
			pstmt.setString(2, user.getPassword());
			pstmt.setInt(3, user.getStatus());
			pstmt.setString(4, user.getUsername());
			pstmt.setInt(5, user.getId());

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection(null,pstmt,conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	public User doLogin(User user) {
		boolean isLogin = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from user_inf where loginname=? and PASSWORD=?");

			pstmt.setString(1, user.getLoginname());
			pstmt.setString(2, user.getPassword());
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				isLogin = true;
				user.setId(rs.getInt("ID"));
				user.setStatus(rs.getInt("STATUS"));
				user.setUsername(rs.getString("username"));
				user.setCreatedate(new Date(rs.getTimestamp("createdate").getTime()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection(rs,pstmt,conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(isLogin) {
			return user;
		}
		return null;
	}
	
	public boolean querySame(User user) {
		boolean isSame = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from user_inf where loginname=?");

			pstmt.setString(1, user.getLoginname());
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				isSame = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection(rs,pstmt,conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isSame;
	}
	@Override
	public ArrayList<User> QueryUser(User user){
		ArrayList<User> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = getConnection();
			
			//System.out.println("user2"+user.getUsername().equals(""));
			if(user.getId()!=null) {
				sql = "select * from user_inf where id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, user.getId());
			}else if(user.getUsername()==null&&user.getStatus()!=null) {
				sql = "select * from user_inf where STATUS=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, user.getStatus());
			}else if(user.getUsername()!=null&&user.getStatus()==null) {
				sql = "select * from user_inf where username like ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+user.getUsername()+"%");
			}else if(user.getUsername()!=null&&user.getStatus()!=null) {
				sql = "select * from user_inf where username like ? and STATUS=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+user.getUsername()+"%");
				pstmt.setInt(2, user.getStatus());
			}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				User nowUser = new User();
				nowUser.setId(rs.getInt("ID"));
				nowUser.setPassword(rs.getString("PASSWORD"));
				nowUser.setLoginname(rs.getString("loginname"));
				nowUser.setStatus(rs.getInt("STATUS"));
				nowUser.setUsername(rs.getString("username"));
				nowUser.setCreatedate(new Date(rs.getTimestamp("createdate").getTime()));
				list.add(nowUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection(rs,pstmt,conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	//查询有多少行
	public Integer QueryUserCount(User user){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Integer count = null;
		StringBuffer sb = new StringBuffer();
		try {
			conn = getConnection();
			if(user.getId()!=null) {
				sql = "select count(*) from user_inf where id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, user.getId());
			} else {
				sb.append("select count(*) from user_inf where '1'='1'");
				if (user.getStatus() != null) {
					sb.append(" and STATUS="+user.getStatus());
				}
				if (user.getUsername()!= null) {
					sb.append(" and username like '%"+user.getUsername()+"%'");
				}
				pstmt = conn.prepareStatement(sb.toString());
					
			}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection(rs,pstmt,conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	//分页查询
	public ArrayList<User> QueryUserByPage(User user,PageModel page){
		ArrayList<User> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		StringBuffer sb = new StringBuffer();
		try {
			conn = getConnection();
			if(user.getId()!=null) {
				sql = "select * from user_inf where id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, user.getId());
			}else {
				sb.append("select * from user_inf where '1'='1'");
				if (user.getStatus() != null) {
					sb.append(" and STATUS="+user.getStatus());
				}
				if (user.getUsername()!= null) {
					sb.append(" and username like '%"+user.getUsername()+"%'");
				}
				sb.append(" limit "+page.startRowNum()+","+page.getPageSize());
				pstmt = conn.prepareStatement(sb.toString());
			}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				User nowUser = new User();
				nowUser.setId(rs.getInt("ID"));
				nowUser.setPassword(rs.getString("PASSWORD"));
				nowUser.setLoginname(rs.getString("loginname"));
				nowUser.setStatus(rs.getInt("STATUS"));
				nowUser.setUsername(rs.getString("username"));
				nowUser.setCreatedate(new Date(rs.getTimestamp("createdate").getTime()));
				list.add(nowUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection(rs,pstmt,conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/*public ArrayList<User> QueryAllUser(){
		ArrayList<User> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from user_inf");
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				User nowUser = new User();
				nowUser.setId(rs.getInt("ID"));
				nowUser.setPassword(rs.getString("PASSWORD"));
				nowUser.setLoginname(rs.getString("loginname"));
				nowUser.setStatus(rs.getInt("STATUS"));
				nowUser.setUsername(rs.getString("username"));
				nowUser.setCreatedate(new Date(rs.getTimestamp("createdate").getTime()));
				list.add(nowUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection(rs,pstmt,conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}*/
}
