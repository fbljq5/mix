package cn.liangjq.mix.common.dao;

import cn.liangjq.mix.common.entity.RoleMenu;

/**
* @Description: 
* @Author: liangjianqiang
* @Date: 2021/03/23
*/
public interface RoleMenuMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(RoleMenu record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(RoleMenu record);

    /**
     *
     * @mbg.generated
     */
    RoleMenu selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(RoleMenu record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(RoleMenu record);
}