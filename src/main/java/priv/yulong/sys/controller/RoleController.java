package priv.yulong.sys.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import priv.yulong.sys.model.Role;
import priv.yulong.sys.model.User;
import priv.yulong.sys.service.RoleService;

@Controller
@RequestMapping(value = "/sys/role")
public class RoleController {

	@Resource
	private RoleService roleService;

	/**
	 * 角色管理-列表页面
	 * 
	 * @return
	 */
	@RequiresPermissions(value = { "sys:role:list" })
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		return "sys/role/list";
	}

	/**
	 * 角色管理-列表数据
	 * 
	 * @param page
	 *            页码
	 * @param rows
	 *            每页条数
	 * @param sort
	 *            排序字段
	 * @param order
	 *            正序或逆序
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions(value = { "sys:role:list" })
	@RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String getUser(int page, int rows, String sort, String order) {
		Page<User> pageinfo = PageHelper.startPage(page, rows, true);
		if (StringUtil.isNotEmpty(sort)) {
			PageHelper.orderBy(sort + " " + order);
		}
		List<Role> roleList = roleService.getAll();
		DataGridModel<Role> grid = new DataGridModel<Role>();
		grid.setRows(roleList);
		grid.setTotal(pageinfo.getTotal());
		return JSON.toJSONString(grid);
	}

	/**
	 * 角色管理-设置权限界面
	 * 
	 * @param roleId
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value = { "sys:role:permission" })
	@RequestMapping(value = "/permission", method = RequestMethod.GET)
	public String permission(String roleId, Model model) {
		model.addAttribute("roleId", roleId);
		return "sys/role/permission";
	}

	/**
	 * 角色管理-设置权限
	 * 
	 * @param roleId
	 * @param list
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions(value = { "sys:role:permission" })
	@RequestMapping(value = "/permission", method = RequestMethod.POST)
	public String permission(String roleId, @RequestParam ArrayList<String> list) {
		roleService.addRolePermission(list, roleId);
		return "success";
	}

	/**
	 * 角色管理-新增界面
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value = { "sys:role:add" })
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		Role role = new Role();
		role.setId(IDGenerator.randomID());
		model.addAttribute("role", role);
		return "sys/role/add";
	}
	
	/**
	 * 角色管理-新增保存
	 * @param role
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions(value = { "sys:role:add" })
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(Role role) {
		roleService.add(role);
		return "success";
	}

	/**
	 * 角色管理-编辑界面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value = { "sys:role:edit" })
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(String id, Model model) {
		Role role = roleService.getByPrimaryKey(id);
		model.addAttribute("role", role);
		return "sys/role/edit";
	}

	/**
	 * 角色管理-编辑保存
	 * 
	 * @param role
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions(value = { "sys:role:edit" })
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(Role role) {
		roleService.update(role);
		return "success";
	}

	@ResponseBody
	@RequestMapping(value = "/queryAll", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String getAllUser(String sort, String order) {
		if (StringUtil.isNotEmpty(sort)) {
			PageHelper.orderBy(sort + " " + order);
		}
		List<Role> roleList = roleService.getAll();
		DataGridModel<Role> grid = new DataGridModel<Role>();
		grid.setRows(roleList);
		return JSON.toJSONString(grid);
	}

	@ResponseBody
	@RequiresPermissions(value = { "sys:role:delete" })
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(String id) {
		roleService.delete(id);
		return "success";
	}

}
