package cn.liangjq.mix.common.dto.user;

import lombok.Data;

import java.util.Date;

/**
 * @author ： liangjq
 * @description ： 用户展示信息
 * @date ： 2021/4/23
 */
@Data
public class UserPageDTO {

    /**
     * 用户ID id
     */
    private Long id;

    /**
     * 用户名称 username
     */
    private String username;

    /**
     * 头像 avatar
     */
    private String avatar;

    /**
     * 邮箱 email
     */
    private String email;

    /**
     * 电话号码 phone
     */
    private String phone;

    /**
     * 帐号状态（1正常 0停用） status
     */
    private Boolean status;

    /**
     * 是否已删除（0正常 1已删除） is_delete
     */
    private Boolean isDelete;

    /**
     * 最后登录时间 login_date
     */
    private String loginDate;

    /**
     * 介绍 remark
     */
    private String remark;

    /**
     * 创建时间 gmt_create
     */
    private String gmtCreate;

    /**
     * 更新时间 gmt_modified
     */
    private String gmtModified;
}
