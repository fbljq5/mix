package cn.liangjq.mix.admin.dao;


import cn.liangjq.mix.common.entity.RoleMenu;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 角色菜单关联DAO
 * @Author: liangjq
 * @Date: 2021/4/25
 */
@Mapper
public interface RoleMenuMapper {

    /**
     * 删除角色菜单关联记录
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入角色菜单关联记录
     *
     * @param record
     * @return
     */
    int insert(RoleMenu record);


    /**
     * 插入角色菜单关联记录
     *
     * @param record
     * @return
     */
    int insertSelective(RoleMenu record);

    /**
     * 查找角色菜单关联信息
     *
     * @param id
     * @return
     */
    RoleMenu selectByPrimaryKey(Long id);

    /**
     * 更新角色菜单关联记录
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(RoleMenu record);

    /**
     * 更新角色菜单关联记录
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(RoleMenu record);
}