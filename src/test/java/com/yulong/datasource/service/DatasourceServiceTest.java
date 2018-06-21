package com.yulong.datasource.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.yulong.BaseJunit4Test;

import priv.yulong.datafetch.datasourse.model.Datasource;
import priv.yulong.datafetch.datasourse.model.Datasource.DbType;
import priv.yulong.datafetch.datasourse.model.Datasource.SoftVersion;
import priv.yulong.datafetch.datasourse.service.DatasourceService;

public class DatasourceServiceTest extends BaseJunit4Test {

	@Resource
	private DatasourceService datasourceService;

	@Test
	@Transactional
	@Rollback(true)
	public void test() {
		Datasource ds = new Datasource();
		ds.setCode("test");
		ds.setName("测试");
		ds.setDbType(DbType.Oracle);
		ds.setHost("host");
		ds.setInstance("instance");
		//ds.setPort("port");
		ds.setSoftVersion(SoftVersion.EBS);
		ds.setUserName("username");
		ds.setUserPassword("password");
		datasourceService.addDatasource(ds);
		datasourceService.deleteDatasource(ds);
		ds = datasourceService.getDataSource("test");
		ds = datasourceService.getDataSource("test");
		System.out.println(ds);

	}

}
