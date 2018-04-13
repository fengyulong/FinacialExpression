package com.yulong.user.service;

import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.yulong.BaseJunit4Test;

import priv.yulong.user.model.User;
import priv.yulong.user.service.UserService;

public class UserServiceTest extends BaseJunit4Test {

	@Resource
	private UserService userService;

	@Test
	@Rollback(true)
	@Transactional
	public void test() {
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setUserCode("test");
		user.setUserName("测试");
		user.setUserPassword("pass");
		user.setDisable(true);
		userService.addUser(user);
		user = userService.getUser("test");
		System.out.println(user);
	}

}
