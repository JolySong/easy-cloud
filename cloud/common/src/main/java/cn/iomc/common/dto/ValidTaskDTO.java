package cn.iomc.common.dto;

import cn.iomc.common.entity.DataSourceEntity;
import cn.iomc.common.entity.ModelRuleNull;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author song
 * @Date 2024/4/3 23:27
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidTaskDTO implements Serializable {

    /**
     * 规则主键
     */
    private Long ruleCode;

    /**
     * 空值规则
     */
    private ModelRuleNull modelRuleNull;

    /**
     * 任务编码
     */
    private Long validCode;

    /**
     * 主任务编码
     */
    private Long validMainCode;

    /**
     * 数据源编码
     */
    private Long dsCode;

    /**
     * 数据源信息
     */
    private DataSourceEntity dataSource;

    /**
     * 质控表
     */
    private Long tableCode;

    /**
     * 评估纬度
     */
    private Long ratingCode;

    /**
     * 质量模型编码
     */
    private Long configCode;

    /**
     * 规则编码
     */
    private String ruleNo;

    /**
     * 执行结果0正在执行1成功2异常3无数据
     */
    private Integer execState;

    /**
     * 异常日志
     */
    private String execExceptionMsg;

    /**
     * 异常日志详情 截断处理 保留1000行
     */
    private String execExceptionMsgDetail;

    /**
     * 规则类型 0空值 1值域
     */
    private Integer ruleType;

    /**
     * 主键字段名,多个逗号隔开
     */
    private String primaryField;

    /**
     * 检查字段名,多个逗号隔开
     */
    private String checkField;

    /**
     * 总数sql
     */
    private String totalSql;

    /**
     * 通过数sql
     */
    private String passSql;

    /**
     * 开始时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Setter(AccessLevel.NONE)
    private Date startTime;
    public Date getCreateTime() {
        return null != startTime ?
                (Date) startTime.clone() : null;
    }
    public void setStartTime(Date startTime) {
        this.startTime =
                null != startTime ?
                        (Date) startTime.clone() : null;
    }

    /**
     * 结束时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Setter(AccessLevel.NONE)
    private Date endTime;

    public Date getEndTime() {
        return null != endTime ?
                (Date) endTime.clone() : null;
    }
    public void setEndTime(Date endTime) {
        this.endTime =
                null != endTime ?
                        (Date) endTime.clone() : null;
    }

}
