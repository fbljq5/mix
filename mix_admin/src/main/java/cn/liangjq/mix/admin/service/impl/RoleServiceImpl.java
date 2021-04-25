package cn.liangjq.mix.admin.service.impl;

import cn.liangjq.mix.admin.dao.MenuMapper;
import cn.liangjq.mix.admin.dao.RoleMapper;
import cn.liangjq.mix.admin.dao.RoleMenuMapper;
import cn.liangjq.mix.admin.service.IRoleService;
import cn.liangjq.mix.admin.util.PageUtils;
import cn.liangjq.mix.common.dto.PageResponse;
import cn.liangjq.mix.common.dto.role.*;
import cn.liangjq.mix.common.entity.Role;
import cn.liangjq.mix.common.result.R;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        Role role = roleMapper.selectByPrimaryKey(roleId);
        if (null == role) {
            return R.fail("角色不存在");
        }
        return R.ok(toRoleUpdateDTO(role));
    }

    @Override
    public R<PageResponse> pageRole(RoleListRequest request) {
        PageResponse pageResponse = PageUtils.setPageResult(request, () ->
                roleMapper.selectByRoleListRequest(request)
                        .stream().map(this::toRoleVO)
                        .collect(Collectors.toList()));

        return R.ok(pageResponse);
    }

    @Override
    public R<String> addRole(RoleAddDTO roleAddDTO) {
        if (roleAddDTO == null || StringUtils.isBlank(roleAddDTO.getRoleName())
                || StringUtils.isBlank(roleAddDTO.getRoleCode())) {
            return R.fail("数据有误");
        }
        // 判断角色是否存在
        boolean checkResult = roleMapper.checkRoleExistByRoleNameAndRoleCode(roleAddDTO.getRoleName()
                , roleAddDTO.getRoleCode());
        if (checkResult) {
            return R.fail("角色已存在");
        }
        Role role = this.toRole(roleAddDTO);
        role.setStatus(true);
        role.setIsDelete(false);
        role.setGmtCreate(new Date());
        int insert = roleMapper.insert(role);
        if (insert > 0) {
            return R.ok("新增成功");
        }
        return R.fail("新增失败");
    }

    @Override
    public R<String> deleteRole(Long roleId) {
        if (null == roleId) {
            return R.fail("id无效");
        }
        // 判断角色是否存在
        Role role = roleMapper.selectByPrimaryKey(roleId);
        if (role == null) {
            return R.fail("角色不存在");
        }
        role.setStatus(false);
        role.setIsDelete(true);
        role.setGmtModified(new Date());
        int result = roleMapper.updateByPrimaryKeySelective(role);
        if (result > 0) {
            return R.ok("删除成功");
        }
        return R.fail("删除失败");
    }

    @Override
    public R<String> updateRole(RoleUpdateDTO updateDTO) {
        if (null == updateDTO || updateDTO.getId() == null) {
            return R.fail("数据不完整");
        }
        //判断修改后的角色是否重复（除了本身）
        boolean checkResult = roleMapper.checkRoleExistByIdAndCodeAndName(updateDTO.getId(), updateDTO.getRoleCode()
                , updateDTO.getRoleName());
        if (checkResult) {
            return R.fail("该角色已被占用");
        }

        //先通过id查找并判断角色是否存在
        Role role = roleMapper.selectByPrimaryKey(updateDTO.getId());
        if (null == role) {
            return R.fail("角色不存在");
        }
        // 更新角色
        BeanUtils.copyProperties(updateDTO, role);
        role.setGmtModified(new Date());
        int i = roleMapper.updateByPrimaryKeySelective(role);
        if (i > 0) {
            return R.ok("更新成功");
        }
        return R.fail("更新失败");
    }

    @Override
    public R<String> assignMenus(RoleAssignMenusDTO roleAssignMenusDTO) {
        if (null == roleAssignMenusDTO || roleAssignMenusDTO.getId() == null
                || ArrayUtils.isEmpty(roleAssignMenusDTO.getMenuIds())) {
            return R.fail("数据不完整");
        }
        // 判断角色是否存在
        Role role = roleMapper.selectByPrimaryKey(roleAssignMenusDTO.getId());
        if (null == role) {
            return R.fail("角色不存在");
        }
        //判断角色是否都存在
        int countIdFromDB = menuMapper.countId(roleAssignMenusDTO.getMenuIds());
        if (countIdFromDB != roleAssignMenusDTO.getMenuIds().length) {
            return R.fail("部分菜单不存在,分配失败");
        }
        // 先删除已有的关联
        roleMenuMapper.deleteRoleMenuAssgin(roleAssignMenusDTO.getId());
        // 插入新增的关联
        roleMenuMapper.assginRoleMenu(roleAssignMenusDTO.getId(), roleAssignMenusDTO.getMenuIds());
        return R.ok("分配成功");
    }

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
