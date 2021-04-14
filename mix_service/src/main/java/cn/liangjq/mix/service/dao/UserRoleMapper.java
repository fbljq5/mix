package cn.liangjq.mix.service.dao;


import cn.liangjq.mix.common.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRoleMapper {
    /**
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @mbg.generated
     */
    int insert(UserRole record);

    /**
     * @mbg.generated
     */
    int insertSelective(UserRole record);

    /**
     * @mbg.generated
     */
    UserRole selectByPrimaryKey(Long id);

    /**
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(UserRole record);

    /**
     * @mbg.generated
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