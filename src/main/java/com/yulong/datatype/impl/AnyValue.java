package com.yulong.datatype.impl;

import com.yulong.datatype.AbstractValue;
import com.yulong.datatype.DataType;

public class AnyValue extends AbstractValue {

	private Object value;

	public AnyValue(Object value) {
		this.value = value;
	}

	@Override
	public DataType getDataType() {
		return DataType.ANY;
	}

	@Override
	public String getStringValue() {
		return value == null ? null : value.toString();
	}

	@Override
	public Object getValue() {
		return value;
	}

}
