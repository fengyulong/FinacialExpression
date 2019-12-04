package priv.yulong.expression.lexer;

import java.math.BigDecimal;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

import priv.yulong.expression.exception.ExpressionRuntimeException;
import priv.yulong.expression.function.Function;
import priv.yulong.expression.function.FunctionFactory;
import priv.yulong.expression.op.Operator;
import priv.yulong.expression.op.OperatorFactory;
import priv.yulong.expression.token.CharToken;
import priv.yulong.expression.token.FunctionToken;
import priv.yulong.expression.token.NumberToken;
import priv.yulong.expression.token.OperatorToken;
import priv.yulong.expression.token.StringToken;
import priv.yulong.expression.token.Token;
import priv.yulong.expression.token.VariableToken;

public class ExpressionLexer {
	// current char
	private char peek;
	// Char iterator for string
	private final CharacterIterator iterator;

	public ExpressionLexer(String expression) {
		this.iterator = new StringCharacterIterator(expression);
		this.peek = this.iterator.current();
	}

	public Token<?> scan() {

		for (;; this.nextChar()) {
			if (this.peek == CharacterIterator.DONE) {
				return null;
			}

			if (this.peek == ' ' || this.peek == '\t' || this.peek == '\r') {
				continue;
			}
			if (this.peek == '\n') {
				throw new ExpressionRuntimeException(
						"System doesn't support multi-lines expression at " + this.iterator.getIndex());
			} else {
				break;
			}
		}

		// if it is a hex digit
		if (Character.isDigit(this.peek) && this.peek == '0') {
			this.nextChar();
			if (this.peek == 'x' || this.peek == 'X') {
				this.nextChar();
				StringBuffer sb = new StringBuffer();
				int startIndex = this.iterator.getIndex() - 2;
				long value = 0L;
				do {
					sb.append(this.peek);
					value = 16 * value + Character.digit(this.peek, 16);
					this.nextChar();
				} while (this.isValidHexChar(this.peek));
				return new NumberToken(new BigDecimal(value), sb.toString(), startIndex);
			} else {
				this.prevChar();
			}
		}

		// If it is a digit
		if (Character.isDigit(this.peek) || this.peek == '.') {
			StringBuffer sb = new StringBuffer();
			int startIndex = this.iterator.getIndex();
			long lval = 0L;
			double dval = 0d;
			boolean hasDot = false;
			double d = 10.0;
			boolean isBigInt = false;
			boolean isBigDecimal = false;
			boolean scientificNotation = false;
			boolean negExp = false;
			do {
				sb.append(this.peek);
				if (this.peek == '.') {
					if (scientificNotation) {
						throw new ExpressionRuntimeException(
								"Illegal number " + sb + " at " + this.iterator.getIndex());
					}
					if (hasDot) {
						throw new ExpressionRuntimeException(
								"Illegal Number " + sb + " at " + this.iterator.getIndex());
					} else {
						hasDot = true;
						this.nextChar();
					}

				} else if (this.peek == 'N') {
					// big integer
					if (hasDot) {
						throw new ExpressionRuntimeException(
								"Illegal number " + sb + " at " + this.iterator.getIndex());
					}
					isBigInt = true;
					this.nextChar();
					break;
				} else if (this.peek == 'M') {
					isBigDecimal = true;
					this.nextChar();
					break;
				} else if (this.peek == 'e' || this.peek == 'E') {
					if (scientificNotation) {
						throw new ExpressionRuntimeException(
								"Illegal number " + sb + " at " + this.iterator.getIndex());
					}
					scientificNotation = true;
					this.nextChar();
					if (this.peek == '-') {
						negExp = true;
						sb.append(this.peek);
						this.nextChar();
					}
				} else {
					int digit = Character.digit(this.peek, 10);
					if (scientificNotation) {
						int n = digit;
						this.nextChar();
						while (Character.isDigit(this.peek)) {
							n = 10 * n + Character.digit(this.peek, 10);
							this.nextChar();
						}
						while (n-- > 0) {
							if (negExp) {
								dval = dval / 10;
							} else {
								dval = 10 * dval;
							}
						}
						hasDot = true;
					} else if (hasDot) {
						dval = dval + digit / d;
						d = d * 10;
						this.nextChar();
					} else {
						lval = 10 * lval + digit;
						dval = 10 * dval + digit;
						this.nextChar();
					}

				}
			} while (Character.isDigit(this.peek) || this.peek == '.' || this.peek == 'E' || this.peek == 'e'
					|| this.peek == 'M' || this.peek == 'N');
			BigDecimal value;
			if (isBigDecimal) {
				value = new BigDecimal(this.getBigNumberLexeme(sb));
			} else if (isBigInt) {
				value = new BigDecimal(this.getBigNumberLexeme(sb));
			} else {
				// if the long value is out of range,then it must be negative,so
				// we make it as a big integer.
				if (lval < 0) {
					value = new BigDecimal(sb.toString());
				} else {
					value = new BigDecimal(lval);
				}
			}
			String lexeme = sb.toString();
			if (isBigDecimal || isBigInt) {
				lexeme = lexeme.substring(0, lexeme.length() - 1);
			}
			return new NumberToken(value, lexeme, startIndex);
		}

		// It is a variable
		if (this.peek == '#') {
			int startIndex = this.iterator.getIndex();
			this.nextChar(); // skip $
			StringBuilder sb = new StringBuilder();
			while (Character.isJavaIdentifierPart(this.peek) || this.peek == '.' || this.peek == '['
					|| this.peek == ']') {
				sb.append(this.peek);
				this.nextChar();
			}
			String lexeme = sb.toString();
			if (lexeme.isEmpty()) {
				throw new ExpressionRuntimeException("Blank variable name after '#'");
			}
			VariableToken variable = new VariableToken(lexeme, startIndex);
			return variable;
		}

		if (Character.isJavaIdentifierStart(this.peek)) {
			int startIndex = this.iterator.getIndex();
			StringBuilder sb = new StringBuilder();
			do {
				sb.append(this.peek);
				this.nextChar();
			} while (Character.isJavaIdentifierPart(this.peek) || this.peek == '.');
			String lexeme = sb.toString();
			Function function = FunctionFactory.getFunction(lexeme);
			if (function != null) {
				int nextNum = 0;
				int splitNum = 0;
				while (this.peek != '(') {
					nextChar();
					nextNum++;
				}
				int left = 0;
				int right = 0;
				do {
					nextChar();
					nextNum++;
					if (this.peek == ',' && left == right) {
						splitNum++;
					} else if (this.peek == '(') {
						left++;
					} else if (this.peek == ')') {
						right++;
					}
				} while (left >= right);
				while (nextNum > 0) {
					prevChar();
					nextNum--;
				}
				if (function.getDimension() == -1) {
					return new FunctionToken(lexeme, startIndex, function, splitNum + 1);
				} else {
					if(splitNum + 1 == function.getDimension()){
						return new FunctionToken(lexeme, startIndex, function, function.getDimension());
					}
					throw new ExpressionRuntimeException("Illegal param number");
				}
			} else {
				return new VariableToken(lexeme, startIndex);
			}
		}
		// It is a operator
		if (isBinaryOP(this.peek)) {
			int startIndex = this.iterator.getIndex();
			StringBuilder sb = new StringBuilder();
			do {
				sb.append(this.peek);
				this.nextChar();
			} while (isBinaryOP(this.peek));
			String lexeme = sb.toString();
			Operator op = OperatorFactory.getOperator(lexeme);
			if (op != null) {
				return new OperatorToken(startIndex, op);
			} else {
				throw new ExpressionRuntimeException("Illegal operator " + lexeme + " at " + startIndex);
			}
		}

		// String
		if (this.peek == '"' || this.peek == '\'') {
			char left = this.peek;
			int startIndex = this.iterator.getIndex();
			StringBuilder sb = new StringBuilder();
			while ((this.peek = this.iterator.next()) != left) {
				if (this.peek == CharacterIterator.DONE) {
					throw new ExpressionRuntimeException("Illegal String " + sb + " at " + startIndex);
				} else {
					sb.append(this.peek);
				}
			}
			this.nextChar();
			return new StringToken(sb.toString(), startIndex);
		}

		Token<Character> token = new CharToken(this.peek, this.iterator.getIndex());
		this.nextChar();
		return token;

	}

	public void prevChar() {
		this.peek = this.iterator.previous();
	}

	public void nextChar() {
		this.peek = this.iterator.next();
	}

	static final char[] VALID_HEX_CHAR = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'a', 'B', 'b', 'C',
			'c', 'D', 'd', 'E', 'e', 'F', 'f' };

	public boolean isValidHexChar(char ch) {
		for (char c : VALID_HEX_CHAR) {
			if (c == ch) {
				return true;
			}
		}
		return false;
	}

	static final char[] OPS = { '=', '>', '<', '+', '-', '*', '/', '%', '!', '&', '|','^' };

	public static boolean isBinaryOP(char ch) {
		for (char tmp : OPS) {
			if (tmp == ch) {
				return true;
			}
		}
		return false;
	}

	private String getBigNumberLexeme(StringBuffer sb) {
		String lexeme = sb.toString();
		lexeme = lexeme.substring(0, lexeme.length() - 1);
		return lexeme;
	}
}
