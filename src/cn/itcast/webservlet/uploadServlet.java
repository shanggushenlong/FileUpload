package cn.itcast.webservlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.mchange.v2.codegen.bean.BeangenUtils;

import cn.itcast.domain.Resource;
import cn.itcast.service.ResourceService;
import cn.itcast.utils.FileUploadUtils;


@WebServlet("/uploadServlet")
public class uploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * 使用apache开源jar包上传工具,commons-fileuplod commons-io
	 * 
	 * 注意三点
	 * 	1).DiskFileItemFactory    --->主要是设置上传的文件的大小,及位置相关的
	 * 	2).ServletFileUpload      --->主要是解析request上传组件,封装成FileItem
	 * 	3).FileItem
	 */
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Map<String, String[]> map = new HashMap<String,String[]>();
		
		//1.创建DiskFileItemFactory
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//2.创建ServletFileUpload
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//设置上传文件中文名乱码的问题
		upload.setHeaderEncoding("utf-8");
//		upload.isMultipartContent(request);    //判断是否是上传组件
		//3.得到所有的FileItem
		try {
			List<FileItem> items = upload.parseRequest(request);
			
			//遍历所有Items,得到所有的上传信息
			for(FileItem item:items){
				if(item.isFormField()){
					//不是上传组件
					map.put(item.getFieldName(), new String[]{item.getString("utf-8")});    //封装上传非组件信息
				}else{
					//是上传组件
					
					//得到上传文件名
					String filename = item.getName();
					//得到真实的文件名,因为可能存在路径
					filename = FileUploadUtils.getRealFileName(filename);
//					System.out.println(filename);
					map.put("realname", new String[]{filename});   //封装上传文件真实名称
					
					//得到随机名称
					String uuidname = FileUploadUtils.getUUIDFileName(filename);
					
					map.put("uuidname", new String[]{uuidname});   //封装上传文件随机名称
					
					//得到随机目录
					String randomDirectory = FileUploadUtils.getRandomDirectory(filename);
					//得到这个文件在磁盘上的真实路径
					String uploadPath = this.getServletContext().getRealPath("/WEB_INF/upload");
//					System.out.println(uploadPath);
					File parentDirectory = new File(uploadPath,randomDirectory);
					if(!parentDirectory.exists()){
						//判断一下,这个文件夹是否存在
						parentDirectory.mkdirs();
					}
					map.put("savepath", new String[]{uploadPath+randomDirectory});   //封装上传文件保存路径
					
					//文件上传复制
					IOUtils.copy(item.getInputStream(), new FileOutputStream(new File(parentDirectory,uuidname)));
					item.delete();
				}
			}
			
			//将数据封装到javaBean中
			Resource resource = new Resource();
			try {
				BeanUtils.populate(resource,map);
				
				//调用service完成数据保存到mysql中
				ResourceService service = new ResourceService();
				service.save(resource);
				
				response.sendRedirect(request.getContextPath()+"/index.jsp");
				
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}















