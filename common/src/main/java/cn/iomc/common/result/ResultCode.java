package cn.iomc.common.result;

/**
 *
 *
 * @Author song
 * @Date 2024/3/28 03:54
 * @Version 1.0
 */
public enum ResultCode implements CustomizeResultCode {

    /* 成功 */
    SUCCESS(200, "成功"),
    /*错误*/
    ERROR(400,"错误失败"),

    /* 默认失败 */
    COMMON_FAIL(999, "失败"),

    /* 参数错误：1000～1999 */
    PARAM_NOT_VALID(1001, "参数无效"),
    PARAM_IS_BLANK(1002, "参数为空"),

    /* 用户错误 */
    USER_NOT_LOGIN(2001, "用户未登录"),
    USER_ACCOUNT_EXPIRED(2002, "账号已过期"),
    USER_CREDENTIALS_ERROR(2003, "密码错误"),
    /*运行时异常*/
    ARITHMETIC_EXCEPTION(9001,"算数异常");
    ;
    private final Integer code;
    private final String message;

    ResultCode(Integer code,String message){
        this.code=code;
        this.message=message;
    }
    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
