package cn.liangjq.mix.common.dto.user;

import cn.liangjq.mix.common.dto.role.RoleInfoDTO;
import lombok.Data;

/**
 * @Description: 用户信息封装
 * @Author: liangjq
 * @Date: 2021/6/5
 */
@Data
public class UserInfoDTO {

    /**
     * 用户ID id
     */
    private Long id;

    /**
     * 用户名称 username
     */
    private String username;

    /**
     * 真实名称 real_name
     */
    private String realName;

    /**
     * 头像 avatar
     */
    private String avatar;

    /**
     * 介绍 remark
     */
    private String remark;

    /**
     * 角色集合 roles
     */
    private RoleInfoDTO[] roles;
}
