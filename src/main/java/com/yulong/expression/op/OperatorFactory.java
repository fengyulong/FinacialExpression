package com.yulong.expression.op;

import java.util.HashMap;
import java.util.Map;

import com.yulong.expression.op.impl.AddOperator;
import com.yulong.expression.op.impl.DivideOperator;
import com.yulong.expression.op.impl.MultiplyOperator;
import com.yulong.expression.op.impl.SubtractOperator;

public class OperatorFactory {

	private static final Map<String, Operator> OP_MAP = new HashMap<String, Operator>();

	static {
		addOperator(new AddOperator());
		addOperator(new SubtractOperator());
		addOperator(new MultiplyOperator());
		addOperator(new DivideOperator());
	}

	public static Operator getOperator(String symbol) {
		return OP_MAP.get(symbol);
	}

	private static void addOperator(Operator op) {
		OP_MAP.put(op.getSymbol(), op);
	}
}
