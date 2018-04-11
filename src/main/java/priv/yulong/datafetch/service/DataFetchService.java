package priv.yulong.datafetch.service;

import priv.yulong.datafetch.requester.DataFetchRequester;
import priv.yulong.datafetch.response.DataFetchResponser;

public interface DataFetchService {

	DataFetchResponser dataFetch(DataFetchRequester dataFetchRequester);
	

}
