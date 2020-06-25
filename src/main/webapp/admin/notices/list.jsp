<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%@taglib prefix="p" uri="http://yyf.pager-tags" %>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/admin/css/Style.css"
	rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/client/js/jquery-1.11.0.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/client/js/jquery-migrate-1.2.1.js"></script>
	<link href="${pageContext.request.contextPath}/admin/js/ligerUI/skins/Aqua/css/ligerui-dialog.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/admin/js/ligerUI/js/core/base.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/admin/js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/admin/js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/admin/js/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>

<script language="javascript"
	src="${pageContext.request.contextPath}/admin/js/public.js"></script>
<script type="text/javascript">
	function addNotice() {
		window.location.href = "${pageContext.request.contextPath}/admin/notices/add.jsp";
	}

	function removeNotice(id) {
        $.ligerDialog.confirm("确认要删除吗?","删除公告",function(r){
            if(r){
                $.get("${pageContext.request.contextPath}/noticeManage/removeNoticeById.do?id="+id,
                    function (data) {
                        if (data == "OK"){
                            alert("删除成功！");
                            window.location.href = "${pageContext.request.contextPath}/noticeManage/findNotices.do"
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
	<form id="Form1" name="Form1" action="" method="post">
		<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
			<tbody>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>公 告 列 表</strong>
					</td>
				</tr>
				<tr>
					<td class="ta_01" align="right">
						<button type="button" id="add" name="add" value="&#28155;&#21152;"
							class="button_add" onclick="addNotice()">&#28155;&#21152;
						</button>
					</td>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
								<td align="center" width="12%">公告编号</td>
								<td align="center" width="12%">公告标题</td>
								<td align="center" width="24%">公告内容</td>
								<td align="center" width="8%">时间</td>
								<td width="8%" align="center">编辑</td>
								<td width="8%" align="center">删除</td>
							</tr>
							<c:forEach items="${noticeModel.data}" var="n">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="23">${n.n_id }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="18%">${n.title }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="8%">${n.details }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="8%">
										<f:formatDate value="${n.n_time }"
													  type="date" dateStyle="long"/>
									</td>
									<td align="center" style="HEIGHT: 22px" width="7%">
										<a href="${pageContext.request.contextPath}/noticeManage/findNoticeById.do?id=${n.n_id}">
											<img src="${pageContext.request.contextPath}/admin/images/i_edit.gif" border="0" style="CURSOR: hand"> 
										</a>
									</td>
									<td align="center" style="HEIGHT: 22px" width="7%"><a
										href="#" onclick="removeNotice(${n.n_id})">
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
						pageIndex="${requestScope.noticeModel.pageIndex}"
						pageSize="${requestScope.noticeModel.pageSize}"
						recordCount="${requestScope.noticeModel.recordCount}"
						style="digg"
						submitUrl="${pageContext.request.contextPath}/noticeManage/findNotices.do?pageIndex={0}"/>
			</td></tr>
		</table>
	</form>
</body>
</HTML>

