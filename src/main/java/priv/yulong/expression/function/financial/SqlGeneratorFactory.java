package priv.yulong.expression.function.financial;

import priv.yulong.datafetch.datasourse.model.Datasource.SoftVersion;
import priv.yulong.expression.exception.ExpressionRuntimeException;

public class SqlGeneratorFactory {

	private static final String sqlGeneratorPath = "priv.yulong.expression.function.financial.%1$s.%2$sSqlGenerator";

	public static SqlGenerator createSqlGenerator(String funcName, SoftVersion softVersion) {
		String softVer = softVersion.name();
		String classPath = String.format(sqlGeneratorPath, softVer.toLowerCase(), funcName.toUpperCase());
		try {
			return (SqlGenerator) Class.forName(classPath).newInstance();
		} catch (InstantiationException e) {
			throw new ExpressionRuntimeException(
					String.format("版本为%1$s，公式为%2$s的SQL生成器实例化出错", softVersion.getTitle(), funcName), e);
		} catch (IllegalAccessException e) {
			throw new ExpressionRuntimeException(
					String.format("访问版本为%1$s，公式为%2$s的SQL生成器时出错", softVersion.getTitle(), funcName), e);
		} catch (ClassNotFoundException e) {
			throw new ExpressionRuntimeException(
					String.format("未找到版本为%1$s，公式为%2$s的SQL生成器", softVersion.getTitle(), funcName), e);
		}
	}

}
