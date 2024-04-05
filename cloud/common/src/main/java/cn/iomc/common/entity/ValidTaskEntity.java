package cn.iomc.common.entity;

import cn.iomc.common.constant.BasePO;
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 执行器-子任务表
 * @TableName t_valid_task
 */
@TableName(value ="t_valid_task")
@Data
public class ValidTaskEntity extends BasePO {
    /**
     * ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 规则主键
     */
    @TableField(value = "RULE_CODE")
    private Long ruleCode;

    /**
     * 任务编码
     */
    @TableField(value = "VALID_CODE")
    private Long validCode;

    /**
     * 主任务编码
     */
    @TableField(value = "VALID_MAIN_CODE")
    private Long validMainCode;

    /**
     * 数据源编码
     */
    @TableField(value = "DS_CODE")
    private Long dsCode;

    /**
     * 质控表
     */
    @TableField(value = "TABLE_CODE")
    private Long tableCode;

    /**
     * 评估纬度
     */
    @TableField(value = "RATING_CODE")
    private Long ratingCode;

    /**
     * 质量模型编码
     */
    @TableField(value = "CONFIG_CODE")
    private Long configCode;

    /**
     * 规则编码
     */
    @TableField(value = "RULE_NO")
    private String ruleNo;

    /**
     * 执行结果0正在执行1成功2异常3无数据
     */
    @TableField(value = "EXEC_STATE")
    private Integer execState;

    /**
     * 异常日志
     */
    @TableField(value = "EXEC_EXCEPTION_MSG")
    private String execExceptionMsg;

    /**
     * 异常日志详情 截断处理 保留1000行
     */
    @TableField(value = "EXEC_EXCEPTION_MSG_DETAIL")
    private String execExceptionMsgDetail;

    /**
     * 规则类型 0空值 1值域
     */
    @TableField(value = "RULE_TYPE")
    private Integer ruleType;

    /**
     * 主键字段名,多个逗号隔开
     */
    @TableField(value = "PRIMARY_FIELD")
    private String primaryField;

    /**
     * 检查字段名,多个逗号隔开
     */
    @TableField(value = "CHECK_FIELD")
    private String checkField;

    /**
     * 总数sql
     */
    @TableField(value = "TOTAL_SQL")
    private String totalSql;

    /**
     * 通过数sql
     */
    @TableField(value = "PASS_SQL")
    private String passSql;

    /**
     * 开始时间
     */
    @TableField(value = "START_TIME")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 结束时间
     */
    @TableField(value = "END_TIME")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ValidTaskEntity other = (ValidTaskEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRuleCode() == null ? other.getRuleCode() == null : this.getRuleCode().equals(other.getRuleCode()))
            && (this.getValidCode() == null ? other.getValidCode() == null : this.getValidCode().equals(other.getValidCode()))
            && (this.getValidMainCode() == null ? other.getValidMainCode() == null : this.getValidMainCode().equals(other.getValidMainCode()))
            && (this.getDsCode() == null ? other.getDsCode() == null : this.getDsCode().equals(other.getDsCode()))
            && (this.getTableCode() == null ? other.getTableCode() == null : this.getTableCode().equals(other.getTableCode()))
            && (this.getRatingCode() == null ? other.getRatingCode() == null : this.getRatingCode().equals(other.getRatingCode()))
            && (this.getConfigCode() == null ? other.getConfigCode() == null : this.getConfigCode().equals(other.getConfigCode()))
            && (this.getRuleNo() == null ? other.getRuleNo() == null : this.getRuleNo().equals(other.getRuleNo()))
            && (this.getExecState() == null ? other.getExecState() == null : this.getExecState().equals(other.getExecState()))
            && (this.getRuleType() == null ? other.getRuleType() == null : this.getRuleType().equals(other.getRuleType()))
            && (this.getPrimaryField() == null ? other.getPrimaryField() == null : this.getPrimaryField().equals(other.getPrimaryField()))
            && (this.getTotalSql() == null ? other.getTotalSql() == null : this.getTotalSql().equals(other.getTotalSql()))
            && (this.getPassSql() == null ? other.getPassSql() == null : this.getPassSql().equals(other.getPassSql()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getCreateUserName() == null ? other.getCreateUserName() == null : this.getCreateUserName().equals(other.getCreateUserName()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateUserId() == null ? other.getUpdateUserId() == null : this.getUpdateUserId().equals(other.getUpdateUserId()))
            && (this.getUpdateUserName() == null ? other.getUpdateUserName() == null : this.getUpdateUserName().equals(other.getUpdateUserName()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRuleCode() == null) ? 0 : getRuleCode().hashCode());
        result = prime * result + ((getValidCode() == null) ? 0 : getValidCode().hashCode());
        result = prime * result + ((getValidMainCode() == null) ? 0 : getValidMainCode().hashCode());
        result = prime * result + ((getDsCode() == null) ? 0 : getDsCode().hashCode());
        result = prime * result + ((getTableCode() == null) ? 0 : getTableCode().hashCode());
        result = prime * result + ((getRatingCode() == null) ? 0 : getRatingCode().hashCode());
        result = prime * result + ((getConfigCode() == null) ? 0 : getConfigCode().hashCode());
        result = prime * result + ((getRuleNo() == null) ? 0 : getRuleNo().hashCode());
        result = prime * result + ((getExecState() == null) ? 0 : getExecState().hashCode());
        result = prime * result + ((getRuleType() == null) ? 0 : getRuleType().hashCode());
        result = prime * result + ((getPrimaryField() == null) ? 0 : getPrimaryField().hashCode());
        result = prime * result + ((getTotalSql() == null) ? 0 : getTotalSql().hashCode());
        result = prime * result + ((getPassSql() == null) ? 0 : getPassSql().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getCreateUserName() == null) ? 0 : getCreateUserName().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUserId() == null) ? 0 : getUpdateUserId().hashCode());
        result = prime * result + ((getUpdateUserName() == null) ? 0 : getUpdateUserName().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", ruleCode=").append(ruleCode);
        sb.append(", validCode=").append(validCode);
        sb.append(", validMainCode=").append(validMainCode);
        sb.append(", dsCode=").append(dsCode);
        sb.append(", tableCode=").append(tableCode);
        sb.append(", ratingCode=").append(ratingCode);
        sb.append(", configCode=").append(configCode);
        sb.append(", ruleNo=").append(ruleNo);
        sb.append(", execState=").append(execState);
        sb.append(", ruleType=").append(ruleType);
        sb.append(", resultField=").append(primaryField);
        sb.append(", totalSql=").append(totalSql);
        sb.append(", passSql=").append(passSql);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append("]");
        return sb.toString();
    }
}