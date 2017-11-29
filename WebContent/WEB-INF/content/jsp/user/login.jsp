<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>人事管理登陆</title>
    <link href="css/ibootstrap.css" rel='stylesheet' type='text/css' />
    <link href="css/istyle.css" media="all" rel="stylesheet" type="text/css" />
    <script type="text/javascript">
      var errori ='<%=request.getAttribute("error")%>';
      if(errori=='yes'){
         alert("用户名或密码错误，登录失败!");
      }
     
  </script>
  </head>
  
<body class="login2">
<div class="login-wrapper">
    <h3 style="margin-top: 30%">人事管理登录</h3>
    <form action="${pageContext.request.contextPath }/user_login" method="post">
        <div class="form-group">
            <div class="input-group">
                <span class="input-group-addon"><i>账号</i></span>
                <input class="form-control" placeholder="请输入您的账号" name="loginname" required type="text">
            </div>
        </div>
        <div class="form-group">
            <div class="input-group">
                <span class="input-group-addon"><i >密码</i></span>
                <input class="form-control" placeholder="请输入您的密码" type="password" required name="password">
            </div>
        </div>
        <input style="margin:20px 0 30px" class="btn btn-lg btn-primary btn-block" type="submit" value="登录">
    </form>
   
</div>
</body>
</html>
