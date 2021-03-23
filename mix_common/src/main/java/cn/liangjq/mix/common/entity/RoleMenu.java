package cn.liangjq.mix.common.entity;

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
public class RoleMenu extends BaseEntity {
    /**
     * 角色ID role_id
     */
    private Long roleId;

    /**
     * 菜单ID menu_id
     */
    private Long menuId;
}