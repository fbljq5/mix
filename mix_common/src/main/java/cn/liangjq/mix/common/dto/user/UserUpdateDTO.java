package cn.liangjq.mix.common.dto.user;

import lombok.Data;

/**
 * @author ： liangjq
 * @description ：  更新用户数据封装
 * @date ： 2021/4/25
 */
@Data
public class UserUpdateDTO {
    
    /**
     * 主键id
     */
    private Long id;

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
     * 备注 remark
     */
    private String remark;

}
