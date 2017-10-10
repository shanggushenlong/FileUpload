<%@page import="java.util.LinkedList"%>
<%@page import="java.util.Queue"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>通过队列来完成下载文件</title>
</head>
<body>
	
	<%--使用队列--%>
	<%--
		使用队列优化递归操作:是可以解决目录层次过多的问题
			由于:递归操作可以理解成为是纵向的遍历,如果目录层次比较多,在内存中存储的数据较多,会引起溢出
			使用队列,它是横向遍历,一层一层遍历,可以解决目录层次比较多的问题
			因为使用队列,最多时候在内存中只存储你了一层的信息
			
		注意:使用队列的情况 ----- 当页面展示过多的时候,像树状呈现;这个时候可以使用队列
	 --%>
	<%
		String path = "/home/wcq/workspace/Fileload/WebContent/a";	
		File uploadDirectory = new File(path);
		
		//创建一个队列
		Queue<File> queue = new LinkedList<File>();
		//相当于集合中的将元素添加到集合中的方法 add();
		queue.offer(uploadDirectory); //将元素添加到集合中
		
		while(!queue.isEmpty()){    //判断队列是否为空
			File f = queue.poll();   //从集合中取出一个元素
			
			if(f.isDirectory()){    
				//如果是目录,将目录下所有文件遍历出来,存储到队列中
				File[] fs = f.listFiles();
				
				for(int i=0;i<fs.length;i++){
					queue.offer(fs[i]);
				}
			}else{
				System.out.println(f.getName());
			}
		}
	%>
	
	
</body>
</html>



