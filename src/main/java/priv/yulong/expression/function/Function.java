package priv.yulong.expression.function;

import java.util.Map;

import priv.yulong.expression.datatype.Valuable;

public interface Function {

	String getName();
	
	int getDimension();
	
	Valuable execute(Valuable[] args,Map<String,Object> env);
	
}
