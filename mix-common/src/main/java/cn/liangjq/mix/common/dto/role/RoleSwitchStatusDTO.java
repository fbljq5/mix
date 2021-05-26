package cn.liangjq.mix.common.dto.role;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author ： liangjq
 * @description ：  更新角色数据封装
 * @date ： 2021/4/25
 */
@Data
public class RoleSwitchStatusDTO {

    /**
     * 主键id
     */
    @NotNull(message = "角色ID不能为空")
    private Long id;

    /**
     * 角色状态（0正常 1停用） status
     */
    @NotNull(message = "角色状态不能为空")
    private Boolean status;

}
