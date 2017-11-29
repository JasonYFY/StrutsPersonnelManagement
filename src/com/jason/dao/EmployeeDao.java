package com.jason.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.jason.domain.Employee;
import com.jason.domain.PageModel;


public class EmployeeDao extends BaseDao{
	
	public int insertEmployee(Employee emp) {
		// 获取数据库的连接对象
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into employee_inf"
					+ "(dept_id,job_id,name,card_id,address,post_code,tel,phone,"
					+ "qq_num,email,sex,party,birthday,race,education,speciality,"
					+ "hobby,remark,create_date) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			pstmt.setInt(1, emp.getDeptId());
			pstmt.setInt(2, emp.getJobId());
			pstmt.setString(3, emp.getEmployeeName());
			pstmt.setString(4, emp.getCardId());
			pstmt.setString(5, emp.getAddress());
			pstmt.setString(6, emp.getPostCode());
			pstmt.setString(7, emp.getTel());
			pstmt.setString(8, emp.getPhone());
			pstmt.setString(9, emp.getQqNum());
			pstmt.setString(10, emp.getEmail());
			pstmt.setInt(11, emp.getSex());
			pstmt.setString(12, emp.getParty());
			if(emp.getBirthday()!=null) {
				pstmt.setDate(13, new java.sql.Date(emp.getBirthday().getTime()));
			}else {
				pstmt.setDate(13, null);
			}
			pstmt.setString(14, emp.getRace());
			pstmt.setString(15, emp.getEducation());
			pstmt.setString(16, emp.getSpeciality());
			pstmt.setString(17, emp.getHobby());
			pstmt.setString(18, emp.getRemark());
			pstmt.setTimestamp(19, new Timestamp(emp.getCreateDate().getTime()));
			
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
	
	public int deleteEmployee(Employee emp) {
		// 获取数据库的连接对象
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("delete from employee_inf where ID = ?");

			pstmt.setInt(1, emp.getId());
			
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
	
	public int updateEmployee(Employee emp) {
		// 获取数据库的连接对象
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("update employee_inf set "
					+ "dept_id=?,job_id=?,name=?,card_id=?,address=?,post_code=?,tel=?,phone=?,"
					+ "qq_num=?,email=?,sex=?,party=?,birthday=?,race=?,education=?,speciality=?," 
					+ "hobby=?,remark=? where ID=?");
			pstmt.setInt(1, emp.getDeptId());
			pstmt.setInt(2, emp.getJobId());
			pstmt.setString(3, emp.getEmployeeName());
			pstmt.setString(4, emp.getCardId());
			pstmt.setString(5, emp.getAddress());
			pstmt.setString(6, emp.getPostCode());
			pstmt.setString(7, emp.getTel());
			pstmt.setString(8, emp.getPhone());
			pstmt.setString(9, emp.getQqNum());
			pstmt.setString(10, emp.getEmail());
			pstmt.setInt(11, emp.getSex());
			pstmt.setString(12, emp.getParty());
			if(emp.getBirthday()!=null) {
				pstmt.setDate(13, new java.sql.Date(emp.getBirthday().getTime()));
			}else {
				pstmt.setDate(13, null);
			}
			pstmt.setString(14, emp.getRace());
			pstmt.setString(15, emp.getEducation());
			pstmt.setString(16, emp.getSpeciality());
			pstmt.setString(17, emp.getHobby());
			pstmt.setString(18, emp.getRemark());
			pstmt.setInt(19, emp.getId());
			
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
	
	public ArrayList<Employee> queryEmployee(Employee emp){
		ArrayList<Employee> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		String employeeName = emp.getEmployeeName();
		String cardId = emp.getCardId();
		Integer jobId = emp.getJobId();
		Integer sex = emp.getSex();
		Integer deptId = emp.getDeptId();
		String phone = emp.getPhone();
		StringBuffer sb = new StringBuffer();
		sb.append("select * from employee_inf where '1'='1' ");
		
		
		if(employeeName!=null&&!employeeName.equals(""))
			sb.append("and name like '%"+employeeName+"%' ");
		if(cardId!=null&&!cardId.equals(""))
			sb.append("and card_id like '%"+cardId+"%' ");
		if(jobId!=null)
			sb.append("and job_id="+jobId+" ");
		if(deptId!=null) {
			sb.append("and dept_id="+deptId+" ");
		}
		if(sex!=null)
			sb.append("and sex="+sex+" ");
		if(phone!=null&&!phone.equals(""))
			sb.append("and phone like '%"+phone+"%' ");
		
		try {
			conn = getConnection();
			if(emp.getId()!=null) {
				pstmt = conn.prepareStatement("select * from employee_inf where id=?");
				pstmt.setInt(1, emp.getId());
			}else
			pstmt = conn.prepareStatement(sb.toString());

			rs = pstmt.executeQuery();
			while(rs.next()) {
				Employee nowEmp = new Employee();
				nowEmp.setId(rs.getInt("ID"));
				nowEmp.setDeptId(rs.getInt("dept_id"));
				nowEmp.setJobId(rs.getInt("job_id"));
				nowEmp.setEmployeeName(rs.getString("name"));
				nowEmp.setCardId(rs.getString("card_id"));
				nowEmp.setAddress(rs.getString("address"));
				nowEmp.setPostCode(rs.getString("post_code"));
				nowEmp.setTel(rs.getString("tel"));
				nowEmp.setPhone(rs.getString("phone"));
				nowEmp.setQqNum(rs.getString("qq_num"));
				nowEmp.setEmail(rs.getString("email"));
				nowEmp.setSex(rs.getInt("sex"));
				nowEmp.setParty(rs.getString("party"));
				if(rs.getDate("birthday")!=null) {
				nowEmp.setBirthday(new java.util.Date(rs.getDate("birthday").getTime()));
				}
				else
					nowEmp.setBirthday(null);
				nowEmp.setRace(rs.getString("race"));
				nowEmp.setEducation(rs.getString("education"));
				nowEmp.setSpeciality(rs.getString("speciality"));
				nowEmp.setHobby(rs.getString("hobby"));
				nowEmp.setRemark(rs.getString("remark"));
				nowEmp.setCreateDate(new java.util.Date(rs.getTimestamp("create_date").getTime()));
				
				list.add(nowEmp);
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
	
	//查询记录数
	public Integer queryEmployeeCount(Employee emp){
		Integer count = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String employeeName = emp.getEmployeeName();
		String cardId = emp.getCardId();
		Integer jobId = emp.getJobId();
		Integer sex = emp.getSex();
		Integer deptId = emp.getDeptId();
		String phone = emp.getPhone();
		StringBuffer sb = new StringBuffer();
		sb.append("select count(*) from employee_inf where '1'='1' ");
		
		
		if(employeeName!=null&&!employeeName.equals(""))
			sb.append("and name like '%"+employeeName+"%' ");
		if(cardId!=null&&!cardId.equals(""))
			sb.append("and card_id like '%"+cardId+"%' ");
		if(jobId!=null)
			sb.append("and job_id="+jobId+" ");
		if(deptId!=null) {
			sb.append("and dept_id="+deptId+" ");
		}
		if(sex!=null)
			sb.append("and sex="+sex+" ");
		if(phone!=null&&!phone.equals(""))
			sb.append("and phone like '%"+phone+"%' ");
		
//		System.out.println("sql:"+sb.toString());
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sb.toString());

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
	public ArrayList<Employee> queryEmployeeByPage(Employee emp,PageModel page){
		ArrayList<Employee> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		String employeeName = emp.getEmployeeName();
		String cardId = emp.getCardId();
		Integer jobId = emp.getJobId();
		Integer sex = emp.getSex();
		Integer deptId = emp.getDeptId();
		String phone = emp.getPhone();
		StringBuffer sb = new StringBuffer();
		sb.append("select * from employee_inf where '1'='1' ");
		
		
		if(employeeName!=null&&!employeeName.equals(""))
			sb.append("and name like '%"+employeeName+"%' ");
		if(cardId!=null&&!cardId.equals(""))
			sb.append("and card_id like '%"+cardId+"%' ");
		if(jobId!=null)
			sb.append("and job_id="+jobId+" ");
		if(deptId!=null) {
			sb.append("and dept_id="+deptId+" ");
		}
		if(sex!=null)
			sb.append("and sex="+sex+" ");
		if(phone!=null&&!phone.equals(""))
			sb.append("and phone like '%"+phone+"%' ");
		sb.append(" limit "+page.startRowNum()+","+page.getPageSize());
//		System.out.println("sql:"+sb.toString());
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sb.toString());

			rs = pstmt.executeQuery();
			while(rs.next()) {
				Employee nowEmp = new Employee();
				nowEmp.setId(rs.getInt("ID"));
				nowEmp.setDeptId(rs.getInt("dept_id"));
				nowEmp.setJobId(rs.getInt("job_id"));
				nowEmp.setEmployeeName(rs.getString("name"));
				nowEmp.setCardId(rs.getString("card_id"));
				nowEmp.setAddress(rs.getString("address"));
				nowEmp.setPostCode(rs.getString("post_code"));
				nowEmp.setTel(rs.getString("tel"));
				nowEmp.setPhone(rs.getString("phone"));
				nowEmp.setQqNum(rs.getString("qq_num"));
				nowEmp.setEmail(rs.getString("email"));
				nowEmp.setSex(rs.getInt("sex"));
				nowEmp.setParty(rs.getString("party"));
				if(rs.getDate("birthday")!=null) {
				nowEmp.setBirthday(new java.util.Date(rs.getDate("birthday").getTime()));
				}
				else
					nowEmp.setBirthday(null);
				nowEmp.setRace(rs.getString("race"));
				nowEmp.setEducation(rs.getString("education"));
				nowEmp.setSpeciality(rs.getString("speciality"));
				nowEmp.setHobby(rs.getString("hobby"));
				nowEmp.setRemark(rs.getString("remark"));
				nowEmp.setCreateDate(new java.util.Date(rs.getTimestamp("create_date").getTime()));
				
				list.add(nowEmp);
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
	
	public ArrayList<Employee> queryAllEmployee(){
		ArrayList<Employee> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from employee_inf");
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Employee nowEmp = new Employee();
				nowEmp.setId(rs.getInt("ID"));
				nowEmp.setDeptId(rs.getInt("DEPT_ID"));
				nowEmp.setJobId(rs.getInt("job_id"));
				nowEmp.setEmployeeName(rs.getString("name"));
				nowEmp.setCardId(rs.getString("card_id"));
				nowEmp.setAddress(rs.getString("address"));
				nowEmp.setPostCode(rs.getString("post_code"));
				nowEmp.setTel(rs.getString("tel"));
				nowEmp.setPhone(rs.getString("phone"));
				nowEmp.setQqNum(rs.getString("qq_num"));
				nowEmp.setEmail(rs.getString("email"));
				nowEmp.setSex(rs.getInt("sex"));
				nowEmp.setParty(rs.getString("party"));
				if(rs.getDate("birthday")!=null) {
					nowEmp.setBirthday(new java.util.Date(rs.getDate("birthday").getTime()));
				}
				else
					nowEmp.setBirthday(null);
				nowEmp.setRace(rs.getString("race"));
				nowEmp.setEducation(rs.getString("education"));
				nowEmp.setSpeciality(rs.getString("speciality"));
				nowEmp.setHobby(rs.getString("hobby"));
				nowEmp.setRemark(rs.getString("remark"));
				nowEmp.setCreateDate(new java.util.Date(rs.getTimestamp("create_date").getTime()));
				
				list.add(nowEmp);
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
	public boolean isSameCardId(Employee emp) {
		boolean i = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from employee_inf where card_id=? ");
			pstmt.setString(1, emp.getCardId());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				i = true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection(rs,pstmt,conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return i;
	}

}
