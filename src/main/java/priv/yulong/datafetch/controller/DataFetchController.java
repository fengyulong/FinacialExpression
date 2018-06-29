package priv.yulong.datafetch.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import priv.yulong.datafetch.requester.DataFetchRequester;
import priv.yulong.datafetch.response.DataFetchResponser;
import priv.yulong.datafetch.service.DataFetchService;

@Controller
public class DataFetchController {
	
	@Resource
	private DataFetchService dataFetchService;
	
	@ResponseBody
	@RequestMapping(value = "/DataFetchService", method = RequestMethod.POST)
	public String dataFetch(@RequestBody String json, HttpServletRequest request, HttpServletResponse response) {
		DataFetchRequester dataFetchRequester = JSON.parseObject(json, DataFetchRequester.class);
		DataFetchResponser rep = dataFetchService.dataFetch(dataFetchRequester);
		return JSON.toJSONString(rep);
	}
}


