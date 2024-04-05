package cn.iomc.baseModel.entity;

import cn.iomc.common.constant.BasePO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 模型表
 * @TableName t_model
 */
@TableName(value ="t_model")
@Data
public class ModelEntity extends BasePO {
    /**
     * ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 业务主键
     */
    @TableField(value = "CONFIG_CODE")
    private Long configCode;

    /**
     * 相同模型唯一编码
     */
    @TableField(value = "MAIN_CONFIG_CODE")
    private Long mainConfigCode;

    /**
     * 模型名称
     */
    @TableField(value = "MODEL_NAME")
    @NotBlank(message = "模型名称不能为空")
    private String modelName;

    /**
     * 版本号
     */
    @TableField(value = "VERSION_NUM")
    private String versionNum;

    /**
     * 发布时间
     */
    @TableField(value = "RELEASE_TIME")
    @Setter(AccessLevel.NONE)
    private Date releaseTime;
    public Date getReleaseTime(){
        return null != releaseTime ?
                (Date) releaseTime.clone() : null;
    }
    public void setReleaseTime(Date udCreateTime){
        this.releaseTime =
                null != udCreateTime ?
                        (Date)udCreateTime.clone() : null;
    }

    /**
     * 状态 0：未发布、1：发布
     */
    @TableField(value = "STATE")
    private Integer state;

    /**
     * 描述
     */
    @TableField(value = "DESCRIPTION")
    private String description;

    /**
     * 发布描述
     */
    @TableField(value = "PUBLISH_DESCRIPTION")
    private String publishDescription;

    /**
     * 发布人
     */
    @TableField(value = "PUBLISH_PEOPLE")
    private String publishPeople;

    /**
     * 排序字段
     */
    @TableField(value = "SORT")
    private Integer sort;

    /**
     * 模型质控库列表
     */
    @TableField(exist = false)
    @Setter(AccessLevel.NONE)
    @NotNull(message = "模型质控库不能为空")
    private List<ModelExtendEntity> modelExtendEntities;
    public List<ModelExtendEntity> getModelExtendEntities() {
        return null != this.modelExtendEntities ? new ArrayList<>(this.modelExtendEntities) : new ArrayList<>();
    }

    public void setModelExtendEntities(List<ModelExtendEntity> modelExtendEntities) {
        this.modelExtendEntities = null != modelExtendEntities ? new ArrayList<>(modelExtendEntities) : new ArrayList<>();
    }

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
        ModelEntity other = (ModelEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getConfigCode() == null ? other.getConfigCode() == null : this.getConfigCode().equals(other.getConfigCode()))
            && (this.getMainConfigCode() == null ? other.getMainConfigCode() == null : this.getMainConfigCode().equals(other.getMainConfigCode()))
            && (this.getModelName() == null ? other.getModelName() == null : this.getModelName().equals(other.getModelName()))
            && (this.getVersionNum() == null ? other.getVersionNum() == null : this.getVersionNum().equals(other.getVersionNum()))
            && (this.getReleaseTime() == null ? other.getReleaseTime() == null : this.getReleaseTime().equals(other.getReleaseTime()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getPublishDescription() == null ? other.getPublishDescription() == null : this.getPublishDescription().equals(other.getPublishDescription()))
            && (this.getPublishPeople() == null ? other.getPublishPeople() == null : this.getPublishPeople().equals(other.getPublishPeople()))
            && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()))
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
        result = prime * result + ((getConfigCode() == null) ? 0 : getConfigCode().hashCode());
        result = prime * result + ((getMainConfigCode() == null) ? 0 : getMainConfigCode().hashCode());
        result = prime * result + ((getModelName() == null) ? 0 : getModelName().hashCode());
        result = prime * result + ((getVersionNum() == null) ? 0 : getVersionNum().hashCode());
        result = prime * result + ((getReleaseTime() == null) ? 0 : getReleaseTime().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getPublishDescription() == null) ? 0 : getPublishDescription().hashCode());
        result = prime * result + ((getPublishPeople() == null) ? 0 : getPublishPeople().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
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
        sb.append(", configCode=").append(configCode);
        sb.append(", mainConfigCode=").append(mainConfigCode);
        sb.append(", modelName=").append(modelName);
        sb.append(", versionNum=").append(versionNum);
        sb.append(", releaseTime=").append(releaseTime);
        sb.append(", state=").append(state);
        sb.append(", description=").append(description);
        sb.append(", publishDescription=").append(publishDescription);
        sb.append(", publishPeople=").append(publishPeople);
        sb.append(", sort=").append(sort);
        sb.append("]");
        return sb.toString();
    }
}