package cn.iomc.common.constant;

import lombok.Data;

/**
 * @Author song
 * @Date 2024/3/28 04:13
 * @Version 1.0
 */
@Data
public class CommonQueryDTO {

    private Long pageNumber = 1L;

    private Long pageSize = 10L;

    private String keyword;
}
