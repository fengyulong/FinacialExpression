package com.yulong.expression.datatype;

import java.math.BigDecimal;
import java.util.Date;

public interface Valuable {

	public DataType getDataType();

	public BigDecimal getNumberValue();

	public String getStringValue();

	public Character getCharValue();

	public Date getDateValue();

	public Boolean getBooleanValue();

	public Object getValue();

}
