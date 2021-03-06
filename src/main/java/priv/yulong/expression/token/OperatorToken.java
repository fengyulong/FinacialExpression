/**
 * Copyright (C) 2010 dennis zhuang (killme2008@gmail.com)
 *
 * This library is free software; you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation; either version
 * 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this program;
 * if not, write to the Free Software Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 *
 **/
package priv.yulong.expression.token;

import java.util.Map;

import priv.yulong.expression.op.Operator;

/**
 * Operator token
 * 
 * @author dennis
 * 
 */
public class OperatorToken extends AbstractToken<Operator> {

	private final Operator operator;

	public Operator getOperator() {
		return operator;
	}

	public OperatorToken(int startIndex, Operator operator) {
		super(startIndex, operator.getSymbol());
		this.operator = operator;
	}

	public TokenType getType() {
		return TokenType.Operator;
	}

	public Operator getValue(Map<String, Object> env) {
		return operator;
	}

	public int getDimension() {
		return operator.getDimension();
	}

}
