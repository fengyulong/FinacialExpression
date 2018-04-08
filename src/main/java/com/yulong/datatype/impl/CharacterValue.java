package com.yulong.datatype.impl;

import java.math.BigDecimal;

import com.yulong.datatype.AbstractValue;
import com.yulong.datatype.DataType;

public class CharacterValue extends AbstractValue {

	private Character value;

	public CharacterValue(Character value) {
		this.value = value;
	}

	@Override
	public DataType getDataType() {
		return DataType.CHARACTER;
	}

	@Override
	public String getStringValue() {
		return value.toString();
	}

	@Override
	public BigDecimal getNumberValue() {
		return new BigDecimal(value);
	}

	@Override
	public Character getCharValue() {
		return value;
	}

	@Override
	public Object getValue() {
		return value;
	}

}
