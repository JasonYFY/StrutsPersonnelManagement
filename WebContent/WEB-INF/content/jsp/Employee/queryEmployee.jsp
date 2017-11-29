<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工查询</title>
<script type="text/javascript">
      var errori ='<%=request.getAttribute("errorD")%>';
      if(errori=='yes'){
         alert("删除失败!");
      }else if(errori=='noC'){
    	  alert("请选择需要删除的选项!");
      }else if(errori=='no'){
    	  alert("删除成功!");
      }
      function allCheck(check){
  	　　　　var checkbox=document.getElementsByName("employeeIds");
  	　　　　if(check.checked){
  	　　　　　　for(var i=0;i<checkbox.length;i++){
  	　　　　　　　　checkbox[i].checked="checked";
  	　　　　　　}    	
  	　　　　}else{
  	　　　　　　for(var i=0;i<checkbox.length;i++){
  	　　　　　　　　checkbox[i].checked="";
  	　　　　　　} 
  	　　　　}
  	　　}
      function toPage(pageIndex)
    {
    	$("#pageIndex").attr("value",pageIndex);
  	    $("#empForm").attr("action", "<%=request.getContextPath()%>/employee_query");
  		$("#empForm").submit();
    }
    	
    function pagerJump()
    {
    	var page_size=$('#pager_jump_page_size').val();
		
 		var regu = /^[1-9]\d*$/;
 			
 		if (!regu.test(page_size)||page_size < 1 || page_size >${pageModel.totalPageSum})
 		{
 			alert('请输入[1-'+ ${pageModel.totalPageSum} +']之间的页码！');	
 		}else
 		{
	 		$("#pageIndex").attr("value",page_size);
 	        $("#empForm").attr("action", "<%=request.getContextPath()%>/employee_query");
 	    	$("#empForm").submit();
 		}
    }
  </script>
</head>
<body>
 <jsp:include  page="../quote.jsp"/>
 <div id="page-wrapper">
			<div class="header">
				<h1 class="page-header">员工查询</h1>
				<ol class="breadcrumb">
					<li><a href="${pageContext.request.contextPath}index2">首页</a></li>
					<li>员工管理</li>
					<li>员工查询</li>
				</ol>
			</div>
			<div id="page-inner">
				<form class="form-horizontal" id="empForm" role="form" action="${pageContext.request.contextPath }/employee_query" method="post">
					<s:hidden  name="pageModel.pageIndex"  id="pageIndex" />
                       <div class="form-group">
                       	<label class="col-sm-1 control-label"><font size="3px" color="#696969">职位:</font></label>
                          <div class="col-sm-2">
                             <s:select list="jobList" listValue="jobName" listKey="id" class="form-control input-sm" 
                             style="width: 140px" name="jobId"  headerValue="--请选择职位--" headerKey=""   />
                          </div>
                          <label class="col-sm-1 control-label"><font size="3px" color="#696969">姓名:</font></label>
                          <div class="col-sm-2">
                             <s:textfield class="form-control" name="employeeName"/>
                          </div>
                          <label class="col-sm-2 control-label"><font size="3px" color="#696969">身份证号码:</font></label>
                          <div class="col-sm-2">
                             <s:textfield class="form-control" name="cardId" />
                          </div>
                          </div>
                          <div class="form-group">
                          <label class="col-sm-1 control-label"><font size="3px" color="#696969">性别:</font></label>
                          <div class="col-sm-2">
	                          <s:select list="#{'1':'男','0':'女' }" class="form-control input-sm" 
	                          style="width: 130px" name="sex" headerKey="" headerValue="--请选择性别--"/>
                          </div>
                          <label class="col-sm-1 control-label"><font size="3px" color="#696969">手机:</font></label>
                          <div class="col-sm-2">
                             <s:textfield class="form-control" name="phone" />
                          </div>
                          <label class="col-sm-2 control-label"><font size="3px" color="#696969">所属部门:</font></label>
                          <div class="col-sm-2">
	                          <s:select list="deptList" listKey="id" listValue="deptName" headerValue="--请选择职位--" headerKey="" 
	                          class="form-control input-sm" style="width: 150px" name="deptId"  />
                          </div>
                          </div>
                          <div class="form-group">
                          <div class="col-sm-2">
                             <s:submit class="form-control" value="搜索" />
                          </div>
                          </form>
                          <form action="${pageContext.request.contextPath }/employee_delete" method="post">
                          <s:hidden  name="pageModel.pageIndex"  id="pageIndex" />
                          <div class="col-sm-2">
                            <s:if test="#session.activeUser.status==2">
                             <s:submit class="form-control" value="删除" />
                            </s:if>
                          </div>
                       </div>
                       <div class="table-responsive table-bordered">
                       <table class="table">
                       		<tr>
                       		<th><input type="checkbox" id="test0" onclick="allCheck(this)"/><label for="test0"></label></th>
                       		 <th>姓名</th>
                       		 <th>性别</th>
                       		 <th>手机号码</th>
                       		 <th>邮箱</th>
                       		 <th>职位</th>
                       		 <th>学历</th>
                       		 <th>身份证号码</th>
                       		 <th>部门</th>
                       		 <th>联系地址</th>
                       		 <th>建档日期</th>
                       		 <th>操作</th>
                       		</tr>
                       		<s:iterator value="empList" var="item">
                       		<tr>
                       		 <td>
                       		 	<input type="checkbox" name="employeeIds" value='<s:property value="#item.employee.id"/>' id="test<s:property value="#item.employee.id"/>"/>
	                       		<label for="test<s:property value="#item.employee.id"/>"></label>
                       		 </td>
                       		 <td><s:property value="#item.employee.employeeName"/></td>
                       		 <td>
                       		 	<s:if test="#item.employee.sex==1">男</s:if>
                       		 	<s:elseif test="#item.employee.sex==0">女</s:elseif>
                       		 </td>
                       		 <td><s:property value="#item.employee.phone"/></td>
                       		 <td><s:property value="#item.employee.email"/></td>
                       		 <td><s:property value="#item.job.jobName"/></td>
                       		 <td><s:property value="#item.employee.education"/></td>
                       		 <td><s:property value="#item.employee.cardId"/></td>
                       		 <td><s:property value="#item.dept.deptName"/></td>
                       		 <td><s:property value="#item.employee.address"/></td>
                       		 <td>
                       		 	<s:date name="#item.employee.createDate" format="yyyy年MM月dd日"/>
                       		 </td>
                       		 <td>
                       		 	<s:url action="employeeUpdateForm" var="edit">
                       		 		<s:param name="id"><s:property value="#item.employee.id"/></s:param>
                       		 	</s:url>
                       		 	<s:a href="%{#edit}"><i class="fa fa-edit"></i></s:a>
                       		 </td>
                       		 </tr>
                       		</s:iterator>
                       		
                       </table>
                       </div>
			</form>
			<jsp:include  page="../page.jsp"/>
			</div>
			<!-- /. PAGE INNER  -->
		</div>
</body>
</html>