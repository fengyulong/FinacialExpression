package priv.yulong.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import priv.yulong.sys.mapper.PermissionMapper;
import priv.yulong.sys.model.Permission;
import priv.yulong.sys.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {
	@Resource
	private PermissionMapper permissionMapper;

	@Override
	public void delete(String id) {
		permissionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void add(Permission record) {
		permissionMapper.insert(record);
	}

	@Override
	public Permission getByPrimaryKey(String id) {
		return permissionMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateSelective(Permission record) {
		permissionMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public void update(Permission record) {
		permissionMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Permission> getAll() {
		return permissionMapper.selectAll();
	}

	@Override
	public List<Permission> getByType(String type) {
		return permissionMapper.selectByType(type);
	}

	@Override
	public List<Permission> getPermissionTree() {
		return permissionMapper.selectPermissionTree();
	}

	@Override
	public List<Permission> getMenuTree() {
		return permissionMapper.selectMenuTree();
	}

	@Override
	public Permission getPermissionTreeById(String id) {
		return permissionMapper.selectPermissionTreeById(id);
	}

	@Override
	public void deleteCascade(Permission permission) {
		List<Permission> children = permission.getChildren();
		if (children != null && !children.isEmpty()) {
			for (Permission p : children) {
				deleteCascade(p);
			}
		}
		delete(permission.getId());

	}

	@Override
	public List<Permission> getPermissionsByRoleId(String roleId) {
		return permissionMapper.selectPermissionsByRoleId(roleId);
	}

	@Override
	public List<Permission> getPermissionsByUserId(String userId) {
		return permissionMapper.selectPermissionsByUserId(userId);
	}

}
