package priv.yulong.expression.function.financial.zm;

import priv.yulong.common.util.LogUtil;
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
public class FMDBSqlGenerator implements SqlGenerator {
    @Override
    public String generateSQL(Valuable[] args, Map<String, Object> env) {
        String tableName = args[0].getStringValue();
        Integer rowNum = args[1].getNumberValue().intValue();
        Map<Integer,  Map<String,String>> cacheMap = (Map<Integer,  Map<String,String>>) env.get(FinancialConstant.EnvField.CACHE_MAP);
        String headerId = cacheMap.get(rowNum).get("HEADER_ID");
        String sql = "select * from CUX_RP_LINE#tableName# where HEADER_ID = #headerId# order by ORDER_NUM";
        sql = sql.replace("#tableName#", tableName);
        sql = sql.replace("#headerId#", headerId);
        LogUtil.info("FBDB取数SQL[{}]",sql);
        return sql;
    }

}
