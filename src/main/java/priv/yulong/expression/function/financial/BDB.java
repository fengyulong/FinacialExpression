package priv.yulong.expression.function.financial;

import priv.yulong.datafetch.datasourse.model.Datasource;
import priv.yulong.expression.datatype.DataType;
import priv.yulong.expression.datatype.Valuable;
import priv.yulong.expression.datatype.impl.DataSet;
import priv.yulong.expression.datatype.impl.StringValue;
import priv.yulong.expression.exception.ArgumentsMismatchException;
import priv.yulong.expression.exception.ExpressionRuntimeException;
import priv.yulong.expression.function.Function;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName BDB
 * @Description: 中煤表对表取数
 * @Author yulong.feng
 * @Date 2019-11-04
 * @Version V1.0
 **/
public class BDB extends FinancialFunctionBase implements Function {
    @Override
    public String getName() {
        return "BDB";
    }

    @Override
    public int getDimension() {
        return 3;
    }

    @Override
    public Valuable execute(Valuable[] args, Map<String, Object> env) {
        if (args == null || args.length != 3) {
            throw new ArgumentsMismatchException(args, getName());
        }
        Map<Integer, DataSet> cacheMap = (Map<Integer, DataSet>) env.get(FinancialConstant.EnvField.CACHE_MAP);
        if (cacheMap == null) {
            cache(args, env);
        }
        //从cacheMap中取数
        cacheMap = (Map<Integer, DataSet>) env.get(FinancialConstant.EnvField.CACHE_MAP);
        Integer rowNum = args[1].getNumberValue().intValue();
        String colName = args[2].getStringValue();
        String result = cacheMap.get(rowNum).getString(colName);
        return new StringValue(result);
    }

    @Override
    public String description() {
        StringBuffer desc = new StringBuffer();
        desc.append("公式格式：BDB(param1,param2,param3) 												\n");
        desc.append("公式描述：中煤ERP中间库取数					            						\n");
        desc.append("参数说明：param1中间库表名(必填)，param2行序号(必填)，param3列序号(必填)		        \n");
        desc.append("公式示例：BDB('cux_rp_header1'，5，3)  表示从中间库cux_rp_header1中取第5行3列的值  	\n");
        return desc.toString();
    }


    protected void cache(Valuable[] args, Map<String, Object> env) {
        Map<Integer, DataSet> cacheMap = new HashMap<>();
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
            //将结果集封装到Map
            while (result.next()) {
                dataSet.newRow();
                for (int i = 0; i < columnCount; i++) {
                    dataSet.add(i, result.getString(i + 1));
                }
                //缓存到cacheMap
                cacheMap.put(result.getInt("ORDER_NUM"), dataSet);
            }
            result.close();
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
        env.put(FinancialConstant.EnvField.CACHE_MAP, cacheMap);
    }
}
