package priv.yulong.common.util;

import java.sql.Connection;
import java.sql.DriverManager;

import priv.yulong.datafetch.datasourse.model.Datasource;

public class DbUtil {

	public static boolean testConnection(Datasource datasource) {
		try {
			Connection conn = null;
			try {
				Class.forName(datasource.getDbType().getDriverName());
				conn = DriverManager.getConnection(datasource.getUrl(), datasource.getUserName(),
						datasource.getUserPassword());
			} finally {
				if (conn != null && conn.isClosed() == false)
					conn.close();
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
