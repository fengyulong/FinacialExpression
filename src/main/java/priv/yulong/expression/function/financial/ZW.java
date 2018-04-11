package priv.yulong.expression.function.financial;

import java.util.Map;

import priv.yulong.expression.datatype.Valuable;
import priv.yulong.expression.function.Function;

public class ZW implements Function {

	@Override
	public String getName() {
		return "zw";
	}

	@Override
	public int getDimension() {
		return -1;
	}

	@Override
	public Valuable execute(Valuable[] args, Map<String, Object> env) {
		return null;
	}

}
