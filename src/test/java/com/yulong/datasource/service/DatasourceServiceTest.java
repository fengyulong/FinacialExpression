package com.yulong.datasource.service;

import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;

import com.yulong.BaseJunit4Test;

import priv.yulong.datasourse.model.Datasource;
import priv.yulong.datasourse.service.DatasourceService;

public class DatasourceServiceTest extends BaseJunit4Test{

	@Resource
	private DatasourceService datasourceService;
	
	@Test
	public void test(){
		Datasource ds = new Datasource();
		ds.setId(UUID.randomUUID().toString());
		ds.setCode("test");
		ds.setName("测试");
		ds.setDbType("dbType");
		ds.setHost("host");
		ds.setInstance("instance");
		ds.setPort("port");
		ds.setSoftVersion("softVersion");
		ds.setUserName("username");
		ds.setUserPassword("password");
		datasourceService.addDatasource(ds);
	}
	
}