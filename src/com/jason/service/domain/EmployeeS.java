package com.jason.service.domain;

import java.io.Serializable;

import com.jason.domain.Department;
import com.jason.domain.Employee;
import com.jason.domain.Job;

public class EmployeeS implements Serializable{
	private Employee employee;
	private Department dept;
	private Job job;
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public Department getDept() {
		return dept;
	}
	public void setDept(Department dept) {
		this.dept = dept;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}

}
