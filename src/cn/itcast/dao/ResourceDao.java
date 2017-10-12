package cn.itcast.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.domain.Resource;
import cn.itcast.utils.DataSourceUtils;

public class ResourceDao {

	public void save(Resource resource) throws SQLException {
		
		String sql = "insert into resource values(null,?,?,?,null,?)";
		
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		
		runner.update(sql,resource.getUuidname(),resource.getRealname(),resource.getSavepath(),
				resource.getDescription());
		
	}

	public List<Resource> findAll() throws SQLException {
		
		String sql = "select * from resource";
		
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		
		return runner.query(sql, new BeanListHandler<Resource>(Resource.class));
	}

	public Resource findById(String id) throws SQLException {
		
		String sql = "select * from resource where id = ?";
		
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		
		return runner.query(sql, new BeanHandler<Resource>(Resource.class),id);
	}

}






















