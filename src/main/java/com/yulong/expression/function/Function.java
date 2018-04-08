package com.yulong.expression.function;

import java.util.Map;

import com.yulong.expression.datatype.Valuable;

public interface Function {

	String getName();
	
	int getDimension();
	
	Valuable execute(Object[] args,Map<String,Object> env);
	
}