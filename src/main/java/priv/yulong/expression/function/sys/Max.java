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
	
	@Override
	public String description() {
		StringBuffer desc = new StringBuffer();
		desc.append("公式格式：judge(param1...) 				\n");
		desc.append("公式描述：取最大值						\n");
		desc.append("参数说明：不定长参数，必须是数字类型		\n");
		desc.append("公式示例：max(6,7.8,0.25)  结果是7.8	\n");
		return desc.toString();
	}

}
