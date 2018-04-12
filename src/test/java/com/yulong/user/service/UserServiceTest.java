package com.yulong.user.service;

import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;

import com.yulong.BaseJunit4Test;

import priv.yulong.user.model.User;
import priv.yulong.user.service.UserService;

public class UserServiceTest extends BaseJunit4Test {

	@Resource
	private UserService userService;

	@Test
	public void test() {
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setUserCode("test");
		user.setUserName("测试");
		user.setUserPassword("pass");
		user.setDisable(true);
		try {
			userService.addUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
