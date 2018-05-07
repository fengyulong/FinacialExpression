package priv.yulong.sys.service;

import java.util.List;

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
	User getUserByName(String username);

	/**
	 * 查询用户
	 * 
	 * @param id
	 * @return
	 */
	User getUserById(String id);

	/**
	 * 更新用户
	 * 
	 * @param user
	 */
	User updateUser(User user);

	/**
	 * 删除用户
	 * 
	 * @param id
	 */
	void deleteUser(String id);

	List<User> getUser(User user);
}
