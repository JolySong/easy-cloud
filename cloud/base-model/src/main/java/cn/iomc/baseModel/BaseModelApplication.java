package cn.iomc.baseModel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author song
 * @Date 2024/3/28 03:07
 * @Version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("cn.iomc.baseModel.mapper")
@ComponentScan({"cn.iomc.common", "cn.iomc.baseModel"})
@EnableTransactionManagement
public class BaseModelApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseModelApplication.class, args);
        System.out.println("baseModel starting...");
    }
}
