package priv.yulong.sys.service;

import priv.yulong.sys.model.User;

public interface UserService {

	/**
	 * 添加用户
	 * 
	 * @param user
	 */
	User addUser(User user);

	/**
	 * 查询用户
	 * 
	 * @param userCode
	 * @return
	 */
	User getUser(String username);

	/**
	 * 更新用户
	 * 
	 * @param user
	 */
	User updateUser(User user);

	/**
	 * 删除用户
	 * 
	 * @param user
	 */
	void deleteUser(User user);
}
