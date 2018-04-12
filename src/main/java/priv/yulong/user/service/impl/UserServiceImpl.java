package priv.yulong.user.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import priv.yulong.user.mapper.UserMapper;
import priv.yulong.user.model.User;
import priv.yulong.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;

	@Override
	public void addUser(User user) {
		userMapper.insert(user);
	}

	@Override
	public User getUser(String userCode) {
		return userMapper.selectByUserCode(userCode);
	}

	@Override
	public void updateUser(User user) {
		userMapper.updateByPrimaryKey(user);
	}

	@Override
	public void deleteUser(User user) {
		userMapper.updateByPrimaryKey(user);
	}

}
