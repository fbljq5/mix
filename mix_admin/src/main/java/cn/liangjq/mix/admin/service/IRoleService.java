package cn.liangjq.mix.admin.service;

import cn.liangjq.mix.common.entity.Role;

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
}
