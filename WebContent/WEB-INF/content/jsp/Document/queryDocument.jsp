<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文档查询</title>
<script type="text/javascript">
      var errori ='<%=request.getAttribute("errorD")%>';
      if(errori=='yes'){
         alert("删除失败!");
      }else if(errori=='no'){
    	  alert("删除成功!");
      }
      function allCheck(check){
    	  var checkbox=document.getElementsByName("docIds");
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
  	    $("#docForm").attr("action", "<%=request.getContextPath()%>/doc_query");
  		$("#docForm").submit();
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
     	        $("#docForm").attr("action", "<%=request.getContextPath()%>/doc_query");
     	    	$("#docForm").submit();
     		}
    	}　　　
  </script>
</head>
<body>
 <jsp:include  page="../quote.jsp"/>
 <div id="page-wrapper">
			<div class="header">
				<h1 class="page-header">文档查询</h1>
				<ol class="breadcrumb">
					<li><a href="index2">首页</a></li>
					<li>下载中心</li>
					<li>文档查询</li>
				</ol>
			</div>
			<div id="page-inner">
				<form class="form-horizontal" id="docForm" role="form" action="${pageContext.request.contextPath }/doc_query" method="post">
					<s:hidden  name="pageModel.pageIndex"  id="pageIndex" />
                       <div class="form-group">
                          <label class="col-sm-2 control-label"><font size="4px" color="#696969">标题:</font></label>
                          <div class="col-sm-2">
                             <s:textfield class="form-control" name="title"/>
                          </div>
                          <div class="col-sm-2">
                             <s:submit class="form-control"  value="搜索" />
                          </div>
               	</form>
                       </div>
                       <div class="table-responsive table-bordered">
                       <table class="table">
                       		<tr>
                       		<th><input type="checkbox" id="test0"  onclick="allCheck(this)"/><label for="test0"  ></label></th>
                       		 <th>标题</th>
                       		 <th>创建时间</th>
                       		 <th>创建人</th>
                       		 <th>描述</th>
                       		 <s:if test="#session.activeUser.status==2">
                       		 	<th>操作</th>
                       		 </s:if>
                       		 <th>下载</th>
                       		</tr>
                       		<s:iterator value="fileList" var="item">
                       		<tr>
                       		 <td>
                       		 	<input type="checkbox" name="docIds" value='<s:property value="#item.doc.id"/>' id='test<s:property value="#item.doc.id"/>'/>
	                       		<label for="test<s:property value="#item.doc.id"/>"></label>
                       		 </td>
                       		 <td><s:property value="#item.doc.title"/></td>
                       		 <td>
                       		 	<s:date name="#item.doc.createDate" format="yyyy年MM月dd日"/>
							</td>
                       		 <td><s:property value="#item.user.username"/></td>
                       		 <td><s:property value="#item.doc.remark"/></td>
                       		 <s:if test="#session.activeUser.status==2">
                       		 <td>
                       		 	<s:url action="doc_delete" var="delete">
                       		 		<s:param name="id"><s:property value="#item.doc.id"/></s:param>
                       		 		<s:param name="pageModel.pageIndex"><s:property value="pageModel.pageIndex"/></s:param>
                       		 	</s:url>
                       		 	<s:a href="%{#delete}"><i class="fa fa-times-circle"></i></s:a>
                       		 </td>
                       		 </s:if>
                       		 <td>
                       		 	<s:url action="doc_download" var="download">
                       		 		<s:param name="id"><s:property value="#item.doc.id"/></s:param>
                       		 	</s:url>
                       		 	<s:a href="%{#download}"><i class="fa fa-cloud-download"></i></s:a>
                       		 </td>
                       		 </tr>
                       		</s:iterator>
                       </table>
                       
                    <jsp:include  page="../page.jsp"/>
              </div>
			</div>
		
</body>
</html>