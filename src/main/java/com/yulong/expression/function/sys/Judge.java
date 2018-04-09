package com.yulong.expression.function.sys;

import java.util.Map;

import com.yulong.expression.datatype.DataType;
import com.yulong.expression.datatype.Valuable;
import com.yulong.expression.exception.ArgumentsMismatchException;
import com.yulong.expression.function.Function;

public class Judge implements Function {

	@Override
	public String getName() {
		return "judge";
	}

	@Override
	public int getDimension() {
		return 3;
	}

	@Override
	public Valuable execute(Valuable[] args, Map<String, Object> env) {
		Valuable condition = args[0];
		if (condition.getDataType() != DataType.BOOLEAN) {
			throw new ArgumentsMismatchException(args, getName());
		}
		if (condition.getBooleanValue())
			return args[1];
		else
			return args[2];
	}

}
