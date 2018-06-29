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

/**
 * String token
 * 
 * @author dennis
 * 
 */
public class VariableToken extends AbstractToken<Object> {

	public VariableToken(String lexeme, int startIndex) {
		super(startIndex, lexeme);
	}

	public TokenType getType() {
		return TokenType.Variable;
	}

	public Object getValue(Map<String, Object> env) {
		if (env != null && env.get(this.lexeme) != null) {
			return env.get(this.lexeme);
		} else {
			return this.lexeme;
		}
	}

	@Override
	public String toString() {
		return "[type='variable',lexeme='#" + getLexeme() + "',index=" + this.getStartIndex() + "]";
	}

}
