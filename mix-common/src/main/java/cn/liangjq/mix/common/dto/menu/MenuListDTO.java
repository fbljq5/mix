package cn.liangjq.mix.common.dto.menu;

import lombok.Builder;
import lombok.Data;

/**
 * @Description: 菜单列表数据封装
 * @Author: liangjianqiang
 * @Date: 2021/5/24
 */
@Data
@Builder
public class MenuListDTO {

    /**
     * 主键id
     */
    protected Long id;

    /**
     * 菜单名称 menu_name
     */
    private String menuName;

    /**
     * 父菜单ID parent_id
     */
    private Long parentId;

    /**
     * 判断角色是否已拥有该菜单
     */
    private Boolean hasMenu;

}
