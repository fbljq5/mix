package cn.liangjq.mix.admin.dao;

import cn.liangjq.mix.common.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

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
}