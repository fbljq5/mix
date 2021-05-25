package cn.liangjq.mix.admin.service;

import cn.liangjq.mix.common.dto.PageResponse;
import cn.liangjq.mix.common.dto.role.*;
import cn.liangjq.mix.common.entity.Role;
import cn.liangjq.mix.common.result.R;

import java.util.List;

/**
 * @author ： liangjq
 * @description ： 角色服务
 * @date ： 2021/4/22
 */
public interface IRoleService {

    /**
     * 获取角色信息
     *
     * @param roleId
     * @return
     */
    Role findRoleByRoleId(Long roleId);

    /**
     * 获取角色信息
     *
     * @param roleId
     * @return
     */
    R<RoleUpdateDTO> findRole(Long roleId);

    /**
     * 分页查询角色列表
     *
     * @param request
     * @return
     */
    R<PageResponse<RoleVO>> pageRole(RolePageRequest request);

    /**
     * 新增角色信息
     *
     * @param roleAddDTO
     * @return
     */
    R<String> addRole(RoleAddDTO roleAddDTO);

    /**
     * 删除角色信息
     *
     * @param roleId
     * @return
     */
    R<String> deleteRole(Long roleId);

    /**
     * 更新角色信息
     *
     * @param updateDTO
     * @return
     */
    R<String> updateRole(RoleUpdateDTO updateDTO);

    /**
     * 分配菜单
     *
     * @param roleAssignMenusDTO
     * @return
     */
    R<String> assignMenus(RoleAssignMenusDTO roleAssignMenusDTO);

    /**
     * 获得角色列表，如果有userid则需要标记是否拥有该角色
     *
     * @param userId
     * @return
     */
    R<List<RoleListDTO>> listRole(Long userId);
}
