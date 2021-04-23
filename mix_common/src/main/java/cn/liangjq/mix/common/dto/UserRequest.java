package cn.liangjq.mix.common.dto;

import lombok.Data;

/**
 * @author ： liangjq
 * @description ： 用户分页查询条件封装
 * @date ： 2021/4/23
 */
@Data
public class UserRequest extends PageBaseRequest {

    /**
     * 查询用户名
     */
    private String username;
}
