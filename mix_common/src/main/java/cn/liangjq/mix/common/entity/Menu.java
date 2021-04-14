package cn.liangjq.mix.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description: 菜单
 * @Author: liangjq
 * @Date: 2021/03/26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Menu extends BaseEntity {
    /**
     * 菜单ID id
     */
    private Long id;

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
     * 组件路径 component
     */
    private String component;

    /**
     * 菜单显示状态（0显示 1隐藏） visible
     */
    private Byte visible;

    /**
     * 菜单状态（0正常 1停用） status
     */
    private Byte status;

    /**
     * 菜单权限标识 perms
     */
    private String perms;

    /**
     * 菜单图标 icon
     */
    private String icon;

    /**
     * 备注 remark
     */
    private String remark;

    /**
     * 创建时间 gmt_create
     */
    private Date gmtCreate;

    /**
     * 更新时间 gmt_modified
     */
    private Date gmtModified;
}