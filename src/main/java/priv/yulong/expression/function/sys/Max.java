package priv.yulong.expression.function.sys;

import java.math.BigDecimal;
import java.util.Map;

import priv.yulong.expression.datatype.DataType;
import priv.yulong.expression.datatype.Valuable;
import priv.yulong.expression.datatype.impl.NumberValue;
import priv.yulong.expression.exception.ArgumentsMismatchException;
import priv.yulong.expression.function.Function;

public class Max implements Function {

	@Override
	public String getName() {
		return "max";
	}

	@Override
	public int getDimension() {
		return -1;
	}

	@Override
	public Valuable execute(Valuable[] args, Map<String, Object> env) {
		if (args.length == 0) {
			return new NumberValue(BigDecimal.ZERO);
		}
		Valuable maxValue = null;
		for (int i = 0; i < args.length; i++) {
			if (args[i].getDataType() != DataType.NUMBER) {
				throw new ArgumentsMismatchException(args, getName());
			} else if (i == 0) {
				maxValue = args[0];
			} else {
				if (maxValue.getNumberValue().compareTo(args[i].getNumberValue()) < 0) {
					maxValue = args[i];
				}
			}
		}
		return maxValue;
	}

}
