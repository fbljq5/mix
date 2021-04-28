package cn.liangjq.mix.common.dto;

import lombok.Data;

/**
 * @Author: liangjq
 * @Description: 用户登录VO
 * @Date: 2021/4/14
 */
@Data
public class LoginVO {

    /**
     * 登录用户名
     */
    private String username;

    /**
     * 登录密码
     */
    private String password;
}
