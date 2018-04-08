package com.yulong.function;

import java.util.Map;

import com.yulong.datatype.Valuable;

public interface Function {

	String getName();
	
	int getDimension();
	
	Valuable execute(Object[] args,Map<String,Object> env);
	
}
