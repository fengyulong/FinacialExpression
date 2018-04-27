package priv.yulong.enumeration;

public enum Enums {
	SEX_MALE("M", "男"), SEX_FEMALE("F", "女");

	public String code;
	public String name;

	private Enums(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public Enums parse(String code) {
		if (code == null) {
			return null;
		}
		for (Enums e : values()) {
			if (e.code.equals(code)) {
				return e;
			}
		}
		return null;
	}
}
