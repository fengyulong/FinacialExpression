package priv.yulong.expression.function;

import java.util.HashMap;
import java.util.Map;

import priv.yulong.common.util.StringUtil;
import priv.yulong.expression.function.financial.*;
import priv.yulong.expression.function.sys.Abs;
import priv.yulong.expression.function.sys.Judge;
import priv.yulong.expression.function.sys.Max;

public class FunctionFactory {

    private final static Map<String, Function> FUNC_MAP = new HashMap<String, Function>();

    static {

        //System function
        registerFunction(new Max());
        registerFunction(new Abs());
        registerFunction(new Judge());

        //Financial function
        registerFunction(new ZW());
        //registerFunction(new ZFZ());
        //registerFunction(new FSQL());
		//中煤取数公式注册
        registerFunction(new BDB());
        registerFunction(new FBDB());
        registerFunction(new VAL());
        registerFunction(new MDB());
        registerFunction(new FMDB());

    }

    public static Function getFunction(String identifier) {
        if (StringUtil.isNotEmpty(identifier)) {
            return FUNC_MAP.get(identifier.toUpperCase());
        }
        return null;
    }

    public static void registerFunction(Function function) {
        FUNC_MAP.put(function.getName().toUpperCase(), function);
    }

}
