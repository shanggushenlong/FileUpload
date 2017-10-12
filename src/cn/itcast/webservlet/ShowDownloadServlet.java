package cn.itcast.webservlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.domain.Resource;
import cn.itcast.service.ResourceService;


@WebServlet("/ShowDownloadServlet")
public class ShowDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 在ShowDownloadservlet中,查看db数据库,得到所有的可以下载的信息
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ResourceService service = new ResourceService();
		
		try {
			List<Resource> resources = service.findAll();
			
			request.setAttribute("resources", resources);
			request.getRequestDispatcher("/download.jsp").forward(request, response);
			return;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
