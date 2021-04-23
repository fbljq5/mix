package cn.liangjq.mix.admin.service.impl;

import cn.liangjq.mix.admin.dao.RoleMapper;
import cn.liangjq.mix.admin.service.IRoleService;
import cn.liangjq.mix.common.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author ： liangjq
 * @description ： 角色服务实现类
 * @date ： 2021/4/22
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {

    private final RoleMapper roleMapper;

    @Override
    public Role findRoleByRoleId(Long roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }
}
