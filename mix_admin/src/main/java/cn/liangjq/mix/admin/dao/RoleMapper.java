package cn.liangjq.mix.admin.dao;


import cn.liangjq.mix.common.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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


    List<Role> getRoleListByUserId(Long userId);
}