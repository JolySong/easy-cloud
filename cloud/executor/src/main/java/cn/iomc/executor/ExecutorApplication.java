package cn.iomc.executor;

import cn.iomc.common.utils.SnowFlakeIdUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author song
 * @Date 2024/4/3 09:12
 * @Version 1.0
 */

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan({"cn.iomc.common.mapper", "cn.iomc.executor.mapper"})
@ComponentScan({"cn.iomc.common", "cn.iomc.executor"})
@EnableTransactionManagement
public class ExecutorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExecutorApplication.class, args);
        System.out.println("------------->>> executor starting...");
    }

    @Bean
    public SnowFlakeIdUtil propertyConfigurer() {
        return new SnowFlakeIdUtil(10);
    }
}
