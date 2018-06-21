package priv.yulong.datafetch.org.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import priv.yulong.common.model.DataGridModel;
import priv.yulong.common.util.StringUtil;
import priv.yulong.datafetch.org.model.OrgMapping;
import priv.yulong.datafetch.org.service.OrgMappingService;

@Controller
@RequestMapping(value = "/datafetch/orgmapping")
public class OrgMappingController {

	@Resource
	private OrgMappingService orgMappingService;

	/**
	 * 机构映射管理-列表页面
	 * 
	 * @return
	 */
	@RequiresPermissions(value = { "datafetch:orgmapping:list" })
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		return "datafetch/orgmapping/list";
	}

	/**
	 * 机构映射管理-左侧树形数据
	 * 
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions(value = { "datafetch:orgmapping:list" })
	@RequestMapping(value = "/tree", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String tree() {
		List<OrgMapping> list = new ArrayList<OrgMapping>();
		OrgMapping orgMapping = orgMappingService.getTree();
		list.add(orgMapping);
		String json = JSON.toJSONString(list, new NameFilter() {

			@Override
			public String process(Object object, String name, Object value) {
				return OrgMapping.convertToTreeField(name);
			}
		});
		return json;
	}

	/**
	 * 机构映射管理-右侧列表数据
	 * 
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @param code
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions(value = { "datafetch:orgmapping:list" })
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String list(String code, int page, int rows) {
		Page<OrgMapping> pageinfo = PageHelper.startPage(page, rows, true);
		List<OrgMapping> list = null;
		if (StringUtil.isEmpty(code)) {
			list = orgMappingService.getAll();
		} else {
			list = orgMappingService.getWithNextLevelChildren(code);
		}
		DataGridModel<OrgMapping> grid = new DataGridModel<OrgMapping>();
		grid.setRows(list);
		grid.setTotal(pageinfo.getTotal());
		return JSON.toJSONString(grid, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullListAsEmpty);
	}

	/**
	 * 机构映射管理-新增
	 * @param orgMapping
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String add(OrgMapping orgMapping) {
		orgMappingService.add(orgMapping);
		return "success";
	}

	/**
	 * 机构映射管理-编辑
	 * @param orgMapping
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(OrgMapping orgMapping) {
		orgMappingService.update(orgMapping);
		return "success";
	}
	
	/**
	 * 机构映射管理-删除
	 * @param orgMapping
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(OrgMapping orgMapping) {
		orgMappingService.delete(orgMapping.getCode());
		return "success";
	}

}
