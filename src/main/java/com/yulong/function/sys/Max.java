package com.yulong.function.sys;

import java.util.Map;

import com.yulong.datatype.Valuable;
import com.yulong.function.Function;

public class Max implements Function{

	@Override
	public String getName() {
		return "max";
	}

	@Override
	public int getDimension() {
		return 2;
	}

	@Override
	public Valuable execute(Object[] args, Map<String, Object> env) {
		return null;
	}

}
