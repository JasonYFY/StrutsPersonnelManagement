<%@page import="com.jason.service.domain.EmployeeS"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改员工</title>

<script type="text/javascript">
      var errori ='<%=request.getAttribute("errorU")%>';
      if(errori=='yes'){
         alert("修改失败!");
      }else if(errori=='no'){
    	  alert("修改成功!");
      }
      
  </script>
 <%
 	/* EmployeeS s = (EmployeeS)request.getAttribute("emp"); 
 	String bDate = null;
 	java.util.Date d = s.getEmployee().getBirthday();
 	if(d!=null){
 		bDate = new SimpleDateFormat("yyyy-MM-dd").format(d);
 	}
 	pageContext.setAttribute("bDate", bDate); */
 
 %>
</head>
<body>
<jsp:include  page="../quote.jsp"/>
<div id="page-wrapper">
	<div class="header">
		<h1 class="page-header">修改员工</h1>
		<ol class="breadcrumb">
			<li><a href="index2">首页</a></li>
			<li>员工管理</li>
			<li>修改员工</li>
			<li><span style="color: red;font-size: 20px"><s:fielderror /></span></li>
		</ol>
	</div>
	<div id="page-inner">
		<form action="${pageContext.request.contextPath }/employee_update" method="post">
			<s:hidden name="id" />
			<table >
				<tr>
					<td>姓名:<s:textfield name="emp.employeeName" required="true"/></td>
					<td width="50%">身份证号码:
						<span id="ex" style="color: red;"></span>
						<s:textfield name="emp.cardId" id="cardId"  required="true"/>
					</td>
				</tr>
				<tr>
					<td>性别:
						<s:select list="#{'1':'男','0':'女'}" class="form-control input-sm" style="width: 80px;margin-top: 10px" name="emp.sex"/>
					</td>
					<td>职位:<br/>
						<s:select list="jobList" listValue="jobName" listKey="id" class="form-control input-sm" 
								style="width: 150px;margin-top: 10px" name="emp.jobId" headerValue="--请选择职位--" headerKey="" required="true" />
					</td>
				</tr>
				<tr>
					<td><br/>学历:<s:textfield name="emp.education" /></td>
					<td><br/>邮箱:<span id="exE" style="color: red;"></span><s:textfield name="emp.email" id="email"/></td>
				</tr>
				<tr>
					<td>手机:<span id="exP" style="color: red;"></span><s:textfield name="emp.phone" id="phone"  required="true"/></td>
					<td>电话号码:<s:textfield name="emp.tel" /></td>
				</tr>
				<tr>
					<td>政治面貌:<s:textfield name="emp.party" /></td>
					<td>QQ号码:<s:textfield name="emp.qqNum"/></td>
				</tr>
				<tr>
					<td>联系地址:<s:textfield name="address" /></td>
					<td>邮政编码:<s:textfield name="postCode"/></td>
				</tr>
				<tr>
					<td>出生日期:<input type="date" value='<s:date name="emp.birthday" format="yyyy-MM-dd"/>' name="emp.birthday" /></td>
					<td>民族:<s:textfield name="emp.race" /></td>
				</tr>
				<tr>
					<td>所学专业:<s:textfield name="emp.speciality" /></td>
					<td>爱好:<s:textfield name="emp.hobby" /></td>
				</tr>
				<tr>
					<td>备注:<s:textfield name="emp.remark" style="width: 95%" /></td>
					<td>所属部门:
						<s:select list="deptList" listKey="id" listValue="deptName" headerValue="--请选择职位--" headerKey=""
								class="form-control input-sm" style="width: 150px;margin-top: 10px" name="emp.deptId" required="true" />
					</td>
				</tr>
				</table><br/>
				<div class="form-group">
					<s:if test="#session.activeUser.status==2">
                       	<div class="col-sm-2">
                      		<s:submit class="form-control" id="submit" value="修改"/>
                      	</div>
                    </s:if>
					<div class="col-sm-2">
						<a href="${pageContext.request.contextPath}/employeeQueryForm">
						<input class="form-control" type="button" value="返回"/></a>
					</div>
				</div>
					
			
		
		</form>
	
	</div>
		<!-- /. PAGE INNER  -->
</div>
		
<script>
$('#cardId').on('change',function(){
    var cardId = $(this).val();
    var reg = /^[0-9]{18}$/;
   	if(!reg.test(cardId)){
    	$('#ex').html("(提示---请填写正确格式)");
    	$('#cardId').focus();
        $('#submit').attr("disabled","disabled")
    }else{
        $.ajax({
            url : "./check_cardId",
            type : 'post',
            data : {cardId:cardId},
            dataType : 'json',
            success : function(data){
              
              if(data['cardId']=="身份证已被使用"){
            	  $('#ex').html("(提示---"+data['cardId']+")")
                  $('#cardId').focus();
                  $('#submit').attr("disabled","disabled")
              }else{
            	  $('#ex').html("")
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
<script type="text/javascript">
    $('#phone').on('blur',function(){
    	var phone = $(this).val();
    	var reg = /^1[0-9]{10}$/;
    	if(reg.test(phone)){
    		$('#exP').html("");
    		$('#submit').removeAttr("disabled")
    	}else{
    		$('#exP').html("(提示---请填写正确的手机号)");
    		$('#phone').focus();
            $('#submit').attr("disabled","disabled")
    	}
    })
    $('#email').on('blur',function(){
    	var email = $(this).val();
    	var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
    	if(reg.test(email)){
    		$('#exE').html("");
    		$('#submit').removeAttr("disabled")
    	}else{
    		$('#exE').html("(提示---请填写正确的邮箱地址)");
    		$('#email').focus();
            $('#submit').attr("disabled","disabled")
    	}
    })
</script>
</body>
</html>