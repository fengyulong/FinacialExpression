package priv.yulong.expression.function.financial.sjjs;

import java.util.Map;

import priv.yulong.expression.datatype.Valuable;
import priv.yulong.expression.function.financial.FinancialConstant;
import priv.yulong.expression.function.financial.SqlGenerator;

public class FSQLSqlGenerator implements SqlGenerator{

	@Override
	public String generateSQL(Valuable[] args, Map<String, Object> env) {
		String sql = args[0].getStringValue();
		Integer endPeriod = (int) env.get(FinancialConstant.EnvField.END_PERIOD);
		Integer endYear = (int) env.get(FinancialConstant.EnvField.END_YEAR);
		Integer startPeriod = (int) env.get(FinancialConstant.EnvField.START_PERIOD);
		Integer startYear = (int) env.get(FinancialConstant.EnvField.START_YEAR);
		String orgCode = (String) env.get(FinancialConstant.EnvField.ORG_CODE);
		String bookCode = (String)env.get(FinancialConstant.EnvField.BOOK_CODE);
		if(bookCode!=null){
			sql = sql.replace(FinancialConstant.SqlParam.BOOK_CODE, bookCode);
		}
		sql = sql.replace(FinancialConstant.SqlParam.ORG_CODE, orgCode);
		sql = sql.replace(FinancialConstant.SqlParam.START_YEAR, startYear.toString());
		sql = sql.replace(FinancialConstant.SqlParam.START_PERIOD, startPeriod.toString());
		sql = sql.replace(FinancialConstant.SqlParam.END_YEAR, endYear.toString());
		sql = sql.replace(FinancialConstant.SqlParam.END_PERIOD, endPeriod.toString());
		sql = sql.replace(FinancialConstant.SqlParam.YEAR, endYear.toString());
		sql = sql.replace(FinancialConstant.SqlParam.PERIOD, endPeriod.toString());
		return sql;
	}

}
