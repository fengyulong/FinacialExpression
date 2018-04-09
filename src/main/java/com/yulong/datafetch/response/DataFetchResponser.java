package com.yulong.datafetch.response;

public class DataFetchResponser {
	private FinanceSoftInfo financeSoft;
	private ResultListing resultListing;
	private String fetchDataSql;

	public FinanceSoftInfo getFinanceSoft() {
		return financeSoft;
	}

	public void setFinanceSoft(FinanceSoftInfo financeSoft) {
		this.financeSoft = financeSoft;
	}

	public ResultListing getResultListing() {
		return resultListing;
	}

	public void setResultListing(ResultListing resultListing) {
		this.resultListing = resultListing;
	}

	public String getFetchDataSql() {
		return fetchDataSql;
	}

	public void setFetchDataSql(String fetchDataSql) {
		this.fetchDataSql = fetchDataSql;
	}

}
