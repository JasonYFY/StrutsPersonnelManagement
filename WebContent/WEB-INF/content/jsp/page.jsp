<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 分页处理 -->
<table>
	<tr>
		<td style="text-align: center;padding-top: 10px">
			总数目:${pageModel.totalRecordSum}&nbsp;&nbsp;
			总页数:${pageModel.totalPageSum}&nbsp;&nbsp;
			<c:choose>
				<c:when test="${pageModel.pageIndex==1}">
					<span class='disabled'>上一页</span>
					<c:forEach begin="1" end="${pageModel.totalPageSum}" var="i">
						<c:choose>
							<c:when test="${pageModel.pageIndex==i}">
								<span class='current'>${i}</span>
							</c:when>
							<c:otherwise>
								<a href='#' onclick="toPage(${i})">${i}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<span class='current'><a href='#'
						onclick="toPage(${pageModel.pageIndex+1})">下一页</a></span>
				</c:when>
				<c:when test="${pageModel.pageIndex==pageModel.totalPageSum}">
					<span class='current'><a href='#'
						onclick="toPage(${pageModel.pageIndex-1})">上一页</a></span>
					<c:forEach begin="1" end="${pageModel.totalPageSum}" var="i">
						<c:choose>
							<c:when test="${pageModel.pageIndex==i}">
								<span class='current'>${i}</span>
							</c:when>
							<c:otherwise>
								<a href='#' onclick="toPage(${i})">${i}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<span class='disabled'>下一页</span>
				</c:when>
				<c:otherwise>
					<span class='current'><a href='#' onclick="toPage(${pageModel.pageIndex-1})" >上一页</a></span>
					<c:forEach begin="1" end="${pageModel.totalPageSum}" var="i">
						<c:choose>
							<c:when test="${pageModel.pageIndex==i}">
								<span class='current'>${i}</span>
							</c:when>
							<c:otherwise>
								<a href='#' onclick="toPage(${i})">${i}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<span class='current'><a href='#'
						onclick="toPage(${pageModel.pageIndex+1})">下一页</a></span>
				</c:otherwise>
			</c:choose> &nbsp;跳转到&nbsp;&nbsp;<input id="pager_jump_page_size"
			style='text-align: center; width:30px;height:17px'/> &nbsp;<input
			type='button'
			style='text-align: center'
			value='确定' id='pager_jump_btn' onclick="pagerJump()"/>
		</td>
	</tr>
</table>