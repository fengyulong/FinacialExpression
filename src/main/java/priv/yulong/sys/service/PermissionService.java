package priv.yulong.sys.service;

import java.util.List;

import priv.yulong.sys.model.Permission;

public interface PermissionService {
	void delete(String id);

	void add(Permission record);

	Permission getByPrimaryKey(String id);

	void updateSelective(Permission record);

	void update(Permission record);
	
	List<Permission> getAll();
	
	List<Permission> getByType(String type);

}
