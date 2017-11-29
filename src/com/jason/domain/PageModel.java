package com.jason.domain;

import org.junit.Test;

public class PageModel 
{
	public static final int PAGE_SIZE = 5;
	//当前页码
	private int pageIndex;
	//总的记录数
	private int totalRecordSum;
	//总的页数
	private int totalPageSum;
	//每一页显示的行数
	private int pageSize;
	
	public int getPageIndex() {
		if(this.pageIndex<=1) {
			this.pageIndex=1;
		}else if(this.pageIndex>getTotalPageSum()){
			this.pageIndex = getTotalPageSum();
			if(getTotalPageSum()==0)
				this.pageIndex = 1;
		}
		return this.pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getTotalRecordSum() {
		return totalRecordSum;
	}
	public void setTotalRecordSum(int totalRecordSum) {
		this.totalRecordSum = totalRecordSum;
	}
	public int getTotalPageSum() {
		
		this.totalPageSum=(getTotalRecordSum()+getPageSize()-1)/getPageSize();
		return totalPageSum;
	}
	public void setTotalPageSum(int totalPageSum) {
		this.totalPageSum = totalPageSum;
	}
	public int getPageSize() {
		
		this.pageSize=PAGE_SIZE;
		
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int startRowNum()
	{
		return (getPageIndex()-1)*getPageSize();
	}
	

}
