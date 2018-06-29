package priv.yulong.expression.lexer;

import java.util.Stack;

import priv.yulong.expression.datatype.Valuable;
import priv.yulong.expression.token.OperatorToken;
import priv.yulong.expression.token.StringToken;
import priv.yulong.expression.token.Token;
import priv.yulong.expression.token.Token.TokenType;

public class ExpressionParser {

	private static final String END_FLAG = "END_FLAG";

	public static Stack<Token<?>> buildPostExpressionStack(String expr) {
		Stack<Token<?>> retStack = new Stack<Token<?>>();
		Stack<Token<?>> tempStack = new Stack<Token<?>>();
		tempStack.push(new StringToken(END_FLAG, -1));
		ExpressionLexer exprLexer = new ExpressionLexer(expr);
		Token<?> token = null;
		while ((token = exprLexer.scan()) != null) {
			if (token.getLexeme().equals("(")) {
				tempStack.push(token);
			} else if (token.getType() == TokenType.Operator) {
				Token<?> lastToken = tempStack.lastElement();
				while (lastToken.getType() == TokenType.function
						|| (lastToken.getType() == TokenType.Operator && ((OperatorToken) lastToken).getOperator()
								.getPriority() >= ((OperatorToken) token).getOperator().getPriority())) {
					retStack.push(tempStack.pop());
					lastToken = tempStack.lastElement();
				}
				tempStack.push(token);
			} else if (token.getType() == TokenType.function) {
				tempStack.push(token);
			} else if (token.getLexeme().equals(",")) {
				while (!tempStack.lastElement().getLexeme().equals("(")) {
					retStack.push(tempStack.pop());
				}
			} else if (token.getLexeme().equals(")")) {
				while (!tempStack.lastElement().getLexeme().equals("(")) {
					retStack.push(tempStack.pop());
				}
				tempStack.pop();
				Token<?> lastToken = tempStack.lastElement();
				if (lastToken.getType() == TokenType.function) {
					retStack.push(tempStack.pop());
				}
			} else {
				retStack.push(token);
			}
		}
		while (!tempStack.lastElement().getLexeme().equals(END_FLAG)) {
			retStack.push(tempStack.pop());
		}
		// 逆序
		Stack<Token<?>> stack = new Stack<Token<?>>();
		while (!retStack.isEmpty()) {
			stack.push(retStack.pop());
		}
		return stack;
	}

	public static ExpressionNode buildExpression(String expr) {
		Stack<Token<?>> postStack = buildPostExpressionStack(expr);
		Stack<ExpressionNode> nodeStack = new Stack<ExpressionNode>();
		while (!postStack.isEmpty()) {
			Token<?> token = postStack.pop();
			ExpressionNode node = new ExpressionNode(token);
			TokenType tokenType = token.getType();
			if (tokenType == TokenType.function || tokenType == TokenType.Operator) {
				int dimension = token.getDimension();
				ExpressionNode[] children = new ExpressionNode[dimension];
				for (int j = dimension - 1; j >= 0; j--) {
					children[j] = nodeStack.pop();
				}
				node.setChildren(children);
				nodeStack.push(node);
			} else {
				nodeStack.push(node);
			}
		}
		ExpressionNode root = nodeStack.pop();
		return root;
	}
	
	public static void main(String[] args) {
		String exp = "max(1+6,2+8,3^4)";
		ExpressionNode node =  buildExpression(exp);
		Valuable ret = ExpressionCalculator.CalculateSubExpression(node,null);
		System.out.println(ret.getNumberValue());
		
	}
}
