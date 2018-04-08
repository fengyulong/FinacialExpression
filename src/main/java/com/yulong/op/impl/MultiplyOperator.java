package com.yulong.op.impl;

import com.yulong.datatype.DataType;
import com.yulong.datatype.Valuable;
import com.yulong.datatype.impl.NumberValue;
import com.yulong.exception.ArgumentsMismatchException;
import com.yulong.op.Operator;

public class MultiplyOperator implements Operator {

	@Override
	public Valuable operate(Valuable[] arguments) {
		Valuable result = null;
		Valuable a1 = arguments[0];
		Valuable a2 = arguments[1];
		if (a1.getDataType() == DataType.NUMBER && a2.getDataType() == DataType.NUMBER) {
			result = new NumberValue(a1.getNumberValue().multiply(a2.getNumberValue()));
		} else {
			throw new ArgumentsMismatchException(arguments, "*");
		}
		return result;
	}

	@Override
	public String getSymbol() {
		return "*";
	}

	@Override
	public int getPriority() {
		return -3;
	}

	@Override
	public int getDimension() {
		return 2;
	}

}
