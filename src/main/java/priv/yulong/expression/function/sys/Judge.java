package priv.yulong.expression.function.sys;

import java.util.Map;

import priv.yulong.expression.datatype.DataType;
import priv.yulong.expression.datatype.Valuable;
import priv.yulong.expression.exception.ArgumentsMismatchException;
import priv.yulong.expression.function.Function;

public class Judge implements Function {

	@Override
	public String getName() {
		return "judge";
	}

	@Override
	public int getDimension() {
		return 3;
	}

	@Override
	public Valuable execute(Valuable[] args, Map<String, Object> env) {
		Valuable condition = args[0];
		if (condition.getDataType() != DataType.BOOLEAN) {
			throw new ArgumentsMismatchException(args, getName());
		}
		if (condition.getBooleanValue())
			return args[1];
		else
			return args[2];
	}

	@Override
	public String description() {
		StringBuffer desc = new StringBuffer();
		desc.append("公式格式：judge(param1,param2,param3) 									\n");
		desc.append("公式描述：条件取值,如果 param1为true返回	param2，反之则返回param3			\n");
		desc.append("参数说明：param1布尔类型(必填),param2任意类型(必填),param3任意类型(必填)	\n");
		desc.append("公式示例：judge(false,20,50)  结果是50 									\n");
		return desc.toString();
	}
}
