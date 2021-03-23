package cn.liangjq.mix.common.dao;

import cn.liangjq.mix.common.entity.Menu;

/**
* @Description: 
* @Author: liangjianqiang
* @Date: 2021/03/23
*/
public interface MenuMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(Menu record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(Menu record);

    /**
     *
     * @mbg.generated
     */
    Menu selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Menu record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Menu record);
}