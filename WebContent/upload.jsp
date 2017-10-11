<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%--upload上传页面注意三点
		1). method = post   --->> 由于post支持大文件上传,而get只能小文件上传
		2). encType = "multiPart/form-data"
		3). <input type="file" name="f">
	 --%>
	 
	 <form action="${ pageContext.request.contextPath }/uploadServlet" method="post" enctype="multipart/form-data">
	 	<input type="file" name="f"><br>
	 	描述:<input type="text" name="description"><br>
	 	<input type="submit" value="提交"> 
	 </form>

</body>
</html>