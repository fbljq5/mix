package cn.liangjq.mix.common.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

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
    @NotEmpty(message = "登录用户名不能为空")
    private String username;

    /**
     * 登录密码
     */
    @NotEmpty(message = "登录密码不能为空")
    private String password;
}
