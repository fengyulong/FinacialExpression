package priv.yulong.sys.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.NameFilter;

import priv.yulong.common.model.DataGridModel;
import priv.yulong.common.util.IDGenerator;
import priv.yulong.common.util.StringUtil;
import priv.yulong.sys.model.Permission;
import priv.yulong.sys.model.Permission.PermissionType;
import priv.yulong.sys.service.PermissionService;

@Controller
@RequestMapping(value = "/sys/permission")
public class PermissionController {

	@Resource
	private PermissionService permissionService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		// model.addAttribute(attributeName, attributeValue)
		return "sys/permission/list";
	}

	@ResponseBody
	@RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String getPermission() {
		List<Permission> list = permissionService.getAll();
		DataGridModel<Permission> grid = new DataGridModel<Permission>();
		grid.setRows(list);
		return JSON.toJSONString(grid, new NameFilter() {

			@Override
			public String process(Object object, String name, Object value) {
				if ("parentId".equals(name)) {
					return "_parentId";
				}
				if ("icon".equals(name)) {
					return "iconCls";
				}
				return name;
			}
		});
	}

	@RequestMapping(value = "/edit/{type}", method = RequestMethod.GET)
	public String edit(Model model, @PathVariable("type") Permission.PermissionType type, String id) {
		Permission permission = null;
		if (StringUtil.isNotEmpty(id)) {
			permission = permissionService.getByPrimaryKey(id);
		} else {
			permission = new Permission();
			permission.setType(type.toString());
		}
		model.addAttribute("permission", permission);
		if (type == PermissionType.GROUP) {
			return "sys/permission/editgroup";
		} else if (type == PermissionType.MENU) {
			return "sys/permission/editmenu";
		} else {
			return "sys/permission/editbutton";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/groupList", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public String groupList() {
		List<Permission> permissionList = permissionService.getByType(Permission.PermissionType.GROUP.toString());
		return JSON.toJSONString(permissionList);
	}

	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editgroup(Permission permission) {
		if (StringUtil.isEmpty(permission.getId())) {
			permission.setId(IDGenerator.randomID());
			permissionService.add(permission);
		} else {
			permissionService.update(permission);
		}
		return "success";
	}
}
