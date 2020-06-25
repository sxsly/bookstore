<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="p" uri="http://yyf.pager-tags" %>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/admin/css/Style.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="${pageContext.request.contextPath}/admin/js/public.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/client/js/jquery-1.11.0.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/client/js/jquery-migrate-1.2.1.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/client/js/jquery-1.11.0.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/client/js/jquery-migrate-1.2.1.js"></script>
	<link href="${pageContext.request.contextPath}/admin/js/ligerUI/skins/Aqua/css/ligerui-dialog.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/admin/js/ligerUI/js/core/base.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/admin/js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/admin/js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/admin/js/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
<script type="text/javascript">
	function addProduct() {
		window.location.href = "${pageContext.request.contextPath}/admin/products/add.jsp";
	}

   function deleteProduct(id) {
       $.ligerDialog.confirm("确认要删除吗?","删除订单",function(r){
           if(r){
               $.get("${pageContext.request.contextPath}/productManage/deteleProduct.do?id="+id,
                   function(data){
                       if (data == "OK"){
                           alert("删除成功！");
                           window.location.href = "${pageContext.request.contextPath}/productManage/findProductByManyCondition.do"
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
		action="${pageContext.request.contextPath}/productManage/findProductByManyCondition.do"
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
									商品编号</td>
								<td class="ta_01" bgColor="#ffffff"><input type="text"
									name="id" size="15" value="${product.id}" id="Form1_userName" class="bg" />
								</td>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
									类别：</td>
								<td class="ta_01" bgColor="#ffffff"><select name="category"
									id="category">
									<option value="">--选择商品类别--</option>
									<c:forEach items="${cList}" var="p">
										<c:choose>
											<c:when test="${p.category eq product.category}">
												<option value="${p.category}" selected="selected">${p.category}</option>
											</c:when>
											<c:otherwise>
												<option value="${p.category}">${p.category}</option>
											</c:otherwise>
										</c:choose>
										<%--<option value="${p.category}">${p.category}</option>--%>
									</c:forEach>
									
								</select></td>
							</tr>

							<tr>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
									商品名称：</td>
								<td class="ta_01" bgColor="#ffffff"><input type="text"
									name="name" size="15" value="${product.name}" id="Form1_userName" class="bg" />
								</td>
								<td height="22" align="center" bgColor="#f5fafe" class="ta_01">
									价格区间(元)：</td>
								<td class="ta_01" bgColor="#ffffff"><input type="text"
									name="minPrice" size="10" value="${minPrice}" />- <input type="text"
									name="maxPrice" size="10" value="${maxPrice}" /></td>
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
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>商 品 列 表</strong>
					</TD>
				</tr>
				<tr>
					<td class="ta_01" align="right">
						<input type="button" id="add" name="add" value="&#28155;&#21152;"
							class="button_add" onclick="addProduct()">
						</input>
					</td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
								<td align="center" width="24%">商品编号</td>
								<td align="center" width="18%">商品名称</td>
								<td align="center" width="9%">商品价格</td>
								<td align="center" width="9%">商品数量</td>
								<td width="8%" align="center">商品类别</td>
								<td width="8%" align="center">编辑</td>
								<td width="8%" align="center">删除</td>
							</tr>

							<c:forEach items="${productModel.data}" var="p">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="200">${p.id }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="18%">${p.name }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="8%">${p.price }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="8%">${p.pnum }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center">
										${p.category}</td>
									<td align="center" style="HEIGHT: 22px" width="7%"><a
										href="${pageContext.request.contextPath}/product/findProductById.do?id=${p.id}&type=admin">
											<img
											src="${pageContext.request.contextPath}/admin/images/i_edit.gif"
											border="0" style="CURSOR: hand"> </a>
									</td>

									<td align="center" style="HEIGHT: 22px" width="7%"><a
										href="#" onclick="deleteProduct(${p.id})">
											<img
											src="${pageContext.request.contextPath}/admin/images/i_del.gif"
											width="16" height="16" border="0" style="CURSOR: hand">
									</a>
									</td>
								</tr>
							</c:forEach>
						</table>
					</td>
				</tr>
			</TBODY>
			<tr valign="top"><td align="center" class="font3">
				<p:pager
						pageIndex="${requestScope.productModel.pageIndex}"
						pageSize="${requestScope.productModel.pageSize}"
						recordCount="${requestScope.productModel.recordCount}"
						style="digg"
						submitUrl="${pageContext.request.contextPath}/productManage/findProductByManyCondition.do?pageIndex={0}"/>
			</td></tr>
		</table>
	</form>
</body>
</HTML>

