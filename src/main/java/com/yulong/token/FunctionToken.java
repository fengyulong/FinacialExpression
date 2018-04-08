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
package com.yulong.token;

import java.util.Map;

import com.yulong.function.Function;

/**
 * String token
 * 
 * @author dennis
 * 
 */
public class FunctionToken extends AbstractToken<Function> {

	private final Function function;
	private int dimension;

	public FunctionToken(String lexeme, int startIndex, Function function, int dimension) {
		super(startIndex, lexeme);
		this.function = function;
		this.dimension = dimension;
	}

	public Function getFunction() {
		return function;
	}

	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	public TokenType getType() {
		return TokenType.function;
	}

	public Function getValue(Map<String, Object> env) {
		return function;
	}

	@Override
	public String toString() {
		return "[type='function',lexeme='$" + getLexeme() + "',index=" + this.getStartIndex() + "]";
	}

}
