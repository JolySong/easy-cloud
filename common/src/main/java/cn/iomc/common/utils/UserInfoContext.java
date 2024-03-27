package cn.iomc.common.utils;

import cn.iomc.common.constant.UserInfo;

/**
 * @Author song
 * @Date 2024/3/28 04:33
 * @Version 1.0
 */
public class UserInfoContext {
    private static final ThreadLocal<UserInfo> userThreadLocal = new ThreadLocal<UserInfo>();

    /**
     * 设置用户信息
     * @param userId
     * @param userName
     */
    public static void serUserInfo(String userId, String userName) {
        userThreadLocal.set(new UserInfo(userId, userName));
    }

    /**
     * 获取用户信息
     * @return
     */
    public static UserInfo getUserInfo() {
        return userThreadLocal.get();
    }

    /**
     * 清除当前上下文
     */
    public static void clean() {
        userThreadLocal.remove();
    }
}
