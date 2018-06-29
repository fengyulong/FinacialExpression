package priv.yulong.datafetch.response;

import java.util.ArrayList;
import java.util.List;

public class FixExpResult {
	private String flag;
	private String dataType;
	private List<String> expression = new ArrayList<String>();
	private List<String> values = new ArrayList<String>();
	private boolean valid = true;

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public List<String> getExpression() {
		return expression;
	}

	public void setExpression(List<String> expression) {
		this.expression = expression;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	public void addExpression(String expr){
		expression.add(expr);
	}
	
	public void addValue(String value) {
		values.add(value);
	}

}
