package cn.liangjq.mix.common.dto.role;

import lombok.Builder;
import lombok.Data;

/**
 * @Description: 角色列表封装
 * @Author: liangjianqiang
 * @Date: 2021/5/21
 */
@Data
@Builder
public class RoleListDTO {

    /**
     * 角色id
     */
    private Long id;
    /**
     * 角色名称 role_name
     */
    private String roleName;

    /**
     * 判断用户是否已拥有该角色
     */
    private Boolean hasRole;

}
