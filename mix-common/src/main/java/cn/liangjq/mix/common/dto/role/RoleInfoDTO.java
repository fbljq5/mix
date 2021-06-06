package cn.liangjq.mix.common.dto.role;

import lombok.Builder;
import lombok.Data;

/**
 * @author ： liangjianqiang
 * @description ： 角色信息封装
 * @date ： 2021/6/5
 */
@Data
@Builder
public class RoleInfoDTO {

    /**
     * 角色ID id
     */
    private Long id;

    /**
     * 角色名称 roleName
     */
    private String roleName;

    /**
     * 角色编号 roleCode
     */
    private String roleCode;
}
