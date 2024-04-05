package cn.iomc.common.constant;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author song
 * @Date 2024/3/28 04:21
 * @Version 1.0
 */

@Data
public class BasePO implements Serializable {

    /**
     * 是否删除 0正常 1删除
     */
    @TableField(value = "DEL_FLAG", fill = FieldFill.INSERT)
    private Integer delFlag;

    /**
     * 创建人用户ID
     */
    @TableField(value = "CREATE_USER_ID", fill = FieldFill.INSERT)
    private String createUserId;

    /**
     * 创建人用户姓名
     */
    @TableField(value = "CREATE_USER_NAME", fill = FieldFill.INSERT)
    private String createUserName;

    /**
     * 创建时间
     */
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    @Setter(AccessLevel.NONE)
    private Date createTime;
    public Date getCreateTime() {
        return null != updateTime ?
                (Date) updateTime.clone() : null;
    }
    public void setCreateTime(Date updateTime) {
        this.updateTime =
                null != updateTime ?
                        (Date)updateTime.clone() : null;
    }
    /**
     * 最后修改人用户ID
     */
    @TableField(value = "UPDATE_USER_ID", fill = FieldFill.UPDATE)
    private String updateUserId;

    /**
     * 最后修改人用户姓名
     */
    @TableField(value = "UPDATE_USER_NAME", fill = FieldFill.UPDATE)
    private String updateUserName;

    /**
     * 最后修改时间
     */
    @TableField(value = "UPDATE_TIME", fill = FieldFill.UPDATE)
    @Setter(AccessLevel.NONE)
    private Date updateTime;
    public Date getUpdateTime() {
        return null != updateTime ?
                (Date) updateTime.clone() : null;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime =
                null != updateTime ?
                        (Date)updateTime.clone() : null;
    }
}
