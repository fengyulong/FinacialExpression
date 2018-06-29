package priv.yulong.expression.function.financial.sjjs;

import java.util.Map;

import priv.yulong.expression.datatype.Valuable;
import priv.yulong.expression.function.financial.FinancialConstant;
import priv.yulong.expression.function.financial.SqlGenerator;

public class ZWSqlGenerator implements SqlGenerator {

	public enum FetchType {
		YE("YE", "Sum(CF)");
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
		if (args.length >= 3) {
			period = args[2].getNumberValue().intValue();
		}
		if (args.length >= 4) {
			year = args[3].getNumberValue().intValue();
		}
		if (args.length >= 5) {
			orgCode = args[4].getStringValue();
		}
		if (args.length >= 6) {
			hbCode = args[5].getStringValue();
		}
		String sql = getSqlTemplate();
		sql = sql.replace("#year#", year + "");
		sql = sql.replace("#period#", period + "");
		sql = sql.replace("#orgCode#", orgCode);
		sql = sql.replace("#kmcode#", kmCode);
		sql = sql.replace("#fetchType#", fetchType.getField());
		return sql;
	}

	private String getSqlTemplate() {
		StringBuffer sql = new StringBuffer();
		sql.append("	select #fetchType#                                         	");
		sql.append("	  from ebs_gl_balance t1, md_accountsubject t2, md_org t3  	");
		sql.append("	 where t1.subjectid = t2.recid								");
		sql.append("	   and t1.unitid = t3.recid									");
		sql.append("	   and t1.acctyear = #year#									");
		sql.append("	   and t1.acctperiod = #period#								");
		sql.append("	   and t3.stdcode = '#orgCode#'								");
		sql.append("	   and t2.stdcode like '#kmcode#%'							");
		return sql.toString();
	}

}
