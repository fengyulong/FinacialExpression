package com.yulong.datafetch.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.yulong.datafetch.requester.DataFetchRequester;

@Controller
public class DataFetchController {
	@RequestMapping(value = "/DataFetchService", method = RequestMethod.POST)
	public void dataFetch(@RequestBody String json, HttpServletRequest request, HttpServletResponse response) {
		DataFetchRequester dataFetchRequester = JSON.parseObject(json, DataFetchRequester.class);
		System.out.println(dataFetchRequester);

	}
}
