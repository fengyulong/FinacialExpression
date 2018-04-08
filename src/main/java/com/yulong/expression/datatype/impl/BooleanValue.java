package com.yulong.expression.datatype.impl;

import com.yulong.expression.datatype.AbstractValue;
import com.yulong.expression.datatype.DataType;

public class BooleanValue extends AbstractValue {

	private Boolean value;

	public BooleanValue(Boolean value) {
		this.value = value;
	}

	@Override
	public DataType getDataType() {
		return DataType.BOOLEAN;
	}

	@Override
	public Boolean getBooleanValue() {
		return value;
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
