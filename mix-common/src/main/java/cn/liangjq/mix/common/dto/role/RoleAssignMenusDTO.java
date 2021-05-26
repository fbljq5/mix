package cn.liangjq.mix.common.dto.role;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @NotNull(message = "角色ID不能为空")
    private Long id;

    /**
     * 菜单ID数组
     */
    @NotNull(message = "菜单ID数组不能为空")
    private Long[] menuIds;

}
