package cn.liangjq.mix.admin.dao;


import cn.liangjq.mix.common.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: 角色DAO
 * @Author: liangjq
 * @Date: 2021/4/25
 */
@Mapper
public interface RoleMapper {

    /**
     * 插入角色信息
     *
     * @param record
     * @return
     */
    int insert(Role record);

    /**
     * 插入角色信息
     *
     * @param record
     * @return
     */
    int insertSelective(Role record);

    /**
     * 查找角色信息
     *
     * @param id
     * @return
     */
    Role selectByPrimaryKey(Long id);

    /**
     * 更新角色信息
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Role record);

    /**
     * 更新角色信息
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(Role record);

    /**
     * 通过用户id获得角色信息
     *
     * @param userId
     * @return
     */
    List<Role> getRoleListByUserId(Long userId);
}