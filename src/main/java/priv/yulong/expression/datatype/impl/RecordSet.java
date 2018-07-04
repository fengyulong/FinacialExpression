package priv.yulong.expression.datatype.impl;

import java.math.BigDecimal;
import java.util.Date;

public class RecordSet {
	private Object[] objArr;

	public RecordSet(int size) {
		objArr = new Object[size];
	}

	public BigDecimal getNumber(int index) {
		return (BigDecimal) objArr[index];
	}

	public char getChar(int index) {
		return (char) objArr[index];
	}

	public Date getDate(int index) {
		return (Date) objArr[index];
	}

	public String getString(int index) {
		return objArr[index].toString();
	}

	public void set(int index, BigDecimal value) {
		objArr[index] = value;
	}

	public void set(int index, String value) {
		objArr[index] = value;
	}

	public void set(int index, Date value) {
		objArr[index] = value;
	}

	public void set(int index, Object value) {
		objArr[index] = value;
	}

	public int getSize() {
		return objArr.length;
	}

	public Object getObject(int index) {
		return objArr[index];
	}
	

}
