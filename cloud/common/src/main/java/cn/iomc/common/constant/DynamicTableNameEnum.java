package cn.iomc.common.constant;

import lombok.Getter;

/**
 * 动态表名操作枚举类
 *
 * @Author song
 * @Date 2024/4/4 00:23
 * @Version 1.0
 */
@Getter
public enum DynamicTableNameEnum {

    DYNAMIC_TABLE_OPERATION_DEL("suffix", "_history")
    ;

    private final String param;

    private final String value;

    DynamicTableNameEnum(String param,
                         String value) {
        this.param = param;
        this.value = value;
    }

}
