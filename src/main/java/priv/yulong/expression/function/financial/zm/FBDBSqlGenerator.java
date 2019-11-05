package priv.yulong.expression.function.financial.zm;

import priv.yulong.expression.datatype.Valuable;
import priv.yulong.expression.function.financial.FinancialConstant;
import priv.yulong.expression.function.financial.SqlGenerator;

import java.util.Map;

/**
 * @ClassName FBDBSqlGenerator
 * @Description: 浮动表对表取数SQL
 * @Author yulong.feng
 * @Date 2019-11-04
 * @Version V1.0
 **/
public class FBDBSqlGenerator implements SqlGenerator {
    @Override
    public String generateSQL(Valuable[] args, Map<String, Object> env) {
        String tableName = args[0].getStringValue();
        String sql = "";
        return sql;
    }

}
