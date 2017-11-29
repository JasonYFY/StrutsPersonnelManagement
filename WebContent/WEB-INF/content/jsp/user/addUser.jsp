<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加用户</title>

<script type="text/javascript">
      var errori ='<%=request.getAttribute("errorA")%>';
      if(errori=='yes'){
         alert("登录名重复，添加失败!");
      }else if(errori=='no'){
    	  alert("添加成功!");
      }
     
  </script>
 
</head>
<body>
 <jsp:include  page="../quote.jsp"/>
 <div id="page-wrapper">
			<div class="header">
				<h1 class="page-header">添加用户</h1>
				<ol class="breadcrumb">
					<li><a href="index2">首页</a></li>
					<li>用户管理</li>
					<li>添加用户</li>
					<li><span style="color: red;font-size: 20px"><s:fielderror /></span></li>
			</div>
			<div id="page-inner">
				<form action="${pageContext.request.contextPath }/user_register" method="post">
					<table>
						<tr>
							<td>姓名:<input type="text" name="username" required/></td>
							<td>
								<div style="margin-left: 30px;">状态:</div>
								<s:select class="form-control input-sm" style="width: 140px;margin:12px 30px 15px 30px" 
								name="status" list="#{'1':'普通管理员','2':'超级管理员'}" />
							</td>
						</tr>
						<tr>
							<td width="50%">登录名:<span id="ex" style="color: red;"></span><input type="text" name="loginname" id="loginname" required/></td>
							<td width="50%">
								<div style="margin-left: 30px;">密码:</div>
								<s:textfield style="margin-left: 30px" name="password" required="true" />
							</td>
						</tr>
						<tr>
							<td  colspan="2">
								<button style="margin-right: 80px;width: 75px" class="waves-effect waves-light btn" type="submit" id="submit">添加</button>
								<button style="width: 75px" class="waves-effect waves-light btn" type="reset" >重置</button>
							</td>
						</tr>
					</table>
				</form>

			</div>
			<!-- /. PAGE INNER  -->
		</div>
		
	<script>
	$('#loginname').on('blur',function(){
        var loginname = $(this).val();
        if(loginname!=""){
        	$.ajax({
                url : "./check_loginname",
                type : 'post',
                data : {loginname:loginname},
                dataType : 'json',
                success : function(data){
                  if(data['loginname']=="登录名已被使用"){
                	 $('#ex').html("(提示---"+data['loginname']+")");
                     $('#loginname').focus();
                     $('#submit').attr("disabled","disabled")
                  }else{
                	$('#ex').html("");
                  	$('#submit').removeAttr("disabled")
                  }
                },
                error : function() {
                    alert("请求失败")
                }
            })
        }
    })
	</script>
</body>
</html>