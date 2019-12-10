package priv.yulong.expression.function.financial;

import priv.yulong.datafetch.datasourse.model.Datasource;
import priv.yulong.expression.datatype.DataType;
import priv.yulong.expression.datatype.Valuable;
import priv.yulong.expression.datatype.impl.DataSet;
import priv.yulong.expression.datatype.impl.DataSetValue;
import priv.yulong.expression.exception.ArgumentsMismatchException;
import priv.yulong.expression.exception.ExpressionRuntimeException;

import java.sql.*;
import java.util.Map;

/**
 * @ClassName FBDB
 * @Description: 中煤表对表浮动取数公式
 * @Author yulong.feng
 * @Date 2019-11-05
 * @Version V1.0
 **/
public class FBDB extends BDB {
    @Override
    public String getName() {
        return "FBDB";
    }

    @Override
    public int getDimension() {
        return 2;
    }

    @Override
    public Valuable execute(Valuable[] args, Map<String, Object> env) {
        if (args.length != 2) {
            throw new ArgumentsMismatchException(args, getName());
        }
        Map<String, Map<String, String>> cacheMap = (Map<String, Map<String, String>>) env.get(FinancialConstant.EnvField.CACHE_MAP);
        if (cacheMap == null) {
            cache(args, env);
        }
        Datasource.SoftVersion softVersion = (Datasource.SoftVersion) env.get(FinancialConstant.EnvField.SOFT_VERSION);
        SqlGenerator sqlGenerator = SqlGeneratorFactory.createSqlGenerator(getName(), softVersion);
        String sql = sqlGenerator.generateSQL(args, env);
        String datasourceCode = (String) env.get(FinancialConstant.EnvField.DATASOURCE_CODE);
        Connection connection = null;
        try {
            connection = datasourceService.getConnection(datasourceCode);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSetMetaData rsmd = statement.getMetaData();
            int columnCount = rsmd.getColumnCount();
            DataSet dataSet = new DataSet(columnCount, 0);
            for (int i = 1; i <= columnCount; i++) {
                dataSet.addField(rsmd.getColumnName(i));
                dataSet.addResultType(DataType.STRING);
            }
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                dataSet.newRow();
                for (int i = 0; i < columnCount; i++) {
                    dataSet.add(i, result.getString(i + 1));
                }
            }
            result.close();
            return new DataSetValue(dataSet);
        } catch (Exception e) {
            throw new ExpressionRuntimeException("执行公式取数发生错误", e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new ExpressionRuntimeException("执行公式取数关闭结果集和连接时出错", e);
                }
            }
        }

    }

    @Override
    public String description() {
        StringBuffer desc = new StringBuffer();
        desc.append("公式格式：FBDB(param1,param2) 								\n");
        desc.append("公式描述：中煤ERP中间库取数					            	\n");
        desc.append("参数说明：param1中间库表名(必填)，param2行序号(必填)		        \n");
        desc.append("公式示例：FBDB(1，5)  表示从中间库cux_rp_header1中取第5行的值  	\n");
        return desc.toString();
    }
}
