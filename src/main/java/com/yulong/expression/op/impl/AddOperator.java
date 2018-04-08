package com.yulong.expression.op.impl;

import com.yulong.expression.datatype.DataType;
import com.yulong.expression.datatype.Valuable;
import com.yulong.expression.datatype.impl.NumberValue;
import com.yulong.expression.datatype.impl.StringValue;
import com.yulong.expression.exception.ArgumentsMismatchException;
import com.yulong.expression.op.Operator;

public class AddOperator implements Operator {

	@Override
	public Valuable operate(Valuable[] arguments) {
		Valuable result = null;
		Valuable a1 = arguments[0];
		Valuable a2 = arguments[1];
		if (a1.getDataType() == DataType.NUMBER && a2.getDataType() == DataType.NUMBER) {
			result = new NumberValue(a1.getNumberValue().add(a2.getNumberValue()));
		} else if (a1.getDataType() == DataType.STRING && a2.getDataType() == DataType.STRING) {
			result = new StringValue(a1.getStringValue() + a2.getStringValue());
		} else if (a1.getDataType() == DataType.STRING && a2.getDataType() == DataType.CHARACTER) {
			result = new StringValue(a1.getStringValue() + a2.getCharValue());
		} else if (a1.getDataType() == DataType.CHARACTER && a2.getDataType() == DataType.STRING) {
			result = new StringValue(a1.getCharValue() + a2.getStringValue());
		} else {
			throw new ArgumentsMismatchException(arguments, "+");
		}
		return result;
	}

	@Override
	public String getSymbol() {
		return "+";
	}

	@Override
	public int getPriority() {
		return -4;
	}

	@Override
	public int getDimension() {
		return 2;
	}

}
