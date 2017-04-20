<%@ page language="Java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>Title</title>
<%
	//判断用户 是否登录 及其类型 
	response.sendRedirect("InitStudentInfo");
%>
</head>
<body>

</body>
</html>