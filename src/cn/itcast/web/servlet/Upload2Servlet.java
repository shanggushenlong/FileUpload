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

import cn.itcast.utils.FileUploadUtils;

@WebServlet("/Upload2Servlet")
public class Upload2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
					if(!item.isFormField()){    //如果是 <input type="file"> 返回的是false,否则返回true
						
	
						String name = item.getName();   //得到上传文件的名称     
						
						//得到上传文件的真实名称
						String filename = FileUploadUtils.getRealFileName(name);
						
						//得到随机上传保存的文件的名称
						String uuidName = FileUploadUtils.getUUIDFileName(filename);
						
						//得到随机目录
						String randomDirectory = FileUploadUtils.getRandomDirectory(filename);
						
						//注意:随机目录可能不存在,需要创建
						File rd = new File("/home/wcq/upload",randomDirectory);
						if(!rd.exists()){
							rd.mkdirs();
						}
						
						/**
						 * 获取上传文件的内容,保存到服务器端
						 * item.getInputStream(),用于读取上传文件的内容,使用文件复制操作就可以完成文件上传
						 */
						IOUtils.copy(item.getInputStream(), new FileOutputStream(new File(rd,filename)));    //写出文件复制的地址
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
