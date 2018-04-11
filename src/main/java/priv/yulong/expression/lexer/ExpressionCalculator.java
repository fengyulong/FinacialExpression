package priv.yulong.expression.lexer;

import java.util.Map;

import priv.yulong.expression.datatype.Valuable;
import priv.yulong.expression.datatype.impl.AnyValue;
import priv.yulong.expression.datatype.impl.CharacterValue;
import priv.yulong.expression.datatype.impl.NumberValue;
import priv.yulong.expression.datatype.impl.StringValue;
import priv.yulong.expression.exception.ExpressionRuntimeException;
import priv.yulong.expression.function.Function;
import priv.yulong.expression.op.Operator;
import priv.yulong.expression.token.CharToken;
import priv.yulong.expression.token.FunctionToken;
import priv.yulong.expression.token.NumberToken;
import priv.yulong.expression.token.OperatorToken;
import priv.yulong.expression.token.StringToken;
import priv.yulong.expression.token.Token;
import priv.yulong.expression.token.VariableToken;

public class ExpressionCalculator {

	public static Valuable CalculateSubExpression(ExpressionNode expressionNode, Map<String, Object> env) {
		if (expressionNode == null) {
			return null;
		}
		Token<?> token = expressionNode.getToken();
		switch (token.getType()) {
		case String:
			return new StringValue(((StringToken) token).getValue(env));
		case Char:
			return new CharacterValue(((CharToken) token).getValue(env));
		case Number:
			return new NumberValue(((NumberToken) token).getValue(env));
		case Variable:
			return new AnyValue(((VariableToken) token).getValue(env));
		case Operator:
			return calculateOperator(expressionNode, env);
		case function:
			return calculateFunction(expressionNode, env);
		default:
			throw new ExpressionRuntimeException("[" + token + "] Unrecognized token");
		}
	}

	private static Valuable calculateOperator(ExpressionNode expressionNode, Map<String, Object> env) {
		OperatorToken token = (OperatorToken) expressionNode.getToken();
		ExpressionNode[] children = expressionNode.getChildren();
		Operator op = token.getOperator();
		if (children == null || children.length == 0) {
			return op.operate(new Valuable[0]);
		}
		Valuable[] arguments = new Valuable[children.length];
		for (int i = 0; i < children.length; i++) {
			arguments[i] = CalculateSubExpression(children[i], env);
		}
		return op.operate(arguments);
	}

	private static Valuable calculateFunction(ExpressionNode expressionNode, Map<String, Object> env) {
		FunctionToken token = (FunctionToken) expressionNode.getToken();
		ExpressionNode[] children = expressionNode.getChildren();
		Function op = token.getFunction();
		if (children == null || children.length == 0) {
			return op.execute(new Valuable[0], env);
		}
		Valuable[] arguments = new Valuable[children.length];
		for (int i = 0; i < children.length; i++) {
			arguments[i] = CalculateSubExpression(children[i], env);
		}
		return op.execute(arguments, env);
	}

}
