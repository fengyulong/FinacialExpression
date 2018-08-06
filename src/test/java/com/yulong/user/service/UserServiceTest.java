package com.yulong.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yulong.BaseJunit4Test;

import priv.yulong.common.model.DataGridModel;
import priv.yulong.sys.model.User;
import priv.yulong.sys.service.UserService;

public class UserServiceTest extends BaseJunit4Test {

	@Resource
	private UserService userService;

	@Test
	@Rollback(false)
	@Transactional
	public void test() {
//		User user = new User();
//		user.setId(UUID.randomUUID().toString().replaceAll("-", ""));
//		user.setUsername("admin");
//		user.setPassword(new SimpleHash("MD5", "", "admin", 3).toHex()); 
//		
//		userService.addUser(user);
//		//user = userService.getUser("admin");
//		System.out.println(user);
		 Page<User> page = PageHelper.startPage(1, 2, true);
		List<User> list = userService.getUser(new User());
		//PageInfo pageInfo = new PageInfo<>(list,5);  
		DataGridModel<User> grid = new DataGridModel<User>();
		grid.setRows(list);
		grid.setTotal(page.getTotal());
		System.out.println(JSON.toJSONString(grid));
		System.out.println("123");
	}

}
