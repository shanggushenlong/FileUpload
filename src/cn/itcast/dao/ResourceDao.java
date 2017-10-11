package cn.itcast.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import cn.itcast.domain.Resource;
import cn.itcast.utils.DataSourceUtils;

public class ResourceDao {

	public void save(Resource resource) throws SQLException {
		
		String sql = "insert into resource values(null,?,?,?,null,?)";
		
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		
		runner.update(sql,resource.getUuidname(),resource.getRealname(),resource.getSavepath(),
				resource.getDescription());
		
	}

}
