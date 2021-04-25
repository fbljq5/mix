package cn.liangjq.mix.common.dto.user;

import lombok.Data;

/**
 * @author ： liangjq
 * @description ： 用户分配角色DTO
 * @date ： 2021/4/25
 */
@Data
public class UserAssignRolesDTO {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 角色id数组
     */
    private Long[] roleIds;

}
