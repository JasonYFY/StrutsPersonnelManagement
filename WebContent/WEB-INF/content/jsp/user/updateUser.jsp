<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户查询</title>
<script type="text/javascript">
      var errori ='<%=request.getAttribute("errorU")%>';
      if(errori=='yes'){
         alert("修改失败!");
      }else if(errori=='no'){
    	  alert("修改成功!");
      }
  </script>
</head>
<body>
<jsp:include  page="../quote.jsp"/>
 <div id="page-wrapper">
			<div class="header">
				<h1 class="page-header">用户编辑</h1>
				<ol class="breadcrumb">
					<li><a href="index2">首页</a></li>
					<li>用户管理</li>
					<li>用户编辑</li>
					<li><span style="color: red;font-size: 20px"><s:fielderror /></span></li>
				</ol>
			</div>
			<div id="page-inner">
				<form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/user_update" method="post">
				<s:hidden name="id" />
                       <div class="form-group">
                          <label class="col-sm-2 control-label"><font size="4px" color="#696969">用户名:</font></label>
                          <div class="col-sm-2">
                             <s:textfield class="form-control"  name="user.username" required="true" />
                          </div>
                          <label class="col-sm-2 control-label"><font size="4px" color="#696969">密码:</font></label>
                          <div class="col-sm-2">
                             <s:textfield class="form-control" name="user.password" required="true"/>
                          </div>
                       </div>
                       <div class="form-group">
                       	<label class="col-sm-2 control-label"><font size="4px" color="#696969">登陆名:</font><br/><span id="ex" style="color: red;"></span></label>
                          <div class="col-sm-2">
                             <s:textfield class="form-control"  name="user.loginname" id="loginname" required="true" />
                          </div>
                       	 <label class="col-sm-2 control-label"><font size="4px" color="#696969">状态:</font></label>
                          <div class="col-sm-2">
                             <s:select style="width: 118px;margin-top: 5px" list="#{'1':'普通管理员','2':'超级管理员'}" class="form-control"  name="user.status" />
                          </div>
                       </div>
                       <div class="form-group">
	                       <s:if test="#session.activeUser.status==2">
	                       	<div class="col-sm-2">
	                      		<s:submit id="submit" class="form-control" value="修改"/>
	                      	</div>
	                       </s:if>
                          <div class="col-sm-2">
                             <a href="${pageContext.request.contextPath}/queryUserForm"><input class="form-control"  type="button" value="返回" /></a>
                          </div>
                       </div>
                 </form>
			</div>
		</div>
		
<script>
	$('#loginname').on('change',function(){
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