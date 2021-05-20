package cn.liangjq.mix.common.dto.role;

import cn.liangjq.mix.common.dto.PageBaseRequest;
import lombok.Data;

/**
 * @author ： liangjq
 * @description ： 角色分页查询条件封装
 * @date ： 2021/4/23
 */
@Data
public class RolePageRequest extends PageBaseRequest {

    /**
     * 角色名称 role_name
     */
    private String roleName;

    /**
     * 角色编码 role_code
     */
    private String roleCode;
}
