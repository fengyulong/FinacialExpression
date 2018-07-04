package priv.yulong.expression.datatype.impl;

import priv.yulong.expression.datatype.AbstractValue;
import priv.yulong.expression.datatype.DataType;

public class DataSetValue extends AbstractValue {

	private DataSet dataSet;

	public DataSetValue(DataSet dataSet) {
		this.dataSet = dataSet;
	}

	@Override
	public DataType getDataType() {
		return DataType.DATASET;
	}

	@Override
	public String getStringValue() {
		return dataSet.toString();
	}

	@Override
	public Object getValue() {
		return dataSet;
	}
	
	@Override
	public DataSet getDataSetValue() {
		return dataSet;
	}

}
