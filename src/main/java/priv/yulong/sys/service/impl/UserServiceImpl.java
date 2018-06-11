package priv.yulong.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import priv.yulong.common.util.IDGenerator;
import priv.yulong.sys.mapper.UserMapper;
import priv.yulong.sys.mapper.UserRoleMapper;
import priv.yulong.sys.model.User;
import priv.yulong.sys.model.UserRole;
import priv.yulong.sys.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;
	@Resource
	private UserRoleMapper userRoleMapper;

	@Override
	public User addUser(User user) {
		userMapper.insert(user);
		return user;
	}

	@Override
	public User getUserByName(String username) {
		return userMapper.selectByUsername(username);
	}

	@Override
	public User updateUser(User user) {
		userMapper.updateByPrimaryKeySelective(user);
		return user;
	}

	@Override
	public void deleteUser(String id) {
		userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<User> getUser(User user) {
		return userMapper.selectUser(user);
	}

	@Override
	public User getUserById(String id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<UserRole> getUserRoleList(String userId) {
		return userRoleMapper.selectByUserId(userId);
	}

	@Override
	public void addUserRole(String userId, List<String> roleIds) {
		userRoleMapper.deleteByUserId(userId);
		for (String roleId : roleIds) {
			UserRole ur = new UserRole();
			ur.setId(IDGenerator.randomID());
			ur.setUserId(userId);
			ur.setRoleId(roleId);
			userRoleMapper.insert(ur);
		}
	}

}
