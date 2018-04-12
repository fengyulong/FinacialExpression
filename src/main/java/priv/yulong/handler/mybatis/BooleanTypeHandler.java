package priv.yulong.handler.mybatis;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

@MappedJdbcTypes({ JdbcType.CHAR })
@MappedTypes({ Boolean.class })
public class BooleanTypeHandler extends BaseTypeHandler<Boolean> {

	private static final String TRUE = "Y";
	private static final String FALSE = "N";

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Boolean parameter, JdbcType jdbcType)
			throws SQLException {
		String value = parameter == null ? FALSE : parameter ? TRUE : FALSE;
		ps.setString(i, value);
	}

	@Override
	public Boolean getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return TRUE.equals(rs.getString(columnName));
	}

	@Override
	public Boolean getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return TRUE.equals(rs.getString(columnIndex));
	}

	@Override
	public Boolean getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return TRUE.equals(cs.getString(columnIndex));
	}
}
