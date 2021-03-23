package cn.liangjq.mix.common.dao;

import cn.liangjq.mix.common.entity.UserRole;

/**
* @Description: 
* @Author: liangjianqiang
* @Date: 2021/03/23
*/
public interface UserRoleMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(UserRole record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(UserRole record);

    /**
     *
     * @mbg.generated
     */
    UserRole selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(UserRole record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(UserRole record);
}