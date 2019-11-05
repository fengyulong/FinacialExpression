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

import priv.yulong.common.util.LogUtil;
import priv.yulong.datafetch.requester.DataFetchRequester;
import priv.yulong.datafetch.response.DataFetchResponser;
import priv.yulong.datafetch.service.DataFetchService;

@Controller
public class DataFetchController {

    @Resource
    private DataFetchService dataFetchService;

    @ResponseBody
    @RequestMapping(value = "/DataFetchService", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String dataFetch(@RequestBody String json, HttpServletRequest request, HttpServletResponse response) {
        LogUtil.info("取数请求参数:{}", json);
        String resp = null;
        try {
            DataFetchRequester dataFetchRequester = JSON.parseObject(json, DataFetchRequester.class);
            DataFetchResponser rep = dataFetchService.dataFetch(dataFetchRequester);
            resp = JSON.toJSONString(rep);
        } catch (Exception e) {
            LogUtil.error("取数异常:{}", e.getMessage(), e);

        }
        LogUtil.info("取数结果:{}", resp);
        return resp;
    }
}
