package priv.yulong.sys.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import priv.yulong.common.model.DataGridModel;
import priv.yulong.common.util.IDGenerator;
import priv.yulong.common.util.StringUtil;
import priv.yulong.sys.model.User;
import priv.yulong.sys.model.UserRole;
import priv.yulong.sys.service.UserService;

@Controller
@RequestMapping(value = "/sys/user")
public class UserController {
	@Resource
	private UserService userService;

	/**
	 * 用户管理-列表页面
	 * 
	 * @return
	 */
	@RequiresPermissions(value = { "sys:user:list" })
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		return "sys/user/list";
	}

	/**
	 * 用户管理-新增页面
	 * 
	 * @return
	 */
	@RequiresPermissions(value = { "sys:user:add" })
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		User user = new User();
		user.setId(IDGenerator.randomID());
		return "sys/user/add";
	}

	/**
	 * 用户管理-新增页面保存数据
	 * 
	 * @param user
	 *            用户数据
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions(value = { "sys:user:add" })
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String add(User user) {
		user.setPassword(new SimpleHash("MD5", user.getUsername(), user.getUsername(), 3).toHex());
		userService.addUser(user);
		return "success";
	}

	/**
	 * 用户管理-编辑页面
	 * 
	 * @param id
	 *            用户id
	 * @param model
	 *            页面模型
	 * @return
	 */
	@RequiresPermissions(value = { "sys:user:edit" })
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(String id, Model model) {
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return "sys/user/edit";
	}

	/**
	 * 用户管理-编辑页面保存数据
	 * 
	 * @param user
	 *            用户数据
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions(value = { "sys:user:edit" })
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(User user) {
		userService.updateUser(user);
		return "success";
	}

	/**
	 * 用户管理-设置角色页面
	 * 
	 * @param userId
	 *            用户id
	 * @param model
	 *            页面模型
	 * @return
	 */
	@RequiresPermissions(value = { "sys:user:role" })
	@RequestMapping(value = "/role", method = RequestMethod.GET)
	public String role(String userId, Model model) {
		List<UserRole> userRoleList = userService.getUserRoleList(userId);
		model.addAttribute("userRoleList", userRoleList);
		return "sys/user/role";
	}

	/**
	 * 用户管理-设置角色保存
	 * 
	 * @param userId
	 *            用户id
	 * @param list
	 *            角色id列表
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions(value = { "sys:user:role" })
	@RequestMapping(value = "/role", method = RequestMethod.POST)
	public String role(String userId, @RequestParam List<String> list) {
		userService.addUserRole(userId, list);
		return "success";
	}

	/**
	 * 用户管理-列表页面json数据
	 * 
	 * @param page
	 *            页码
	 * @param rows
	 *            每页条数
	 * @param sort
	 *            排序字段
	 * @param order
	 *            正序或逆序
	 * @param user
	 *            用户的查询条件
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions(value = { "sys:user:list" })
	@RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String getUser(int page, int rows, String sort, String order, User user) {
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

	/**
	 * 用户管理-删除用于
	 * 
	 * @param id
	 *            用户id
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions(value = { "sys:user:delete" })
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(String id) {
		userService.deleteUser(id);
		return "success";
	}
}
