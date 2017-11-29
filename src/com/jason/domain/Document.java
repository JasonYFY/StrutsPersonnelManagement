package com.jason.domain;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;

public class Document implements Serializable{
	private Integer id;
	private String title;
	private String fileName;
	private String remark;
	private Date createDate;
	private InputStream fileData;
	private Integer userId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public InputStream getFileData() {
		return fileData;
	}
	public void setFileData(InputStream fileData) {
		this.fileData = fileData;
	}
	@Override
	public String toString() {
		return "Document [id=" + id + ", title=" + title + ", fileName=" + fileName + ", remark=" + remark
				+ ", createDate=" + createDate + ", fileData=" + fileData + ", userId=" + userId + "]";
	}

}
