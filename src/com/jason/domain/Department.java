package com.jason.domain;

import java.io.Serializable;

public class Department implements Serializable{
	private Integer id;
	private String deptName;
	private String remark;
	public Department() {
		super();
	}
	
	public Department(Integer id, String deptName, String remark) {
		super();
		this.id = id;
		this.deptName = deptName;
		this.remark = remark;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Department [id=" + id + ", deptName=" + deptName + ", remark=" + remark + "]";
	}
	
}
