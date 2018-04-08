package com.yulong.expression.datatype.impl;

import java.math.BigDecimal;

import com.yulong.expression.datatype.AbstractValue;
import com.yulong.expression.datatype.DataType;

public class NumberValue extends AbstractValue {

	private BigDecimal value;

	public NumberValue(BigDecimal value) {
		this.value = value;
	}

	@Override
	public DataType getDataType() {
		return DataType.NUMBER;
	}

	@Override
	public BigDecimal getNumberValue() {
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