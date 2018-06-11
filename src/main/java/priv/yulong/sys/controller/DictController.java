package priv.yulong.sys.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import priv.yulong.common.model.DataGridModel;
import priv.yulong.common.util.StringUtil;
import priv.yulong.sys.model.Dict;
import priv.yulong.sys.model.DictItem;
import priv.yulong.sys.model.DictItemKey;
import priv.yulong.sys.model.User;
import priv.yulong.sys.service.DictService;

@Controller
@RequestMapping(value = "/sys/dict")
public class DictController {

	@Resource
	private DictService dictService;

	/**
	 * 字典管理-列表页面
	 * 
	 * @return
	 */
	@RequiresPermissions(value = { "sys:dict:list" })
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		return "sys/dict/list";
	}

	/**
	 * 字典管理-列表页面树形JSON
	 * 
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions(value = { "sys:dict:list" })
	@RequestMapping(value = "/tree", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String tree() {
		List<Dict> list = dictService.getDictTree();
		return JSON.toJSONString(list);
	}

	/**
	 * 字典管理-新增界面
	 * 
	 * @param parentId
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value = { "sys:dict:add" })
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(String parentId, Model model) {
		Dict dict = new Dict();
		dict.setParentId(parentId);
		model.addAttribute("dict", dict);
		return "sys/dict/add";
	}

	/**
	 * 字典管理-新增数据保存
	 * 
	 * @param dict
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions(value = { "sys:dict:add" })
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(Dict dict) {
		dictService.add(dict);
		return "success";
	}

	/**
	 * 字典管理-编辑页面
	 * 
	 * @param id
	 *            字典id
	 * @param parentId
	 *            上级id
	 * @param model
	 *            页面模型
	 * @return
	 */
	@RequiresPermissions(value = { "sys:dict:edit" })
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(String id, String parentId, Model model) {
		Dict dict = dictService.getByPrimaryKey(id);
		model.addAttribute("dict", dict);
		return "sys/dict/edit";
	}

	/**
	 * 字典管理-编辑数据保存
	 * 
	 * @param dict
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions(value = { "sys:dict:edit" })
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(Dict dict) {
		dictService.update(dict);
		return "success";
	}

	/**
	 * 字典管理-删除字典
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions(value = { "sys:dict:delete" })
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(String id) {
		dictService.deleteByPrimaryKey(id);
		return "success";
	}

	/**
	 * 字典管理-字典项列表
	 * 
	 * @return
	 */
	@RequiresPermissions(value = { "sys:dict:list" })
	@RequestMapping(value = "/itemList", method = RequestMethod.GET)
	public String itemList() {
		return "sys/dict/itemList";
	}

	/**
	 * 字典管理-字典项数据JSON
	 * 
	 * @param dictId
	 *            字典id
	 * @param page
	 *            页码
	 * @param rows
	 *            每页数量
	 * @param sort
	 *            排序字段
	 * @param order
	 *            正序或逆序
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions(value = { "sys:dict:list" })
	@RequestMapping(value = "/itemQuery", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String itemQuery(String dictId, int page, int rows, String sort, String order) {
		Page<User> pageinfo = PageHelper.startPage(page, rows, true);
		if (StringUtil.isNotEmpty(sort)) {
			PageHelper.orderBy(sort + " " + order);
		}
		List<DictItem> itemList = dictService.getItems(dictId);
		DataGridModel<DictItem> grid = new DataGridModel<DictItem>();
		grid.setRows(itemList);
		grid.setTotal(pageinfo.getTotal());
		return JSON.toJSONString(grid);
	}

	/**
	 * 字典引用数据JSON
	 * 
	 * @param dictId
	 *            字典id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/itemQuery", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public String itemQuery(String dictId) {
		List<DictItem> itemList = dictService.getItems(dictId);
		return JSON.toJSONString(itemList);
	}

	/**
	 * 字典管理-字典项新增界面
	 * 
	 * @param dictId
	 * @param model
	 * @return
	 */
	@RequiresPermissions(value = { "sys:dict:add" })
	@RequestMapping(value = "/itemAdd", method = RequestMethod.GET)
	public String itemAdd(String dictId, Model model) {
		DictItem dictItem = new DictItem();
		dictItem.setDictId(dictId);
		model.addAttribute("dictItem", dictItem);
		return "sys/dict/itemAdd";
	}

	/**
	 * 字典管理-字典项新增数据保存
	 * 
	 * @param dictItem
	 *            字典项数据
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions(value = { "sys:dict:add" })
	@RequestMapping(value = "/itemAdd", method = RequestMethod.POST)
	public String itemAdd(DictItem dictItem) {
		dictService.addItem(dictItem);
		return "success";
	}

	/**
	 * 字典管理-字典项编辑页面
	 * 
	 * @param dictId
	 *            字典id
	 * @param value
	 *            字典项值
	 * @param model
	 *            页面模型
	 * @return
	 */
	@RequiresPermissions(value = { "sys:dict:edit" })
	@RequestMapping(value = "/itemEdit", method = RequestMethod.GET)
	public String itemEdit(String dictId, String value, Model model) {
		DictItemKey key = new DictItemKey();
		key.setDictId(dictId);
		key.setValue(value);
		DictItem dictItem = dictService.getItem(key);
		model.addAttribute("dictItem", dictItem);
		return "sys/dict/itemEdit";
	}

	/**
	 * 字典管理-字典项编辑数据保存
	 * 
	 * @param dictItem
	 *            字典项数据
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions(value = { "sys:dict:edit" })
	@RequestMapping(value = "/itemEdit", method = RequestMethod.POST)
	public String itemEdit(DictItem dictItem) {
		dictService.updateItem(dictItem);
		return "success";
	}

	/**
	 * 字典管理-字典项删除
	 * 
	 * @param dictItem
	 *            字典项
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions(value = { "sys:dict:delete" })
	@RequestMapping(value = "/itemDelete", method = RequestMethod.POST)
	public String delete(DictItem dictItem) {
		dictService.deleteItem(dictItem);
		return "success";
	}

}
