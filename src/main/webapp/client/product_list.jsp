<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://yyf.pager-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>bookStore列表</title>
	<%--导入css --%>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/client/css/main.css" type="text/css" />
</head>

<body class="main">
	<jsp:include page="head.jsp" />
	<jsp:include page="menu_search.jsp" />
	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td><div style="text-align:right; margin:5px 10px 5px 0px">
						<a href="${pageContext.request.contextPath }/index.jsp">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;
					${requestScope.category}&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;图书列表
					</div>
					<table cellspacing="0" class="listcontent">
						<tr>
							<td>
								<h1>商品目录</h1>
								<hr />
								<h1>${requestScope.category}</h1>&nbsp;&nbsp;&nbsp;&nbsp;共${productsModel.recordCount}种商品
								<hr />
								<div style="margin-top:20px; margin-bottom:5px">
									<img src="${pageContext.request.contextPath }/client/images/productlist.gif" width="100%" height="38" />
								</div>

								<table cellspacing="0" class="booklist">
									<tr>
										<c:forEach items="${productsModel.data}" var="p" varStatus="vs">
											<td>
												<div class="divbookpic">
													<p>
														<a href="${pageContext.request.contextPath}/product/findProductById.do?id=${p.id}">
															<img src="${pageContext.request.contextPath}${p.imgurl}" width="115" height="129" border="0" /> 
														</a>
													</p>
												</div>
												<div class="divlisttitle">
													<a href="${pageContext.request.contextPath}/product/findProductById.do?id=${p.id}">书名： ${p.name}<br />售价：￥${p.price} </a>
												</div>
											</td>

											<%-- <c:if test="${vs.count%4==0}">
											</c:if> --%>
										</c:forEach>
									</tr>
								</table>
								<!-- <table cellspacing="0" class="booklist">
									<tr>
									</tr>
								</table> -->
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<tr valign="top"><td align="center" class="font3">
			<f:pager
					pageIndex="${requestScope.productsModel.pageIndex}"
					pageSize="${requestScope.productsModel.pageSize}"
					recordCount="${requestScope.productsModel.recordCount}"
					style="digg"
					submitUrl="${pageContext.request.contextPath}/product/findProductInfo.do?pageIndex={0}&category=${requestScope.category}"/>
		</td></tr>
	</div>
	<jsp:include page="foot.jsp" />
</body>
</html>
