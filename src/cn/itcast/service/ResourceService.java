package cn.itcast.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.dao.ResourceDao;
import cn.itcast.domain.Resource;

public class ResourceService {

	public void save(Resource resource) throws SQLException {
		new ResourceDao().save(resource);
	}

	public List<Resource> findAll() throws SQLException {
		return new ResourceDao().findAll();
	}

	public Resource findById(String id) throws SQLException {
		return new ResourceDao().findById(id);
	}

}
