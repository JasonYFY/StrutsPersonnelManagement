package com.jason.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jason.domain.Department;
import com.jason.domain.Job;
import com.jason.domain.PageModel;

public class JobDao extends BaseDao {

	
	public int insertJob(Job job) {
		// 获取数据库的连接对象
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into job_inf(name,remark) values(?,?)");

			pstmt.setString(1, job.getJobName());
			pstmt.setString(2, job.getRemark());
			
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

	public int deleteJob(Job job) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("delete from job_inf where ID=?");
			
			pstmt.setInt(1, job.getId());
			
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

	public int updateJob(Job job) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("update job_inf set name=?,remark=? where ID=?");

			pstmt.setString(1, job.getJobName());
			pstmt.setString(2, job.getRemark());
			pstmt.setInt(3, job.getId());

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
	
	public ArrayList<Job> queryJob(Job job){
		ArrayList<Job> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			if(job.getId()!=null) {
				pstmt = conn.prepareStatement("select * from job_inf where id=? ");
				pstmt.setInt(1, job.getId());
			}else {
				pstmt = conn.prepareStatement("select * from job_inf where name like ? ");
				pstmt.setString(1, "%"+job.getJobName()+"%");
			}
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Job nowJob = new Job(rs.getInt("ID"), 
						rs.getString("name"), rs.getString("remark"));
				list.add(nowJob);
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
	
	//分页查询
	public ArrayList<Job> queryJobByPage(Job job,PageModel page){
		ArrayList<Job> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			if(job.getId()!=null) {
				pstmt = conn.prepareStatement("select * from job_inf where id=? limit "+page.startRowNum()+","+page.getPageSize());
				pstmt.setInt(1, job.getId());
			}else {
				pstmt = conn.prepareStatement("select * from job_inf where name like ? limit "+page.startRowNum()+","+page.getPageSize());
				if(job.getJobName()==null) {
					pstmt.setString(1, "%%");
				}else {
					pstmt.setString(1, "%"+job.getJobName()+"%");
				}
			}
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Job nowJob = new Job(rs.getInt("ID"), 
						rs.getString("name"), rs.getString("remark"));
				list.add(nowJob);
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
	public Integer queryJobCount(Job job) {
		Integer count = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			if(job.getId()!=null) {
				pstmt = conn.prepareStatement("select count(*) from job_inf where id=? ");
				pstmt.setInt(1, job.getId());
			}else {
				pstmt = conn.prepareStatement("select count(*) from job_inf where name like ?");
				if(job.getJobName()==null) {
					pstmt.setString(1, "%%");
				}else {
					pstmt.setString(1, "%"+job.getJobName()+"%");
				}
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
	
	public ArrayList<Job> queryAllJob(){
		ArrayList<Job> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from job_inf ");
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Job nowJob = new Job(rs.getInt("ID"), 
						rs.getString("name"), rs.getString("remark"));
				list.add(nowJob);
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
	
	public boolean querySame(Job job) {
		boolean isSame = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from job_inf where name=? ");

			pstmt.setString(1, job.getJobName());
			
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

}
