package cn.liangjq.mix.admin.dao;

import cn.liangjq.mix.common.dto.menu.MenuSearchDTO;
import cn.liangjq.mix.common.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: 菜单DAO
 * @Author: liangjq
 * @Date: 2021/4/25
 */
@Mapper
public interface MenuMapper {

    /**
     * 新增菜单信息
     *
     * @param record
     * @return
     */
    int insert(Menu record);

    /**
     * 新增菜单信息
     *
     * @param record
     * @return
     */
    int insertSelective(Menu record);

    /**
     * 查找菜单信息
     *
     * @param id
     * @return
     */
    Menu selectByPrimaryKey(Long id);

    /**
     * 更新菜单信息
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Menu record);

    /**
     * 更新菜单信息
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(Menu record);

    /**
     * 查看指定id数组里面存在的id数量
     *
     * @param menuIds
     * @return
     */
    int countId(Long[] menuIds);

    /**
     * 获取所有菜单信息
     *
     * @return
     */
    List<Menu> selectAll();

    /**
     * 根据查询条件查询菜单信息
     *
     * @param searchDTO
     * @return
     */
    List<Menu> selectByMenuSearchDTO(MenuSearchDTO searchDTO);


    /**
     * 判断菜单名称是否存在
     *
     * @param menuName
     * @return
     */
    Boolean checkMenuExistByName(String menuName);

    /**
     * 判断菜单名称除指定菜单外是否存在
     *
     * @param menuName
     * @param menuId
     * @return
     */
    Boolean checkMenuExistByNameAndId(String menuName, Long menuId);

    /**
     * 删除菜单（包括子菜单）
     *
     * @param menuId
     * @return
     */
    Integer cycleDeleteMenu(Long menuId);
}