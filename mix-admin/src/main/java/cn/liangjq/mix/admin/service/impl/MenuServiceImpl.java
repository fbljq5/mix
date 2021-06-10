package cn.liangjq.mix.admin.service.impl;

import cn.liangjq.mix.admin.dao.MenuMapper;
import cn.liangjq.mix.admin.dao.RoleMenuMapper;
import cn.liangjq.mix.admin.exception.OperationException;
import cn.liangjq.mix.admin.service.IMenuService;
import cn.liangjq.mix.admin.util.PageUtils;
import cn.liangjq.mix.common.dto.PageResponse;
import cn.liangjq.mix.common.dto.menu.*;
import cn.liangjq.mix.common.dto.role.RolePageDTO;
import cn.liangjq.mix.common.entity.Menu;
import cn.liangjq.mix.common.entity.Role;
import cn.liangjq.mix.common.result.R;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
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

    @Override
    public R<List<MenuPageDTO>> pageMenu(MenuSearchDTO searchDTO) {
        List<Menu> menus = menuMapper.selectByMenuSearchDTO(searchDTO);
        if (null == menus) {
            return null;
        }
        List<MenuPageDTO> menuPageDTOList = menus.stream().map(this::toPageDTO).collect(Collectors.toList());
        return R.ok(menuPageDTOList);
    }

    @Override
    public R<String> addMenu(MenuAddDTO menuAddDTO) {
        // 检查菜单名是否存在
        this.checkMenuNameExist(menuAddDTO.getMenuName());
        // 新增菜单
        this.doAddMenu(menuAddDTO);
        return R.ok();
    }

    /**
     * 判断菜单名是否存在
     *
     * @param menuName
     */
    private void checkMenuNameExist(String menuName) {
        Boolean checkResult = menuMapper.checkMenuExistByName(menuName);
        if (checkResult) {
            throw new OperationException("菜单名已存在");
        }
    }

    /**
     * 判断菜单名是否存在,排除指定菜单ID
     *
     * @param menuName
     * @param menuId
     */
    private void checkMenuNameExist(String menuName, Long menuId) {
        Boolean checkResult = menuMapper.checkMenuExistByNameAndId(menuName, menuId);
        if (checkResult) {
            throw new OperationException("菜单名已存在");
        }
    }

    /**
     * 新增菜单
     *
     * @param menuAddDTO
     */
    private void doAddMenu(MenuAddDTO menuAddDTO) {
        Menu menu = this.toMenu(menuAddDTO);
        int res = menuMapper.insertSelective(menu);
        if (res <= 0) {
            throw new OperationException("新增菜单失败");
        }
    }

    /**
     * 菜单新增DTO转换成菜单
     *
     * @param menuAddDTO
     * @return
     */
    private Menu toMenu(MenuAddDTO menuAddDTO) {
        if (null == menuAddDTO) {
            return null;
        }
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuAddDTO, menu);
        return menu;
    }

    @Override
    public R<String> updateMenu(MenuUpdateDTO menuUpdateDTO) {
        Menu menu = this.toMenu(menuUpdateDTO);
        int res = menuMapper.updateByPrimaryKeySelective(menu);
        if (res <= 0) {
            throw new OperationException("更新菜单失败");
        }
        return R.ok();
    }

    /**
     * 菜单更新DTO转换成菜单
     *
     * @param menuUpdateDTO
     * @return
     */
    private Menu toMenu(MenuUpdateDTO menuUpdateDTO) {
        if (null == menuUpdateDTO) {
            return null;
        }
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuUpdateDTO, menu);
        return menu;
    }

    @Override
    public R<String> deleteMenu(Long menuId) {
        // 如果包含子菜单，则一起删除
        Integer count = menuMapper.cycleDeleteMenu(menuId);
        if (count <= 0) {
            throw new OperationException("删除菜单失败");
        }
        return R.ok();
    }

    /**
     * 菜单数据转换为菜单DTO
     *
     * @param menu
     * @return
     */
    private MenuPageDTO toPageDTO(Menu menu) {
        if (null == menu) {
            return null;
        }
        MenuPageDTO menuPageDTO = MenuPageDTO.builder().build();
        BeanUtils.copyProperties(menu, menuPageDTO);
        menuPageDTO.setGmtCreate(menu.getGmtCreate() == null ? "" : DateFormatUtils.format(menu.getGmtCreate(), "yyyy-MM-dd HH:mm:ss"));
        return menuPageDTO;
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
