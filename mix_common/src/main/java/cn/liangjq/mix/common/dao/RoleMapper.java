package cn.liangjq.mix.common.dao;

import cn.liangjq.mix.common.entity.Role;

/**
* @Description: 
* @Author: liangjianqiang
* @Date: 2021/03/23
*/
public interface RoleMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String uid);

    /**
     *
     * @mbg.generated
     */
    int insert(Role record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(Role record);

    /**
     *
     * @mbg.generated
     */
    Role selectByPrimaryKey(String uid);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Role record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeyWithBLOBs(Role record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Role record);
}