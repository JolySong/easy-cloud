package cn.iomc.dataPublish;

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
 * @Date 2024/4/3 12:51
 * @Version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan({"cn.iomc.common.mapper", "cn.iomc.dataPublish.mapper"})
@ComponentScan({"cn.iomc.common", "cn.iomc.dataPublish"})
@EnableTransactionManagement
public class DataPublishApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataPublishApplication.class, args);
        System.out.println("------------->>> DataPublishApplication starting...");
    }

    @Bean
    public SnowFlakeIdUtil propertyConfigurer() {
        return new SnowFlakeIdUtil(10);
    }
}
