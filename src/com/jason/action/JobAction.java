package com.jason.action;

import java.util.ArrayList;

import com.jason.domain.Job;
import com.jason.domain.PageModel;
import com.jason.service.JobService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

public class JobAction extends ActionSupport implements ModelDriven<Job>{
	private Job job = new Job();
	private JobService jobService = new JobService();
	private PageModel pageModel = new PageModel();
	private String[] jobIds;

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public PageModel getPageModel() {
		return pageModel;
	}

	public void setPageModel(PageModel pageModel) {
		this.pageModel = pageModel;
	}

	public String[] getJobIds() {
		return jobIds;
	}

	public void setJobIds(String[] jobIds) {
		this.jobIds = jobIds;
	}

	@Override
	public Job getModel() {
		return this.job;
	}
	//职位查询页面
	public String jobQueryForm() throws Exception {
		ValueStack vs = ActionContext.getContext().getValueStack();
		ArrayList<Job> jobList = jobService.queryJobsByPage(getModel(), getPageModel());
		vs.set("jobList", jobList);
		return SUCCESS;
	}
	//职位修改页面
	public String jobUpdateForm() throws Exception {
		ArrayList<Job> list = jobService.getJobs(getModel());
		if(list!=null&&list.size()>0) {
			Job d = list.get(0);
			setJob(d);
		}
		return SUCCESS;
	}
	//添加职位
	public String jobAdd() throws Exception {
		ValueStack vs = ActionContext.getContext().getValueStack();
		boolean pos = jobService.addJob(getModel());
		if(pos) {
			vs.set("errorJ", "no");
		}else {
			vs.set("errorJ", "yes");
		}
		
		return SUCCESS;
	}
	//查询职位
	public String jobQuery() throws Exception {
		ValueStack vs = ActionContext.getContext().getValueStack();
		ArrayList<Job> jobList = jobService.queryJobsByPage(getModel(), getPageModel());
		vs.set("jobList", jobList);
		return SUCCESS;
	}
	//删除职位
	public String jobDelete() throws Exception {
		ValueStack vs = ActionContext.getContext().getValueStack();
		if(getJobIds()!=null&&getJobIds().length>0) {
			for(String s:getJobIds()) {
				Job job = new Job();
				job.setId(Integer.parseInt(s));
				boolean pos = jobService.deleteJob(job);
				if(!pos) {
					vs.set("errorDelete", "yes");
				}
			}
			vs.set("errorDelete", "ok");
		}else {
			vs.set("errorDelete", "no");
		}
		return SUCCESS;
	}
	
	//修改职位处理
	public String jobUpdate() throws Exception {
		ValueStack vs = ActionContext.getContext().getValueStack();
		boolean d = jobService.udateJob(getModel());
		
		if (d) {
			vs.set("errorU", "no");
		} else {
			vs.set("errorU", "yes");
		}
		return SUCCESS;
	}
	//检查职位名称是否相同
	public String checkJobName() throws Exception {
		boolean pos = jobService.isSameJobName(getModel());
		if(pos) {
        	 getModel().setJobName("职位名称已被使用");
        }else {
        	getModel().setJobName("pass");
        }
		return SUCCESS;
	}

}
