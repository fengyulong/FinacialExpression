package com.yulong.expression.function;

import java.util.HashMap;
import java.util.Map;

import com.yulong.expression.function.financial.ZW;
import com.yulong.util.StringUtil;

public class FunctionFactory {

	private final static Map<String, Function> FUNC_MAP = new HashMap<String, Function>();
	
	static{
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
