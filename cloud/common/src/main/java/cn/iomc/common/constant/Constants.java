package cn.iomc.common.constant;

/**
 *  const
 *
 * @Author song
 * @Date 2024/3/28 03:51
 * @Version 1.0
 */
public class Constants {

    /**
     * 默认数据源标识
     */
    public static final String MASTER = "master";

    /**
     * 校验任务队列名
     */
    public static final String VALID_TASK_QUEUE = "executor.validTask";

    /**
     * 明细结果处理队列名
     */
    public static final String VALID_TASK_DETAIL_HANDLE_QUEUE = "executor.validTask.detail";

    /**
     * 数据源-set
     */
    public static final String DATA_SOURCE = "executor.datasource";

    /**
     * 空值类型
     */
    public static final Integer RULE_TYPE_NULL = 0;


    /**
     * 业务日期开始时间占位符
     */
    public static final String RULE_EXEC_START_TIME_FIELD = "replace_param_start_time";

    /**
     * 业务日期结束时间占位符
     */
    public static final String RULE_EXEC_END_TIME_FIELD = "replace_param_end_time";

    /**
     * 执行中
     */
    public static final Integer RULE_EXEC_RUNNING = 0;

    /**
     * 执行成功
     */
    public static final Integer RULE_EXEC_SUCCESS = 1;

    /**
     * 执行异常
     */
    public static final Integer RULE_EXEC_EXCEPTION = 2;

    /**
     * 执行无数据
     */
    public static final Integer RULE_EXEC_NO_DATA = 3;

    /**
     * 字符串英文逗号
     */
    public static final String STR_COMMA = ",";


    /**
     * 动态表名操作-删除
     */
    public static final Integer DYNAMIC_TABLE_OPERATION_DEL = 1;
    /**
     * 动态表名操作-分表
     */
    public static final Integer DYNAMIC_TABLE_OPERATION_SUB_TABLE = 2;

    public static final String DYNAMIC_TABLE_OPERATION_DEL_SUFFIX = "_history";

    public static final String DYNAMIC_TABLE_OPERATION_SUFFIX_PARAM = "suffix";


    public static final Integer NUMBER = 3;

}
