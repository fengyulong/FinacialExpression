package com.yulong.lexer;

import java.util.Map;

import com.yulong.datatype.Valuable;
import com.yulong.datatype.impl.AnyValue;
import com.yulong.datatype.impl.CharacterValue;
import com.yulong.datatype.impl.NumberValue;
import com.yulong.datatype.impl.StringValue;
import com.yulong.exception.ExpressionRuntimeException;
import com.yulong.function.Function;
import com.yulong.op.Operator;
import com.yulong.token.CharToken;
import com.yulong.token.FunctionToken;
import com.yulong.token.NumberToken;
import com.yulong.token.OperatorToken;
import com.yulong.token.StringToken;
import com.yulong.token.Token;
import com.yulong.token.VariableToken;

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
			return op.operate(null);
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
			return op.execute(null, env);
		}
		Valuable[] arguments = new Valuable[children.length];
		for (int i = 0; i < children.length; i++) {
			arguments[i] = CalculateSubExpression(children[i], env);
		}
		return op.execute(arguments, env);
	}

}
