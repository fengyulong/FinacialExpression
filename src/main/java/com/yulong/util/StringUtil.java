package com.yulong.util;

public class StringUtil {
	/**
	 * 首字母变小写
	 * 
	 * @param str
	 * @return
	 */
	public static String firstCharToLowerCase(String str) {
		char firstChar = str.charAt(0);
		if (firstChar >= 'A' && firstChar <= 'Z') {
			char[] arr = str.toCharArray();
			arr[0] += ('a' - 'A');
			return new String(arr);
		}
		return str;
	}

	/**
	 * 首字母变大写
	 * 
	 * @param str
	 * @return
	 */
	public static String firstCharToUpperCase(String str) {
		char firstChar = str.charAt(0);
		if (firstChar >= 'a' && firstChar <= 'z') {
			char[] arr = str.toCharArray();
			arr[0] -= ('a' - 'A');
			return new String(arr);
		}
		return str;
	}

	/**
	 * 判断是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(final String str) {
		return (str == null) || (str.length() == 0);
	}

	/**
	 * 判断是否不为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(final String str) {
		return !isEmpty(str);
	}

	/**
	 * 判断是否空白
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(final String str) {
		int strLen;
		if ((str == null) || ((strLen = str.length()) == 0))
			return true;
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断是否不是空白
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(final String str) {
		return !isBlank(str);
	}

	/**
	 * 判断多个字符串全部是否为空
	 * 
	 * @param strings
	 * @return
	 */
	public static boolean isAllEmpty(String... strings) {
		if (strings == null) {
			return true;
		}
		for (String str : strings) {
			if (isNotEmpty(str)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断多个字符串其中任意一个是否为空
	 * 
	 * @param strings
	 * @return
	 */
	public static boolean isAnyEmpty(String... strings) {
		if (strings == null) {
			return true;
		}
		for (String str : strings) {
			if (isEmpty(str)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 比较字符相等
	 * 
	 * @param value
	 * @param equals
	 * @return
	 */
	public static boolean equals(String value, String equals) {
		if (isAllEmpty(value, equals)) {
			return true;
		}
		return value.equals(equals);
	}

	/**
	 * 比较字符串不相等
	 * 
	 * @param value
	 * @param equals
	 * @return
	 */
	public static boolean isNotEquals(String value, String equals) {
		return !equals(value, equals);
	}
}
