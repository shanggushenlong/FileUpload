<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>单文件上传</title>
</head>
<body>
	
	<form action="${ pageContext.request.contextPath }/Upload1Servlet" method="post" encType="multipart/form-data">
		<input type="text" name="context"><br>
		<input type="file" name="f"><br>
		<input type="submit" value="上传"><br>
	</form>
</body>
</html>