package cn.iomc.common.utils;

import java.util.Map;

/**
 * @Author song
 * @Date 2024/3/28 15:39
 * @Version 1.0
 */
public class RequestDataHelper {
    /**
     * 请求参数存取
     */
    private static final ThreadLocal<Map<String, Object>> REQUEST_DATA = new ThreadLocal<>();

    /**
     * 设置请求参数
     *
     * @param requestData 请求参数 MAP 对象
     */
    public static void setRequestData(Map<String, Object> requestData) {
        REQUEST_DATA.set(requestData);
    }


    /**
     * 获取请求参数
     *
     * @return 请求参数 MAP 对象
     */
    public static Map<String, Object> getRequestData() {
        return REQUEST_DATA.get();
    }

    /**
     * 清空thread-local
     */
    public static void clean() {
        REQUEST_DATA.remove();
    }
}
