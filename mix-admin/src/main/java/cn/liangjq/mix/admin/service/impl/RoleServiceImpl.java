package cn.liangjq.mix.admin.service.impl;

import cn.liangjq.mix.admin.dao.MenuMapper;
import cn.liangjq.mix.admin.dao.RoleMapper;
import cn.liangjq.mix.admin.dao.RoleMenuMapper;
import cn.liangjq.mix.admin.exception.OperationException;
import cn.liangjq.mix.admin.service.IRoleService;
import cn.liangjq.mix.admin.util.PageUtils;
import cn.liangjq.mix.common.dto.PageResponse;
import cn.liangjq.mix.common.dto.role.*;
import cn.liangjq.mix.common.entity.Role;
import cn.liangjq.mix.common.result.R;
import com.alibaba.nacos.common.utils.CollectionUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ： liangjq
 * @description ： 角色服务实现类
 * @date ： 2021/4/22
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {

    private final RoleMapper roleMapper;
    private final MenuMapper menuMapper;
    private final RoleMenuMapper roleMenuMapper;

    @Override
    public Role findRoleByRoleId(Long roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public R<RoleUpdateDTO> findRole(Long roleId) {
        Role role = this.checkRoleExist(roleId);
        return R.ok(toRoleUpdateDTO(role));
    }

    /**
     * 判断角色是否存在
     *
     * @param roleId
     */
    private Role checkRoleExist(Long roleId) {
        Role role = roleMapper.selectByPrimaryKey(roleId);
        if (null == role) {
            throw new OperationException("角色不存在");
        }
        return role;
    }

    @Override
    public R<PageResponse<RoleVO>> pageRole(RolePageRequest request) {
        PageResponse<Role> pageResponse = PageUtils.setPageResult(request, () ->
                roleMapper.selectByRoleListRequest(request));
        if (null == pageResponse) {
            return null;
        }
        List<RoleVO> roleVOList = pageResponse.getList().stream().map(this::toRoleVO).collect(Collectors.toList());
        PageResponse<RoleVO> res = new PageResponse<>();
        BeanUtils.copyProperties(pageResponse, res);
        res.setList(roleVOList);
        return R.ok(res);
    }

    @Override
    @Transactional
    public R<String> addRole(RoleAddDTO roleAddDTO) {
        // 判断角色是否存在
        this.checkRoleNameAndCodeExist(roleAddDTO.getRoleName(), roleAddDTO.getRoleCode());
        this.doAddRole(roleAddDTO);
        return R.ok();
    }

    /**
     * 检查角色名称、角色编号是否存在
     *
     * @param roleName
     * @param roleCode
     */
    private void checkRoleNameAndCodeExist(String roleName, String roleCode) {
        // 判断角色是否存在
        boolean checkResult = roleMapper.checkRoleExistByRoleNameAndRoleCode(roleName
                , roleCode);
        if (checkResult) {
            throw new OperationException("角色已存在");
        }
    }

    /**
     * 新增角色
     *
     * @param roleAddDTO
     */
    private void doAddRole(RoleAddDTO roleAddDTO) {
        Role role = this.toRole(roleAddDTO);
        role.add();
        int insert = roleMapper.insert(role);
        if (insert <= 0) {
            throw new OperationException("新增失败");
        }
    }

    @Override
    public R<String> deleteRole(Long roleId) {
        // 检查角色是否合法
        this.checkRoleExist(roleId);
        // 删除角色菜单关联
        this.deleteRoleMenu(roleId);
        // 删除角色信息
        this.doDeleteRole(roleId);
        return R.ok();
    }

    /**
     * 删除角色菜单关联
     *
     * @param roleId
     */
    private void deleteRoleMenu(Long roleId) {
        // 删除角色菜单关联记录
        roleMenuMapper.deleteRoleMenuAssgin(roleId);
    }

    /**
     * 新增角色菜单关联
     *
     * @param roleId
     * @param menuIds
     */
    private void addRoleMenu(Long roleId, Long[] menuIds) {
        roleMenuMapper.assginRoleMenu(roleId, menuIds);
    }

    /**
     * 删除角色信息
     *
     * @param roleId
     */
    private void doDeleteRole(Long roleId) {
        int res = roleMapper.deleteById(roleId);
        if (res <= 0) {
            throw new OperationException("删除失败");
        }
    }

    @Override
    public R<String> updateRole(RoleUpdateDTO updateDTO) {
        // 判断角色是否存在
        Role role = this.checkRoleExist(updateDTO.getId());
        // 判断角色名称、编号是否重复
        this.checkRoleNameAndCodeExist(updateDTO.getId(), updateDTO.getRoleName(), updateDTO.getRoleCode());
        // 更新角色
        this.doUpdateRole(updateDTO, role);
        return R.ok();
    }

    /**
     * 判断角色名称编码是否已被占用
     *
     * @param roleId
     * @param roleName
     * @param roleCode
     */
    private void checkRoleNameAndCodeExist(Long roleId, String roleName, String roleCode) {
        //判断修改后的角色是否重复（除了本身）
        boolean checkResult = roleMapper.checkRoleExistByIdAndCodeAndName(roleId, roleName
                , roleCode);
        if (checkResult) {
            throw new OperationException("该角色名称或编码已被占用");
        }
    }

    /**
     * 更新角色
     *
     * @param updateDTO
     * @param role
     */
    private void doUpdateRole(RoleUpdateDTO updateDTO, Role role) {
        BeanUtils.copyProperties(updateDTO, role);
        role.update();
        int i = roleMapper.updateByPrimaryKeySelective(role);
        if (i <= 0) {
            throw new OperationException("更新失败");
        }
    }

    @Override
    public R<String> assignMenus(RoleAssignMenusDTO roleAssignMenusDTO) {

        // 判断角色是否存在
        this.checkRoleExist(roleAssignMenusDTO.getId());
        //判断菜单是否都存在
        this.checkAllMenuExist(roleAssignMenusDTO.getMenuIds());
        // 删除已有角色菜单的关联
        this.deleteRoleMenu(roleAssignMenusDTO.getId());
        // 新增角色菜单关联
        this.addRoleMenu(roleAssignMenusDTO.getId(), roleAssignMenusDTO.getMenuIds());
        return R.ok("分配成功");
    }

    /**
     * 判断菜单是否都存在
     *
     * @param menuIds
     */
    private void checkAllMenuExist(Long[] menuIds) {
        int countIdFromDB = menuMapper.countId(menuIds);
        if (countIdFromDB != menuIds.length) {
            throw new OperationException("部分菜单不存在,分配失败");
        }
    }


    @Override
    public R<List<RoleListDTO>> listRole(Long userId) {
        List<Role> roleList = roleMapper.list();
        if (null != userId) {
            // 直接返回列表
            List<Role> roleListByUser = roleMapper.getRoleListByUserId(userId);
            if (CollectionUtils.isNotEmpty(roleListByUser)) {
                List<Long> selectedIdList = roleListByUser.stream().map(Role::getId).collect(Collectors.toList());
                return R.ok(this.transfer(roleList, selectedIdList));
            }
        }
        return R.ok(this.transfer(roleList, null));
    }

    @Override
    public R<String> switchStatus(RoleSwitchStatusDTO roleSwitchStatusDTO) {
        // 判断就是是否存在
        this.checkRoleExist(roleSwitchStatusDTO.getId());
        this.doSwitchStatus(roleSwitchStatusDTO.getId(), roleSwitchStatusDTO.getStatus());
        return R.ok();
    }

    /**
     * 更新角色状态
     *
     * @param roleId
     * @param status
     */
    private void doSwitchStatus(Long roleId, Boolean status) {
        Role role = Role.builder().status(status).build();
        role.setId(roleId);
        int result = roleMapper.updateByPrimaryKeySelective(role);
        if (result <= 0) {
            throw new OperationException("角色状态更新失败");
        }
    }

    /**
     * 角色数据批量转换
     *
     * @param roleList
     * @param hasRoleIdList
     * @return
     */
    private List<RoleListDTO> transfer(List<Role> roleList, List<Long> hasRoleIdList) {

        if (CollectionUtils.isEmpty(roleList)) {
            return null;
        }
        List<RoleListDTO> res = roleList.stream().map(role -> {
            return constructRoleListDTO(role, hasRoleIdList);
        }).collect(Collectors.toList());

        return res;
    }

    /**
     * 单个角色数据转换
     *
     * @param role
     * @param hasRoleIdList
     * @return
     */
    private RoleListDTO constructRoleListDTO(Role role, List<Long> hasRoleIdList) {
        if (role == null) {
            return null;
        }
        return RoleListDTO.builder()
                .id(role.getId())
                .roleName(role.getRoleName())
                .hasRole(hasRoleIdList == null ? null : hasRoleIdList.contains(role.getId()))
                .build();
    }

    /**
     * 角色转换为更新页面所需数据DTO
     *
     * @param role
     * @return
     */
    private RoleUpdateDTO toRoleUpdateDTO(Role role) {
        if (null == role) {
            return null;
        }
        RoleUpdateDTO roleUpdateDTO = new RoleUpdateDTO();
        BeanUtils.copyProperties(role, roleUpdateDTO);
        return roleUpdateDTO;
    }

    /**
     * 角色信息转换成roleVO信息
     *
     * @param role
     * @return
     */
    private RoleVO toRoleVO(Role role) {
        RoleVO roleVO = new RoleVO();
        BeanUtils.copyProperties(role, roleVO);
        roleVO.setGmtCreate(null == role.getGmtCreate() ? null : DateFormatUtils.format(role.getGmtCreate(), "yyyy-MM-dd HH:mm:ss"));
        roleVO.setGmtModified(null == role.getGmtModified() ? null : DateFormatUtils.format(role.getGmtModified(), "yyyy-MM-dd HH:mm:ss"));
        return roleVO;
    }

    /**
     * 数据转换，DTO 转成user
     *
     * @param roleAddDTO
     * @return
     */
    private Role toRole(RoleAddDTO roleAddDTO) {
        Role role = new Role();
        BeanUtils.copyProperties(roleAddDTO, role);
        return role;
    }
}
