package priv.yulong.sys.service;

import java.util.List;

import priv.yulong.sys.model.Role;

public interface RoleService {
	void delete(String id);

	void add(Role record);

	Role getByPrimaryKey(String id);

	void updateSelective(Role record);

	void update(Role record);

	List<Role> getAll();
	
	List<Role> getByUserId(String userId);

	void addRolePermission(List<String> permissionIds,String roleId);

	void deleteRolePermissionByRoleId(String roleId);
}
