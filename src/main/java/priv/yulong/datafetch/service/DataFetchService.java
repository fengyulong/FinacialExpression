package priv.yulong.datafetch.service;

import priv.yulong.datafetch.requester.DataFetchRequester;
import priv.yulong.datafetch.requester.FixExpression;
import priv.yulong.datafetch.requester.FloatExpression;
import priv.yulong.datafetch.response.DataFetchResponser;
import priv.yulong.datafetch.response.FixExpResult;
import priv.yulong.datafetch.response.FloatExpResult;

import java.util.List;
import java.util.Map;

public interface DataFetchService {

	DataFetchResponser dataFetch(DataFetchRequester dataFetchRequester);

	List<FixExpResult> fixFetch(List<FixExpression> fixExpList, Map<String, Object> env);

	List<FloatExpResult> floatFetch(List<FloatExpression> floatExpList, Map<String, Object> env);

	FixExpResult fixFetch(FixExpression fixExp, Map<String, Object> env);

	FloatExpResult floatFetch(FloatExpression floatExp, Map<String, Object> env);
	

}
