<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<s:bean name="java.util.Date" var="nowDate"></s:bean>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
</head>
<body>
<jsp:include  page="quote.jsp"/>
<div id="wrapper">
		<nav class="navbar navbar-default top-navbar" role="navigation">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle waves-effect waves-dark"
				data-toggle="collapse" data-target=".sidebar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand waves-effect waves-dark" href="index"><i
				class="large material-icons"><i class="material-icons dp48">thumbs_up_down</i></i>
				<strong>人事管理系统</strong></a>

		</div>

		<ul class="nav navbar-top-links navbar-right">
			<li><a class="dropdown-button waves-effect waves-dark"></a></li>
			<li><a class="dropdown-button waves-effect waves-dark"><i
					class="fa fa-clock-o fa-fw"></i>&nbsp;
					<s:date name="#nowDate" format="yyyy年MM月dd日   EEEE"/>
				</a></li>
						
			<!-- <li><a class="dropdown-button waves-effect waves-dark" href="#!"
				data-activates="dropdown3"><i class="fa fa-tasks fa-fw"></i> <i
					class="material-icons right">arrow_drop_down</i></a></li>
			<li><a class="dropdown-button waves-effect waves-dark" href="#!"
				data-activates="dropdown2"><i class="fa fa-bell fa-fw"></i> <i
					class="material-icons right">arrow_drop_down</i></a></li> -->
					
			<li><a class="dropdown-button waves-effect waves-dark" href="#!"
				data-activates="dropdown1"><i class="fa fa-user fa-fw"></i> 
				<b>${sessionScope.activeUser.username }</b> <i class="material-icons right">arrow_drop_down</i></a></li>
		</ul>
		</nav>
		<!-- Dropdown Structure -->
		<ul id="dropdown1" class="dropdown-content" style="margin-left: 30px">
			<!-- <li><a href="#"><i class="fa fa-user fa-fw"></i> My Profile</a>
			</li>
			<li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a></li> -->
			<li><a href="${pageContext.request.contextPath }/user_loginout"><i class="fa fa-sign-out fa-fw"></i> 注销</a></li>
		</ul>

		<!--/. NAV TOP  -->
		<nav class="navbar-default navbar-side" role="navigation">
		<div class="sidebar-collapse">
			<ul class="nav" id="main-menu">


				<li><a href="#" class="waves-effect waves-dark"><i
						class="fa fa-user fa-fw"></i>用户管理<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="${pageContext.request.contextPath}/queryUserForm" target="myFrame">用户查询</a></li>
						<s:if test="#session.activeUser.status==2">
							<li><a href="${pageContext.request.contextPath}/addUser" target="myFrame">添加用户</a></li>
						</s:if>
					</ul>
				</li>
				<li><a href="#" class="waves-effect waves-dark"><i
						class="fa fa-sitemap"></i>部门管理<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="${pageContext.request.contextPath}/deptQueryForm" target="myFrame">部门查询</a></li>
						<li><a href="${pageContext.request.contextPath}/addDeptForm" target="myFrame">添加部门</a></li>
					</ul></li>
				<li><a href="#" class="waves-effect waves-dark"><i
						class="fa fa-table"></i>职位管理<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="${pageContext.request.contextPath}/jobQueryForm" target="myFrame">职位查询</a></li>
						<li><a href="${pageContext.request.contextPath}/addJobForm" target="myFrame">添加职位</a></li>
					</ul></li>
				<li><a href="#" class="waves-effect waves-dark"><i
						class="fa fa-qrcode"></i>员工管理<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="${pageContext.request.contextPath}/employeeQueryForm" target="myFrame">员工查询</a></li>
						<li><a href="${pageContext.request.contextPath}/employeeAddForm" target="myFrame">添加员工</a></li>
					</ul></li>
				<li><a href="" class="waves-effect waves-dark"><i
						class="fa fa-fw fa-file"></i>下载中心<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="${pageContext.request.contextPath}/docQueryForm" target="myFrame">文档查询</a></li>
						<li><a href="${pageContext.request.contextPath}/uploadFileForm" target="myFrame">上传文档</a></li>
					</ul></li>

			</ul>

		</div>
		</nav>
</div>


				<!--正文内容  -->
				<iframe frameborder="0" name="myFrame" src="${pageContext.request.contextPath}/index2" marginheight="0" 
				marginwidth="0" scrolling="yes" width="100%" height="676">
					您的浏览器不支持嵌入式框架，或者当前配置为不显示嵌入式框架。
				</iframe>



</body>
</html>