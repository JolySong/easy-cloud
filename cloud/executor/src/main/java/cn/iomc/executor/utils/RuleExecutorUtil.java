package cn.iomc.executor.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.iomc.common.constant.Constants;
import cn.iomc.common.dto.ValidTaskDTO;
import cn.iomc.common.entity.QcResultDetailsEntity;
import cn.iomc.common.utils.SnowFlakeIdUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @Author song
 * @Date 2024/4/3 22:46
 * @Version 1.0
 */

@Component
public class RuleExecutorUtil {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 主键值默认
     */
    private static final String PRIMARY_KEY_VALUE_DEFAULT = "";

    /**
     * 批量插入明细数据大小
     **/
    @Value("${config.insertSize:1000}")
    private Integer insertSize;

    /**
     * 处理sql的业务日期
     *
     * @param task
     * @param sql
     */
    public static String handleExecTime(ValidTaskDTO task,
                                        String sql) {

        String startTimeStr = DateUtil.formatDateTime(task.getStartTime());
        String endTimeStr = DateUtil.formatDateTime(task.getEndTime());

        return sql.replaceAll(Constants.RULE_EXEC_START_TIME_FIELD, startTimeStr)
                .replaceAll(Constants.RULE_EXEC_END_TIME_FIELD, endTimeStr);
    }


    /**
     * 处理规则的异常信息
     *
     * @param task
     * @param msg
     */
    public static void handleExceptionRule(ValidTaskDTO task,
                                           String msg) {

        String message = exceptionParse(msg);
        task.setExecExceptionMsg(message);
        msg = null != msg && msg.length() > 2000 ? msg.substring(0, 1999) : msg;

        task.setExecExceptionMsgDetail(msg);
    }

    /**
     * 异常解析
     *
     * @param msg
     * @return:
     */
    private static String exceptionParse(final String msg) {

        String msgParse = "其它异常";
        if (null == msg) {
            return msgParse;
        }
        String msgLimit = msg.toLowerCase(Locale.ROOT);
        if (msgLimit.contains("标识符无效") || msgLimit.contains("unknown column")) {
            msgParse = "字段不存在或语法错误";
        } else if (msgLimit.contains("unknown table") || msgLimit.contains("unknown view")) {
            msgParse = "表不存在";
        } else if (msgLimit.contains("无法绑定由多个部分组成的标识符")) {
            msgParse = "WHERE条件错误或其它语法错误";
        }

        return msgParse;
    }

    /**
     * 明细结果生成
     *
     * @param task 当前任务
     * @param addResults 结果集
     * @param sqlResult sql当前行
     * @param state 质控结果 0正常 1异常
     */
    public static void addResult(ValidTaskDTO task,
                                 List<QcResultDetailsEntity> addResults,
                                 Map<String, Object> sqlResult,
                                 Integer state) {

        QcResultDetailsEntity result = new QcResultDetailsEntity();
        BeanUtil.copyProperties(task, result);

        // 生成主键
        result.setBusinessCode(SnowFlakeIdUtil.nextId());

        // 主键字段值处理
        List<String> primaryKeyValueList = new ArrayList<>();
        handleSplitField(task.getPrimaryField(), primaryKeyValueList, sqlResult);
        result.setPrimaryFieldValue(String.join(Constants.STR_COMMA, primaryKeyValueList));

        // 检查字段值处理
        List<String> checkFieldValueList = new ArrayList<>();
        handleSplitField(task.getCheckField(), checkFieldValueList, sqlResult);
        result.setCheckFieldValue(String.join(Constants.STR_COMMA, checkFieldValueList));


        result.setResultState(state);
        addResults.add(result);
    }


    /**
     * 字段值处理
     *
     * @param handleFields 待处理待字段值
     * @param handleFieldValues 处理结果接受list
     * @param sqlResult sql当前行结果
     */
    public static void handleSplitField(String handleFields,
                                        List<String> handleFieldValues,
                                        Map<String, Object> sqlResult) {
        if (StringUtils.isNotBlank(handleFields)) {
            String[] split = handleFields.split(Constants.STR_COMMA);
            for (String checkField : split) {
                if (sqlResult.get(checkField) != null) {
                    handleFieldValues.add(String.valueOf(sqlResult.get(checkField)));
                }
            }
        }
    }

    /**
     * 明细结果处理
     *
     * @param list
     */
    public void handleResult(List<QcResultDetailsEntity> list) {

        int size = list.size();
        for (int i = 0; i < size; ) {
            int end = i + insertSize;
            if (end > size) {
                end = size;
            }
            List<QcResultDetailsEntity> subList = list.subList(i, end);

            // mq异步处理
            String json = JSONObject.toJSONString(subList);
            Message message = MessageBuilder.withBody(json.getBytes())
                    .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                    .setContentEncoding("UTF-8")
                    .setMessageId(SnowFlakeIdUtil.nextId()+"").build();
            rabbitTemplate.convertAndSend(Constants.VALID_TASK_DETAIL_HANDLE_QUEUE, message);

            i = end;
        }
    }
}
