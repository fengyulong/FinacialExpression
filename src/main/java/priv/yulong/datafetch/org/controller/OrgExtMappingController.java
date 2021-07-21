package priv.yulong.datafetch.org.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.yulong.common.model.DataGridModel;
import priv.yulong.common.util.StringUtil;
import priv.yulong.datafetch.org.model.OrgMapping;
import priv.yulong.datafetch.org.service.OrgExtMappingService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/datafetch/orgextmapping")
public class OrgExtMappingController {

    @Resource
    private OrgExtMappingService orgExtMappingService;

    /**
     * 机构映射管理-列表页面
     *
     * @return
     */
    @RequiresPermissions(value = {"datafetch:orgextmapping:list"})
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "datafetch/orgextmapping/list";
    }

    /**
     * 机构映射管理-左侧树形数据
     *
     * @return
     */
    @ResponseBody
    @RequiresPermissions(value = {"datafetch:orgextmapping:list"})
    @RequestMapping(value = "/tree", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String tree() {
        List<OrgMapping> list = new ArrayList<OrgMapping>();
        OrgMapping orgMapping = orgExtMappingService.getTree();
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
     * @param code
     * @return
     */
    @ResponseBody
    @RequiresPermissions(value = {"datafetch:orgextmapping:list"})
    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String list(String code, int page, int rows) {
        Page<OrgMapping> pageinfo = PageHelper.startPage(page, rows, true);
        List<OrgMapping> list = null;
        if (StringUtil.isEmpty(code)) {
            list = orgExtMappingService.getAll();
        } else {
            list = orgExtMappingService.getWithNextLevelChildren(code);
        }
        DataGridModel<OrgMapping> grid = new DataGridModel<OrgMapping>();
        grid.setRows(list);
        grid.setTotal(pageinfo.getTotal());
        return JSON.toJSONString(grid, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty);
    }

    /**
     * 机构映射管理-新增
     *
     * @param orgMapping
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String add(OrgMapping orgMapping) {
        orgExtMappingService.add(orgMapping);
        return "success";
    }

    /**
     * 机构映射管理-编辑
     *
     * @param orgMapping
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(OrgMapping orgMapping) {
        orgExtMappingService.update(orgMapping);
        return "success";
    }

    /**
     * 机构映射管理-删除
     *
     * @param orgMapping
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(OrgMapping orgMapping) {
        orgExtMappingService.delete(orgMapping.getCode());
        return "success";
    }

}
