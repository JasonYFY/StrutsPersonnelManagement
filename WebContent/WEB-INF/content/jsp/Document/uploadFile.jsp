<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传文档</title>
<script type="text/javascript">
      var errori ='<%=request.getAttribute("errorF")%>';
      if(errori=='yes'){
         alert("添加失败!");
      }else if(errori=='no'){
    	  alert("添加成功!");
      }  
  </script>
</head>
<body>
 <jsp:include  page="../quote.jsp"/>
 <div id="page-wrapper">
			<div class="header">
				<h1 class="page-header">上传文档</h1>
				<ol class="breadcrumb">
					<li><a href="index2">首页</a></li>
					<li>下载中心</li>
					<li>上传文档</li>
				</ol>
			</div>
			<div id="page-inner">
				<form action="${pageContext.request.contextPath }/upload_file" enctype="multipart/form-data" method="post">
					<table width="30px">
						<tr>
							<td>文档标题:<input type="text" name="title" required/></td>
						</tr>
						<tr>
							<td>文档描述</td>
						</tr>
						<tr>
							<td><textarea style="height: 200px"  cols="" name="remark"></textarea></td>
						</tr>
						<tr>
							<td><br/>文档:</td>
						</tr>
						<tr>
							<td><s:file name="uploadData" required="true"/></td>
						</tr>
						<tr>
							<td>
							<br/>
								<button style="margin-right: 80px;width: 75px" class="waves-effect waves-light btn" type="submit" id="submit">添加</button>
								<button style="width: 75px" class="waves-effect waves-light btn" type="reset" >重置</button>
							</td>
						</tr>
					</table>
				</form>

			</div>
		</div>
</body>
</html>