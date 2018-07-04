package priv.yulong.expression.function.financial;

import java.util.Map;

import priv.yulong.expression.datatype.DataType;
import priv.yulong.expression.datatype.Valuable;
import priv.yulong.expression.datatype.impl.DataSet;
import priv.yulong.expression.datatype.impl.StringValue;
import priv.yulong.expression.exception.ArgumentsMismatchException;
import priv.yulong.expression.function.Function;

public class VAL extends FinancialFunctionBase implements Function {

	@Override
	public String getName() {
		return "VAL";
	}

	@Override
	public int getDimension() {
		return 1;
	}

	@Override
	public Valuable execute(Valuable[] args, Map<String, Object> env) {
		if (args == null || args.length != 1 || (args[0].getDataType() != DataType.STRING && args[0].getDataType() != DataType.NUMBER)) {
			throw new ArgumentsMismatchException(args, getName());
		}
		Valuable arg = args[0];
		DataSet dataSet = (DataSet) env.get(FinancialConstant.EnvField.DATA_SET);
		String ret = null;
		if (arg.getDataType() == DataType.NUMBER) {
			ret = dataSet.getString(arg.getNumberValue().intValue());
		} else {
			ret = dataSet.getString(arg.getStringValue());
		}
		return new StringValue(ret);
	}

	@Override
	public String description() {
		StringBuffer desc = new StringBuffer();
		desc.append("公式格式：VAL(param1) 						\n");
		desc.append("公式描述：浮动取值公式						\n");
		desc.append("参数说明：param1 取数列（必填）				\n");
		desc.append("公式示例：VAL(“YE”)							\n");
		desc.append("表示取对应浮动公式查询结果中YE列值 			\n");
		return desc.toString();
	}

}
