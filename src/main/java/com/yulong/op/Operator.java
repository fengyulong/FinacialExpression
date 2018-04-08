package com.yulong.op;

import com.yulong.datatype.Valuable;

public interface Operator {
	public Valuable operate(Valuable[] arguments);
	
	public String getSymbol();
	
	public int getPriority();
	
	public int getDimension();
	
	
}
