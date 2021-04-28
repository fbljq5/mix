package cn.liangjq.mix.common.dto.role;

import lombok.Data;

/**
 * @author ： liangjq
 * @description ：  更新角色数据封装
 * @date ： 2021/4/25
 */
@Data
public class RoleUpdateDTO {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 角色名称 role_name
     */
    private String roleName;

    /**
     * 角色编码 role_code
     */
    private String roleCode;

    /**
     * 显示顺序 role_sort
     */
    private Integer roleSort;

    /**
     * 备注 remark
     */
    private String remark;

}
