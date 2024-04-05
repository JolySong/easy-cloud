package cn.iomc.executor.dynamicDataSource;

/**
 * 数据源切换处理
 *
 * @Author song
 * @Date 2024/4/3 09:25
 * @Version 1.0
 */
public class DataSourceContextHolder {

    /**
     * 这个ThreadLocal  是用来储存当前线程中的数据源的key
     */
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<String>() {
        /**
         * 将 master 数据源的 dsCode作为默认数据源的 key
         */
        @Override
        protected String initialValue() {
            return "master";
        }
    };

    /**
     * 切换数据源前调用 call clear();
     *
     * @param dsCode
     */
    public static void set(String dsCode) {
        CONTEXT_HOLDER.set(dsCode);
    }
    /**
     * 获取数据源
     * @return
     */
    public static String get() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 重置数据源
     */
    public static void clear() {
        CONTEXT_HOLDER.remove();
    }
}
