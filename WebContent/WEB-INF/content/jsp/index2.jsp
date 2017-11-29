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
 <div id="page-wrapper">
			<div class="header">
				<h1 class="page-header">首页</h1>
				<ol class="breadcrumb">
					<li>首页</li>
				</ol>

			</div>
			<div id="page-inner">


			<!-- 正文 -->



			</div>
			<!-- /. PAGE INNER  -->
		</div>
</body>
</html>