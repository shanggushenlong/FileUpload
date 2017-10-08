<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>多文件上传</title>
</head>
<body>

	<%--多文件的上传,后台servlet不变,主要改变的是前台jsp页面的上传操作 --%>

	<input type="button" value="add file" onclick="addFile();">
	<br><br>
	<form action="${ pageContext.request.contextPath }/Upload1Servlet" method="post" enctype="multipart/form-data">
		<input type="file" name="f"><br>
		<div id="content">   <%-- 增加一个div,使得每当点击js函数的时候,能够增加进去 --%>
			 
		</div>
		<input type="submit" value="上传"><br>
	</form>
</body>
<script type="text/javascript">
	function addFile() {
		var div = document.getElementById("content");
		/*innnerHTML设置html属性页面*/                                                       //this代表这个button,与java中当前对象同义
		div.innerHTML += "<div><input type='file' name='f'><input type='button' value='remove file' onclick='removeFile(this)'></div>"
	}
	
	function removeFile(btn) {
		//alert(btn.value);
		document.getElementById("content").removeChild(btn.parentNode);
		/*
		document.getElementById("content")  得到这个最外层的div,
		btn.parentNode   得到这个按钮btn的父节点
		.removeChild()   删除这个最外层div下面的子节点
		*/
	}
</script>
</html>