package cn.itcast.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.sun.java_cup.internal.runtime.virtual_parse_stack;

/**
 * 文件上传(单个文件上传)
 * @author wcq
 *
 */
@WebServlet("/Upload1Servlet")
public class Upload1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1.创建DiskFileItemFactory
		//DiskFileItemFactory factory = new DiskFileItemFactory();   //默认为无参数的构造方法
		
		File file = new File(this.getServletContext().getRealPath("/temp"));   //获取temp目录部署到tomcat后的绝对磁盘路径
		//使用有参数的构造,第一个构造为设置缓冲区的大小,第二个设置缓冲区的位置
		DiskFileItemFactory factory = new DiskFileItemFactory(1024*100, file);
		
		//2.创建ServletFilteUpload
		ServletFileUpload upload = new ServletFileUpload(factory);
		boolean flag = upload.isMultipartContent(request);   //用于判断是否是上传的操作
		if(flag){
			//解决上传文件的时候文件名为中文名乱码的问题
			upload.setHeaderEncoding("utf-8");
			try {
				List<FileItem> items = upload.parseRequest(request);   //解救request,得到所有的上传项FileItem
				
				//3.得到所有上传项,遍历所有上传项
				for(FileItem item:items){
					if(item.isFormField()){        //如果是 <input type="file"> 返回的是false,否则返回true
						//非组件上传
						System.out.println("组件名称:"+item.getFieldName());   //得到组件的名称 <input name="">
						System.out.println("内容:"+item.getString("utf-8"));         //使用 getString("utf-8")的重载方法,解决中文乱码的问题       
					}else{
						//上传组件
						System.out.println("组件上传:"+item.getFieldName());   //得到组件的名称 <input name="">
						System.out.println("上传文件名称:"+item.getName());    //得到的是上传的文件的名称
					//	System.out.println("内容:"+item.getString());         //如果上传的是文本文件,则得到的是文本的内容
				
						
						//文件上传其实就是文件的复制
						String name = item.getName();   //得到上传文件的名称
						System.out.println(name);       
						name = name.substring(name.lastIndexOf("\\")+1);
						//通过字符串分割,得到文件的名称
						//复制文件(使用提供的jar包方法)
						/**
						 * 获取上传文件的内容,保存到服务器端
						 * item.getInputStream(),用于读取上传文件的内容,使用文件复制操作就可以完成文件上传
						 */
						IOUtils.copy(item.getInputStream(), new FileOutputStream("/home/wcq/day05_1/"+name));    //写出文件复制的地址
					}
				}
				
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
		}else{
			response.getWriter().write("不是上传操作");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}














