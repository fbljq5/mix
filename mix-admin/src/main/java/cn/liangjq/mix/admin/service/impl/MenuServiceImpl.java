package cn.liangjq.mix.admin.service.impl;

import cn.liangjq.mix.admin.dao.MenuMapper;
import cn.liangjq.mix.admin.dao.RoleMenuMapper;
import cn.liangjq.mix.admin.service.IMenuService;
import cn.liangjq.mix.common.dto.menu.MenuListDTO;
import cn.liangjq.mix.common.entity.Menu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ： liangjq
 * @description ： 菜单服务实现
 * @date ： 2021/5/27
 */
@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements IMenuService {

    private final MenuMapper menuMapper;
    private final RoleMenuMapper roleMenuMapper;

    @Override
    public List<MenuListDTO> listAll() {
        List<Menu> menuList = menuMapper.selectAll();
        return menuList.stream().map(this::constructMenuListDTO).collect(Collectors.toList());
    }

    @Override
    public List<MenuListDTO> listAllByRoleId(Long roleId) {
        List<Menu> menuList = menuMapper.selectAll();
        // 获取关联菜单列表
        List<Long> menuIdList = roleMenuMapper.selectMenuIdByRoleId(roleId);

        return menuList.stream().map(menu -> {
            return this.constructMenuListDTO(menu, menuIdList);
        }).collect(Collectors.toList());
    }

    /**
     * 构建菜单展示封装类
     *
     * @param menu
     * @return
     */
    private MenuListDTO constructMenuListDTO(Menu menu) {
        if (null == menu) {
            return null;
        }
        MenuListDTO menuListDTO = MenuListDTO.builder().id(menu.getId())
                .menuName(menu.getMenuName())
                .hasMenu(false)
                .parentId(menu.getParentId())
                .build();
        return menuListDTO;
    }

    /**
     * 构建菜单展示封装类
     *
     * @param menu
     * @return
     */
    private MenuListDTO constructMenuListDTO(Menu menu, List<Long> menuIdList) {
        if (null == menu) {
            return null;
        }
        boolean hasMenu = false;
        if (!CollectionUtils.isEmpty(menuIdList)) {
            hasMenu = menuIdList.contains(menu.getId());
        }
        MenuListDTO menuListDTO = MenuListDTO.builder().id(menu.getId())
                .menuName(menu.getMenuName())
                .hasMenu(hasMenu)
                .parentId(menu.getParentId())
                .build();
        return menuListDTO;
    }
}
