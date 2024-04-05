package cn.iomc.common.entity;

import cn.iomc.common.constant.BasePO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 模型-规则配置表-空值
 * @TableName t_model_rule_null
 */
@TableName(value ="t_model_rule_null")
@Data
public class ModelRuleNull extends BasePO {
    /**
     * ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 业务主键
     */
    @TableField(value = "RULE_CODE")
    private Long ruleCode;

    /**
     * 质量模型编码
     */
    @TableField(value = "CONFIG_CODE")
    private Long configCode;

    /**
     * 规则类型 0空值 1值域
     */
    @TableField(value = "RULE_TYPE")
    private Integer ruleType;

    /**
     * 检查字段，多个用逗号隔开
     */
    @TableField(value = "CHECK_FIELD")
    private String checkField;

    /**
     * 不能同时为空标识 默认0每个字段都不能为空
     */
    @TableField(value = "NOT_NULL_STATE")
    private Integer notNullState;

    /**
     * 空格是否算正常数据 0 否 1 是
     */
    @TableField(value = "CHECK_SPACE")
    private Integer checkSpace;

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
        ModelRuleNull other = (ModelRuleNull) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRuleCode() == null ? other.getRuleCode() == null : this.getRuleCode().equals(other.getRuleCode()))
            && (this.getConfigCode() == null ? other.getConfigCode() == null : this.getConfigCode().equals(other.getConfigCode()))
            && (this.getRuleType() == null ? other.getRuleType() == null : this.getRuleType().equals(other.getRuleType()))
            && (this.getCheckField() == null ? other.getCheckField() == null : this.getCheckField().equals(other.getCheckField()))
            && (this.getNotNullState() == null ? other.getNotNullState() == null : this.getNotNullState().equals(other.getNotNullState()))
            && (this.getCheckSpace() == null ? other.getCheckSpace() == null : this.getCheckSpace().equals(other.getCheckSpace()))
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
        result = prime * result + ((getConfigCode() == null) ? 0 : getConfigCode().hashCode());
        result = prime * result + ((getRuleType() == null) ? 0 : getRuleType().hashCode());
        result = prime * result + ((getCheckField() == null) ? 0 : getCheckField().hashCode());
        result = prime * result + ((getNotNullState() == null) ? 0 : getNotNullState().hashCode());
        result = prime * result + ((getCheckSpace() == null) ? 0 : getCheckSpace().hashCode());
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
        sb.append(", configCode=").append(configCode);
        sb.append(", ruleType=").append(ruleType);
        sb.append(", checkField=").append(checkField);
        sb.append(", notNullState=").append(notNullState);
        sb.append(", checkSpace=").append(checkSpace);
        sb.append("]");
        return sb.toString();
    }
}