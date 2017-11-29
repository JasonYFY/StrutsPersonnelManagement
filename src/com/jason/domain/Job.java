package com.jason.domain;

import java.io.Serializable;

public class Job implements Serializable{
	private Integer id;
	private String jobName;
	private String remark;
	public Job() {
		super();
	}
	public Job(Integer id, String jobName, String remark) {
		super();
		this.id = id;
		this.jobName = jobName;
		this.remark = remark;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Job [id=" + id + ", jobName=" + jobName + ", remark=" + remark + "]";
	}
	
}
