package cn.liangjq.mix.admin.dao;


import cn.liangjq.mix.common.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: 用户角色关联DAO
 * @Author: liangjq
 * @Date: 2021/4/25
 */
@Mapper
public interface UserRoleMapper {
    /**
     * 删除用户就是关联记录
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 新增用户角色关联记录
     *
     * @param record
     * @return
     */
    int insert(UserRole record);

    /**
     * 新增用户角色关联记录
     *
     * @param record
     * @return
     */
    int insertSelective(UserRole record);

    /**
     * 查找用户角色关联记录
     *
     * @param id
     * @return
     */
    UserRole selectByPrimaryKey(Long id);

    /**
     * 更新用户角色关联信息
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(UserRole record);

    /**
     * 更新用户角色关联信息
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(UserRole record);

    /**
     * 查找用户关联角色列表
     *
     * @param userId
     * @return
     */
    List<UserRole> selectByUserId(Long userId);
}