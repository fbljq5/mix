package cn.liangjq.mix.common.dto.menu;

import lombok.Builder;
import lombok.Data;

/**
 * @Description: 菜单分页数据封装
 * @Author: liangjq
 * @Date: 2021/5/29
 */
@Data
@Builder
public class MenuUpdateDTO {

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
     * 显示顺序 order_sort
     */
    private Integer orderSort;

    /**
     * 路由地址 path
     */
    private String path;

    /**
     * 菜单显示状态（0显示 1隐藏） visible
     */
    private Boolean visible;

    /**
     * 菜单状态（0正常 1停用） status
     */
    private Boolean status;

    /**
     * 菜单图标 icon
     */
    private String icon;

    /**
     * 备注 remark
     */
    private String remark;

}
