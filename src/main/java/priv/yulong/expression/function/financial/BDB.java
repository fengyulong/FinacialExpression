package priv.yulong.expression.function.financial;

import priv.yulong.datafetch.datasourse.model.Datasource;
import priv.yulong.expression.datatype.DataType;
import priv.yulong.expression.datatype.Valuable;
import priv.yulong.expression.datatype.impl.DataSet;
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
        if (args == null || args.length < 3 ) {
            throw new ArgumentsMismatchException(args, getName());
        }
        Map<String,DataSet> cacheMap = (Map<String,DataSet>)env.get(FinancialConstant.EnvField.CACHE_MAP);
        if(cacheMap == null){
            cache(args,env);
        }
        //从cacheMap中取数
        cacheMap = (Map<String,DataSet>)env.get(FinancialConstant.EnvField.CACHE_MAP);

        return null;
    }

    @Override
    public String description() {
        return null;
    }


    private void cache(Valuable[] args, Map<String, Object> env){
        Map<String,DataSet> cacheMap = new HashMap<>();
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
            while(result.next()){
                dataSet.newRow();
                for (int i = 0; i < columnCount; i++) {
                    dataSet.add(i, result.getString(i + 1));
                }
                //缓存到cacheMap
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
        env.put(FinancialConstant.EnvField.CACHE_MAP,cacheMap);
    }
}
