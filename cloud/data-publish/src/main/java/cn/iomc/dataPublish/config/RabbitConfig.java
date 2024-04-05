package cn.iomc.dataPublish.config;

import cn.iomc.common.utils.LogUtil;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author song
 * @Date 2024/4/3 13:02
 * @Version 1.0
 */
@Configuration
public class RabbitConfig {


    // Spring-Boot的依赖注入功能
    // 注入连接工厂：Spring-Boot的依赖注入：启动时，RabbitProperties会自动读取RabbitMQ的配置信息。
    // 若配置参数命名符合其规范，会注入到spring的bean容器中，创建生成连接工厂
    @Autowired
    private CachingConnectionFactory connectionFactory;


    /**
     * 重新实例化 RabbitTemplate 操作类
     * @return
     */
    @Bean
    public RabbitTemplate rabbitTemplate(){
        // 生产者确认消息是否发送过去了
        connectionFactory.setPublisherConfirms(true);

        // 生产者发送消息后，返回反馈消息
        connectionFactory.setPublisherReturns(true);

        // 构建rabbitTemlate操作模板
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);

        // 设置消息类型为json
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());

        // 生产者发送消息后，如果发送成功，则打印“消息发送成功”的日志信息
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) ->
                LogUtil.info("消息发送成功:" +
                "correlationData(" + correlationData + ")," +
                "ack(" + ack + ")," +
                "cause(" + cause + ")"));

        // 生产者发送消息后，若发送失败，则输出“消息发送失败”的日志信息
        rabbitTemplate.setReturnCallback(
                (message, replyCode, replyText, exchange, routingKey) ->
                        LogUtil.error("消息丢失:" +
                "exchange(" + exchange + ")," +
                "route(" + routingKey + ")," +
                "replyCode(" + replyCode + ")," +
                "replyText(" + replyText + ")," +
                "message:" + message));

        return rabbitTemplate;
    }


    /**
     * 监听消息 改为json格式
     * @param connectionFactory
     * @return
     */
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }

}