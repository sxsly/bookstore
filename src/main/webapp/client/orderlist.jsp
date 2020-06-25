<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="p" uri="http://yyf.pager-tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>电子书城</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/client/css/main.css" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/client/js/jquery-1.11.0.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/client/js/jquery-migrate-1.2.1.js"></script>
	<link href="${pageContext.request.contextPath}/admin/js/ligerUI/skins/Aqua/css/ligerui-dialog.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/admin/js/ligerUI/js/core/base.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/admin/js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/admin/js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/admin/js/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>

	<script type="text/javascript">
        function removeClientOrder(data) {
            var id = data.type;
            // alert(type);
            $.ligerDialog.confirm("确认要删除吗?","删除订单",function(r){
                if(r){
                    $.get("${pageContext.request.contextPath}/order/removeOrderById.do?id="+id,
                        function (data) {
                            if (data == "OK"){
                                alert("删除成功！");
                                window.location.href = "${pageContext.request.contextPath}/order/findOrderByUser.do"
                            } else {
                                alert("删除失败！");
                                window.location.reload();
                            }
                        });
                }
            });
        }
	</script>
</head>

<body class="main">
	<!-- 使用了自定义标签 -->
<%-- 	<p:user/> --%>
	<jsp:include page="head.jsp" />
	<jsp:include page="menu_search.jsp" />

	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td width="25%">
					<table width="100%" border="0" cellspacing="0" style="margin-top:30px">
						<tr>
							<td class="listtitle">我的帐户</td>
						</tr>
						<tr>
							<td class="listtd">
								<img src="${pageContext.request.contextPath }/client/images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp; 
								<a href="${pageContext.request.contextPath }/client/modifyuserinfo.jsp">用户信息修改</a>
							</td>
						</tr>
						<tr>
							<td class="listtd">
								<img src="${pageContext.request.contextPath }/client/images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp; 
								<a href="${pageContext.request.contextPath}/order/findOrderByUser.do">订单查询</a>
							</td>
						</tr>
						<tr>
							<td class="listtd">
								<img src="${pageContext.request.contextPath }/client/images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp; 
								<a href="${pageContext.request.contextPath}/user/logout.do">用戶退出</a>
							</td>
						</tr>
					</table>
				</td>
				<td>
					<div style="text-align:right; margin:5px 10px 5px 0px">
						<a href="${pageContext.request.contextPath }/index.jsp">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;
						<a href="${pageContext.request.contextPath }/client/myAccount.jsp">&nbsp;我的帐户</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;
						订单查询
					</div>
					<table cellspacing="0" class="infocontent">
						<tr>
							<td style="padding:20px"><p><b>我的订单</b></p>
								<p>
									共有<font style="color:#FF0000" >${orders.size()}</font>订单
								</p>
								<table width="100%" border="0" cellspacing="0" class="tableopen">
									<tr>
										<td bgcolor="#A3E6DF" class="tableopentd01">订单号</td>
										<td bgcolor="#A3D7E6" class="tableopentd01">收件人</td>
										<td bgcolor="#A3CCE6" class="tableopentd01">订单时间</td>
										<td bgcolor="#A3B6E6" class="tableopentd01">状态</td>
										<td bgcolor="#A3E2E6" class="tableopentd01">操作</td>
									</tr>
									<c:forEach items="${orderModel.data}" var="order">
										<tr>
											<td class="tableopentd02">${order.id}</td>
											<td class="tableopentd02">${order.receivername }</td>
											<td class="tableopentd02"><f:formatDate value="${order.ordertime}" type="date" dateStyle="long"/></td>
											<td class="tableopentd02">${order.paystatus==0?"未支付":"已支付"}</td>
											<td class="tableopentd03">
												<a href="${pageContext.request.contextPath}/order/findOrderById.do?id=${order.id}">查看</a>&nbsp;&nbsp;
												<c:if test="${order.paystatus==0 }">
													<a href="#" onclick="removeClientOrder(this)" type="${order.id}">刪除</a>
												</c:if> 
												<c:if test="${order.paystatus!=0 }">
													删除
												</c:if>
											</td>
										</tr>
									</c:forEach>
								</table>
							</td>
						</tr>
                        <tr valign="top"><td align="center" class="font3">
                            <p:pager
                                    pageIndex="${requestScope.orderModel.pageIndex}"
                                    pageSize="${requestScope.orderModel.pageSize}"
                                    recordCount="${requestScope.orderModel.recordCount}"
                                    style="digg"
                                    submitUrl="${pageContext.request.contextPath}/order/findOrderByUser.do?pageIndex={0}"/>
                        </td></tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<%@ include file="foot.jsp" %>
</body>
</html>
