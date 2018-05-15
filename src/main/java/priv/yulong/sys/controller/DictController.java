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
import priv.yulong.common.util.StringUtil;
import priv.yulong.sys.model.Dict;
import priv.yulong.sys.model.DictItem;
import priv.yulong.sys.model.DictItemKey;
import priv.yulong.sys.model.User;
import priv.yulong.sys.service.DictItemService;
import priv.yulong.sys.service.DictService;

@Controller
@RequestMapping(value = "/sys/dict")
public class DictController {

	@Resource
	private DictService dictService;
	@Resource
	private DictItemService dictItemService;

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(String id, String parentId, Model model) {
		Dict dict = null;
		if (StringUtil.isNotEmpty(id)) {
			dict = dictService.getByPrimaryKey(id);
		} else {
			dict = new Dict();
			dict.setParentId(parentId);
		}
		model.addAttribute("dict", dict);
		return "sys/dict/edit";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		return "sys/dict/list";
	}

	@ResponseBody
	@RequestMapping(value = "/tree", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String tree() {
		List<Dict> list = dictService.getDictTree();
		return JSON.toJSONString(list);
	}

	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(Dict dict) {
		if (dictService.getByPrimaryKey(dict.getId()) != null) {
			dictService.update(dict);
		} else {
			dictService.add(dict);
		}
		return "success";
	}

	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(String id) {
		dictService.deleteByPrimaryKey(id);
		return "success";
	}

	@RequestMapping(value = "/itemList", method = RequestMethod.GET)
	public String itemList() {
		return "sys/dict/itemList";
	}

	@ResponseBody
	@RequestMapping(value = "/itemQuery", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String itemQuery(String dictId, int page, int rows, String sort, String order) {
		Page<User> pageinfo = PageHelper.startPage(page, rows, true);
		if (StringUtil.isNotEmpty(sort)) {
			PageHelper.orderBy(sort + " " + order);
		}
		List<DictItem> itemList = dictItemService.getItems(dictId);
		DataGridModel<DictItem> grid = new DataGridModel<DictItem>();
		grid.setRows(itemList);
		grid.setTotal(pageinfo.getTotal());
		return JSON.toJSONString(grid);
	}
	
	@ResponseBody
	@RequestMapping(value = "/itemQuery", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public String itemQuery(String dictId) {
		List<DictItem> itemList = dictItemService.getItems(dictId);
		return JSON.toJSONString(itemList);
	}

	@RequestMapping(value = "/itemEdit", method = RequestMethod.GET)
	public String itemEdit(String dictId, String value, Model model) {
		DictItem dictItem = null;
		if (StringUtil.isNotEmpty(value)) {
			DictItemKey key = new DictItemKey();
			key.setDictId(dictId);
			key.setValue(value);
			dictItem = dictItemService.getByPrimaryKey(key);
		} else {
			dictItem = new DictItem();
			dictItem.setDictId(dictId);
		}
		model.addAttribute("dictItem", dictItem);
		return "sys/dict/itemEdit";
	}

	@ResponseBody
	@RequestMapping(value = "/itemEdit", method = RequestMethod.POST)
	public String itemEdit(DictItem dictItem) {
		if (dictItemService.getByPrimaryKey(dictItem) == null) {
			dictItemService.add(dictItem);
		} else {
			dictItemService.update(dictItem);
		}
		return "success";
	}

	@ResponseBody
	@RequestMapping(value = "/itemDelete", method = RequestMethod.POST)
	public String delete(DictItem dictItem) {
		dictItemService.deleteByPrimaryKey(dictItem);
		return "success";
	}

}
