package priv.yulong.datafetch.fetchtest.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import priv.yulong.common.util.DateUtil;
import priv.yulong.datafetch.datasourse.model.Datasource;
import priv.yulong.datafetch.datasourse.service.DatasourceService;
import priv.yulong.datafetch.org.model.OrgMapping;
import priv.yulong.datafetch.org.service.OrgMappingService;
import priv.yulong.datafetch.requester.FixExpression;
import priv.yulong.datafetch.requester.FloatExpression;
import priv.yulong.datafetch.response.FixExpResult;
import priv.yulong.datafetch.response.FloatExpResult;
import priv.yulong.datafetch.service.DataFetchService;
import priv.yulong.expression.function.financial.FinancialConstant;

import javax.annotation.Resource;
import java.util.*;

/**
 * @ClassName FetchTestController
 * @Description: 取数测试
 * @Author yulong.feng
 * @Date 2019-11-05
 * @Version V1.0
 **/
@Controller
@RequestMapping(value = "/datafetch/fetchtest")
public class FetchTestController {

    @Resource
    private OrgMappingService orgMappingService;
    @Resource
    private DatasourceService datasourceService;
    @Resource
    private DataFetchService dataFetchService;

    /**
     * @MethodName: list
     * @Description: 取数测试页面
     * @Param: []
     * @Return: java.lang.String
     * @Author: yulong.feng
     * @Date: 2019-11-05
     **/
    @RequiresPermissions(value = {"datafetch:fetchtest:list"})
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "datafetch/fetchtest/list";
    }

    /**
     * @MethodName: fixFetch
     * @Description: 固定公式取数
     * @Param: [unitCode, startDate, endDate, expression]
     * @Return: priv.yulong.datafetch.response.FixExpResult
     * @Author: yulong.feng
     * @Date: 2019-11-08
    **/
    @RequestMapping(value = "/fix", method = RequestMethod.POST)
    public FixExpResult fixFetch(String unitCode, Date startDate, Date endDate, String expression) {
        Map<String, Object> envMap = new HashMap<String, Object>();
        OrgMapping orgMapping = orgMappingService.getByRepCode(unitCode);
        Datasource ds = datasourceService.getDataSource(orgMapping.getDatasourceCode());
        envMap.put(FinancialConstant.EnvField.ORG_CODE, orgMapping.getCode());
        envMap.put(FinancialConstant.EnvField.DATASOURCE_CODE, orgMapping.getDatasourceCode());
        envMap.put(FinancialConstant.EnvField.BOOK_CODE, orgMapping.getBookCode());
        envMap.put(FinancialConstant.EnvField.START_YEAR, DateUtil.getYear(startDate));
        envMap.put(FinancialConstant.EnvField.START_PERIOD, DateUtil.getMonth(startDate));
        envMap.put(FinancialConstant.EnvField.END_YEAR, DateUtil.getYear(endDate));
        envMap.put(FinancialConstant.EnvField.END_PERIOD, DateUtil.getMonth(endDate));
        envMap.put(FinancialConstant.EnvField.SOFT_VERSION, ds.getSoftVersion());
        FixExpression fixExpression = new FixExpression();
        fixExpression.setExpression(expression);
        FixExpResult fixExpResult = dataFetchService.fixFetch(fixExpression, envMap);
        return fixExpResult;
    }

    /**
     * @MethodName: floatFetch
     * @Description: 浮动公式取数
     * @Param: [unitCode, startDate, endDate, expression, colExpression]
     * @Return: priv.yulong.datafetch.response.FloatExpResult
     * @Author: yulong.feng
     * @Date: 2019-11-08
    **/
    @RequestMapping(value = "/float", method = RequestMethod.POST)
    public FloatExpResult floatFetch(String unitCode, Date startDate, Date endDate, String expression, String colExpression) {
        Map<String, Object> envMap = new HashMap<String, Object>();
        OrgMapping orgMapping = orgMappingService.getByRepCode(unitCode);
        Datasource ds = datasourceService.getDataSource(orgMapping.getDatasourceCode());
        envMap.put(FinancialConstant.EnvField.ORG_CODE, orgMapping.getCode());
        envMap.put(FinancialConstant.EnvField.DATASOURCE_CODE, orgMapping.getDatasourceCode());
        envMap.put(FinancialConstant.EnvField.BOOK_CODE, orgMapping.getBookCode());
        envMap.put(FinancialConstant.EnvField.START_YEAR, DateUtil.getYear(startDate));
        envMap.put(FinancialConstant.EnvField.START_PERIOD, DateUtil.getMonth(startDate));
        envMap.put(FinancialConstant.EnvField.END_YEAR, DateUtil.getYear(endDate));
        envMap.put(FinancialConstant.EnvField.END_PERIOD, DateUtil.getMonth(endDate));
        envMap.put(FinancialConstant.EnvField.SOFT_VERSION, ds.getSoftVersion());
        FloatExpression floatExpression = new FloatExpression();
        floatExpression.setExpression(expression);
        List<FixExpression> fList = new ArrayList<>();
        for (String col : colExpression.split(",")) {
            FixExpression fExp = new FixExpression();
            fExp.setExpression(col);
        }
        floatExpression.setColExpressions(fList);
        FloatExpResult floatExpResult = dataFetchService.floatFetch(floatExpression, envMap);
        return floatExpResult;
    }
}
