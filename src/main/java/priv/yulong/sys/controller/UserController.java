package priv.yulong.sys.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import priv.yulong.common.model.DataGridModel;
import priv.yulong.common.util.StringUtil;
import priv.yulong.sys.model.User;
import priv.yulong.sys.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	@Resource
	private UserService userService;

	@ResponseBody
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public String getUser(int page, int rows, String sort, String order) {
		Page<User> pageinfo = PageHelper.startPage(page, rows, true);
		if (StringUtil.isNotEmpty(sort)) {
			PageHelper.orderBy(sort + " " + order);
		}
		List<User> userList = userService.getUser();
		DataGridModel<User> grid = new DataGridModel<>();
		grid.setRows(userList);
		grid.setTotal(pageinfo.getTotal());
		return JSON.toJSONString(grid);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String toUser() {
		return "user";
	}
}
