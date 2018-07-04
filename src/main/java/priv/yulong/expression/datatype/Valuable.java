package priv.yulong.expression.datatype;

import java.math.BigDecimal;
import java.util.Date;

import priv.yulong.expression.datatype.impl.DataSet;

public interface Valuable {

	public DataType getDataType();

	public BigDecimal getNumberValue();

	public String getStringValue();

	public Character getCharValue();

	public Date getDateValue();

	public Boolean getBooleanValue();

	public DataSet getDataSetValue();
	
	public Object getValue();

}
