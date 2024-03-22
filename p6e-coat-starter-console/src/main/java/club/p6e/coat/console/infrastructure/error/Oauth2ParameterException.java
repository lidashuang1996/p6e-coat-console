package club.p6e.coat.console.infrastructure.error;

/**
 * 自定义异常
 * 类型不匹配异常
 *
 * @author lidashuang
 * @version 1.0
 */
public class Oauth2ParameterException extends CustomException {

    /**
     * 默认的代码
     */
    public static final int DEFAULT_CODE = 14000;

    /**
     * 默认的简述
     */
    private static final String DEFAULT_SKETCH = "TYPE_MISMATCH_EXCEPTION";

    /**
     * 类型不匹配异常
     *
     * @param sc      源 class
     * @param error   异常对象
     * @param content 描述内容
     */
    public Oauth2ParameterException(Class<?> sc, String error, String content) {
        super(sc, Oauth2ParameterException.class, error, DEFAULT_CODE, DEFAULT_SKETCH, content);
    }

    /**
     * 类型不匹配异常
     *
     * @param sc        源 class
     * @param throwable 异常对象
     * @param content   描述内容
     */
    public Oauth2ParameterException(Class<?> sc, Throwable throwable, String content) {
        super(sc, Oauth2ParameterException.class, throwable, DEFAULT_CODE, DEFAULT_SKETCH, content);
    }

    /**
     * 类型不匹配异常
     *
     * @param sc      源 class
     * @param error   异常内容
     * @param code    代码
     * @param sketch  简述
     * @param content 描述内容
     */
    public Oauth2ParameterException(Class<?> sc, String error, int code, String sketch, String content) {
        super(sc, Oauth2ParameterException.class, error, code, sketch, content);
    }

    /**
     * 类型不匹配异常
     *
     * @param sc        源 class
     * @param throwable 异常对象
     * @param code      代码
     * @param sketch    简述
     * @param content   描述内容
     */
    public Oauth2ParameterException(Class<?> sc, Throwable throwable, int code, String sketch, String content) {
        super(sc, Oauth2ParameterException.class, throwable, code, sketch, content);
    }
}
