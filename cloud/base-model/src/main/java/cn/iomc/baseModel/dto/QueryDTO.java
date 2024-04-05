package cn.iomc.baseModel.dto;

import cn.iomc.common.constant.CommonQueryDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author song
 * @Date 2024/3/28 04:14
 * @Version 1.0
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class QueryDTO extends CommonQueryDTO {

    /**
     * 数据库类型 0mysql 1oracle 2sqlserver
     */
    private Integer dbType;

    /**
     * 批量操作主键
     */
    private String codes;

}
