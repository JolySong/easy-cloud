package cn.iomc.taskManage.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author song
 * @Date 2024/3/27 15:50
 * @Version 1.0
 */

@RestController
public class TaskManageController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
