package cn.liangjq.mix.common.dto.role;

import lombok.Data;

/**
 * @author ： liangjq
 * @description ： 角色分配菜单DTO
 * @date ： 2021/4/25
 */
@Data
public class RoleAssignMenusDTO {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 菜单ID数组
     */
    private Long[] menuIds;

}
