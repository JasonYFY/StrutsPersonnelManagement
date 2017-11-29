package com.jason.action;

import java.util.ArrayList;

import com.jason.domain.Department;
import com.jason.domain.PageModel;
import com.jason.service.DeptService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

public class DepartmentAction extends ActionSupport implements ModelDriven<Department>{
	private Department dept = new Department();
	private DeptService deptService = new DeptService();
	private PageModel pageModel = new PageModel();
	private String[] deptIds;
	
	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public PageModel getPageModel() {
		return pageModel;
	}

	public void setPageModel(PageModel pageModel) {
		this.pageModel = pageModel;
	}
	

	public String[] getDeptIds() {
		return deptIds;
	}

	public void setDeptIds(String[] deptIds) {
		this.deptIds = deptIds;
	}

	@Override
	public Department getModel() {
		return this.dept;
	}
	//部门查询页面
	public String deptQueryForm() throws Exception {
		ValueStack vs = ActionContext.getContext().getValueStack();
		ArrayList<Department> deptList = deptService.getAllDepartments(getPageModel());
		vs.set("deptList", deptList);
		return SUCCESS;
	}
	//部门修改页面
	public String deptUpdateForm() throws Exception {
		ArrayList<Department> list = deptService.getDepartments(getModel());
		if(list!=null&&list.size()>0) {
			Department d = list.get(0);
			setDept(d);
		}
		return SUCCESS;
	}
	//添加部门
	public String deptAdd() throws Exception {
		ValueStack vs = ActionContext.getContext().getValueStack();
		boolean pos = deptService.addDepartment(getModel());
		if(pos) {
			vs.set("errorD", "no");
		}else {
			vs.set("errorD", "yes");
		}
		
		return SUCCESS;
	}
	//查询部门
	public String deptQuery() throws Exception {
		ArrayList<Department> deptByPage = deptService.getDeptByPage(getModel(), getPageModel());
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.set("deptList", deptByPage);
		return SUCCESS;
	}
	//删除部门
	public String deptDelete() throws Exception {
		ValueStack vs = ActionContext.getContext().getValueStack();
		if(getDeptIds()!=null&&getDeptIds().length>0) {
			for(String s:getDeptIds()) {
				Department dept = new Department();
				dept.setId(Integer.parseInt(s));
				boolean pos = deptService.DeleteDepartment(dept);
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
	
	//修改部门处理
	public String deptUpdate() throws Exception {
		ValueStack vs = ActionContext.getContext().getValueStack();
		boolean d = deptService.udateDepartment(dept);
		
		if (d) {
			vs.set("errorU", "no");
		} else {
			vs.set("errorU", "yes");
		}
		return SUCCESS;
	}
	//检查部门名称是否相同
	public String checkDeptName() throws Exception {
		boolean pos = deptService.isSameDeptName(getModel());
		if(pos) {
        	 getModel().setDeptName("部门名称已被使用");
        }else {
        	getModel().setDeptName("pass");
        }
		return SUCCESS;
	}

}
