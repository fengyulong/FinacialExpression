package com.yulong.user.service;

import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.yulong.BaseJunit4Test;

import priv.yulong.enumeration.Sex;
import priv.yulong.sys.model.User;
import priv.yulong.sys.service.UserService;

public class UserServiceTest extends BaseJunit4Test {

	@Resource
	private UserService userService;

	@Test
	@Rollback(false)
	@Transactional
	public void test() {
		User user = new User();
		user.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		user.setUsername("admin");
		user.setSex(Sex.F);
		
		userService.addUser(user);
		//user = userService.getUser("admin");
		System.out.println(user);
	}

}
