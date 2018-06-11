package priv.yulong.datafetch.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import priv.yulong.datasourse.model.Datasource;
import priv.yulong.datasourse.service.DatasourceService;

@Controller
public class TestLogController {

	@Resource
	private DatasourceService datasourceService;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public void test(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Datasource ds = new Datasource();
		ds.setCode("test");
		ds.setName("测试");
		//ds.setDbType("dbType");
		ds.setHost("host");
		ds.setInstance("instance");
		ds.setPort("port");
		//ds.setSoftVersion("softVersion");
		ds.setUserName("username");
		ds.setUserPassword("password");
		datasourceService.addDatasource(ds);
		
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("调用完成");
	}
}
