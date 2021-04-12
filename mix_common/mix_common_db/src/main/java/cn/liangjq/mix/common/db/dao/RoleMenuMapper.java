package cn.liangjq.mix.common.db.dao;


import cn.liangjq.mix.common.db.entity.RoleMenu;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMenuMapper {
    /**
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @mbg.generated
     */
    int insert(RoleMenu record);

    /**
     * @mbg.generated
     */
    int insertSelective(RoleMenu record);

    /**
     * @mbg.generated
     */
    RoleMenu selectByPrimaryKey(Long id);

    /**
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(RoleMenu record);

    /**
     * @mbg.generated
     */
    int updateByPrimaryKey(RoleMenu record);
}