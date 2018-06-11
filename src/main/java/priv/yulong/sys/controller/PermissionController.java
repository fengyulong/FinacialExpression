package priv.yulong.sys.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.AfterFilter;
import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.SerializeFilter;

import priv.yulong.common.model.DataGridModel;
import priv.yulong.common.util.IDGenerator;
import priv.yulong.sys.model.Permission;
import priv.yulong.sys.model.Permission.PermissionType;
import priv.yulong.sys.service.PermissionService;

@Controller
@RequestMapping(value = "/sys/permission")
public class PermissionController {

	@Resource
	private PermissionService permissionService;

	/**
	 * 权限管理-列表页面
	 * 
	 * @return
	 */
	@RequiresPermissions(value = { "sys:permission:list" })
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		return "sys/permission/list";
	}

	/**
	 * 权限管理-列表页面树形数据JSON
	 * 
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions(value = { "sys:permission:list" })
	@RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String getPermission() {
		List<Permission> list = permissionService.getPermissionTree();
		DataGridModel<Permission> grid = new DataGridModel<Permission>();
		grid.setRows(list);
		return JSON.toJSONString(grid, new SerializeFilter[] { new NameFilter() {

			@Override
			public String process(Object object, String name, Object value) {
				if ("icon".equals(name)) {
					return "iconCls";
				}
				return name;
			}
		}, new AfterFilter() {

			@Override
			public void writeAfter(Object object) {
				if (object instanceof Permission) {
					Permission p = (Permission) object;
					if (p.getType() == PermissionType.MENU) {
						writeKeyValue("state", "closed");
					}
				}
			}
		} });
	}

	/**
	 * 权限管理-新增页面
	 * 
	 * @param model
	 * @param type
	 * @param id
	 * @return
	 */
	@RequiresPermissions(value = { "sys:permission:add" })
	@RequestMapping(value = "/add/{type}", method = RequestMethod.GET)
	public String add(Model model, @PathVariable("type") Permission.PermissionType type) {
		Permission permission = new Permission();
		permission.setType(type);
		permission.setId(IDGenerator.randomID());
		model.addAttribute("permission", permission);
		if (type == PermissionType.GROUP) {
			return "sys/permission/addgroup";
		} else if (type == PermissionType.MENU) {
			return "sys/permission/addmenu";
		} else {
			return "sys/permission/addbutton";
		}
	}

	/**
	 * 权限管理-新增保存
	 * 
	 * @param permission
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions(value = { "sys:permission:add" })
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(Permission permission) {
		permissionService.add(permission);
		return "success";
	}

	/**
	 * 权限管理-编辑页面
	 * 
	 * @param model
	 * @param type
	 * @param id
	 * @return
	 */
	@RequiresPermissions(value = { "sys:permission:edit" })
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Model model, String id) {
		Permission permission = permissionService.getByPrimaryKey(id);
		model.addAttribute("permission", permission);
		PermissionType type = permission.getType();
		if (type == PermissionType.GROUP) {
			return "sys/permission/editgroup";
		} else if (type == PermissionType.MENU) {
			return "sys/permission/editmenu";
		} else {
			return "sys/permission/editbutton";
		}
	}

	/**
	 * 权限管理-编辑保存
	 * 
	 * @param permission
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions(value = { "sys:permission:edit" })
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editgroup(Permission permission) {
		permissionService.update(permission);
		return "success";
	}

	/**
	 * 权限管理-分组列表JSON
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/groupList", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public String groupList() {
		List<Permission> permissionList = permissionService.getByType(Permission.PermissionType.GROUP.toString());
		return JSON.toJSONString(permissionList);
	}

	/**
	 * 权限管理-菜单树形JSON
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/menuTree", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public String menuTree() {
		List<Permission> permissionList = permissionService.getMenuTree();
		return JSON.toJSONString(permissionList, new NameFilter() {

			@Override
			public String process(Object object, String name, Object value) {
				if ("name".equals(name)) {
					return "text";
				}
				if ("icon".equals(name)) {
					return "iconCls";
				}
				return name;
			}
		});
	}

	/**
	 * 权限管理-权限树形JSON
	 * 
	 * @param roleId
	 *            角色Id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/permissionTree", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public String permissionTree(String roleId) {
		List<Permission> checkedList = permissionService.getPermissionsByRoleId(roleId);
		List<Permission> permissionList = permissionService.getPermissionTree();
		NameFilter nameFilter = new NameFilter() {

			@Override
			public String process(Object object, String name, Object value) {
				if ("name".equals(name)) {
					return "text";
				}
				if ("icon".equals(name)) {
					return "iconCls";
				}
				return name;
			}
		};

		AfterFilter afterFilter = new AfterFilter() {

			@Override
			public void writeAfter(Object object) {
				if (checkedList.contains(object)) {
					writeKeyValue("checked", "indeterminate");
				} else {
					writeKeyValue("checked", false);
				}

			}
		};
		return JSON.toJSONString(permissionList, new SerializeFilter[] { nameFilter, afterFilter });
	}

	/**
	 * 权限管理-删除
	 * 
	 * @param permission
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions(value = { "sys:permission:delete" })
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(Permission permission) {
		permission = permissionService.getPermissionTreeById(permission.getId());
		permissionService.deleteCascade(permission);
		return "success";
	}
}
