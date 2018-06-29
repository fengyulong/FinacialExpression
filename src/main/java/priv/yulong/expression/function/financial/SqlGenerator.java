package priv.yulong.expression.function.financial;

import java.util.Map;

import priv.yulong.expression.datatype.Valuable;

public interface SqlGenerator {
	public String generateSQL(Valuable[] args, Map<String, Object> env);
}
