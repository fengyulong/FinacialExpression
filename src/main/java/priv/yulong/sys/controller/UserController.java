package priv.yulong.sys.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import priv.yulong.common.model.DataGridModel;
import priv.yulong.common.util.IDGenerator;
import priv.yulong.common.util.StringUtil;
import priv.yulong.sys.model.User;
import priv.yulong.sys.service.UserService;

@Controller
@RequestMapping(value = "/sys/user")
public class UserController {
	@Resource
	private UserService userService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		return "sys/user/list";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(String id, Model model) {
		if(StringUtil.isNotEmpty(id)){
			User user = userService.getUserById(id);
			model.addAttribute("user", user);
		}
		return "sys/user/edit";
	}

	@ResponseBody
	@RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String getUser(int page, int rows, String sort, String order,User user) {
		Page<User> pageinfo = PageHelper.startPage(page, rows, true);
		if (StringUtil.isNotEmpty(sort)) {
			PageHelper.orderBy(sort + " " + order);
		}
		List<User> userList = userService.getUser(user);
		DataGridModel<User> grid = new DataGridModel<User>();
		grid.setRows(userList);
		grid.setTotal(pageinfo.getTotal());
		return JSON.toJSONString(grid);
	}

	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(User user) {
		if (StringUtil.isEmpty(user.getId())) {
			user.setId(IDGenerator.randomID());
			userService.addUser(user);
		}else{
			userService.updateUser(user);
		}
		return "success";
	}

	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(String id) {
		userService.deleteUser(id);
		return "success";
	}
}
