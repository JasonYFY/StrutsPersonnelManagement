package com.jason.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jason.dao.Interface.IDeptDao;
import com.jason.domain.Department;
import com.jason.domain.PageModel;

public class DeptDao extends BaseDao implements IDeptDao{

	@Override
	public int insertDepartment(Department dept) {
		// 获取数据库的连接对象
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into dept_inf(name,remark) values(?,?)");

			pstmt.setString(1, dept.getDeptName());
			pstmt.setString(2, dept.getRemark());
			
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
	public int deleteDepartment(Department dept) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("delete from dept_inf where ID=?");
			
			pstmt.setInt(1, dept.getId());
			
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
	public int updateDepartment(Department dept) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("update dept_inf set name=?,remark=? where ID=?");

			pstmt.setString(1, dept.getDeptName());
			pstmt.setString(2, dept.getRemark());
			pstmt.setInt(3, dept.getId());

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
	public ArrayList<Department> queryDepartment(Department dept){
		ArrayList<Department> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			if(dept.getId()!=null) {
				pstmt = conn.prepareStatement("select * from dept_inf where id=? ");
				pstmt.setInt(1, dept.getId());
			}else {
				pstmt = conn.prepareStatement("select * from dept_inf where name like ? ");
				pstmt.setString(1, "%"+dept.getDeptName()+"%");
			}
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Department nowDept = new Department(rs.getInt("ID"),
						rs.getString("name"),rs.getString("remark"));
				list.add(nowDept);
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
	
	public ArrayList<Department> queryAllDepartment(){
		ArrayList<Department> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from dept_inf ");
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Department nowDept = new Department(rs.getInt("ID"),
						rs.getString("name"),rs.getString("remark"));
				list.add(nowDept);
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
	
	public boolean querySame(Department dept) {
		boolean isSame = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from dept_inf where name=? ");

			pstmt.setString(1, dept.getDeptName());
			
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
	//分页查询
	public ArrayList<Department> queryDeptByPage(Department dept,PageModel page){
		ArrayList<Department> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			if(dept.getId()!=null) {
				pstmt = conn.prepareStatement("select * from dept_inf where id=? limit "+page.startRowNum()+","+page.getPageSize());
				pstmt.setInt(1, dept.getId());
			}else {
				pstmt = conn.prepareStatement("select * from dept_inf where name like ? limit "+page.startRowNum()+","+page.getPageSize());
				if(dept.getDeptName()==null)
					pstmt.setString(1, "%%");
				else
					pstmt.setString(1, "%"+dept.getDeptName()+"%");
			}
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Department nowDept = new Department(rs.getInt("ID"),
						rs.getString("name"),rs.getString("remark"));
				list.add(nowDept);
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
	//查询页数
	public Integer queryDeptCount(Department dept){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer count = null;
		try {
			conn = getConnection();
			if(dept.getId()!=null) {
				pstmt = conn.prepareStatement("select count(*) from dept_inf where id=? ");
				pstmt.setInt(1, dept.getId());
			}else {
				pstmt = conn.prepareStatement("select count(*) from dept_inf where name like ?");
				if(dept.getDeptName()==null)
					pstmt.setString(1, "%%");
				else
					pstmt.setString(1, "%"+dept.getDeptName()+"%");
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

}
