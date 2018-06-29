package priv.yulong.expression;

import java.util.Map;

import priv.yulong.expression.datatype.Valuable;
import priv.yulong.expression.lexer.ExpressionCalculator;
import priv.yulong.expression.lexer.ExpressionNode;
import priv.yulong.expression.lexer.ExpressionParser;

public class ExpressionEvaluator {

	public static Valuable evaluate(String expr, Map<String, Object> env) {
		ExpressionNode node = ExpressionParser.buildExpression(expr);
		Valuable ret = ExpressionCalculator.CalculateSubExpression(node, env);
		return ret;
	}
}
