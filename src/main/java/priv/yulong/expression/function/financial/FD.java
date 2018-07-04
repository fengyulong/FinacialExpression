package priv.yulong.expression.function.financial;

import java.util.Map;

import priv.yulong.expression.datatype.Valuable;
import priv.yulong.expression.exception.ArgumentsMismatchException;
import priv.yulong.expression.function.Function;

public class FD extends FinancialFunctionBase implements Function {

	@Override
	public String getName() {
		return "FD";
	}

	@Override
	public int getDimension() {
		return -1;
	}

	@Override
	public Valuable execute(Valuable[] args, Map<String, Object> env) {
		if (args == null || args.length < 3 || args.length > 5) {
			throw new ArgumentsMismatchException(args, getName());
		}
		return null;
	}

	@Override
	public String description() {
		StringBuffer desc = new StringBuffer();
		desc.append("公式格式：FD(param1,param2,param3,param4,param5) 																\n");
		desc.append("公式描述：浮动公式取数																							\n");
		desc.append("参数说明：param1表名（必填），param2查询列（必填），param3取数条件（必填），param4分组字段，param5分组过滤条件		\n");
		desc.append("公式示例：FD(GL_BALANCE,”KM:KH”,”BM=’001’”,”KM:KH:XM”, ”sum(nc)<>0“)  											\n");
		desc.append("表示select KM,KH from GL_BALANCE where BM=’001’ group by KM,KH,XM having sum(nc)<>0	 							\n");
		return desc.toString();
	}

}
