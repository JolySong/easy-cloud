package cn.iomc.common.result;

/**
 * 公共返回接口
 *
 * @Author song
 * @Date 2024/3/28 03:53
 * @Version 1.0
 */
public interface CustomizeResultCode {

    /**
     * 获取错误码
     * @return 错误状态码
     */
    Integer getCode();

    /**
     * 获取错误信息
     * @return 错误信息
     */
    String getMessage();
}
