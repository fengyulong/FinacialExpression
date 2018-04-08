package com.yulong.datatype.impl;

import java.util.Date;

import com.yulong.datatype.AbstractValue;
import com.yulong.datatype.DataType;
import com.yulong.util.DateUtil;

public class DateValue extends AbstractValue {

	private Date value;

	@Override
	public DataType getDataType() {
		return DataType.DATE;
	}

	@Override
	public Date getDateValue() {
		return value;
	}

	@Override
	public String getStringValue() {
		return DateUtil.getDateFormat(value);
	}

	@Override
	public Object getValue() {
		return value;
	}

}
