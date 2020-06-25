<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>bookStore注册页面</title>
<%--导入css --%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/client/css/main.css"
	type="text/css" />
<script type="text/javascript">
function active() {
    if (document.getElementById("activeUsername").value==""){
        alert("请输入要激活的账号！");
        window.location.reload();
	}else if(document.getElementById("activeCode").value==""){
	    alert("请输入激活码！");
	    window.location.reload();
	} else {
	    var activeCode = document.getElementById("activeCode").value;
	    var activeUsername = document.getElementById("activeUsername").value;
	    window.location.href="${pageContext.request.contextPath}/user/activeUser.do?activeCode="+activeCode+"&activeUsername="+activeUsername;
        window.event.returnValue=false;
	}


}
</script>
</head>

<body class="main">
	<%--导入头 --%>
	<%@include file="head.jsp"%>
	<div id="divcontent">
		<form method="post" onsubmit="active()">
			<table width="850px" border="0" cellspacing="0">


						<h1>账号激活</h1>
						<table width="80%" border="0" cellspacing="2" class="upline">
							<tr>
								<td style="text-align: right; width: 20%">输入要激活的账号：</td>
								<td style="width: 50%">
									<input type="text" class="textinput" name="activeUsername" id="activeUsername" value="${requestScope.activeUsername}"/>
								</td>
								<td>&nbsp;</td>

								${requestScope.errorMsg}

								<td style="text-align: right; width: 20%">输入激活码：</td>
								<td style="width: 50%">
								<input type="text" class="textinput" name="activeCode" id="activeCode" value="${requestScope.activeCode}"/>
								</td>
								<td>&nbsp;</td>
							</tr>
						</table>

						<table width="70%" border="0" cellspacing="0">
							<tr>
								<td style="padding-top: 20px; text-align: center">
									<input type="image" src="${pageContext.request.contextPath}/client/images/activeCode.gif" name="submit" border="0"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
