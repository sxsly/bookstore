<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="divhead">
	<table cellspacing="0" class="headtable">
		<tr>
			<td>
				<a href="${pageContext.request.contextPath }/index.jsp">
					<img src="${pageContext.request.contextPath}/client/images/logo.png" width="200" height="60" border="0" /> 
				</a>
			</td>
			<td style="text-align:right">
				<img src="${pageContext.request.contextPath}/client/images/cart.gif" width="26" height="23" style="margin-bottom:-4px" />&nbsp;<a href="${pageContext.request.contextPath}/client/cart.jsp">购物车</a> 
				| <a href="#">帮助中心</a> 
				| <a href="${pageContext.request.contextPath}/client/myAccount.jsp">我的帐户</a>
				| <a href="${pageContext.request.contextPath}/client/register.jsp">新用户注册</a>							
 				<%--<br><br><br>欢迎您： ${sessionScope.login_user.username} --%>
				<c:choose>
					<c:when test="${sessionScope.login_user != null}">
						<br><br><br>欢迎您： ${sessionScope.login_user.username}
					</c:when>
					<c:otherwise>
						<br><br><br>您还未登录，请<a href="${pageContext.request.contextPath}/client/login.jsp">登录</a>
					</c:otherwise>
				</c:choose>
			</td>		
		</tr>

	</table>
</div>