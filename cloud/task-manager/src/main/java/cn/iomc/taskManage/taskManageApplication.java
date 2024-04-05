package cn.iomc.taskManage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author song
 * @Date 2024/3/27 14:55
 * @Version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class taskManageApplication {
    public static void main(String[] args) {
        SpringApplication.run(taskManageApplication.class, args);
        System.out.println("------------->>> taskManage starting...");
    }


}
