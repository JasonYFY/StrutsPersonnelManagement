<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加部门</title>
<script type="text/javascript">
      var errori ='<%=request.getAttribute("errorD")%>';
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
				<h1 class="page-header">添加部门</h1>
				<ol class="breadcrumb">
					<li><a href="index.jsp">首页</a></li>
					<li>部门管理</li>
					<li>添加部门</li>
				</ol>
			</div>
			<div id="page-inner">
				<form action="${pageContext.request.contextPath }/dept_add" method="post">
					<table>
						<tr>
							<td width="50%">部门名称:
								<span id="ex" style="color: red;"></span>
								<input type="text" name="deptName" id="deptName" required="true"/>
							</td>
							<td>详细描述:<input type="text" name="remark"/></td>
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
		</div>
<script>
	$('#deptName').on('change',function(){
        var deptName = $(this).val();
        	$.ajax({
                url : "./check_deptName",
                type : 'post',
                data : {deptName:deptName},
                dataType : 'json',
                success : function(data){
                  if(data['deptName']=="部门名称已被使用"){
                	  $('#ex').html("(提示---"+data['deptName']+")")
                      $('#deptName').focus();
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
    })
</script>
</body>
</html>