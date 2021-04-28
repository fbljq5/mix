package cn.liangjq.mix.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * @author ： liangjq
 * @description ： MD5加密工具
 * @date ： 2021/4/23
 */
public class MD5Utils {

    /**
     * 对字符串进行MD5加密
     *
     * @param sourceStr
     * @return
     */
    public static String getMd5(String sourceStr) {
        if (StringUtils.isBlank(sourceStr)) {
            return null;
        }
        return DigestUtils.md5DigestAsHex(sourceStr.getBytes(StandardCharsets.UTF_8));
    }

}
