package priv.yulong.user.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import priv.yulong.user.mapper.UserMapper;
import priv.yulong.user.model.User;
import priv.yulong.user.service.UserService;

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
	public User getUser(String userCode) {
		return userMapper.selectByUserCode(userCode);
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
