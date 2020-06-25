<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath}/admin/css/Style.css"
	type="text/css" rel="stylesheet">
<script language="javascript"
	src="${pageContext.request.contextPath}/admin/js/public.js"></script>
<script language="javascript"
	src="${pageContext.request.contextPath}/admin/js/check.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/client/js/jquery-1.11.0.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/client/js/jquery-migrate-1.2.1.js"></script>
	<script type="text/javascript">
		function modifyNotice() {
			$.post("${pageContext.request.contextPath}/noticeManage/modifyNotice.do",
			$("#modifyNoticeForm").serialize(),function (data) {
					if (data == "OK"){
					    alert("修改成功！");
					    window.location.href = "${pageContext.request.contextPath}/noticeManage/findNotices.do"
					} else {
					    alert("修改失败，请重试！");
					    history.go(-1);
                    }
                });
        }
	</script>

</HEAD>
<body>
	<form id="modifyNoticeForm" name="Form1">
		<input type="hidden" name="n_id" value="${notice.n_id}" /> &nbsp;
		<table cellSpacing="1" cellPadding="5" width="100%" align="center"
			bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
			<tr>
				<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4" height="26">
					<strong>修改公告</strong>
				</td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01">公告标题：</td>
				<td class="ta_01" bgColor="#ffffff" colSpan="3">
					<input type="text" name="title" class="bg" maxlength="10"  value="${notice.title }"/>
				</td>
			</tr>
			<tr>
				<td class="ta_01" align="center" bgColor="#f5fafe">公告内容：</td>
				<td class="ta_01" bgColor="#ffffff" colSpan="3">
					<textarea name="details" cols="30" rows="3" style="WIDTH: 96%" >${notice.details }</textarea>
				</td>
			</tr>
			<tr>
				<td align="center" colSpan="4" class="sep1"><img
					src="${pageContext.request.contextPath}/admin/images/shim.gif">
				</td>
			</tr>

			<tr>
				<td class="ta_01" style="WIDTH: 100%" align="center" bgColor="#f5fafe" colSpan="4">
					<input type="button" class="button_ok" value="确定" onclick="modifyNotice()">
					<font face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
					<input type="reset" value="重置" class="button_cancel"> 
					<font face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font> 
					<input class="button_ok" type="button" onclick="history.go(-1)" value="返回" />
					<span id="Label1"> </span>
				</td>
			</tr>
		</table>
	</form>




</body>
</HTML>