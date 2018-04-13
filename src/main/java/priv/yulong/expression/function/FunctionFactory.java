package priv.yulong.expression.function;

import java.util.HashMap;
import java.util.Map;

import priv.yulong.expression.function.financial.ZW;
import priv.yulong.expression.function.sys.Abs;
import priv.yulong.expression.function.sys.Judge;
import priv.yulong.expression.function.sys.Max;
import priv.yulong.util.StringUtil;

public class FunctionFactory {

	private final static Map<String, Function> FUNC_MAP = new HashMap<String, Function>();

	static {

		//System function
		registerFunction(new Max());
		registerFunction(new Abs());
		registerFunction(new Judge());

		//Financial function
		registerFunction(new ZW());
	}

	public static Function getFunction(String identifier) {
		if (StringUtil.isNotEmpty(identifier)) {
			return FUNC_MAP.get(identifier.toUpperCase());
		}
		return null;
	}

	public static void registerFunction(Function function) {
		FUNC_MAP.put(function.getName().toUpperCase(), function);
	}

}