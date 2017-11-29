package com.jason.service;

import java.util.ArrayList;

import com.jason.dao.DaoFactory;
import com.jason.dao.DeptDao;
import com.jason.dao.JobDao;
import com.jason.dao.Interface.IDeptDao;
import com.jason.domain.Department;
import com.jason.domain.Job;
import com.jason.domain.PageModel;

public class JobService {
	private JobDao jobDao;
	public JobService() {
		jobDao = DaoFactory.getJobDao();
	}
	public boolean addJob(Job job) {
		if(!isSameJobName(job)) {
			int pos = jobDao.insertJob(job);
			if(pos>0) {
				return true;
			}
		}
		return false;
		
	}
	public boolean deleteJob(Job job) {
		int pos = jobDao.deleteJob(job);
		if(pos>0) {
			return true;
		}
		return false;
		
	}
	public boolean udateJob(Job job) {
		int pos = jobDao.updateJob(job);
		if(pos>0) {
			return true;
		}	
		return false;
		
	}
	public ArrayList<Job> getJobs(Job job){
		ArrayList<Job> list = jobDao.queryJob(job);
		if(list!=null&&list.size()>0) {
			return list;
		}
		return null;
	}
	
	//查询页数
	public Integer queryJobCount(Job job) {
		return jobDao.queryJobCount(job);
	}
	
	//分页查询
	public ArrayList<Job> queryJobsByPage(Job job,PageModel page){
		page.setTotalRecordSum(queryJobCount(job));
		ArrayList<Job> list = jobDao.queryJobByPage(job, page);
		if(list!=null&&list.size()>0) {
			return list;
		}
		return null;
	}
	
	
	public boolean isSameJobName(Job job) {
		return jobDao.querySame(job);
	}
	public ArrayList<Job> getAllJobs(){
		return jobDao.queryAllJob();
	}

}
