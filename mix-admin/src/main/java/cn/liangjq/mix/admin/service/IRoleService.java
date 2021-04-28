package cn.liangjq.mix.admin.service;

import cn.liangjq.mix.common.dto.PageResponse;
import cn.liangjq.mix.common.dto.role.RoleAddDTO;
import cn.liangjq.mix.common.dto.role.RoleAssignMenusDTO;
import cn.liangjq.mix.common.dto.role.RoleListRequest;
import cn.liangjq.mix.common.dto.role.RoleUpdateDTO;
import cn.liangjq.mix.common.entity.Role;
import cn.liangjq.mix.common.result.R;

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
    R<PageResponse> pageRole(RoleListRequest request);

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

}
