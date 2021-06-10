package cn.liangjq.mix.admin.service;

import cn.liangjq.mix.common.dto.PageResponse;
import cn.liangjq.mix.common.dto.menu.*;
import cn.liangjq.mix.common.result.R;

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

    /**
     * 分页查询菜单信息
     *
     * @param searchDTO
     * @return
     */
    R<List<MenuPageDTO>> pageMenu(MenuSearchDTO searchDTO);

    /**
     * 新增菜单
     *
     * @param menuAddDTO
     * @return
     */
    R<String> addMenu(MenuAddDTO menuAddDTO);

    /**
     * 更新菜单
     *
     * @param menuUpdateDTO
     * @return
     */
    R<String> updateMenu(MenuUpdateDTO menuUpdateDTO);

    /**
     * 删除菜单
     *
     * @param menuId
     * @return
     */
    R<String> deleteMenu(Long menuId);
}
