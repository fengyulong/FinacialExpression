package com.yulong.datafetch.service;

import com.yulong.datafetch.requester.DataFetchRequester;
import com.yulong.datafetch.response.DataFetchResponser;

public interface DataFetchService {

	DataFetchResponser dataFetch(DataFetchRequester dataFetchRequester);
	

}
