package com.yulong.expression.op;

import java.util.HashMap;
import java.util.Map;

import com.yulong.expression.op.impl.AddOperator;
import com.yulong.expression.op.impl.AndOperator;
import com.yulong.expression.op.impl.DivideOperator;
import com.yulong.expression.op.impl.EqualOperator;
import com.yulong.expression.op.impl.GreaterEqualOperator;
import com.yulong.expression.op.impl.GreaterOperator;
import com.yulong.expression.op.impl.LessEqualOperator;
import com.yulong.expression.op.impl.LessOperator;
import com.yulong.expression.op.impl.ModOperator;
import com.yulong.expression.op.impl.MultiplyOperator;
import com.yulong.expression.op.impl.NotEqualOperator;
import com.yulong.expression.op.impl.OrOperator;
import com.yulong.expression.op.impl.PowerOperator;
import com.yulong.expression.op.impl.SubtractOperator;

public class OperatorFactory {

	private static final Map<String, Operator> OP_MAP = new HashMap<String, Operator>();

	static {
		addOperator(new AddOperator());
		addOperator(new SubtractOperator());
		addOperator(new MultiplyOperator());
		addOperator(new DivideOperator());
		addOperator(new ModOperator());
		addOperator(new PowerOperator());
		addOperator(new LessOperator());
		addOperator(new LessEqualOperator());
		addOperator(new GreaterOperator());
		addOperator(new GreaterEqualOperator());
		addOperator(new EqualOperator());
		addOperator(new NotEqualOperator());
		addOperator(new AndOperator());
		addOperator(new OrOperator());
	}

	public static Operator getOperator(String symbol) {
		return OP_MAP.get(symbol);
	}

	private static void addOperator(Operator op) {
		OP_MAP.put(op.getSymbol(), op);
	}
}
