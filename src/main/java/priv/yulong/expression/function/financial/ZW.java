package priv.yulong.expression.function.financial;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import priv.yulong.datafetch.datasourse.model.Datasource.SoftVersion;
import priv.yulong.expression.datatype.Valuable;
import priv.yulong.expression.datatype.impl.NumberValue;
import priv.yulong.expression.exception.ArgumentsMismatchException;
import priv.yulong.expression.exception.ExpressionRuntimeException;
import priv.yulong.expression.function.Function;

public class ZW extends FinancialFunctionBase implements Function {

	@Override
	public String getName() {
		return "ZW";
	}

	@Override
	public int getDimension() {
		return -1;
	}

	@Override
	public Valuable execute(Valuable[] args, Map<String, Object> env) {
		if (args == null || args.length < 2 || args.length > 6) {
			throw new ArgumentsMismatchException(args, getName());
		}
		SoftVersion softVersion = (SoftVersion) env.get(FinancialConstant.EnvField.SOFT_VERSION);
		SqlGenerator sqlGenerator = SqlGeneratorFactory.createSqlGenerator(getName(), softVersion);
		String sql = sqlGenerator.generateSQL(args, env);
		String datasourceCode = (String) env.get(FinancialConstant.EnvField.DATASOURCE_CODE);
		Connection connection = null;
		try {
			connection = datasourceService.getConnection(datasourceCode);
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			result.next();
			BigDecimal ret = result.getBigDecimal(1);
			return new NumberValue(ret);
		} catch (Exception e) {
			throw new ExpressionRuntimeException("执行公式取数发生错误", e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public String description() {
		StringBuffer desc = new StringBuffer();
		desc.append("公式格式：ZW(param1,param2,param3,param4,param5,param6) 														\n");
		desc.append("公式描述：账务科目取数																							\n");
		desc.append("参数说明：param1取数类型(必填)，param2科目代码(必填)，param3会计期间，param4会计年度，param5机构，param6货币		\n");
		desc.append("公式示例：ZW(C，1001， 12，2006，L,RMB)  表示从单位L对应的账簿中的2006年账务中提取1001科目币别为RMB的12月月初余额	\n");
		return desc.toString();
	}
}
