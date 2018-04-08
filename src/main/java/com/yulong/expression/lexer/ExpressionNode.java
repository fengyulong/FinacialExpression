package com.yulong.expression.lexer;

import com.yulong.expression.token.Token;

public class ExpressionNode {

	private ExpressionNode[] children;
	private Token<?> token;

	public ExpressionNode(Token<?> token) {
		this.token = token;
	}

	public ExpressionNode[] getChildren() {
		return children;
	}

	public void setChildren(ExpressionNode[] children) {
		this.children = children;
	}

	public Token<?> getToken() {
		return token;
	}

	public void setToken(Token<?> token) {
		this.token = token;
	}

}
