<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>部门编辑</title>
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
				<h1 class="page-header">部门编辑</h1>
				<ol class="breadcrumb">
					<li><a href="index1">首页</a></li>
					<li>部门管理</li>
					<li>部门编辑</li>
				</ol>
			</div>
			<div id="page-inner">
				<form class="form-horizontal" role="form" action="${pageContext.request.contextPath }/dept_update" method="post">
					<s:hidden name="id" />
                       <div class="form-group">
                          <label class="col-sm-2 control-label"><font size="4px" color="#696969">部门名称:</font></label>
                          <div class="col-sm-2">
                             <s:textfield class="form-control" name="dept.deptName" id="deptName" required="true"/>
                             <span id="ex" style="color: red;"></span>
                          </div>
                          <label class="col-sm-2 control-label"><font size="4px" color="#696969">详细信息:</font></label>
                          <div class="col-sm-3">
                             <s:textfield class="form-control" name="dept.remark"/>
                          </div>
                       </div>
                       <div class="form-group">
                       	<s:if test="#session.activeUser.status==2">
	                       	<div class="col-sm-2">
	                      		<s:submit class="form-control" id="submit" value="修改"/>
	                      	</div>
	                    </s:if>
                        <div class="col-sm-2">
                           <a href="${pageContext.request.contextPath}/deptQueryForm"><input class="form-control"  type="button" value="返回" /></a>
                        </div>
                       </div>
                          </form>
			</div>
		</div>
<script>
	$('#deptName').on('change',function(){
        var deptName = $(this).val();
        /* if(deptName!=""){ */
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