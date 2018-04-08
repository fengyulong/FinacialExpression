package com.yulong.exception;

import com.yulong.datatype.Valuable;

public class ArgumentsMismatchException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ArgumentsMismatchException(String message) {
		super(message);
	}

	public ArgumentsMismatchException(Valuable[] arguments, String operatorName) {
		super("The operator(or method) " + operatorName + " is undefined for the arguments (" + getErrorTypes(arguments)
				+ ").");
	}

	private static String getErrorTypes(Valuable[] arguments) {
		if (arguments.length == 0)
			return "";
		StringBuilder types = new StringBuilder();
		for (Valuable argument : arguments)
			types.append(argument.getDataType().name()).append(',');
		return types.toString().substring(0, types.length() - 1);
	}
}
