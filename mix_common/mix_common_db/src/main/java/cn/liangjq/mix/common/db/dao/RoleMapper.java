package cn.liangjq.mix.common.db.dao;


import cn.liangjq.mix.common.db.entity.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper {
    /**
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @mbg.generated
     */
    int insert(Role record);

    /**
     * @mbg.generated
     */
    int insertSelective(Role record);

    /**
     * @mbg.generated
     */
    Role selectByPrimaryKey(Long id);

    /**
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Role record);

    /**
     * @mbg.generated
     */
    int updateByPrimaryKey(Role record);
}