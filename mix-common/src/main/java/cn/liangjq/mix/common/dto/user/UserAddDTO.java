package cn.liangjq.mix.common.dto.user;

import lombok.Data;

import java.util.Date;

/**
 * @author ： liangjq
 * @description ： 新增用户数据封装
 * @date ： 2021/4/25
 */
@Data
public class UserAddDTO {
    /**
     * 用户名称 user_name
     */
    private String userName;

    /**
     * 邮箱 email
     */
    private String email;

    /**
     * 电话号码 phone
     */
    private String phone;

    /**
     * 密码 password
     */
    private String password;

    /**
     * 备注 remark
     */
    private String remark;

}
