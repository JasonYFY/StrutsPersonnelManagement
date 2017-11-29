package com.jason.action;

import java.util.ArrayList;

import com.jason.domain.Department;
import com.jason.domain.Employee;
import com.jason.domain.Job;
import com.jason.domain.PageModel;
import com.jason.service.DeptService;
import com.jason.service.EmployeeService;
import com.jason.service.JobService;
import com.jason.service.domain.EmployeeS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

public class EmployeeAction extends ActionSupport implements ModelDriven<Employee>{
	private Employee emp = new Employee();
	private EmployeeService employeeService = new EmployeeService();
	private PageModel pageModel = new PageModel();
	private String[] employeeIds;
	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}
	
	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public PageModel getPageModel() {
		return pageModel;
	}

	public void setPageModel(PageModel pageModel) {
		this.pageModel = pageModel;
	}

	public String[] getEmployeeIds() {
		return employeeIds;
	}

	public void setEmployeeIds(String[] employeeIds) {
		this.employeeIds = employeeIds;
	}

	@Override
	public Employee getModel() {
		// TODO Auto-generated method stub
		return this.emp;
	}
	
	//添加员工界面
	public String employeeAddForm() throws Exception {
		getListData();
		return SUCCESS;
	}
	
	//修改员工界面
	public String employeeUpdateForm() throws Exception {
		ValueStack vs = ActionContext.getContext().getValueStack();
		ArrayList<Employee> list = employeeService.queryEmployee(getModel());
		getListData();
		if(list!=null&&list.size()>0) {
			setEmp(list.get(0));
		}
		return SUCCESS;
	}
	
	//查询员工界面
	public String employeeQueryForm() throws Exception {
		getListData();
		ValueStack vs = ActionContext.getContext().getValueStack();
		ArrayList<EmployeeS> allEmployee = employeeService.getAllEmployee(getPageModel());
		vs.set("empList", allEmployee);
		return SUCCESS;
	}
	/*//添加员工
	public String employeeAdd() throws Exception {
		ValueStack vs = ActionContext.getContext().getValueStack();
		boolean pos = employeeService.addEmployee(getModel());
		if(pos) {
			vs.set("errorA", "no");
		}else {
			vs.set("errorA", "yes");
		}
		return SUCCESS;
	}*/
	//查询员工
	public String employeeQuery() throws Exception {
		ValueStack vs = ActionContext.getContext().getValueStack();
		getListData();
		ArrayList<EmployeeS> list3 = employeeService.queryEmployeeByPage(getModel(), getPageModel());
		vs.set("empList", list3);
		return SUCCESS;
	}
	//删除员工
	public String employeeDelete() throws Exception {
		ValueStack vs = ActionContext.getContext().getValueStack();
		if(getEmployeeIds()==null||getEmployeeIds().length==0) {
			vs.set("errorD", "noC");
		}else {
			vs.set("errorD", "no");
			for(String s: getEmployeeIds() ) {
				Employee e = new Employee();
				e.setId(Integer.parseInt(s));
				boolean pos = employeeService.deleteEmployee(e);
				if(!pos) {
					vs.set("errorD", "yes");
				}
			}
		}
		return SUCCESS;
	}
	/*//修改员工
	public String employeeUpdate() throws Exception {
		ValueStack vs = ActionContext.getContext().getValueStack();
		boolean pos = employeeService.udateEmployee(getModel());
		getListData();
		if(pos) {
			vs.set("errorU", "no");
		}else {
			vs.set("errorU", "yes");
		}
		return SUCCESS;
	}*/
	//查询身份证是否存在
	public String checkCardId() throws Exception {
		boolean pos = employeeService.isSameCardId(getModel());
        if(pos) {
        	getModel().setCardId("身份证已被使用");
        }else {
        	getModel().setCardId("pass");
        }
		return SUCCESS;
	}
	public void getListData() {
		ValueStack vs = ActionContext.getContext().getValueStack();
		JobService jobService = new JobService();
		DeptService deptService = new DeptService();
		ArrayList<Job> list = jobService.getAllJobs();
		ArrayList<Department> list2 = deptService.getAllDepartments();
		vs.set("jobList", list);
		vs.set("deptList", list2);
	}
	
	

}
