package priv.yulong.expression.datatype.impl;

import java.util.Date;

import priv.yulong.expression.datatype.AbstractValue;
import priv.yulong.expression.datatype.DataType;
import priv.yulong.util.DateUtil;

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
