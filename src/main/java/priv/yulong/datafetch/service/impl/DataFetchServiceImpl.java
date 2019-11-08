package priv.yulong.datafetch.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import priv.yulong.common.util.DateUtil;
import priv.yulong.datafetch.datasourse.model.Datasource;
import priv.yulong.datafetch.datasourse.service.DatasourceService;
import priv.yulong.datafetch.org.model.OrgMapping;
import priv.yulong.datafetch.org.service.OrgMappingService;
import priv.yulong.datafetch.requester.DataFetchEnv;
import priv.yulong.datafetch.requester.DataFetchRequester;
import priv.yulong.datafetch.requester.ExpressionListing;
import priv.yulong.datafetch.requester.FixExpression;
import priv.yulong.datafetch.requester.FloatExpression;
import priv.yulong.datafetch.response.DataFetchResponser;
import priv.yulong.datafetch.response.FinanceSoftInfo;
import priv.yulong.datafetch.response.FixExpResult;
import priv.yulong.datafetch.response.FloatExpResult;
import priv.yulong.datafetch.response.ResultListing;
import priv.yulong.datafetch.service.DataFetchService;
import priv.yulong.expression.ExpressionEvaluator;
import priv.yulong.expression.datatype.Valuable;
import priv.yulong.expression.datatype.impl.DataSet;
import priv.yulong.expression.function.financial.FinancialConstant;

@Service
public class DataFetchServiceImpl implements DataFetchService {
	@Resource
	private OrgMappingService orgMappingService;
	@Resource
	private DatasourceService datasourceService;

	@Override
	public DataFetchResponser dataFetch(DataFetchRequester dataFetchRequester) {
		DataFetchEnv env = dataFetchRequester.getFetchEnv();

		Map<String, Object> envMap = new HashMap<String, Object>();
		OrgMapping orgMapping = orgMappingService.getByRepCode(env.getUnitCode());
		Datasource ds = datasourceService.getDataSource(orgMapping.getDatasourceCode());

		envMap.put(FinancialConstant.EnvField.ORG_CODE, orgMapping.getCode());
		envMap.put(FinancialConstant.EnvField.DATASOURCE_CODE, orgMapping.getDatasourceCode());
		envMap.put(FinancialConstant.EnvField.BOOK_CODE, orgMapping.getBookCode());
		envMap.put(FinancialConstant.EnvField.IS_INCLUDE_UNCHARGED, env.isIncludeUncharged());
		envMap.put(FinancialConstant.EnvField.START_YEAR, DateUtil.getYear(env.getStartTime()));
		envMap.put(FinancialConstant.EnvField.START_PERIOD, DateUtil.getMonth(env.getStartTime()));
		envMap.put(FinancialConstant.EnvField.END_YEAR, DateUtil.getYear(env.getEndTime()));
		envMap.put(FinancialConstant.EnvField.END_PERIOD, DateUtil.getMonth(env.getEndTime()));
		envMap.put(FinancialConstant.EnvField.SOFT_VERSION, ds.getSoftVersion());

		ExpressionListing expressionListing = dataFetchRequester.getExpListing();

		List<FixExpression> fixExpList = expressionListing.getFixExpressions();
		List<FloatExpression> floatExpList = expressionListing.getFloatExpressions();

		DataFetchResponser dataFetchResponser = new DataFetchResponser();

		FinanceSoftInfo softInfo = new FinanceSoftInfo();
		softInfo.setSoftVersion(ds.getSoftVersion().getTitle());
		softInfo.setStartTime(env.getStartTime());
		softInfo.setEndTime(env.getEndTime());
		softInfo.setCreateTime(new Date());

		List<FixExpResult> fixResultList = fixFetch(fixExpList, envMap);
		List<FloatExpResult> floatResultList = floatFetch(floatExpList, envMap);

		ResultListing resultListing = new ResultListing();
		resultListing.setUnitCode(env.getUnitCode());
		resultListing.setOrgCode(orgMapping.getCode());
		resultListing.setFormatNeeded(expressionListing.isFormatNeeded());
		resultListing.setFixExpResults(fixResultList);
		resultListing.setFloatExpResults(floatResultList);
		resultListing.setSuccess(true);

		dataFetchResponser.setFinanceSoft(softInfo);
		dataFetchResponser.setResultListing(resultListing);

		return dataFetchResponser;
	}

	/**
	 * 固定公式取数
	 * 
	 * @param fixExpList
	 * @param env
	 * @return
	 */
	@Override
	public List<FixExpResult> fixFetch(List<FixExpression> fixExpList, Map<String, Object> env) {
		if (fixExpList == null || fixExpList.isEmpty()) {
			return null;
		}
		List<FixExpResult> fixResultList = new ArrayList<FixExpResult>();
		for (FixExpression fixExp : fixExpList) {
			FixExpResult fixExpResult = fixFetch(fixExp, env);
			fixResultList.add(fixExpResult);
		}
		return fixResultList;
	}

	/**
	 * 浮动公式取数
	 * 
	 * @param floatExpList
	 * @param env
	 * @return
	 */
	@Override
	public List<FloatExpResult> floatFetch(List<FloatExpression> floatExpList, Map<String, Object> env) {
		if (floatExpList == null || floatExpList.isEmpty()) {
			return null;
		}
		List<FloatExpResult> floatResultList = new ArrayList<FloatExpResult>();
		for (FloatExpression floatExp : floatExpList) {
			FloatExpResult floatExpResult = floatFetch(floatExp, env);
			floatResultList.add(floatExpResult);
		}
		return floatResultList;
	}

	/**
	 * 单个固定公式取数
	 * 
	 * @param fixExp
	 * @param env
	 * @return
	 */
	@Override
	public FixExpResult fixFetch(FixExpression fixExp, Map<String, Object> env) {
		Valuable ret = ExpressionEvaluator.evaluate(fixExp.getExpression(), env);
		FixExpResult result = new FixExpResult();
		result.setFlag(fixExp.getFlag());
		result.addExpression(fixExp.getExpression());
		result.setDataType(ret.getDataType().toString());
		result.setValid(true);
		result.addValue(ret.getStringValue());
		return result;
	}

	/**
	 * 单个浮动公式取数
	 * 
	 * @param floatExp
	 * @param env
	 * @return
	 */
	@Override
	public FloatExpResult floatFetch(FloatExpression floatExp, Map<String, Object> env) {
		FloatExpResult floatExpResult = new FloatExpResult();
		Valuable ret = ExpressionEvaluator.evaluate(floatExp.getExpression(), env);
		DataSet dataSet = ret.getDataSetValue();
		int rowCount = dataSet.getRowCount();
		floatExpResult.setFlag(floatExp.getFlag());
		floatExpResult.setRowCount(rowCount);

		List<FixExpression> colExpressions = floatExp.getColExpressions();
		for (int i = 0; i < colExpressions.size(); i++) {
			FixExpression colExpressio = colExpressions.get(i);
			FixExpResult colResult = new FixExpResult();
			colResult.setFlag(colExpressio.getFlag());
			colResult.setValid(true);
			for (int j = 0; j < rowCount; j++) {
				dataSet.setCurrRow(j);
				String expr = colExpressio.getExpression();
				for (int k = 1; k <= dataSet.getcolCount(); k++) {
					expr = expr.replace("*" + k, dataSet.getString(k - 1));
				}
				Valuable colRet = ExpressionEvaluator.evaluate(expr, env);
				colResult.addExpression(expr);
				colResult.addValue(colRet.getStringValue());
				colResult.setDataType(colRet.getDataType().name());
			}
			floatExpResult.addColResult(colResult);
		}
		return floatExpResult;
	}

}
