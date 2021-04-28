package cn.liangjq.mix.common.dto.user;

import lombok.Data;

/**
 * @author ： liangjq
 * @description ： 修改用户密码数据封装
 * @date ： 2021/4/25
 */
@Data
public class UserModifyPwdDTO {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 密码 password
     */
    private String password;

}
