package com.yulong.expression.op.impl;

import com.yulong.expression.datatype.DataType;
import com.yulong.expression.datatype.Valuable;
import com.yulong.expression.datatype.impl.BooleanValue;
import com.yulong.expression.exception.ArgumentsMismatchException;
import com.yulong.expression.op.Operator;

public class GreaterOperator implements Operator {

	@Override
	public Valuable operate(Valuable[] arguments) {
		Valuable result = null;
		Valuable a1 = arguments[0];
		Valuable a2 = arguments[1];
		if (a1.getDataType() == DataType.NUMBER && a2.getDataType() == DataType.NUMBER) {
			result = new BooleanValue(a1.getNumberValue().compareTo(a2.getNumberValue()) > 0);
		} else if (a1.getDataType() == DataType.STRING && a2.getDataType() == DataType.STRING) {
			result = new BooleanValue(a1.getStringValue().compareTo(a2.getStringValue()) > 0);
		} else if (a1.getDataType() == DataType.CHARACTER && a2.getDataType() == DataType.CHARACTER) {
			result = new BooleanValue(a1.getCharValue().compareTo(a2.getCharValue()) > 0);
		} else if (a1.getDataType() == DataType.DATE && a2.getDataType() == DataType.DATE) {
			result = new BooleanValue(a1.getDateValue().compareTo(a2.getDateValue()) > 0);
		} else {
			throw new ArgumentsMismatchException(arguments, getSymbol());
		}
		return result;
	}

	@Override
	public String getSymbol() {
		return ">";
	}

	@Override
	public int getPriority() {
		return -5;
	}

	@Override
	public int getDimension() {
		return 2;
	}

}
