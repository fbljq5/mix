package cn.liangjq.mix.admin.service;

import cn.liangjq.mix.common.dto.menu.MenuListDTO;

import java.util.Collections;
import java.util.List;

/**
 * @author ： liangjq
 * @description ： 菜单服务接口
 * @date ： 2021/5/27
 */
public interface IMenuService {

    /**
     * 获取所有菜单列表
     *
     * @return
     */
    default List<MenuListDTO> listAll() {
        return Collections.emptyList();
    }

    /**
     * 获取所有菜单列表（标记已选中）
     *
     * @param roleId
     * @return
     */
    default List<MenuListDTO> listAllByRoleId(Long roleId) {
        return Collections.emptyList();
    }
}
