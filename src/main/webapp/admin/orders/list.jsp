<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="p" uri="http://yyf.pager-tags" %>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/admin/css/Style.css"
	rel="stylesheet" type="text/css" />
<script language="javascript"
	src="${pageContext.request.contextPath}/admin/js/public.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/client/js/jquery-1.11.0.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/client/js/jquery-migrate-1.2.1.js"></script>
	<link href="${pageContext.request.contextPath}/admin/js/ligerUI/skins/Aqua/css/ligerui-dialog.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/admin/js/ligerUI/js/core/base.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/admin/js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/admin/js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/admin/js/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>

	<script type="text/javascript">
        function removeOrder(data) {
            var id = data.type;
            // alert(type);
            $.ligerDialog.confirm("确认要删除吗?","删除订单",function(r){
                if(r){
                    $.get("${pageContext.request.contextPath}/order/removeOrderById.do?id="+id,
                        function (data) {
                            if (data == "OK"){
                                alert("删除成功！");
                                window.location.href = "${pageContext.request.contextPath}/orderManage/findAllOrders.do"
                            } else {
                                alert("删除失败！");
                                window.location.reload();
                            }
                        });
                }
            });
        }
	</script>
</HEAD>
<body>
	<br>
	<form id="Form1" name="Form1"
		action="${pageContext.request.contextPath}/orderManage/findAllOrders.do"
		method="post">
		<table cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3">
						<strong>查 询 条 件</strong>
					</td>
				</tr>
				<tr>
					<td>
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
									订单编号</td>
								<td class="ta_01" bgColor="#ffffff"><input type="text"
									name="id" size="15" value="${order.id}" id="Form1_userName" class="bg" />
								</td>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
									收件人：</td>
								<td class="ta_01" bgColor="#ffffff"><input type="text"
									name="receivername" size="15" value="${order.receivername}" id="Form1_userName"
									class="bg" />
								</td>
							</tr>
							<tr>
								<td width="100" height="22" align="center" bgColor="#f5fafe"
									class="ta_01"></td>
								<td class="ta_01" bgColor="#ffffff"><font face="宋体"
									color="red"> &nbsp;</font>
								</td>
								<td align="right" bgColor="#ffffff" class="ta_01"><br>
									<br></td>
								<td align="right" bgColor="#ffffff" class="ta_01">
									<button type="submit" id="search" name="search"
										value="&#26597;&#35810;" class="button_view">
										&#26597;&#35810;</button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
									type="reset" name="reset" value="&#37325;&#32622;"
									class="button_view" />
								</td>
							</tr>
						</table>
					</td>

				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3">
						<strong>订 单 列 表</strong>
					</td>
				</tr>
				<tr>
					<td class="ta_01" align="right"></td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
								<td align="center" width="20%">订单编号</td>
								<td align="center" width="10%">收件人</td>
								<td align="center" width="15%">地址</td>
								<td align="center" width="10%">联系电话</td>
								<td width="11%" align="center">总价</td>
								<td width="8%" align="center">所属用户</td>
								<td width="10%" align="center">订单状态</td>
								<td width="7%" align="center">查看</td>
								<td width="7%" align="center">删除</td>
							</tr>
							<c:forEach items="${orderModel.data}" var="order">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="20%">${order.id}</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="10%">${order.receivername}</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="15%">${order.receiveraddress }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="10%">${order.receiverphone }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center">
										${order.money}</td>
									<td width="8%" align="center">${order.user.username}</td>
									<td width="10%" align="center">${order.paystatus==0?"未支付":"已支付"}</td>

									<td align="center" style="HEIGHT: 22px"><a
										href="${pageContext.request.contextPath}/order/findOrderById.do?id=${order.id}&type=admin">
											<img
											src="${pageContext.request.contextPath}/admin/images/button_view.gif"
											border="0" style="CURSOR: hand"> </a>
									</td>
									<td align="center" style="HEIGHT: 22px"><c:if
											test="${order.paystatus!=0 }">
											<a
												href="#" onclick="removeOrder(this)" type="${order.id}">
												<img
												src="${pageContext.request.contextPath}/admin/images/i_del.gif"
												width="16" height="16" border="0" style="CURSOR: hand">
											</a>
										</c:if> 
										<c:if test="${order.paystatus==0 }">
											<a href="javascript:alert('不能删除未支付订单')">
											<img
												src="${pageContext.request.contextPath}/admin/images/i_del.gif"
												width="16" height="16" border="0" style="CURSOR: hand">
											</a>
										</c:if>
									</td>

								</tr>
							</c:forEach>
						</table>
					</td>
				</tr>
			</TBODY>
			<tr valign="top"><td align="center" class="font3">
				<p:pager
						pageIndex="${requestScope.orderModel.pageIndex}"
						pageSize="${requestScope.orderModel.pageSize}"
						recordCount="${requestScope.orderModel.recordCount}"
						style="digg"
						submitUrl="${pageContext.request.contextPath}/orderManage/findAllOrders.do?pageIndex={0}"/>
			</td></tr>
		</table>
	</form>
</body>
</HTML>

