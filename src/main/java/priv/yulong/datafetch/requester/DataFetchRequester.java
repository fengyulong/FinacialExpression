package priv.yulong.datafetch.requester;

public class DataFetchRequester {
	private DataFetchEnv fetchEnv;
	private ReportSoftInfo reportSoft;
	private ExpressionListing expListing;
	private boolean isReturnSql = false;

	public DataFetchEnv getFetchEnv() {
		return fetchEnv;
	}

	public void setFetchEnv(DataFetchEnv fetchEnv) {
		this.fetchEnv = fetchEnv;
	}

	public ReportSoftInfo getReportSoft() {
		return reportSoft;
	}

	public void setReportSoft(ReportSoftInfo reportSoft) {
		this.reportSoft = reportSoft;
	}

	public ExpressionListing getExpListing() {
		return expListing;
	}

	public void setExpListing(ExpressionListing expListing) {
		this.expListing = expListing;
	}

	public boolean isReturnSql() {
		return isReturnSql;
	}

	public void setReturnSql(boolean isReturnSql) {
		this.isReturnSql = isReturnSql;
	}
}
