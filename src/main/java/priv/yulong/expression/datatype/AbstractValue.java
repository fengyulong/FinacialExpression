package priv.yulong.expression.datatype;

import java.math.BigDecimal;
import java.util.Date;

import priv.yulong.expression.datatype.impl.DataSet;
import priv.yulong.expression.exception.ExpressionRuntimeException;

public abstract class AbstractValue implements Valuable {

	@Override
	public abstract DataType getDataType();

	@Override
	public abstract String getStringValue();

	@Override
	public abstract Object getValue();

	@Override
	public BigDecimal getNumberValue() {
		throw new ExpressionRuntimeException("[" + getStringValue() + "] cannot be converted to a Number value");
	}

	@Override
	public Character getCharValue() {
		throw new ExpressionRuntimeException("[" + getStringValue() + "] cannot be converted to a Char value");
	}

	@Override
	public Date getDateValue() {
		throw new ExpressionRuntimeException("[" + getStringValue() + "] cannot be converted to a Date value");
	}

	@Override
	public Boolean getBooleanValue() {
		throw new ExpressionRuntimeException("[" + getStringValue() + "] cannot be converted to a Boolean value");
	}
	
	@Override
	public DataSet getDataSetValue() {
		throw new ExpressionRuntimeException("[" + getStringValue() + "] cannot be converted to a Boolean value");
	}


}
