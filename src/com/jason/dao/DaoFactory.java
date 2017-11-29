package com.jason.dao;

import com.jason.dao.Interface.IDeptDao;
import com.jason.dao.Interface.IUserDao;

/*
 * 工厂DAO，用于获取各种DAO对象
 * */
public class DaoFactory 
{
	/*
	 * 获取UserDao对象
	 * */
	public static IUserDao getUserDao()
	{
		return new UserDao();
	}
	public static IDeptDao getDeptDao() {
		return new DeptDao();
	}
	public static DocumentDao getDocumentDao() {
		return new DocumentDao();
	}
	public static JobDao getJobDao() {
		return new JobDao();
	}
	public static EmployeeDao getEmployeeDao() {
		return new EmployeeDao();
	}

	
}
