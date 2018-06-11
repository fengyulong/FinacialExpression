package priv.yulong.datasourse.model;

public class Datasource {

	public enum SoftVersion {
		EBS
	}

	public enum DbType {
		MySql, SqlServer, Oracle
	}


	private String code;

	private String name;

	private SoftVersion softVersion;

	private DbType dbType;

	private String instance;

	private String host;

	private String port;

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

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port == null ? null : port.trim();
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
}