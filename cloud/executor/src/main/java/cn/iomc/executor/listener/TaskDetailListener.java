package cn.iomc.executor.listener;

import cn.iomc.common.constant.Constants;
import cn.iomc.common.entity.QcResultDetailsEntity;
import cn.iomc.common.utils.LogUtil;
import cn.iomc.common.utils.RedisUtils;
import cn.iomc.executor.service.RuleExecutor;
import com.alibaba.fastjson.JSON;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 明细结果处理监听
 *
 * @Author song
 * @Date 2024/4/4 21:19
 * @Version 1.0
 */
@Component
public class TaskDetailListener {


    @Autowired
    RedissonClient redissonClient;

    @Autowired
    private RuleExecutor ruleExecutor;

    /**
     * 明细结果监听
     *
     * @param message
     */
    @RabbitHandler
    @RabbitListener(queuesToDeclare = @Queue(value = Constants.VALID_TASK_DETAIL_HANDLE_QUEUE, durable = "true"))
    public void detailResultMessage(Message message) {
        String messageId = message.getMessageProperties().getMessageId();

        Boolean b = RedisUtils.sIsMember(Constants.VALID_TASK_DETAIL_HANDLE_QUEUE, messageId);
        if (b) {
            LogUtil.info("该条消息已被消费 id为：" + messageId);
            return;
        }
        RedisUtils.sAdd(Constants.VALID_TASK_DETAIL_HANDLE_QUEUE, messageId);

        LogUtil.info("接收到的消息id为：" + messageId);
        String messageStr = new String(message.getBody(), StandardCharsets.UTF_8);
        // 根据传入类转换对应接收类型
        List<QcResultDetailsEntity> detailsList =
                JSON.parseArray(messageStr, QcResultDetailsEntity.class);

        LogUtil.info("接收到的明细数据大小：" + detailsList.size());
    }
}
