package com.yulong.expression.op.impl;

import com.yulong.expression.datatype.DataType;
import com.yulong.expression.datatype.Valuable;
import com.yulong.expression.datatype.impl.BooleanValue;
import com.yulong.expression.exception.ArgumentsMismatchException;
import com.yulong.expression.op.Operator;

public class AndOperator implements Operator {

	@Override
	public Valuable operate(Valuable[] arguments) {
		Valuable result = null;
		Valuable a1 = arguments[0];
		Valuable a2 = arguments[1];
		if (a1.getDataType() == DataType.BOOLEAN && a2.getDataType() == DataType.BOOLEAN) {
			result = new BooleanValue(a1.getBooleanValue() && a2.getBooleanValue());
		} else {
			throw new ArgumentsMismatchException(arguments, getSymbol());
		}
		return result;
	}

	@Override
	public String getSymbol() {
		return "&&";
	}

	@Override
	public int getPriority() {
		return -7;
	}

	@Override
	public int getDimension() {
		return 2;
	}

}
