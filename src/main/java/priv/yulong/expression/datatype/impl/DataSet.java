package priv.yulong.expression.datatype.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import priv.yulong.common.util.DateUtil;
import priv.yulong.expression.datatype.DataType;

public class DataSet {

	private int currRow;
	private int rowCount;
	private int fieldCount;
	private List<String> fields;
	private RecordSet activeRecord;
	private List<RecordSet> recordList;
	private List<DataType> resultTypes;
	private Map<String, Integer> fieldIndexMap;

	public DataSet(int colCount, int rowCount) {
		fieldCount = colCount;
		fields = new ArrayList<String>();
		recordList = new ArrayList<RecordSet>();
		resultTypes = new ArrayList<DataType>();
		fieldIndexMap = new HashMap<String, Integer>();
		for (int i = 0; i < rowCount; i++)
			newRow();
	}

	public int newRow() {
		RecordSet row = new RecordSet(fieldCount);
		recordList.add(row);
		rowCount = recordList.size();
		setCurrRow(rowCount - 1);
		return rowCount;
	}

	public int getCurrRow() {
		return currRow;
	}

	public DataType getDataType(int index) {
		return resultTypes.get(index);
	}

	public DataType getDataType(String field) {
		return resultTypes.get(fieldIndexMap.get(field.toUpperCase()));
	}

	public BigDecimal getNumber(String field) {
		return activeRecord.getNumber(fieldIndexMap.get(field.toUpperCase()));
	}

	public BigDecimal getNumber(int index) {
		return activeRecord.getNumber(index);
	}

	public Date getDate(int index) {
		return activeRecord.getDate(index);
	}

	public Object getObject(String field) {
		return activeRecord.getObject(fieldIndexMap.get(field.toUpperCase()));
	}

	public Object getObject(int index) {
		return activeRecord.getObject(index);
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getcolCount() {
		return fieldCount;
	}

	public boolean isNull(int index) {
		return (activeRecord.getObject(index) == null);
	}

	public boolean isNull(String field) {
		return (activeRecord.getObject(fieldIndexMap.get(field.toUpperCase())) == null);
	}

	public void setCurrRow(int index) {
		currRow = index;
		activeRecord = recordList.get(currRow);
	}

	public int add(int index, BigDecimal value) {
		activeRecord.set(index, value);
		return rowCount;
	}

	public int add(int index, String value) {
		activeRecord.set(index, value);
		return rowCount;
	}

	public int add(int index, Date value) {
		activeRecord.set(index, value);
		return rowCount;
	}

	public int add(int index, Object value) {
		activeRecord.set(index, value);
		return rowCount;
	}

	public boolean addField(String field) {
		if (fields.size() < fieldCount) {
			fields.add(field);
			fieldIndexMap.put(field.toUpperCase(), fields.size());
			return true;
		}
		return false;
	}

	public boolean addResultType(DataType resultType) {
		if (resultTypes.size() < fieldCount) {
			resultTypes.add(resultType);
			return true;
		}
		return false;
	}

	public String getString(int index) {
		if (getDataType(index) == DataType.DATE) {
			return DateUtil.getDateFormat(activeRecord.getDate(index));
		} else {
			return activeRecord.getObject(index).toString();
		}
	}

	public String getString(String field) {
		int index = fieldIndexMap.get(field.toUpperCase());
		if (getDataType(index) == DataType.DATE) {
			return DateUtil.getDateFormat(activeRecord.getDate(index));
		} else {
			return activeRecord.getObject(index).toString();
		}
	}

	public RecordSet getRow(int index) {
		return recordList.get(index);
	}

	public String getField(int index) {
		return fields.get(index);
	}

}
