package priv.yulong.expression.op;

public enum OperatorType {
	LEFT_BRACKET("(", -1, -1), RIGHT_BRACKET(")", -1, -1), POWER("^", -2, 1), MULTIPLY("*", -3, 2), DIVIDE("/", -3,
			2), MOD("%", -3, 2), ADD("+", -4, 2), SUBTRACT("-", -4, 2), LESS("<", -5,
					2), LESS_EQUAL("<=", -5, 2), GREATER(">", -5, 2), GREATER_EQUAL(">=", -5,
							2), EQUAL("==", -6, 2), NOT_EQUAL("!=", -6, 2), AND("&&", -7, 2), OR("||", -8, 2);

	private String symbol;
	private int priority;
	private int dimension;

	OperatorType(String symbol, int priority, int dimension) {
		this.symbol = symbol;
		this.priority = priority;
		this.dimension = dimension;
	}

	public static boolean isOperatorSymbol(String symbol) {
		for (OperatorType o : OperatorType.values()) {
			if (o.getSymbol().equals(symbol)) {
				return true;
			}
		}
		return false;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	public static OperatorType getEnum(String symbol) {
		for (OperatorType o : OperatorType.values()) {
			if (o.getSymbol().equals(symbol)) {
				return o;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		return this.symbol;
	}

}
