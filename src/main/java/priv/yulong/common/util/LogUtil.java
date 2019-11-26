package priv.yulong.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LogUtil
 * @Description: TODO
 * @Author yulong.feng
 * @Date 2019-11-05
 * @Version V1.0
 **/
public class LogUtil {
    public static final String DEBUG = "DEBUG";
    public static final String INFO = "INFO";
    public static final String WARN = "WARN";
    public static final String ERROR = "ERROR";
    public static final String EMPTY = "EMPTY";
    public static Map<String, String> notErrorMap = new HashMap();

    public LogUtil() {
    }

    public static Logger getLogger() {
        return LoggerFactory.getLogger(getLogClass());
    }

    public static Map<String, String> getNotErrorMap() {
        return notErrorMap;
    }


    public static void info(Object msg) {
        Logger logger = getLogger();
        logger.info(getMsg(msg));
    }

    public static void info(Object msg, Object o1) {
        Logger logger = getLogger();
        logger.info(getMsg(msg), o1);
    }

    public static void info(Object msg, Object o1, Object o2) {
        Logger logger = getLogger();
        logger.info(getMsg(msg), o1, o2);
    }

    public static void info(Object msg, Object... obj) {
        Logger logger = getLogger();
        logger.info(getMsg(msg), obj);
    }

    public static void debug(Object msg) {
        Logger logger = getLogger();
        logger.debug(getMsg(msg));
    }

    public static void debug(Object msg, Object o) {
        Logger logger = getLogger();
        logger.debug(getMsg(msg), o);
    }

    public static void debug(Object msg, Object o1, Object o2) {
        Logger logger = getLogger();
        logger.debug(getMsg(msg), o1, o2);
    }

    public static void debug(Object msg, Object... obj) {
        Logger logger = getLogger();
        logger.debug(getMsg(msg), obj);
    }

    public static void warn(Object msg) {
        Logger logger = getLogger();
        logger.warn(getMsg(msg));
    }

    public static void warn(Object msg, Object o) {
        Logger logger = getLogger();
        logger.warn(getMsg(msg), o);
    }

    public static void warn(Object msg, Object o1, Object o2) {
        Logger logger = getLogger();
        logger.warn(getMsg(msg), o1, o2);
    }

    public static void warn(Object msg, Object... obj) {
        Logger logger = getLogger();
        logger.warn(getMsg(msg), obj);
    }

    public static void error(Object msg) {
        Logger logger = getLogger();
        logger.error(getMsg(msg));
    }

    public static void error(Object msg, Object o) {
        Logger logger = getLogger();
        logger.error(getMsg(msg), o);
    }

    public static void error(Object msg, Object o1, Object o2) {
        Logger logger = getLogger();
        logger.error(getMsg(msg), o1, o2);
    }

    public static void error(Object msg, Object... args) {
        Logger logger = getLogger();
        if (args != null && args.length > 0) {
            if (args[args.length - 1] instanceof Throwable) {
                logger.error(getMsg(msg, (Throwable) args[args.length - 1]), args);
            } else {
                logger.error(getMsg(msg), args);
            }
        } else {
            logger.error(getMsg(msg), args);
        }

    }


    public static String getMsg(Object msg, Throwable ex) {
        String str = "";
        if (msg != null) {
            str = getLogMethod() + "[" + msg.toString() + "]";
        } else {
            str = getLogMethod() + "[null]";
        }
        if (ex != null) {
            str = str + "[" + ex.getMessage() + "]\n";
            StackTraceElement[] stackArray = ex.getStackTrace();
            for (StackTraceElement temp : stackArray) {
                str = str + temp.toString() + "\n";
            }
            Throwable cause = ex.getCause();
            if (cause != null) {
                stackArray = cause.getStackTrace();
                for (StackTraceElement temp : stackArray) {
                    str = str + temp.toString() + "\n";
                }
            }
        }
        return str;
    }

    public static String getMsg(Object msg) {
        return getMsg(msg, (Throwable) null);
    }

    private static String getLogClass() {
        String str = "";
        StackTraceElement[] stack = (new Throwable()).getStackTrace();
        if (stack.length > 3) {
            StackTraceElement ste = stack[3];
            str = ste.getClassName();
        }

        return str;
    }

    private static String getLogMethod() {
        String str = "";
        StackTraceElement[] stack = (new Throwable()).getStackTrace();
        if (stack.length > 4) {
            StackTraceElement ste = stack[4];
            str = "Method[" + ste.getMethodName() + ":" + ste.getLineNumber() + "]";
        }
        return str;
    }

}
