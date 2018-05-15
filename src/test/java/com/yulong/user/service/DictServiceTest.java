package com.yulong.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.yulong.BaseJunit4Test;

import priv.yulong.sys.model.Dict;
import priv.yulong.sys.service.DictService;

public class DictServiceTest extends BaseJunit4Test{

	@Resource
	private DictService dictService;

	@Test
	@Rollback(false)
	@Transactional
	public void test(){
		List<Dict> dict = dictService.getDictTree();
		System.out.println(JSON.toJSONString(dict));
	}
	
	
}
