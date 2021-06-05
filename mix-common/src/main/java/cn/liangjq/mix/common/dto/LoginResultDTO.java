package cn.liangjq.mix.common.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Author: liangjq
 * @Description: 用户登录VO
 * @Date: 2021/4/14
 */
@Data
public class LoginResultDTO {
    /**
     * 登录用户名
     */
    private Long userId;

    /**
     * 登录密码
     */
    private String token;

}
