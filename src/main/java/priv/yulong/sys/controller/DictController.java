package priv.yulong.sys.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import priv.yulong.common.util.StringUtil;
import priv.yulong.sys.model.Dict;
import priv.yulong.sys.service.DictService;

@Controller
@RequestMapping(value = "/sys/dict")
public class DictController {

	@Resource
	private DictService dictService;

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(String id, String parentId, Model model) {
		Dict dict = null;
		if (StringUtil.isNotEmpty(id)) {
			dict = dictService.selectByPrimaryKey(id);
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
		List<Dict> list = dictService.selectDictTree();
		return JSON.toJSONString(list);
	}

	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(Dict dict) {
		if (dictService.selectByPrimaryKey(dict.getId()) != null) {
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
}
