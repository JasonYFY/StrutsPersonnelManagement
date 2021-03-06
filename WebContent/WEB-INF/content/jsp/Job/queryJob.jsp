<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>职位查询</title>
<script type="text/javascript">
      var errori ='<%=request.getAttribute("errorDelete")%>';
      if(errori=='yes'){
         alert("删除失败!");
      }else if(errori=='no'){
    	  alert("请选择需要删除的选项!");
      }else if(errori=='ok'){
    	  alert("删除成功!");
      }
      function allCheck(check){
    	  var checkbox=document.getElementsByName("jobIds");
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
  	    $("#jobForm").attr("action", "<%=request.getContextPath()%>/job_query");
  		$("#jobForm").submit();
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
     	        $("#jobForm").attr("action", "<%=request.getContextPath()%>/job_query");
     	    	$("#jobForm").submit();
     		}
    	}　　　　
  </script>
</head>
<body>
 <jsp:include  page="../quote.jsp"/>
 <div id="page-wrapper">
			<div class="header">
				<h1 class="page-header">职位查询</h1>
				<ol class="breadcrumb">
					<li><a href="index2">首页</a></li>
					<li>职位管理</li>
					<li>职位查询</li>
				</ol>
			</div>
			<div id="page-inner">
				<form class="form-horizontal" id="jobForm" role="form" action="${pageContext.request.contextPath }/job_query" method="post">
                      <s:hidden  name="pageModel.pageIndex"  id="pageIndex" />
                       <div class="form-group">
                          <label class="col-sm-2 control-label"><font size="4px" color="#696969">职位名称:</font></label>
                          <div class="col-sm-2">
                             <s:textfield class="form-control" name="jobName"/>
                          </div>
                          <div class="col-sm-2">
                             <s:submit class="form-control"  value="搜索" />
                          </div>
                </form>
                <form action="${pageContext.request.contextPath }/job_delete" method="post">
                	<s:hidden  name="pageModel.pageIndex"  id="pageIndex" />
                          <div class="col-sm-2">
                            <s:if test="#session.activeUser.status==2">
                          		<s:submit class="form-control" value="删除"/>
                          	</s:if>
                          </div>
                       </div>
                       <div class="table-responsive table-bordered">
                       <table class="table">
                       		<tr>
                       		<th>
                       			<input type="checkbox" id="test0"  onclick="allCheck(this)"/><label for="test0"  ></label>
                       		</th>
                       		 <th>职位名称</th>
                       		 <th>详细信息</th>
                       		 <th>操作</th>
                       		</tr>
                       		<s:iterator value="jobList" var="item">
                       		<tr>
                       		 <td>
                       		 	<input type="checkbox" name="jobIds" value='<s:property value="#item.id"/>' id='test<s:property value="#item.id"/>'/>
	                       		<label for="test<s:property value="#item.id"/>"></label>
                       		 </td>
                       		 <td><s:property value="#item.jobName"/></td>
                       		 <td><s:property value="#item.remark"/></td>
                       		 <td>
                       		 	<s:url action="jobUpdateForm" var="edit">
                       		 		<s:param name="id"><s:property value="#item.id"/></s:param>
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
		
</body>
</html>