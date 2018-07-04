package priv.yulong.expression.function.financial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;

import priv.yulong.datafetch.datasourse.model.Datasource.SoftVersion;
import priv.yulong.expression.datatype.DataType;
import priv.yulong.expression.datatype.Valuable;
import priv.yulong.expression.datatype.impl.DataSet;
import priv.yulong.expression.datatype.impl.DataSetValue;
import priv.yulong.expression.exception.ArgumentsMismatchException;
import priv.yulong.expression.exception.ExpressionRuntimeException;
import priv.yulong.expression.function.Function;

public class FSQL extends FinancialFunctionBase implements Function {

	@Override
	public String getName() {
		return "FSQL";
	}

	@Override
	public int getDimension() {
		return 1;
	}

	@Override
	public Valuable execute(Valuable[] args, Map<String, Object> env) {
		if (args == null || args.length != 1 || args[0].getDataType() != DataType.STRING) {
			throw new ArgumentsMismatchException(args, getName());
		}
		SoftVersion softVersion = (SoftVersion) env.get(FinancialConstant.EnvField.SOFT_VERSION);
		SqlGenerator sqlGenerator = SqlGeneratorFactory.createSqlGenerator(getName(), softVersion);
		String sql = sqlGenerator.generateSQL(args, env);
		String datasourceCode = (String) env.get(FinancialConstant.EnvField.DATASOURCE_CODE);
		Connection connection = null;
		ResultSet result = null;
		try {
			connection = datasourceService.getConnection(datasourceCode);
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSetMetaData rsmd = statement.getMetaData();
			int columnCount = rsmd.getColumnCount();
			DataSet dataSet = new DataSet(columnCount, 0);
			for (int i = 1; i <= columnCount; i++) {
				dataSet.addField(rsmd.getColumnName(i));
				dataSet.addResultType(DataType.STRING);
			}
			result = statement.executeQuery();
			while (result.next()) {
				dataSet.newRow();
				for (int i = 0; i < columnCount; i++) {
					dataSet.add(i, result.getString(i + 1));
				}
			}
			result.close();
			return new DataSetValue(dataSet);
		} catch (Exception e) {
			throw new ExpressionRuntimeException("执行公式取数发生错误", e);
		} finally {
			try {
				result.close();
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new ExpressionRuntimeException("执行公式取数关闭结果集和连接时出错", e);
			}

		}
	}

	@Override
	public String description() {
		StringBuffer desc = new StringBuffer();
		desc.append("公式格式：FSQL(param1) 						\n");
		desc.append("公式描述：浮动公式取数						\n");
		desc.append("参数说明：param1 SQL语句（必填）				\n");
		desc.append("公式示例：FSQL(’select * from gl_balance‘)	\n");
		desc.append("表示查询gl_balance中所有字段	 			\n");
		return desc.toString();
	}

}
