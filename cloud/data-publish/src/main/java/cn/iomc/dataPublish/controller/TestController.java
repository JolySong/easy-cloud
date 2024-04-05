package cn.iomc.dataPublish.controller;

import cn.hutool.core.date.DateTime;
import cn.iomc.common.constant.Constants;
import cn.iomc.common.dto.ValidTaskDTO;
import cn.iomc.common.entity.DataSourceEntity;
import cn.iomc.common.entity.ModelRuleNull;
import cn.iomc.common.result.Result;
import cn.iomc.common.utils.SnowFlakeIdUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author song
 * @Date 2024/4/3 13:07
 * @Version 1.0
 */


@RestController
public class TestController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sent")
    public Result sent(@RequestParam("msg") String msg) {

        ValidTaskDTO validTask = new ValidTaskDTO();
        validTask.setRuleType(Constants.RULE_TYPE_NULL);
        validTask.setDsCode(530457199486529536L);

        DataSourceEntity dataSourceEntity = new DataSourceEntity();
        dataSourceEntity.setDsCode(530457199486529536L);
        dataSourceEntity.setDbType(0);
        dataSourceEntity.setDbUrl("jdbc:mysql://10.0.0.10:3306/kb-dms?useSSL=true&useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai");
        dataSourceEntity.setDbDrive("com.mysql.cj.jdbc.Driver");
        dataSourceEntity.setDbUsername("root");
        dataSourceEntity.setDbPassword("mysql.iomc");
        validTask.setDataSource(dataSourceEntity);

        ModelRuleNull modelRuleNull = new ModelRuleNull();
        modelRuleNull.setCheckSpace(0);
        modelRuleNull.setNotNullState(0);
        validTask.setModelRuleNull(modelRuleNull);

        validTask.setPrimaryField("id");
        validTask.setCheckField("type");
        validTask.setTotalSql(
                "SELECT id, type, count, create_time "
                        + "FROM st_sql_exe_result_count_timeline "
                        + "WHERE create_time >= 'replace_param_start_time' "
                        + "AND create_time < 'replace_param_end_time' ");

        validTask.setStartTime(new DateTime("2024-03-27"));
        validTask.setEndTime(new DateTime("2024-03-28"));

        Message message = MessageBuilder.withBody(JSONObject.toJSONBytes(validTask))
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setContentEncoding("UTF-8")
                .setMessageId(SnowFlakeIdUtil.nextId()+"").build();

        rabbitTemplate.convertAndSend(Constants.VALID_TASK_QUEUE, message);

        return Result.OK();
    }
}
