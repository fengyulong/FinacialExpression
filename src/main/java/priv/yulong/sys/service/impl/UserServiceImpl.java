package priv.yulong.sys.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import priv.yulong.sys.mapper.UserMapper;
import priv.yulong.sys.model.User;
import priv.yulong.sys.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;

	@Override
	public User addUser(User user) {
		userMapper.insert(user);
		return user;
	}

	@Override
	public User getUser(String username) {
		return userMapper.selectByUsername(username);
	}

	@Override
	public User updateUser(User user) {
		userMapper.updateByPrimaryKey(user);
		return user;
	}

	@Override
	public void deleteUser(User user) {
		userMapper.updateByPrimaryKey(user);
	}

}
