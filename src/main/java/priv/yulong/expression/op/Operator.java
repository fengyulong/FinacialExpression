package priv.yulong.expression.op;

import priv.yulong.expression.datatype.Valuable;

public interface Operator {
	public Valuable operate(Valuable[] arguments);
	
	public String getSymbol();
	
	public int getPriority();
	
	public int getDimension();
	
	
}
