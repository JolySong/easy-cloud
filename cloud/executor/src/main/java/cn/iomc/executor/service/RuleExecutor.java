package cn.iomc.executor.service;

import cn.iomc.common.dto.ValidTaskDTO;
import org.springframework.stereotype.Component;

/**
 * @Author song
 * @Date 2024/4/3 18:16
 * @Version 1.0
 */
@Component
public interface RuleExecutor {


    /**
     * 执行
     *
     * @param task
     */
    void exec(ValidTaskDTO task);

    /**
     * 获取任务类型
     *
     */
    int getTaskType();
}
