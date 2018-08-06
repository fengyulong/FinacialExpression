package priv.yulong.datafetch.datasourse.model;

public class Datasource {

	public enum SoftVersion {
		EBS("EBS"),
		TEST("测试"),
		SJJS("数据集市");

		private String title;

		SoftVersion(String title) {
			this.title = title;
		}

		public String getTitle() {
			return title;
		}

	}

	public enum DbType {
		MySql("com.mysql.jdbc.Driver", "jdbc:mysql://%1$s:%2$d/%3$s", 3306, "select x"),

		SqlServer("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://%1$s:%2$d;DatabaseName=%3$s", 1433,
				"select 1"),

		Oracle("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@%1$s:%2$d:%3$s", 1521, "select 1 from dual");

		private final String driverName;
		private final String urlFormat;
		private final int defaultPort;
		private final String validationQuery;

		/**
		 * 构造函数
		 * 
		 * @param description
		 * @param driverName
		 * @param urlFormat
		 */
		DbType(String driverName, String urlFormat, int defaultPort, String validationQuery) {
			this.driverName = driverName;
			this.urlFormat = urlFormat;
			this.defaultPort = defaultPort;
			this.validationQuery = validationQuery;
		}

		/**
		 * 根据参数构建数据库连接URL
		 * 
		 * @param server
		 * @param port
		 * @param dbname
		 * @return
		 */
		public String buildURL(String host, int port, String instance) {
			return String.format(urlFormat, host, port, instance);
		}

		public String getDriverName() {
			return driverName;
		}

		public String getUrlFormat() {
			return urlFormat;
		}

		public int getDefaultPort() {
			return defaultPort;
		}

		public String getValidationQuery() {
			return validationQuery;
		}

	}

	private String code;

	private String name;

	private SoftVersion softVersion;

	private DbType dbType;

	private String instance;

	private String host;

	private int port;

	private String userName;

	private String userPassword;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public SoftVersion getSoftVersion() {
		return softVersion;
	}

	public void setSoftVersion(SoftVersion softVersion) {
		this.softVersion = softVersion;
	}

	public DbType getDbType() {
		return dbType;
	}

	public void setDbType(DbType dbType) {
		this.dbType = dbType;
	}

	public String getInstance() {
		return instance;
	}

	public void setInstance(String instance) {
		this.instance = instance == null ? null : instance.trim();
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host == null ? null : host.trim();
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword == null ? null : userPassword.trim();
	}

	public String getUrl() {
		return dbType.buildURL(host, port, instance);
	}
}