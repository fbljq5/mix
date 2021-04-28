package cn.liangjq.mix.common.dto.user;

import cn.liangjq.mix.common.dto.PageBaseRequest;
import lombok.Data;

/**
 * @author ： liangjq
 * @description ： 用户分页查询条件封装
 * @date ： 2021/4/23
 */
@Data
public class UserListRequest extends PageBaseRequest {

    /**
     * 查询用户名
     */
    private String username;
}
