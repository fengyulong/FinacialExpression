package priv.yulong.expression.function.financial.test;

import java.util.Map;

import priv.yulong.expression.datatype.Valuable;
import priv.yulong.expression.function.financial.FinancialConstant;
import priv.yulong.expression.function.financial.SqlGenerator;

public class ZFZSqlGenerator implements SqlGenerator {

	public enum FetchType {
		JL("JL", "sum(jl)"),DL("DL","sum(dl)"),JF("JF","sum(jf)"),DF("DF","sum(df)");
		private String symbol;
		private String field;

		private FetchType(String symbol, String field) {
			this.symbol = symbol;
			this.field = field;
		}

		public String getSymbol() {
			return symbol;
		}

		public String getField() {
			return field;
		}

	}

	@Override
	public String generateSQL(Valuable[] args, Map<String, Object> env) {
		FetchType fetchType = FetchType.valueOf(args[0].getStringValue().toUpperCase());
		String kmCode = args[1].getStringValue();
		int period = (int) env.get(FinancialConstant.EnvField.END_PERIOD);
		int year = (int) env.get(FinancialConstant.EnvField.END_YEAR);
		String orgCode = (String) env.get(FinancialConstant.EnvField.ORG_CODE);
		String hbCode = null;
		String assCode = null;
		if (args.length >= 3) {
			assCode = args[2].getStringValue();
		}
		if (args.length >= 4) {
			period = args[2].getNumberValue().intValue();
		}
		if (args.length >= 5) {
			year = args[3].getNumberValue().intValue();
		}
		if (args.length >= 6) {
			orgCode = args[4].getStringValue();
		}
		if (args.length >= 7) {
			hbCode = args[5].getStringValue();
		}
		String sql = getSqlTemplate();
		sql = sql.replace("#year#", year + "");
		sql = sql.replace("#period#", period + "");
		sql = sql.replace("#orgCode#", orgCode);
		sql = sql.replace("#kmcode#", kmCode);
		sql = sql.replace("#fetchType#", fetchType.getField());
		//sql = sql.replace("#hbCode#", hbCode);
		return sql;
	}

	private String getSqlTemplate() {
		StringBuffer sql = new StringBuffer();
		
		sql.append("	select #fetchType#						");
		sql.append("	  from efdc_test						");
		sql.append("	 where unitcode = '#orgCode#'			");
		sql.append("	   and acctyear = #year#				");
		sql.append("	   and acctperiod = #period#			");
		sql.append("	   and subjectcode like '#kmcode#%'		");
		return sql.toString();
	}

}
