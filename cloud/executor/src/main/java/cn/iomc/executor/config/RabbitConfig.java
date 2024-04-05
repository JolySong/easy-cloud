package cn.iomc.executor.config;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @Author song
 * @Date 2024/4/3 16:40
 * @Version 1.0
 */
@Configuration
public class RabbitConfig {

    /**
     * 监听消息 改为json格式
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());

        // 确认方式,manual为手动ack.
        // factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        // 每次处理数据数量，提高并发量
        //factory.setPrefetchCount(250);
        // 设置线程数
        //factory.setConcurrentConsumers(30);
        // 最大线程数
        //factory.setMaxConcurrentConsumers(50);
        /* setConnectionFactory：设置spring-amqp的ConnectionFactory。 */
        factory.setConnectionFactory(connectionFactory);

        // 如果设置为1，在消费的时候线程池就会开启多个线程来消费进行，意思就是一个线程只消费一条消息，
        // 这种适于消费时间处理长，处理的流程比较复杂，这种例如文件转换，需要时间
        // 如果是大于1的，看具体设置的值，比如50，那每个线程就会消费50个消息，等到消息满了，
        // 才会开启其他线程来消费，这种适用于高并发的情况，消费时间短，消费量很大，比如发短信
        factory.setConcurrentConsumers(1);
        factory.setPrefetchCount(1);
        //factory.setDefaultRequeueRejected(true);
        // 使用自定义线程池来启动消费者。
        factory.setTaskExecutor(queueHandleThreadPool());

        return factory;
    }

    /**
     * 线程池常态线程数
     */
    @Value("${config.queueHandleThreadPool.corePoolSize:5}")
    private Integer corePoolSize;

    /**
     * 线程池最大线程数
     */
    @Value("${config.queueHandleThreadPool.maximumPoolSize:10}")
    private Integer maximumPoolSize;

    /**
     * 额外线程空状态生存时间
     */
    @Value("${config.queueHandleThreadPool.keepAliveTimeSeconds:10}")
    private Integer keepAliveTimeSeconds;

    /**
     * 阻塞队列。当核心线程都被占用，且阻塞队列已满的情况下，才会开启额外线程。
     */
    @Value("${config.queueHandleThreadPool.capacity:100}")
    private Integer capacity;


    /**
     * 队列处理线程池
     *
     * @return
     */
    @Bean("queueHandleThreadPool")
    @Primary
    public TaskExecutor queueHandleThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        executor.setCorePoolSize(corePoolSize);
        // 设置最大线程数
        executor.setMaxPoolSize(maximumPoolSize);
        // 设置队列容量
        executor.setQueueCapacity(capacity);
        // 设置线程活跃时间（秒）
        executor.setKeepAliveSeconds(keepAliveTimeSeconds);
        // 设置默认线程名称
        executor.setThreadNamePrefix("thread-file-queue");
        // 设置拒绝策略rejection-policy：当pool已经达到max size的时候，丢弃
        // executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        return executor;
    }

}
