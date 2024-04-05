package cn.iomc.common.entity;

import cn.iomc.common.constant.BasePO;
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.Date;

/**
 * 明细表
 *
 * @author song
 * @TableName t_qc_result_details
 */
@TableName(value ="t_qc_result_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QcResultDetailsEntity extends BasePO {

    /**
     * 业务主键
     */
    @TableField("BUSINESS_CODE")
    private Long businessCode;

    /** 主任务编码 */
    @TableField(value = "VALID_MAIN_TASK_CODE")
    private Long validMainTaskCode;

    /**
     * 质量模型编码
     */
    @TableField(value = "CONFIG_CODE")
    private Long configCode;

    /**
     * 质量规则ID
     */
    @TableField(value = "RULE_CODE")
    private Long ruleCode;
    /**
     * 评分维度
     */
    @TableField(value = "RATING_CODE")
    private  Long ratingCode;

    /**
     * 数据源编码
     */
    @TableField(value = "DS_CODE")
    private Long dsCode;

    /**
     * 表业务主键
     */
    @TableField(value = "TABLE_CODE")
    private Long tableCode;

    /**
     * 字典类型
     */
    @TableField(value = "RULE_TYPE")
    private Integer ruleType;

    /**
     * 主键名(多个用头号隔开)
     */
    @TableField(value = "PRIMARY_FIELD")
    private String primaryField;

    /**
     * 主键值(多个用头号隔开)
     */
    @TableField(value = "PRIMARY_FIELD_VALUE")
    private String primaryFieldValue;

    /**
     * 检查字段名(多个用头号隔开)
     */
    @TableField(value = "CHECK_FIELD")
    private String checkField;

    /**
     * 检查字段值(多个用头号隔开)
     */
    @TableField(value = "CHECK_FIELD_VALUE")
    private String checkFieldValue;

    /**
     * 比对表条目数
     */
    @TableField(value = "SOURCE_NUMS")
    private Integer sourceNums;
    /**
     * 质控表条目数
     */
    @TableField(value = "TARGET_NUMS")
    private Integer targetNums;

    /**
     * 开始时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "START_TIME")
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
    @TableField(value = "END_TIME")
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

    /**
     * 结果状态 0正常 1异常
     */
    @TableField(value = "RESULT_STATE")
    private Integer resultState;
}