package com.jason.dao.Interface;

import java.util.ArrayList;

import com.jason.domain.Department;


public interface IDeptDao {
	public int insertDepartment(Department dept);
	public int deleteDepartment(Department dept);
	public int updateDepartment(Department dept);
	public ArrayList<Department> queryDepartment(Department dept);
}
