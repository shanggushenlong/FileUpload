<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件下载</title>
</head>
<body>
	
	<%--
		文件下载(两种方式): 
			1.超链接的下载方式
			2.服务器端通过流下载
	--%>
	<a href="${ pageContext.request.contextPath }/download/a.doc">a.doc</a><br>
	<a href="${ pageContext.request.contextPath }/download/user.xml">user.xml</a>
</body>
</html>