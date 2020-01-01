package priv.yulong.expression.function.financial.zm;

import priv.yulong.common.util.LogUtil;
import priv.yulong.expression.datatype.Valuable;
import priv.yulong.expression.function.financial.FinancialConstant;
import priv.yulong.expression.function.financial.SqlGenerator;

import java.util.Map;

/**
 * @ClassName BDBSqlGenerator
 * @Description: 表对表取数SQL
 * @Author yulong.feng
 * @Date 2019-11-04
 * @Version V1.0
 **/
public class BDBSqlGenerator implements SqlGenerator {
    @Override
    public String generateSQL(Valuable[] args, Map<String, Object> env) {
        String tableName = args[0].getStringValue();
        int period = (int) env.get(FinancialConstant.EnvField.END_PERIOD);
        int year = (int) env.get(FinancialConstant.EnvField.END_YEAR);
        String orgCode = (String) env.get(FinancialConstant.EnvField.ORG_CODE);
        String sql = getSqlTemplate();
        sql = sql.replace("#tableName#", tableName);
        sql = sql.replace("#year#", year + "");
        //年终取调整期数据
        if(period == 12){
            period = 13;
        }
        sql = sql.replace("#period#", period > 9 ? period + "" : "0" + period);
        sql = sql.replace("#orgCode#", orgCode);
        LogUtil.info("BDB取数SQL[{}]",sql);
        return sql;
    }

    private String getSqlTemplate() {
        return "select * from CUX_RP_HEADER#tableName# where COM_CODE = '#orgCode#' and PERIOD_NAME = '#year#-#period#'";
    }
}
