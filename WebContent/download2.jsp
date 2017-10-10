<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>通过递归来完成下载文件</title>
</head>
<body>
	
	<%--使用递归--%>
	<%! //声明一个方法
		public void getFile(File file){
			if(file.isDirectory()){  //判断是否是目录
				//是目录
				File[] fs = file.listFiles();
			
				for(int i=0;i<fs.length;i++){
					getFile(fs[i]);    //递归调用
				}
			}else if(file.isFile()){
				//是文件
				System.out.println(file.getName());
			}
		}
		
	%>
	<%
		String path = "/home/wcq/workspace/Fileload/WebContent/a";
		File uploadDirectory = new File(path);
		
		getFile(uploadDirectory);
	%>	
	
	
</body>
</html>



