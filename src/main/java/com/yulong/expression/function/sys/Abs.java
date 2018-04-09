package com.yulong.expression.function.sys;

import java.util.Map;

import com.yulong.expression.datatype.DataType;
import com.yulong.expression.datatype.Valuable;
import com.yulong.expression.datatype.impl.NumberValue;
import com.yulong.expression.exception.ArgumentsMismatchException;
import com.yulong.expression.function.Function;

public class Abs implements Function {

	@Override
	public String getName() {
		return "abs";
	}

	@Override
	public int getDimension() {
		return 1;
	}

	@Override
	public Valuable execute(Valuable[] args, Map<String, Object> env) {
		Valuable arg = args[0];
		if (arg.getDataType() == DataType.NUMBER) {
			return new NumberValue(arg.getNumberValue().abs());
		}
		throw new ArgumentsMismatchException(args, getName());
	}

}
