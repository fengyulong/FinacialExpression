package priv.yulong.expression.datatype.impl;

import priv.yulong.expression.datatype.AbstractValue;
import priv.yulong.expression.datatype.DataType;

public class StringValue extends AbstractValue {

	private String value;

	public StringValue(String value) {
		this.value = value;
	}

	@Override
	public DataType getDataType() {
		return DataType.STRING;
	}

	@Override
	public String getStringValue() {
		return value;
	}

	@Override
	public Object getValue() {
		return value;
	}

}
