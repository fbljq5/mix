package cn.liangjq.mix.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* @Description: 
* @Author: liangjianqiang
* @Date: 2021/03/26
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleMenu extends BaseEntity {
    /**
     * 主键ID id
     */
    private Long id;

    /**
     * 角色ID role_id
     */
    private Long roleId;

    /**
     * 菜单ID menu_id
     */
    private Long menuId;

    /**
     * 创建时间 gmt_create
     */
    private Date gmtCreate;

    /**
     * 更新时间 gmt_modified
     */
    private Date gmtModified;
}