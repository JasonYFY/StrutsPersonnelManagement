<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加职位</title>
<script type="text/javascript">
      var errori ='<%=request.getAttribute("errorJ")%>';
      if(errori=='yes'){
         alert("职位名称重复，添加失败!");
      }else if(errori=='no'){
    	  alert("添加成功!");
      }  
  </script>
</head>
<body>
 <jsp:include  page="../quote.jsp"/>
 <div id="page-wrapper">
			<div class="header">
				<h1 class="page-header">添加职位</h1>
				<ol class="breadcrumb">
					<li><a href="index2">首页</a></li>
					<li>职位管理</li>
					<li>添加职位</li>
				</ol>
			</div>
			<div id="page-inner">
				<form action="${pageContext.request.contextPath }/job_add" method="post">
					<table>
						<tr>
							<td width="50%">职位名称:
								<span id="ex" style="color: red;"></span>
								<input type="text" name="jobName" id="jobName" required="true"/>
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
	$('#jobName').on('change',function(){
        var jobName = $(this).val();
        	$.ajax({
                url : "./check_jobName",
                type : 'post',
                data : {jobName:jobName},
                dataType : 'json',
                success : function(data){
                  if(data['jobName']=="职位名称已被使用"){
                	  $('#ex').html("(提示---"+data['jobName']+")")
                      $('#jobName').focus();
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