package priv.yulong.datafetch.fetchtest.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.yulong.common.util.DateUtil;
import priv.yulong.common.util.LogUtil;
import priv.yulong.datafetch.datasourse.model.Datasource;
import priv.yulong.datafetch.datasourse.service.DatasourceService;
import priv.yulong.datafetch.org.model.OrgMapping;
import priv.yulong.datafetch.org.service.OrgExtMappingService;
import priv.yulong.datafetch.org.service.OrgMappingService;
import priv.yulong.datafetch.requester.DataFetchEnv;
import priv.yulong.datafetch.requester.FixExpression;
import priv.yulong.datafetch.requester.FloatExpression;
import priv.yulong.datafetch.response.FixExpResult;
import priv.yulong.datafetch.response.FloatExpResult;
import priv.yulong.datafetch.service.DataFetchService;
import priv.yulong.expression.function.financial.FinancialConstant;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
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
    private OrgExtMappingService orgExtMappingService;
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

    @RequiresPermissions(value = {"datafetch:fetchtest:list1"})
    @RequestMapping(value = "/list1", method = RequestMethod.GET)
    public String list1() {
        return "datafetch/fetchtest/list1";
    }

    /**
     * @MethodName: fixFetch
     * @Description: 固定公式取数
     * @Param: [unitCode, startDate, endDate, expression]
     * @Return: priv.yulong.datafetch.response.FixExpResult
     * @Author: yulong.feng
     * @Date: 2019-11-08
     **/
    @ResponseBody
    @RequestMapping(value = "/fix", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String fixFetch(String unitCode, String startDate, String endDate, String expression) throws ParseException {
        LogUtil.info("固定取数测试开始，unitCode[{}];startDate[{}];endDate[{}];expression:[{}]", unitCode, startDate, endDate, expression);
        JSONObject respObj = new JSONObject();
        try {
            Map<String, Object> envMap = new HashMap<String, Object>();
            OrgMapping orgMapping = null;
            if (isMonthPeriod(startDate, endDate)) {
                orgMapping = orgExtMappingService.getByRepCode(unitCode);
            } else {
                orgMapping = orgMappingService.getByRepCode(unitCode);
            }
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
            respObj.put("code", "success");
            respObj.put("data", fixExpResult);
            LogUtil.info("固定取数测试完成，unitCode[{}];startDate[{}];endDate[{}];expression:[{}];result[{}]", unitCode, startDate, endDate, expression, respObj.toJSONString());
        } catch (Exception e) {
            respObj.put("code", "fail");
            respObj.put("data", e.getMessage());
            LogUtil.error("固定取数测试完成，unitCode[{}];startDate[{}];endDate[{}];expression:[{}];exception[{}]", unitCode, startDate, endDate, expression, e);
        }
        return respObj.toJSONString();
    }


    /**
     * @MethodName: floatFetch
     * @Description: 浮动公式取数
     * @Param: [unitCode, startDate, endDate, expression, colExpression]
     * @Return: priv.yulong.datafetch.response.FloatExpResult
     * @Author: yulong.feng
     * @Date: 2019-11-08
     **/
    @ResponseBody
    @RequestMapping(value = "/float", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String floatFetch(String unitCode, String startDate, String endDate, String expression, String colExpression) throws ParseException {
        LogUtil.info("固定取数测试开始，unitCode[{}];startDate[{}];endDate[{}];expression:[{}];colExpression", unitCode, startDate, endDate, expression, colExpression);
        JSONObject respObj = new JSONObject();
        try {
            Map<String, Object> envMap = new HashMap<String, Object>();
            OrgMapping orgMapping = null;
            if (isMonthPeriod(startDate, endDate)) {
                orgMapping = orgExtMappingService.getByRepCode(unitCode);
            } else {
                orgMapping = orgMappingService.getByRepCode(unitCode);
            }
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
                fList.add(fExp);
            }
            floatExpression.setColExpressions(fList);
            FloatExpResult floatExpResult = dataFetchService.floatFetch(floatExpression, envMap);
            respObj.put("code", "success");
            respObj.put("data", floatExpResult);
            LogUtil.info("固定取数测试开始，unitCode[{}];startDate[{}];endDate[{}];expression:[{}];colExpression;result[{}]", unitCode, startDate, endDate, expression, colExpression, respObj);
        } catch (Exception e) {
            respObj.put("code", "fail");
            respObj.put("data", e.getMessage());
            LogUtil.error("固定取数测试开始，unitCode[{}];startDate[{}];endDate[{}];expression:[{}];colExpression;exception[{}]", unitCode, startDate, endDate, expression, colExpression, e);
        }
        return respObj.toJSONString();
    }

    private boolean isMonthPeriod(String startDate, String endDate) {
        int start = 0;
        int end = 0;
        try {
            start = DateUtil.getMonth(startDate);
            end = DateUtil.getMonth(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return start == end;
    }

}
