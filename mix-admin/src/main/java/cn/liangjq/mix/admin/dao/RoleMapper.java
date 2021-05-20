package cn.liangjq.mix.admin.dao;


import cn.liangjq.mix.common.dto.role.RolePageRequest;
import cn.liangjq.mix.common.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: 角色DAO
 * @Author: liangjq
 * @Date: 2021/4/25
 */
@Mapper
public interface RoleMapper {

    /**
     * 插入角色信息
     *
     * @param record
     * @return
     */
    int insert(Role record);

    /**
     * 插入角色信息
     *
     * @param record
     * @return
     */
    int insertSelective(Role record);

    /**
     * 查找角色信息
     *
     * @param id
     * @return
     */
    Role selectByPrimaryKey(Long id);

    /**
     * 更新角色信息
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Role record);

    /**
     * 更新角色信息
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(Role record);

    /**
     * 通过用户id获得角色信息
     *
     * @param userId
     * @return
     */
    List<Role> getRoleListByUserId(Long userId);

    /**
     * 查看指定id数组里面存在的id数量
     *
     * @param roleIds
     * @return
     */
    int countId(Long[] roleIds);

    /**
     * 按条件查询角色列表
     *
     * @param request
     * @return
     */
    List<Role> selectByRoleListRequest(RolePageRequest request);

    /**
     * 判断角色名称或角色编号是否被占用
     *
     * @param roleName
     * @param roleCode
     * @return
     */
    boolean checkRoleExistByRoleNameAndRoleCode(String roleName, String roleCode);

    /**
     * 判断除本身外是否有其他角色占用角色名称和角色编号
     *
     * @param roleId
     * @param roleCode
     * @param roleName
     * @return
     */
    boolean checkRoleExistByIdAndCodeAndName(Long roleId, String roleCode, String roleName);

    /**
     * 获得所有角色
     *
     * @return
     */
    List<Role> list();
}