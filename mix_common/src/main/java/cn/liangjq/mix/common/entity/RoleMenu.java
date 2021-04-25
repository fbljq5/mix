package cn.liangjq.mix.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description: 角色-菜单关联
 * @Author: liangjq
 * @Date: 2021/03/26
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