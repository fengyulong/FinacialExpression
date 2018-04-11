package priv.yulong.expression.op.impl;

import priv.yulong.expression.datatype.DataType;
import priv.yulong.expression.datatype.Valuable;
import priv.yulong.expression.datatype.impl.NumberValue;
import priv.yulong.expression.exception.ArgumentsMismatchException;
import priv.yulong.expression.op.Operator;

public class SubtractOperator implements Operator {

	@Override
	public Valuable operate(Valuable[] arguments) {
		Valuable result = null;
		Valuable a1 = arguments[0];
		Valuable a2 = arguments[1];
		if (a1.getDataType() == DataType.NUMBER && a2.getDataType() == DataType.NUMBER) {
			result = new NumberValue(a1.getNumberValue().subtract(a2.getNumberValue()));
		} else {
			throw new ArgumentsMismatchException(arguments, getSymbol());
		}
		return result;
	}

	@Override
	public String getSymbol() {
		return "-";
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
