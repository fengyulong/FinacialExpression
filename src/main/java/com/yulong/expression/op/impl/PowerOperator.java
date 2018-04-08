package com.yulong.expression.op.impl;

import com.yulong.expression.datatype.DataType;
import com.yulong.expression.datatype.Valuable;
import com.yulong.expression.datatype.impl.NumberValue;
import com.yulong.expression.exception.ArgumentsMismatchException;
import com.yulong.expression.op.Operator;

public class PowerOperator implements Operator {

	@Override
	public Valuable operate(Valuable[] arguments) {
		Valuable a1 = arguments[0];
		Valuable a2 = arguments[1];
		if (a1.getDataType() == DataType.NUMBER && a2.getDataType() == DataType.NUMBER) {
			return new NumberValue(a1.getNumberValue().pow(a2.getNumberValue().intValue()));
		}
		throw new ArgumentsMismatchException(arguments, getSymbol());
	}

	@Override
	public String getSymbol() {
		return "^";
	}

	@Override
	public int getPriority() {
		return -2;
	}

	@Override
	public int getDimension() {
		return 2;
	}

}
