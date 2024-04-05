package cn.iomc.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author song
 * @Date 2024/3/28 15:07
 * @Version 1.0
 */
public class CommonUtil {

    /**
     * 逗号
     */
    public static final String COMMA_STRING = ",";
    /**
     * 冒号
     */
    public static final String COLON_STRING = ":";
    /**
     * 杠
     * */
    public static final String BAR_STRING = "-";

    /**
     * 根据逗号切割字符串
     * @param str
     * @return
     */
    public static List<String> stringToListByComma(String str) {

        if (StringUtils.isBlank(str)) {
            throw new RuntimeException("str cannot be null");
        }

        List<String> ret = new ArrayList<>();
        for (String s : str.split(COMMA_STRING)) {
            ret.add(s);
        }

        return ret;
    }
}
