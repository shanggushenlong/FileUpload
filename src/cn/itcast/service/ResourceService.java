package cn.itcast.service;

import java.sql.SQLException;

import cn.itcast.dao.ResourceDao;
import cn.itcast.domain.Resource;

public class ResourceService {

	public void save(Resource resource) throws SQLException {
		new ResourceDao().save(resource);
	}

}
