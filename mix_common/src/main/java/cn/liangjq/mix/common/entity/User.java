package cn.liangjq.mix.common.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @Description: 
* @Author: liangjianqiang
* @Date: 2021/03/23
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
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
     * 帐号状态（0正常 1停用） status
     */
    private Byte status;

    /**
     * 是否已删除（0正常 1已删除） is_delete
     */
    private Byte isDelete;

    /**
     * 最后登录时间 login_date
     */
    private Date loginDate;

    /**
     * 备注 remark
     */
    private String remark;
}