<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>下载页面</title>
</head>
<body>
	<%-- 创建一个download.jsp页面,展示所有可以下载的信息 --%>
	
	<table border="1" width="65%">
		<tr>
			<td>文件名称</td>
			<td>文件描述</td>
			<td>下载操作</td>
		</tr>
		<c:forEach items="${ resources }" var="r">
			<tr>
				<td>${ r.realname }</td>
				<td>${ r.description }</td>
				<td><a href="${ pageContext.request.contextPath }/DownloadServlet?id=${r.id}">下载</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>






















