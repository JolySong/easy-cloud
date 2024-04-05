package cn.iomc.common.entity;

import cn.iomc.common.constant.BasePO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 数据源表
 * @TableName t_data_source
 */
@TableName(value ="t_data_source")
@Data
public class DataSourceEntity extends BasePO {

    /**
     * ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 数据源编码
     */
    @TableField(value = "DS_CODE")
    private Long dsCode;

    /**
     * 数据源名称
     */
    @TableField(value = "NAME")
    @NotBlank(message = "数据源名称不能为空")
    private String name;

    /**
     * 描述
     */
    @TableField(value = "REMARK")
    private String remark;

    /**
     * 数据库类型0mysql 1oracle 2sqlserver 3flink
     */
    @TableField(value = "DB_TYPE")
    @NotNull(message = "数据源类型不能为空")
    private Integer dbType;

    /**
     * 数据库驱动
     */
    @TableField(value = "DB_DRIVE")
    private String dbDrive;

    /**
     * 数据库URL
     */
    @TableField(value = "DB_URL")
    @NotBlank(message = "数据库URL不能为空")
    private String dbUrl;

    /**
     * 用户名
     */
    @TableField(value = "DB_USERNAME")
    @NotBlank(message = "用户名不能为空")
    private String dbUsername;

    /**
     * 密码
     */
    @TableField(value = "DB_PASSWORD")
    @NotBlank(message = "密码不能为空")
    private String dbPassword;


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
        DataSourceEntity other = (DataSourceEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getDsCode() == null ? other.getDsCode() == null : this.getDsCode().equals(other.getDsCode()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getDbType() == null ? other.getDbType() == null : this.getDbType().equals(other.getDbType()))
            && (this.getDbDrive() == null ? other.getDbDrive() == null : this.getDbDrive().equals(other.getDbDrive()))
            && (this.getDbUrl() == null ? other.getDbUrl() == null : this.getDbUrl().equals(other.getDbUrl()))
            && (this.getDbUsername() == null ? other.getDbUsername() == null : this.getDbUsername().equals(other.getDbUsername()))
            && (this.getDbPassword() == null ? other.getDbPassword() == null : this.getDbPassword().equals(other.getDbPassword()))
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
        result = prime * result + ((getDsCode() == null) ? 0 : getDsCode().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getDbType() == null) ? 0 : getDbType().hashCode());
        result = prime * result + ((getDbDrive() == null) ? 0 : getDbDrive().hashCode());
        result = prime * result + ((getDbUrl() == null) ? 0 : getDbUrl().hashCode());
        result = prime * result + ((getDbUsername() == null) ? 0 : getDbUsername().hashCode());
        result = prime * result + ((getDbPassword() == null) ? 0 : getDbPassword().hashCode());
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
        sb.append(", dsCode=").append(dsCode);
        sb.append(", name=").append(name);
        sb.append(", remark=").append(remark);
        sb.append(", dbType=").append(dbType);
        sb.append(", dbDrive=").append(dbDrive);
        sb.append(", dbUrl=").append(dbUrl);
        sb.append(", dbUsername=").append(dbUsername);
        sb.append(", dbPassword=").append(dbPassword);
        sb.append("]");
        return sb.toString();
    }
}