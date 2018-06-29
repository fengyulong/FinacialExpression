package priv.yulong.expression.function.sys;

import java.util.Map;

import priv.yulong.expression.datatype.DataType;
import priv.yulong.expression.datatype.Valuable;
import priv.yulong.expression.datatype.impl.NumberValue;
import priv.yulong.expression.exception.ArgumentsMismatchException;
import priv.yulong.expression.function.Function;

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

	@Override
	public String description() {
		StringBuffer desc = new StringBuffer();
		desc.append("公式格式：abs(param) 			\n");
		desc.append("公式描述：求绝对值 				\n");
		desc.append("参数说明：param数字类型，必填 	\n");
		desc.append("公式示例：abs(-5.8)  结果是5.8 	\n");
		return desc.toString();
	}

}
