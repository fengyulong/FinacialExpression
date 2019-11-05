package priv.yulong.datafetch.fetchtest.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName FetchTestController
 * @Description: 取数测试
 * @Author yulong.feng
 * @Date 2019-11-05
 * @Version V1.0
 **/
@Controller
@RequestMapping(value = "/datafetch/fetchtest")
public class FetchTestController {
   /**
    * @MethodName: list
    * @Description: 取数测试页面
    * @Param: []
    * @Return: java.lang.String
    * @Author: yulong.feng
    * @Date: 2019-11-05
   **/
    @RequiresPermissions(value = { "datafetch:fetchtest:list" })
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "datafetch/fetchtest/list";
    }
}
