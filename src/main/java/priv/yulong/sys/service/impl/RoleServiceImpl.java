package priv.yulong.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import priv.yulong.common.util.IDGenerator;
import priv.yulong.sys.mapper.RoleMapper;
import priv.yulong.sys.mapper.RolePermissionMapper;
import priv.yulong.sys.model.Role;
import priv.yulong.sys.model.RolePermission;
import priv.yulong.sys.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleMapper roleMapper;
	@Resource
	private RolePermissionMapper rolePermissionMapper;

	@Override
	public void delete(String id) {
		roleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void add(Role record) {
		roleMapper.insert(record);
	}

	@Override
	public Role getByPrimaryKey(String id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateSelective(Role record) {
		roleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public void update(Role record) {
		roleMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Role> getAll() {
		return roleMapper.selectAll();
	}

	@Override
	public void addRolePermission(List<String> permissionIds, String roleId) {
		rolePermissionMapper.deleteByRoleId(roleId);
		for (String pid : permissionIds) {
			RolePermission rp = new RolePermission();
			rp.setId(IDGenerator.randomID());
			rp.setPermissionId(pid);
			rp.setRoleId(roleId);
			rolePermissionMapper.insert(rp);
		}
	}

	@Override
	public void deleteRolePermissionByRoleId(String roleId) {
		rolePermissionMapper.deleteByRoleId(roleId);
	}

	@Override
	public List<Role> getByUserId(String userId) {
		return roleMapper.selectByUserId(userId);
	}

}
