package priv.yulong.expression.op.impl;

import java.math.BigDecimal;

import priv.yulong.expression.datatype.DataType;
import priv.yulong.expression.datatype.Valuable;
import priv.yulong.expression.datatype.impl.NumberValue;
import priv.yulong.expression.exception.ArgumentsMismatchException;
import priv.yulong.expression.op.Operator;

public class DivideOperator implements Operator {

	@Override
	public Valuable operate(Valuable[] arguments) {
		Valuable result = null;
		Valuable a1 = arguments[0];
		Valuable a2 = arguments[1];
		if (a1.getDataType() == DataType.NUMBER && a2.getDataType() == DataType.NUMBER) {
			if (a2.getNumberValue().compareTo(new BigDecimal("0")) == 0)
				throw new ArithmeticException("Divided by zero.");
			result = new NumberValue(a1.getNumberValue().divide(a2.getNumberValue()));
		} else {
			throw new ArgumentsMismatchException(arguments, getSymbol());
		}
		return result;
	}

	@Override
	public int getDimension() {
		return 2;
	}

	@Override
	public String getSymbol() {
		return "/";
	}

	@Override
	public int getPriority() {
		return -3;
	}

}
