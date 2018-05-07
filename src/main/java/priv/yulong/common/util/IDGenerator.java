package priv.yulong.common.util;

import java.util.UUID;

public class IDGenerator {
	public static String randomID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
