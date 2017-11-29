<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
 String path = request.getContextPath();
 String basePath = request.getScheme() + "://"
   + request.getServerName() + ":" + request.getServerPort()
   + path + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
var msg = '<%=session.getAttribute("notLogin")%>';
if(msg!='null'){
	  alert(msg);
	  <%session.removeAttribute("notLogin");%>
}
   window.top.location.href="<%=basePath%>loginForm";
</script>
</head>
<body>
<%-- <s:action name="loginForm" executeResult="true"/> --%>
</body>
</html>